package com.mmobite.admin_jobs.network.admin_channel.handlers;

import com.mmobite.admin_jobs.network.admin_channel.packets.OpcodeCS;
import com.mmobite.admin_jobs.network.model.packet.ReadPacket;
import com.mmobite.admin_jobs.network.model.server.ITcpServer;
import io.netty.channel.ChannelHandlerContext;

public class KickCharacterPacket extends ReadPacket {

    private int char_id_;
    private String admin_name_;

    @Override
    public boolean read() {
        char_id_ = readD();
        admin_name_ = readS();
        return true;
    }

    @Override
    public void run(ITcpServer server, ChannelHandlerContext ctx) {
        server.replyOk(ctx, getOpcode());
    }

    @Override
    public int getOpcode() {
        return OpcodeCS.KickCharacter;
    }
}
