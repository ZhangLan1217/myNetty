package client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import protocol.MessageProtocolDecoder;

/**
 * 客户端Channel通道初始化设置
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //字符串解码和编码
        pipeline.addLast("decoder", new MessageProtocolDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        //客户端的逻辑
        pipeline.addLast("handler", new DemoClientHandler());
    }
}