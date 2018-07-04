package com.mmobite.admin.handlers;

import com.mmobite.admin.model.packet.WritePacket;

public class AdminReplyPacket extends WritePacket {

    private int op_code_;
    private int success_;
    private int error_code_;

    public AdminReplyPacket(int op_code) {
        super();

        op_code_ = op_code;
        success_ = 1;
        error_code_ = 0;
    }

    public AdminReplyPacket(int op_code, int error_code) {
        super();

        op_code_ = op_code;
        success_ = 0;
        error_code_ = error_code;
    }

    @Override
    public int getOpcode() {
        return op_code_;
    }

    @Override
    public void writeBody() {
        writeD(success_);
        if (success_ == 0) {
            writeD(error_code_);
        }
    }
}
