package com.insping.libra.sock.net.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.insping.libra.sock.net.codec.data.LibraMessage;

public class SyncWriteMap {

	public static Map<Long, WriteFuture<LibraMessage>> syncKey = new ConcurrentHashMap<>();

}
