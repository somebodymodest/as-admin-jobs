package com.mmobite.admin_jobs.network.handlers;

import com.mmobite.admin_jobs.network.managers.PacketManager;
import com.mmobite.admin_jobs.network.packet.AdminRequestPacket;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SendMessageToGamePacket extends AdminRequestPacket {

    private static Logger log = Logger.getLogger(PacketManager.class.getName());

    private int msg_id_;
    private String admin_name_;

    @Override
    public boolean read() {
        msg_id_ = readD();
        admin_name_ = readS();
        return true;
    }

    @Override
    public void run() {
        // process message
        log.log(Level.SEVERE, "msg_id[{0}] admin_name[{1}]", new Object[]{msg_id_, admin_name_});

        // send reply
        //replyOk();
        replyError(2);
    }

}
