package com.insping;

import com.insping.libra.http.HttpServer;
import com.insping.libra.sock.SockServer;
import com.insping.libra.world.LibraConfig;
import com.insping.log.LibraLog;

public class GatewayServerApp implements Instances {
    public static void main(String[] args) {
        // 日志初始化
        LibraLog.init();
        // 加载config
        LibraConfig.load();
        // 启动Socket服务
        SockServer.getInstance().start();
        // 启动Http服务
        HttpServer.getInstance().start();
        LibraLog.info("服务器启动完成.");
    }
}
