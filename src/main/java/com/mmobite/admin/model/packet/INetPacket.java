package com.mmobite.admin.model.packet;

import io.netty.buffer.ByteBuf;

/**
 * Created by Ivan on 23.03.2018.
 */
public interface INetPacket {
    int getOpcode();
    ByteBuf getBuffer();
    void setBuffer(ByteBuf buf);
    void releaseBuffer();
}
