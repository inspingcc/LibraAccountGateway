package com.insping.libra.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.insping.Instances;
import com.insping.common.utils.JsonUtil;
import com.insping.common.utils.StringUtils;
import com.insping.libra.proto.ReqHttpMessage.ReqHttpMessageData;
import com.insping.libra.proto.ResHttpMessage.ResHttpMessageData;
import com.insping.libra.sock.net.codec.data.LibraMessage;
import com.insping.libra.sock.net.codec.data.LibraMessageType;
import com.insping.libra.sock.net.future.SyncWrite;
import com.insping.libra.world.LibraConfig;
import com.insping.libra.world.LibraServer;
import com.insping.log.LibraLog;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.timeout.TimeoutException;
import io.netty.util.CharsetUtil;

public class HttpLogicHandler extends SimpleChannelInboundHandler<FullHttpRequest> implements Instances {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (!msg.decoderResult().isSuccess()) {
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        Map<String, String> params = null;
        if (msg.method() == HttpMethod.GET) {
            // GET请求
            if (msg.uri().endsWith("favicon.ico")) {
                return;
            }
            if (msg.uri().length() < 3) {
                LibraLog.info("error uri ! uri = " + msg.uri());
                return;
            }
            params = HttpGetParseParams(msg);
        } else if (msg.method() == HttpMethod.POST) {
            // POST请求
            params = HttpPostParseParams(msg);
        } else {
            sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
            return;
        }
        if (params == null) {
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }

        // 转发给账号逻辑服务器
        LibraServer server = serverMgr.getServer();
        if (server == null) {
            sendError(ctx, HttpResponseStatus.SERVICE_UNAVAILABLE);
            return;
        }
        LibraMessage message = new LibraMessage();
        // 协议数据变更
        message.getHead().setDestServerID(server.getServerID());
        message.getHead().setSrcServerID(LibraConfig.SERVER_ID);
        message.getHead().setType(LibraMessageType.MESSAGE_REQ.getValue());
        message.getHead().setProtocolID(1);// 1 :HTTP的消息

        ReqHttpMessageData.Builder build = ReqHttpMessageData.newBuilder();
        build.setHttpReqMessage(JsonUtil.ObjectToJsonString(params));
        message.setBody(build.build());

        // 同步发送
        FullHttpResponse response = null;
        SyncWrite syncWrite = new SyncWrite();
        Map<String, Object> result = new HashMap<>();
        try {
            LibraMessage respMsg = syncWrite.writeAndSync(server.getCtx().channel(), message, 60 * 1000);
            // 将返回的protobuf数据处理成字符串(优化为http统一使用protobuf)
            ByteBuf buf = Unpooled.copiedBuffer(((ResHttpMessageData) respMsg.getBody()).getHttpResMessage(), CharsetUtil.UTF_8);
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
            result.put("data", ((ResHttpMessageData) respMsg.getBody()).getHttpResMessage());
        } catch (TimeoutException e) {
            // 超时处理
            LibraLog.error("HttpLogicHandler timeout ,exception " + e.getMessage());
            ByteBuf buf = Unpooled.copiedBuffer("timeout exception!", CharsetUtil.UTF_8);
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.EXPECTATION_FAILED, buf);
        }
        response.headers().add("Access-Control-Allow-Origin", "*");
        ctx.channel().writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        //ctx.channel().writeAndFlush("callback(" + JsonUtil.ObjectToJsonString(result) + ")").addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * POST参数解析
     *
     * @param request
     * @return
     */
    private Map<String, String> HttpPostParseParams(FullHttpRequest request) {
        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
        List<InterfaceHttpData> datas = decoder.getBodyHttpDatas();

        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < datas.size(); i++) {
            InterfaceHttpData data = datas.get(i);
            if (data == null || StringUtils.isNull(data.toString())) {
                continue;
            }
            String[] strings = data.toString().split("[=]");
            if (strings.length < 2) {
                continue;
            }
            params.put(strings[0], strings[1]);
        }
        return params;
    }

    /**
     * GET参数解析
     *
     * @param request
     * @return
     */
    private Map<String, String> HttpGetParseParams(FullHttpRequest request) {
        final String uri = request.uri();

        String uriStr = uri.substring(2);
        String[] paramList = uriStr.split("[&]");

        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < paramList.length; i++) {
            String[] strings = paramList[i].split("[=]");
            if (strings.length < 2 || StringUtils.isNull(strings[0])) {
                continue;
            }
            params.put(strings[0], strings[1]);
        }
        return params;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            sendError(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer("Failure: " + status.toString() + "\r\n", CharsetUtil.UTF_8));
        response.headers().set("Content-Type", "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


}
