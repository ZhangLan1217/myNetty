package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import protocol.MessageProtocol;

import java.net.Inet4Address;
import java.util.Scanner;

/**
 * 服务器业务逻辑
 */
@Slf4j(topic = "server")
public class DemoServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    int count  =  0;
    /*@Override
    protected void channelRead0(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        System.out.println("服务器读取线程"+ Thread.currentThread().getName());
        ctx.writeAndFlush("消息开始发送......");
        Thread.sleep(10000);
        System.out.println("t();Client say : " + msg.toString());
        //返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("Received your message : " + msg.toString());
        System.out.println("这是第"+ ++count +"条消息");
        System.out.println("Client say:"+msg.toString());
    }*/

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RemoteAddress : " + ctx.channel().remoteAddress() + " active !");
        ctx.writeAndFlush("连接成功！");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {

        log.info("这是第"+ ++count +"条消息" );
        log.info("内容为 " + new String(messageProtocol.getContent(), CharsetUtil.UTF_8));

    }
    // @Override
    /*public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.writeAndFlush("发送消息完成");
    }*/
}