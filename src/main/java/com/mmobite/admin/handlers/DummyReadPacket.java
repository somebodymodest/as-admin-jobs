package com.mmobite.admin.handlers;

import com.mmobite.admin.model.packet.ReadPacket;
import com.mmobite.admin.model.server.ITcpServer;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DummyReadPacket extends ReadPacket {

    private static Logger log = LoggerFactory.getLogger(DummyReadPacket.class.getName());

    @Override
    public boolean read() {
        return true;
    }

    @Override
    public void run(ITcpServer server, ChannelHandlerContext ctx) {
        log.error("DummyPacket called!!!");
        server.replyError(ctx, getOpcode(), -1);
    }

    @Override
    public int getOpcode() {
        return -1;
    }
}
