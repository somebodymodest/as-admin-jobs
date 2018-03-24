package com.mmobite.admin_jobs.network.packet;

import com.mmobite.admin_jobs.network.managers.PacketManager;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * Created by Ivan on 23.03.2018.
 */
public class AdminRequestPacket extends ReadPacket {

    private static Logger log = Logger.getLogger(PacketManager.class.getName());

    public AdminRequestPacket() {
        super();
    }

    public AdminRequestPacket(byte[] bytes, ChannelHandlerContext ctx) {
        super(bytes, ctx);
    }

    @Override
    public boolean read() {
        return true;
    }

    @Override
    public void run() {
        System.out.print("called Dummy AdminRequestPacket");
    }

    public void replyOk() {
        sendReply(new AdminReplyPacket(this));
    }

    public void replyError(int error_code) {
        sendReply(new AdminReplyPacket(this, error_code));
    }

    public void sendReply(AdminReplyPacket reply)
    {
        reply.write();
        reply.getChannel().writeAndFlush(reply.getBuffer()).addListener(ChannelFutureListener.CLOSE);
    }

}
