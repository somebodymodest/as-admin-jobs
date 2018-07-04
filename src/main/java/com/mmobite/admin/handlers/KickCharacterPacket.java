package com.mmobite.admin.handlers;

import com.mmobite.admin.packets.OpcodeCS;
import com.mmobite.admin.model.packet.ReadPacket;
import com.mmobite.admin.model.server.ITcpServer;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KickCharacterPacket extends ReadPacket {

    private static Logger log = LoggerFactory.getLogger(KickCharacterPacket.class.getName());

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
