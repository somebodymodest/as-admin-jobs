package com.mmobite.admin.handlers;


import com.mmobite.admin.packets.OpcodeCS;
import com.mmobite.admin.model.packet.ReadPacket;
import com.mmobite.admin.model.server.ITcpServer;
import com.mmobite.admin.server.AdminServer;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PunishCharPacket extends ReadPacket {

    private static Logger log = LoggerFactory.getLogger(PunishCharPacket.class.getName());

    private int char_id_;
    private int punish_type_;
    private int time_;
    private String admin_name_;
    private String world_guid_;

    @Override
    public boolean read() {
        char_id_ = readD();
        punish_type_= readD();
        time_ = readD();
        admin_name_ = readS();
        world_guid_ = readS();
        return true;
    }

    @Override
    public void run(ITcpServer server, ChannelHandlerContext ctx) {
        AdminServer.getAdminImpl().punishChar(char_id_, punish_type_, time_, admin_name_);
        server.replyOk(ctx, getOpcode());
    }

    @Override
    public int getOpcode() {
        return OpcodeCS.PunishChar;
    }
}
