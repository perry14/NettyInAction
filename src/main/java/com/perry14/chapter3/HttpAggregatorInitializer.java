package com.perry14.chapter3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {

	private final boolean isClient;

	public HttpAggregatorInitializer(boolean isClient) {
		this.isClient = isClient;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if (isClient) {
			pipeline.addLast("codec", new HttpClientCodec()); // 1
			pipeline.addLast("decompressor", new HttpContentDecompressor()); // 2
		} else {
			pipeline.addLast("codec", new HttpServerCodec()); // 3
			pipeline.addLast("compressor", new HttpContentCompressor()); // 4
		}
	}
}
