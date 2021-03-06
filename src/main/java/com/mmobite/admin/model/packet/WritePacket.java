package com.mmobite.admin.model.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by Ivan on 22.03.2018.
 */
public abstract class WritePacket implements IWritePacket {

    protected final int default_buffer_size_ = 1024;
    protected ByteBuf buf_;

    public WritePacket() {
        setBuffer(Unpooled.buffer(default_buffer_size_));
    }

    public WritePacket(int buffer_size) {
        setBuffer(Unpooled.buffer(buffer_size));
    }

    public ByteBuf getBuffer() {
        return buf_;
    }

    public void setBuffer(ByteBuf buf) {
        buf_ = buf;
    }

    public void releaseBuffer()
    {
        if (buf_ != null)
            buf_.release();
    }

    public void writeC(byte value) {
        buf_.writeByte(value);
    }

    public void writeH(int value) {
        buf_.writeShortLE((short) value);
    }

    public void writeD(int value) {
        buf_.writeIntLE(value);
    }

    public void writeQ(long value) {
        buf_.writeLongLE(value);
    }

    public void writeF(double value) {
        buf_.writeDoubleLE(value);
    }

    public void writeS(String value) {
        int length = value.length();
        for (int i = 0; i < length; i++) {
            buf_.writeShortLE(value.charAt(i));
        }
        buf_.writeShortLE('\000');
    }

    public void writes(String value) {
        int length = value.length();
        for (int i = 0; i < length; i++) {
            buf_.writeByte(value.charAt(i));
        }
        buf_.writeByte('\0');
    }

    public void writeB(byte[] data) {
        buf_.writeBytes(data);
    }
}
