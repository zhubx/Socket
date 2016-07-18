package com.neu.demo.factory;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

import com.neu.demo.decoder.MessageDecoder;
import com.neu.demo.encoder.MessageEncoder;
import com.neu.demo.handler.IHandler;
import com.neu.demo.handler.MessageServerHandler;
import com.neu.demo.heart.Heartbeat;


public class MessageServerpipelineFactory implements ChannelPipelineFactory{

	private Map<String,IHandler> handlers = new HashMap<String,IHandler>();
	
	public MessageServerpipelineFactory(Map<String,IHandler> map){
		this.handlers = map;
	}
	
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline(); 
        pipeline.addLast("timeout", new IdleStateHandler(new HashedWheelTimer(), 60*2, 0, 0));
        pipeline.addLast("heartbeat", new Heartbeat());
        pipeline.addLast("decoder", new MessageDecoder(1024 * 1024, 1, 2));
        pipeline.addLast("handler", new MessageServerHandler(handlers));
        pipeline.addLast("encoder", new MessageEncoder());
        return pipeline; 
	}

}
