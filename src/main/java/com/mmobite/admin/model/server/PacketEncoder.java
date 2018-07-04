package com.mmobite.admin.model.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mmobite.admin.model.packet.WritePacket;

public class PacketEncoder extends MessageToByteEncoder<WritePacket> {

    private static Logger log = LoggerFactory.getLogger(PacketEncoder.class.getName());

    @Override
    protected void encode(ChannelHandlerContext ctx, WritePacket pkt, ByteBuf out) throws Exception {
        //log.debug("enter encode: opcode[{}]", pkt.getOpcode());
        pkt.writeC((byte) pkt.getOpcode());
        pkt.writeBody();

        int dataLength = pkt.getBuffer().readableBytes();
        //log.debug("dataLength: {}", dataLength);

        out.writeShortLE(dataLength + 2);
        out.writeBytes(pkt.getBuffer());
    }
}