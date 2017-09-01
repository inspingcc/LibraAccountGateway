package com.insping.libra.sock.net.future;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.insping.libra.sock.net.codec.data.LibraMessage;

public class SyncWriteFuture implements WriteFuture<LibraMessage> {

	private CountDownLatch latch = new CountDownLatch(1);
	private final long begin = System.currentTimeMillis();
	private long timeout;
	private LibraMessage response;
	private final long requestId;
	private boolean writeResult;
	private Throwable cause;
	private boolean isTimeout = false;

	public SyncWriteFuture(Long requestId) {
		this.requestId = requestId;
	}

	public SyncWriteFuture(Long requestId, long timeout) {
		this.requestId = requestId;
		this.timeout = timeout;
		writeResult = true;
		isTimeout = false;
	}

	public Throwable cause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public boolean isWriteSuccess() {
		return writeResult;
	}

	public void setWriteResult(boolean result) {
		this.writeResult = result;
	}

	public long requestId() {
		return requestId;
	}

	public LibraMessage getResponse() {
		return response;
	}

	public void setResponse(LibraMessage response) {
		this.response = response;
		latch.countDown();
	}

	public boolean cancel(boolean mayInterruptIfRunning) {
		return true;
	}

	public boolean isCancelled() {
		return false;
	}

	public boolean isDone() {
		return false;
	}

	public LibraMessage get() throws InterruptedException, ExecutionException {
		latch.wait();
		return response;
	}

	public LibraMessage get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		if (latch.await(timeout, unit)) {
			return response;
		}
		return null;
	}

	public boolean isTimeout() {
		if (isTimeout) {
			return isTimeout;
		}
		return System.currentTimeMillis() - begin > timeout;
	}
}
