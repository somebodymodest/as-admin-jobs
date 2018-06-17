package com.mmobite.admin_jobs.network.packet;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Ivan on 23.03.2018.
 */
public class AdminRequestPacket extends ReadPacket {

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
        //System.out.print("called Dummy AdminRequestPacket");
        replyOk();
    }

    public void replyOk() {
        sendReply(new AdminReplyPacket(this));
    }

    public void replyError(int error_code) {
        sendReply(new AdminReplyPacket(this, error_code));
    }

    public void sendReply(AdminReplyPacket reply) {
        reply.write();
        reply.getChannel().writeAndFlush(reply.getBuffer()).addListener(ChannelFutureListener.CLOSE);
    }

}
