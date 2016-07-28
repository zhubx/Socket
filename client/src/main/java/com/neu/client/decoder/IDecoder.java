package com.neu.client.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public interface IDecoder {

	public Object doDecode(ChannelHandlerContext ctx, ByteBuf arg1);
}
