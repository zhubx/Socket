package com.neu.demo.handler;

import org.jboss.netty.channel.Channel;

import com.neu.demo.bean.ProtocolMsg;

public interface Handler<T extends ProtocolMsg> {

	public void doHandler(Channel channel,T msg);
}
