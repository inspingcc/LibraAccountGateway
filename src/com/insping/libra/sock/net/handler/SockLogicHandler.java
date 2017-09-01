package com.insping.libra.sock.net.handler;

import com.insping.Instances;
import com.insping.libra.sock.net.codec.data.LibraMessage;
import com.insping.libra.sock.net.codec.data.LibraMessageType;
import com.insping.libra.sock.net.future.SyncWriteFuture;
import com.insping.libra.sock.net.future.SyncWriteMap;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author houshanping
 *
 */
public class SockLogicHandler extends ChannelInboundHandlerAdapter implements Instances {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		LibraMessage message = (LibraMessage) msg;
		if (message.getHead() != null) {
			if (message.getHead().getType() == LibraMessageType.MESSAGE_RESP.getValue()) {
				// 逻辑服务器回复的消息
				long requestId = message.getHead().getRequestID();
				SyncWriteFuture future = (SyncWriteFuture) SyncWriteMap.syncKey.get(requestId);
				if (future != null) {
					future.setResponse(message);
				}
			}
		}
		ctx.fireChannelRead(msg);
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();// 发生异常，关闭链路
	}
}
