package com.mmobite.admin_jobs.network.admin_channel.handlers;

import com.mmobite.admin_jobs.network.admin_channel.packets.OpcodeCS;
import com.mmobite.admin_jobs.network.model.packet.ReadPacket;
import com.mmobite.admin_jobs.network.model.server.ITcpServer;
import io.netty.channel.ChannelFutureListener;
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
