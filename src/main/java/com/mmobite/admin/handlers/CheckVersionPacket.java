package com.mmobite.admin.handlers;

import com.mmobite.admin.model.server.ITcpServer;
import com.mmobite.admin.packets.OpcodeCS;
import com.mmobite.admin.model.packet.ReadPacket;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Ivan on 23.03.2018.
 */
public class CheckVersionPacket extends ReadPacket {

    private String admin_name_;

    @Override
    public boolean read() {
        admin_name_ = readS();
        return true;
    }

    @Override
    public void run(ITcpServer server, ChannelHandlerContext ctx) {
        server.replyOk(ctx, getOpcode());
    }

    @Override
    public int getOpcode() {
        return OpcodeCS.CheckVersion;
    }
}
