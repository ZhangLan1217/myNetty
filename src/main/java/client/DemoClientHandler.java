package client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import protocol.MessageProtocol;

import java.nio.charset.Charset;

/**
 * 客户端业务逻辑
 */
@Slf4j(topic = "client handler")
public class DemoClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
            /*for(int i=0;i<10;i++){
                ByteBuf byteBuf = Unpooled.copiedBuffer("hello , Server" + i, Charset.forName("utf-8"));
                ctx.writeAndFlush(byteBuf);
            }*/
        for (int i = 0; i < 10; i++) {
            String string = "天气冷，一起来吃火锅";
            int length = string.getBytes(CharsetUtil.UTF_8).length;
            byte[] content = string.getBytes(CharsetUtil.UTF_8);
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(length);
            messageProtocol.setContent(content);
            ctx.writeAndFlush(messageProtocol);
        }
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {

    }
}