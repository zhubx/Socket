package com.neu.demo.factory;

import java.util.Map;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

import com.neu.demo.decoder.IDecoder;
import com.neu.demo.decoder.MessageDecoder;
import com.neu.demo.encoder.IEncoder;
import com.neu.demo.encoder.MessageEncoder;
import com.neu.demo.handler.IHandler;
import com.neu.demo.handler.MessageServerHandler;
import com.neu.demo.heart.Heartbeat;


public class MessageServerpipelineFactory implements ChannelPipelineFactory{

	private Map<String,IHandler> handlers;
	
	private Map<Byte,IDecoder> decoders;
	
	private Map<String,IEncoder> encoders;
	
	public MessageServerpipelineFactory(Map<String,IHandler> map,Map<Byte,IDecoder> decoders,Map<String,IEncoder> encoders){
		this.handlers = map;
		this.decoders = decoders;
		this.encoders = encoders;
	}
	
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline(); 
        pipeline.addLast("timeout", new IdleStateHandler(new HashedWheelTimer(), 60*1, 0, 0));
        pipeline.addLast("heartbeat", new Heartbeat());
        pipeline.addLast("decoder", new MessageDecoder(1024 * 1024, 1, 2,decoders));
        pipeline.addLast("handler", new MessageServerHandler(handlers));
        pipeline.addLast("encoder", new MessageEncoder(encoders));
        return pipeline; 
	}

}
