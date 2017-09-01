package com.insping.libra.world;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.insping.libra.proto.ReqServerHeartbeat.HeartbeatData;
import com.insping.libra.proto.ReqServerRegist.ServerRegistData;
import com.insping.log.LibraLog;

import io.netty.channel.ChannelHandlerContext;

public class ServerManager {

	private ServerManager() {
	}

	private static ServerManager instance = new ServerManager();

	public static ServerManager getInstance() {
		return instance;
	}

	private Map<Integer, LibraServer> servers = new ConcurrentHashMap<>();

	public LibraServer searchServer(int serverID) {
		return servers.get(serverID);
	}

	public void addServer(LibraServer server) {
		servers.put(server.getServerID(), server);
	}

	public void deleteServer(int serverID) {
		servers.remove(serverID);
	}

	public void deleteServer(LibraServer server) {
		servers.remove(server.getServerID());
	}

	/**
	 * 注册逻辑服务器到网关服务器
	 * 
	 * @param ctx
	 * @param data
	 */
	public void registServer(ChannelHandlerContext ctx, ServerRegistData data) {
		LibraServer server = new LibraServer();
		server.setServerID(data.getServerID());
		server.setServerName(data.getServerName());
		server.setServerKey(data.getServerKey());
		server.setServerType(data.getServerType());
		server.setServerStatus(data.getServerStatus());
		server.setRegistTime(data.getTime());
		server.setHeartbeatTime(data.getTime());
		server.setServerIp(data.getServerIp());
		server.setServerDesc(data.getServerDesc());
		server.setCtx(ctx);
		addServer(server);
		LibraLog.info("Server Regist >>>> ServerID = " + server.getServerID() + ", serverRegistTime = " + server.getRegistTime());
	}

	/**
	 * 更新逻辑服务器相关数据
	 * 
	 * @param data
	 */
	public void updateServer(HeartbeatData data) {
		LibraServer server = servers.get(data.getServerID());
		server.setServerID(data.getServerID());
		server.setServerName(data.getServerName());
		server.setServerKey(data.getServerKey());
		server.setServerType(data.getServerType());
		server.setServerStatus(data.getServerStatus());
		server.setHeartbeatTime(data.getTime());
		server.setServerDesc(data.getServerDesc());
		addServer(server);
		LibraLog.info("Heartbeat >>>> ServerID = " + server.getServerID() + ", heartbeatTime = " + server.getHeartbeatTime());
	}

	/**
	 * 获取一个压力比较小的服务器
	 * 
	 * @return
	 */
	public LibraServer getServer() {
		for (LibraServer temp : servers.values()) {
			if (temp == null) {
				continue;
			}
			return temp;
		}
		return null;
	}
}
