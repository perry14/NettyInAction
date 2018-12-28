package com.perry14.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ShortToByteEncoder extends MessageToByteEncoder<Short> {

	@Override
	public void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
		out.writeShort(msg); // 2
	}
}