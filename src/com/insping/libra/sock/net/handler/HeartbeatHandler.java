package com.insping.libra.sock.net.handler;

import com.insping.Instances;
import com.insping.libra.proto.ReqServerHeartbeat.HeartbeatData;
import com.insping.libra.sock.net.codec.data.LibraMessage;
import com.insping.libra.sock.net.codec.data.LibraMessageType;
import com.insping.libra.sock.net.response.GeneralResponse;
import com.insping.log.LibraLog;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
 * @author houshanping
 *
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter implements Instances {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		LibraMessage message = (LibraMessage) msg;
		if (message.getHead() != null && message.getHead().getType() == LibraMessageType.HEARTBEAT_REQ.getValue()) {
			GeneralResponse response = new GeneralResponse();
			if (!(message.getBody() instanceof HeartbeatData)) {
				LibraLog.error("HeartbeatHandler body is invaild!");
				response.fail("HeartbeatHandler body is invaild!");
			} else {
				HeartbeatData data = (HeartbeatData) message.getBody();
				if (serverMgr.searchServer(data.getServerID()) == null) {
					response.fail();
				} else {
					serverMgr.updateServer(data);
				}
			}
			// 重复登陆，拒绝
			// if (serverMgr.searchServer(data.getServerID()) != null) {
			// response.fail("重复登录 !");
			// } else {
			//
			// }
			LibraMessage libraMessage = response.result();
			libraMessage.getHead().setType(LibraMessageType.HEARTBEAT_RESP.getValue());
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
