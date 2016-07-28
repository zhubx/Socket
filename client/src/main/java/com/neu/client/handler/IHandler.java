package com.neu.client.handler;

import io.netty.channel.ChannelHandlerContext;

public interface IHandler {

	public void doHandler(ChannelHandlerContext ctx, Object msg);
}
