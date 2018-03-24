package com.mmobite.admin_jobs.network.packet;

import com.mmobite.admin_jobs.network.managers.PacketManager;
import com.mmobite.admin_jobs.network.packet.AdminRequestPacket;
import com.mmobite.admin_jobs.network.packet.WritePacket;

import java.util.logging.Logger;

public class AdminReplyPacket extends WritePacket {

    private static Logger log = Logger.getLogger(PacketManager.class.getName());
    private int success_;
    private int error_code_;

    AdminReplyPacket(AdminRequestPacket request) {
        setOpcode(request.getOpcode());
        setBuffer(request.getBuffer());
        setChannel(request.getChannel());
        buf_.retain();

        success_ = 1;
        error_code_ = 0;
    }

    AdminReplyPacket(AdminRequestPacket request, int error_code) {
        setOpcode(request.getOpcode());
        setBuffer(request.getBuffer());
        setChannel(request.getChannel());
        buf_.retain();

        success_ = 0;
        error_code_ = error_code;
    }

    @Override
    protected void writeBody() {
        writeD(success_);
        if (success_ == 0) {
            writeD(error_code_);
        }
    }

    @Override
    public void write() {
        writeH(0);// write packet size (unknown yet)
        writeC((byte) opcode_);
        writeBody();
        // calc & write packet size
        short dataLength = (short) (buf_.readableBytes());
        buf_.markWriterIndex();
        buf_.writerIndex(buf_.writerIndex() - dataLength);
        buf_.writeShortLE(dataLength);
        buf_.resetWriterIndex();
    }
}
