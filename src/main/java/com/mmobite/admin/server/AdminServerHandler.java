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

import com.mmobite.admin.model.packet.ReadPacket;
import com.mmobite.admin.packets.PacketManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handler for a server-side channel.  This handler maintains stateful
 * information which is specific to a certain channel using member variables.
 * Therefore, an instance of this handler can cover only one channel.  You have
 * to create a new handler instance whenever you create a new channel and insert
 * this handler  to avoid a race condition.
 */
@ChannelHandler.Sharable
public class AdminServerHandler extends ChannelInboundHandlerAdapter {

    private static Logger log = LoggerFactory.getLogger(AdminServerHandler.class.getName());

    private AdminServer server_;

    public void setServer(AdminServer server) {
        server_ = server;
    }

    public AdminServer getServer() {
        return server_;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!AdminServerProperties.Enabled)
            return;

        ByteBuf buf = (ByteBuf) msg;
        int opcode = (int) buf.readUnsignedByte();

        log.debug("Called packet opcode[{}]", opcode);

        ReadPacket pkt = PacketManager.getPacket(opcode);
        pkt.setBuffer(buf);

        try {
            if (pkt.read())
                pkt.run(getServer(), ctx);
        } finally {
            //buf.release(); //wrong!!!
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
