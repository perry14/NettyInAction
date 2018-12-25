package com.perry14.chapter3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LineBasedFrameDecoder;

public class CmdHandlerInitializer extends ChannelInitializer<Channel> {

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new CmdDecoder(65 * 1024));// 1
		pipeline.addLast(new CmdHandler()); // 2
	}

	public static final class Cmd { // 3
		private final ByteBuf name;
		private final ByteBuf args;

		public Cmd(ByteBuf name, ByteBuf args) {
			this.name = name;
			this.args = args;
		}

		public ByteBuf name() {
			return name;
		}

		public ByteBuf args() {
			return args;
		}
	}

	public static final class CmdDecoder extends LineBasedFrameDecoder {
		public CmdDecoder(int maxLength) {
			super(maxLength);
		}

		@Override
		protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
			ByteBuf frame = (ByteBuf) super.decode(ctx, buffer); // 4
			if (frame == null) {
				return null; // 5
			}
			int index = frame.indexOf(frame.readerIndex(), frame.writerIndex(), (byte) ' '); // 6
			return new Cmd(frame.slice(frame.readerIndex(), index), frame.slice(index + 1, frame.writerIndex())); // 7
		}
	}

	public static final class CmdHandler extends SimpleChannelInboundHandler<Cmd> {
		@Override
		public void channelRead0(ChannelHandlerContext ctx, Cmd msg) throws Exception {
			// Do something with the command //8
		}
	}
}
