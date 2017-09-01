package com.insping.libra.sock.net.handler;

import com.insping.Instances;
import com.insping.libra.proto.ReqServerRegist.ServerRegistData;
import com.insping.libra.sock.net.codec.data.LibraMessage;
import com.insping.libra.sock.net.codec.data.LibraMessageType;
import com.insping.libra.sock.net.response.GeneralResponse;
import com.insping.log.LibraLog;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
 * @author houshanping
 *
 */
public class ServerRegistHandler extends ChannelInboundHandlerAdapter implements Instances {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		LibraMessage message = (LibraMessage) msg;
		if (message.getHead() != null && message.getHead().getType() == LibraMessageType.REGIST_REQ.getValue()) {
			GeneralResponse response = new GeneralResponse();
			if (!(message.getBody() instanceof ServerRegistData)) {
				LibraLog.error("ServerRegistHandler body is invaild!");
				response.fail("ServerRegistHandler body is invaild!");
			} else {
				ServerRegistData data = (ServerRegistData) message.getBody();
				serverMgr.registServer(ctx, data);
			}
			// 重复登陆，拒绝
			// if (serverMgr.searchServer(data.getServerID()) != null) {
			// response.fail("重复登录 !");
			// } else {
			//
			// }
			LibraMessage libraMessage = response.result();
			libraMessage.getHead().setType(LibraMessageType.REGIST_RESP.getValue());
			ctx.writeAndFlush(libraMessage);
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
