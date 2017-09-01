package com.insping.libra.sock.net.future;

import java.util.concurrent.Future;

import com.insping.libra.sock.net.codec.data.LibraMessage;

public interface WriteFuture<T> extends Future<T> {

	Throwable cause();

	void setCause(Throwable cause);

	boolean isWriteSuccess();

	void setWriteResult(boolean result);

	long requestId();

	T getResponse();

	void setResponse(LibraMessage response);

	boolean isTimeout();

}
