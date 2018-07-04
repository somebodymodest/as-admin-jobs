package com.mmobite.admin.handlers;

import com.mmobite.admin.packets.OpcodeCS;
import com.mmobite.admin.model.packet.ReadPacket;
import com.mmobite.admin.model.server.ITcpServer;
import io.netty.channel.ChannelHandlerContext;

public class DeleteUserPostPacket extends ReadPacket {

    private int char_id_;
    private int account_id_;
    private String admin_name_;

    @Override
    public boolean read() {
        char_id_ = readD();
        account_id_ = readD();
        admin_name_ = readS();
        return true;
    }

    @Override
    public void run(ITcpServer server, ChannelHandlerContext ctx) {
        server.replyOk(ctx, getOpcode());
    }

    @Override
    public int getOpcode() {
        return OpcodeCS.DeleteUserPost;
    }
}
