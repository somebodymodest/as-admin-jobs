package com.mmobite.admin.handlers;

import com.mmobite.admin.model.server.ITcpServer;
import com.mmobite.admin.packets.OpcodeCS;
import com.mmobite.admin.model.packet.ReadPacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ivan on 23.03.2018.
 */
public class CheckVersionPacket extends ReadPacket {

    private static Logger log = LoggerFactory.getLogger(CheckVersionPacket.class.getName());

    private String admin_name_;
    private String world_guid_;

    @Override
    public boolean read() {
        admin_name_ = readS();
        world_guid_ = readS();
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
