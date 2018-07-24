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
package com.mmobite.admin.server;

import com.mmobite.admin.handlers.AdminReplyPacket;
import com.mmobite.admin.model.server.ITcpServer;
import com.mmobite.api.AdminActionImpl;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public final class AdminServer extends ITcpServer {

    private final static EventLoopGroup bossGroup_ = new NioEventLoopGroup(1);
    private final static EventLoopGroup workerGroup_ = new NioEventLoopGroup();

    private final AdminServerHandler handler_ = new AdminServerHandler();
    private static AdminActionImpl admin_impl_;

    public static void init(String configPath, AdminActionImpl ai) {
        admin_impl_ = ai;
        AdminServerProperties.load(configPath);
        new Thread(() -> {
            try {
                AdminServer server = new AdminServer();
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();;
    }

    void start() throws Exception {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup_, workerGroup_)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.ERROR))
                    .childHandler(new AdminServerInitializer(handler_));

            handler_.setServer(this);

            b.bind(AdminServerProperties.Port).sync().channel().closeFuture().sync();
        } finally {
            bossGroup_.shutdownGracefully();
            workerGroup_.shutdownGracefully();
        }
    }

    public EventLoopGroup getLoop()
    {
        return workerGroup_;
    }

    public static AdminActionImpl getAdminImpl()
    {
        return admin_impl_;
    }

    public void replyOk(ChannelHandlerContext ctx, int op_code) {
        sendPacketAndClose(ctx, new AdminReplyPacket(op_code));
    }

    public void replyError(ChannelHandlerContext ctx, int op_code, int error_code) {
        sendPacketAndClose(ctx, new AdminReplyPacket(op_code, error_code));
    }
}
