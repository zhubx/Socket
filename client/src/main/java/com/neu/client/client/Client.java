package com.neu.client.client;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.ProtocolMsg;
import com.neu.client.decoder.IDecoder;
import com.neu.client.decoder.MessageDecoder;
import com.neu.client.encoder.IEncoder;
import com.neu.client.encoder.MessageEncoder;
import com.neu.client.handler.IHandler;
import com.neu.client.handler.LoginHandler;
import com.neu.client.handler.MessageServerHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * 客户端
 * @author zhubx
 *
 */
public class Client {
	
	private static final Logger LOG = LoggerFactory.getLogger(Client.class);

	private static EventLoopGroup group;
	
	public void init(int port,String ip,final String username,final String password,
			final Map<Byte,IDecoder> decoders,final Map<String,IHandler> handlers,final Map<String,IEncoder<ProtocolMsg>> encoders
			){
		group = new NioEventLoopGroup();
		 
			try {
				Bootstrap b = new Bootstrap();
				b.group(group).channel(NioSocketChannel.class)
						.option(ChannelOption.TCP_NODELAY, true)
						.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
						.handler(new ChannelInitializer<SocketChannel>() {

							@Override
							protected void initChannel(SocketChannel ch)
									throws Exception {
								
							 ch.pipeline().addLast("decoder",new MessageDecoder(1024 * 1024, 1, 2,decoders));
							 ch.pipeline().addLast(new LoginHandler(username,password));
							 ch.pipeline().addLast(new MessageServerHandler(handlers));
							 ch.pipeline().addLast(new MessageEncoder(encoders));
							}
						});
				
				b.connect(ip, port).sync();
				//f.channel().closeFuture().sync();
				//SysVariables.clientStatus = true;
				LOG.info("[客户端client初始化完成]");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				group.shutdownGracefully();
				LOG.error("[客户端client初始化失败]");
				e.printStackTrace();
			}finally{
				System.out.println("finally.....");
			}
		 
	}
	
	public static void shutDown(){
		group.shutdownGracefully();
	}

}
