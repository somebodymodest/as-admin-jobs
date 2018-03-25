package com.mmobite.admin_jobs.network.handlers;

import com.mmobite.admin_jobs.network.packet.AdminRequestPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMessageToGamePacket extends AdminRequestPacket {

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
    public void run() {
        // process message
        log.info("msg_id[{}] admin_name[{}]", msg_id_, admin_name_);

        // send reply
        //replyOk();
        replyError(2);
    }

}
