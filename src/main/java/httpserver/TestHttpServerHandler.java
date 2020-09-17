package httpserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.spdy.SpdyHeaders;
import io.netty.util.CharsetUtil;

import javax.xml.ws.spi.http.HttpHandler;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpHandler> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpHandler httpHandler) throws Exception {
        ByteBuf content = Unpooled.copiedBuffer("这是服务器！", CharsetUtil.UTF_8);
        FullHttpResponse httpResponse =
                new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);

        channelHandlerContext.writeAndFlush(httpResponse);
    }
}
