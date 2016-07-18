package com.neu.demo.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

public interface IHandler {

	public void doHandler(ChannelHandlerContext ctx, MessageEvent e);
}
