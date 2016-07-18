package com.neu.demo.server;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.factory.MessageServerpipelineFactory;
import com.neu.demo.handler.IHandler;

/**
 * tcp服务启动
 * @author zhubx
 *
 */
public class Server {

	private static final Logger LOG = LoggerFactory.getLogger(Server.class);
	
	private int port;
	
	private String host;
	
	private Map<String,IHandler> handlers = new HashMap<String,IHandler>();
	
	/**
	 * 服务初始化
	 */
	public void init(){
		startServer(port,host);
		LOG.info("server初始化完成" + "host:" + host + "-port:" + port);
	}
	
	private void startServer(int port,String host){
		ServerBootstrap bootStrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),Executors.newCachedThreadPool()
				));
		bootStrap.setPipelineFactory(new MessageServerpipelineFactory(handlers));
		bootStrap.setOption("child.tcpNoDelay", true);
		bootStrap.setOption("child.keepAlive", true);
		bootStrap.bind(new InetSocketAddress(host,port));
	}

	public final void setPort(int port) {
		this.port = port;
	}

	public final void setHost(String host) {
		this.host = host;
	}

	public final void setHandlers(Map<String, IHandler> handlers) {
		this.handlers = handlers;
	}
	
}
