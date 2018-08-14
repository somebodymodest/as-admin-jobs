package com.mmobite.admin.handlers;

import com.mmobite.admin.model.packet.ReadPacket;
import com.mmobite.admin.model.server.ITcpServer;
import com.mmobite.admin.packets.OpcodeCS;
import com.mmobite.admin.server.AdminServer;
import com.mmobite.admin.server.AdminServerProperties;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMessageToGamePacketGF extends ReadPacket {

    private static Logger log = LoggerFactory.getLogger(SendMessageToGamePacketGF.class.getName());

    /**
     1 - reload ban-list
     2 - reload access-list
     3 - add ban to bans.xml, additional parameters:
     4 - remove ban from bans.xml, additional parameters:
     **/
    private int msg_id_;
    private int time_ = 1;
    private int delay_ = 1;
    private String[] s_params_ = null;
    private String admin_name_;
    private String world_guid_;

    @Override
    public boolean read() {
        msg_id_ = readD();
        if(msg_id_ == 3)
        {
            s_params_ = new String[4];
            s_params_[0] = readS(); // S - hwid (UTF-16LE string)
            s_params_[1] = readS(); // S - account (UTF-16LE string)
            s_params_[2] = readS(); // S - punish_actione = [REALTIME | TEMPORARY | DELAYED | DELAYEDTEMPORARY] (UTF-16LE string, see SmartGuard.ini DetectAction parameter)
            time_ = readD(); // d - time (in minutes)
            delay_ = readD(); // d - delay (in minutes)
            s_params_[3] = readS(); // S - comment (UTF-16LE string)

        }
        else if(msg_id_ == 4)
        {
            s_params_ = new String[2];
            s_params_[0] = readS(); // S - hwid (UTF-16LE string)
            s_params_[1] = readS(); // S - account (UTF-16LE string)
        }
        else if (msg_id_ == 8)  // ban account
        {
            s_params_ = new String[2];
            s_params_[0] = readS(); // S - account (UTF-16LE string)
            s_params_[1] = readS(); // S - ban remark (UTF-16LE string)
        }
        else if (msg_id_ == 9)  // unban account
        {
            s_params_ = new String[2];
            s_params_[0] = readS(); // S - account (UTF-16LE string)
            s_params_[1] = readS(); // S - unban remark (UTF-16LE string)
        }
        admin_name_ = readS();
        world_guid_ = readS();
        return true;
    }

    @Override
    public void run(ITcpServer server, ChannelHandlerContext ctx) {
        if (AdminServerProperties.CheckWorldGuid && !AdminServerProperties.WorldGuid.equals(world_guid_))
        {
            server.replyError(ctx, getOpcode(), 1);
            return;
        }

        // process message
        AdminServer.getAdminImpl().sendMessageToGame(msg_id_, s_params_, time_, delay_, admin_name_);

        server.replyOk(ctx, getOpcode());
    }

    @Override
    public int getOpcode() {
        return OpcodeCS.SendMessageToGameGF;
    }
}