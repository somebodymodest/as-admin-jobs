package com.mmobite.admin_jobs.network.model.packet;

import com.mmobite.admin_jobs.network.model.server.ITcpServer;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Ivan on 22.03.2018.
 *
 */
public interface IReadPacket extends INetPacket {
    boolean read();
    void run(ITcpServer server, ChannelHandlerContext ctx);
}

