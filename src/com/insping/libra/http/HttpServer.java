package com.insping.libra.http;

import com.insping.Instances;
import com.insping.libra.world.LibraConfig;
import com.insping.log.LibraLog;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpServer implements Instances {

	private HttpServer() {
	}

	private static class SingletonHolder {
		private static final HttpServer instance = new HttpServer();
	}

	public static HttpServer getInstance() {
		return SingletonHolder.instance;
	}

	boolean isRunning = false;

	EventLoopGroup bossGroup = new NioEventLoopGroup();
	EventLoopGroup workerGroup = new NioEventLoopGroup();

	public void start(){
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast("http-decoder", new HttpRequestDecoder()); // 请求消息解码器
					ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));// 目的是将多个消息转换为单一的request或者response对象

					ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());// 响应解码器
					ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());// 目的是支持异步大文件传输（）
					ch.pipeline().addLast("logicServerHandler", new HttpLogicHandler());// 业务逻辑
				}
			});
			ChannelFuture future = b.bind(LibraConfig.HTTP_IP, LibraConfig.HTTP_PORT).sync();
			LibraLog.info("HTTP服务器已启动，port:" + LibraConfig.HTTP_PORT);
			isRunning = true;
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			LibraLog.error("exception shutdown!");
			isRunning = false;
		}
	}

	public void shutdown() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
		LibraLog.error("exception shutdown!");
		isRunning = false;
	}

}