/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.mmobite.admin_jobs.network.admin_channel.server;

import com.mmobite.admin_jobs.network.model.server.PacketDecoder;
import com.mmobite.admin_jobs.network.model.server.PacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Creates a newly configured {@link ChannelPipeline} for a server-side channel.
 */
public class AdminServerInitializer extends ChannelInitializer<SocketChannel> {

    private final AdminServerHandler handler_;

    AdminServerInitializer(AdminServerHandler handler) {
        this.handler_ = handler;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();

        // Add the number codec first,
        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new PacketEncoder());

        // and then business logic.
        // Please note we create a handler for every new channel
        // because it has stateful properties.
        pipeline.addLast(handler_);
    }
}
