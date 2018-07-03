package com.mmobite.admin_jobs.network.admin_channel.handlers;

import com.mmobite.admin_jobs.network.admin_channel.packets.OpcodeCS;
import com.mmobite.admin_jobs.network.model.packet.ReadPacket;
import com.mmobite.admin_jobs.network.model.server.ITcpServer;
import com.sun.org.apache.xpath.internal.compiler.OpCodes;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMessageToGamePacket extends ReadPacket {

    private static Logger log = LoggerFactory.getLogger(SendMessageToGamePacket.class.getName());

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
        return OpcodeCS.SendMessageToGame;
    }
}
