package com.insping.libra.sock.net.future;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.insping.Instances;
import com.insping.libra.sock.net.codec.data.LibraMessage;

import io.netty.channel.Channel;

public class SyncWrite implements Instances {

	public LibraMessage writeAndSync(final Channel channel, final LibraMessage request, final long timeout) throws Exception {

		if (channel == null) {
			throw new NullPointerException("channel");
		}
		if (request == null || request.getHead() == null) {
			throw new NullPointerException("request");
		}
		if (timeout <= 0) {
			throw new IllegalArgumentException("timeout <= 0");
		}
		request.getHead().setRequestID(idGenerator.next());

		WriteFuture<LibraMessage> future = new SyncWriteFuture(request.getHead().getRequestID());
		SyncWriteMap.syncKey.put(request.getHead().getRequestID(), future);

		LibraMessage response = doWriteAndSync(channel, request, timeout, future);

		SyncWriteMap.syncKey.remove(request.getHead().getRequestID());
		return response;
	}

	private LibraMessage doWriteAndSync(final Channel channel, final LibraMessage request, final long timeout, final WriteFuture<LibraMessage> writeFuture) throws Exception {

		channel.writeAndFlush(request).addListener((future) -> {
			writeFuture.setWriteResult(future.isSuccess());
			writeFuture.setCause(future.cause());
			// 失败移除
			if (!writeFuture.isWriteSuccess()) {
				SyncWriteMap.syncKey.remove(writeFuture.requestId());
			}
		});

		LibraMessage response = writeFuture.get(timeout, TimeUnit.MILLISECONDS);
		if (response == null) {
			if (writeFuture.isTimeout()) {
				throw new TimeoutException();
			} else {
				// write exception
				throw new Exception(writeFuture.cause());
			}
		}
		return response;
	}

}
