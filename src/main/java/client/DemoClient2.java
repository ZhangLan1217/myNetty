package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 客户端启动逻辑
 */
public class DemoClient2 {

    public static String host = "127.0.0.1"; //服务器IP地址
    public static int port = 8000; //服务器端口

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer());

            //连接客户端
            Channel channel = b.connect(host, port).sync().channel();

            //控制台输入
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            for (;;) {
                String line = in.readLine();
                if (line == null) {
                    continue;
                }
                if(line.equals("end")){
                    break;
                }
                //向服务端发送数据
                channel.writeAndFlush(line);
            }
        } finally {
            //优雅退出，释放线程池资源
            group.shutdownGracefully();
        }
    }
}