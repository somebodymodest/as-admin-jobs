package com.mmobite.admin.handlers;

import com.mmobite.admin.packets.OpcodeCS;
import com.mmobite.admin.model.packet.ReadPacket;
import com.mmobite.admin.model.server.ITcpServer;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMessageToGamePacketIL extends ReadPacket {

    private static Logger log = LoggerFactory.getLogger(SendMessageToGamePacketIL.class.getName());

    private int msg_id_;
    private String admin_name_;

    @Override
    public boolean read() {
        msg_id_ = readD();
        admin_name_ = readS();
        return true;
    }

    @Override
    public void run(ITcpServer server, ChannelHandlerContext ctx) {
        // process message
        server.replyOk(ctx, getOpcode());
    }

    @Override
    public int getOpcode() {
        return OpcodeCS.SendMessageToGameIL;
    }
}
