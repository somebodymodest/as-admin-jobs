package com.mmobite.admin.model.server;

import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmobite.admin.model.packet.WritePacket;

public abstract class ITcpServer {

    private static Logger log = LoggerFactory.getLogger(ITcpServer.class.getName());

    abstract public EventLoopGroup getLoop();

    public void sendPacket(ChannelHandlerContext ctx, final WritePacket pkt) {
        ctx.writeAndFlush(pkt).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (!future.isSuccess()) {
                    future.cause().printStackTrace();
                }
                pkt.releaseBuffer();
            }
        });
    }

    public void sendPacketAndClose(ChannelHandlerContext ctx, final WritePacket pkt) {
        ctx.writeAndFlush(pkt).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (!future.isSuccess()) {
                    future.cause().printStackTrace();
                }
                pkt.releaseBuffer();
                future.channel().close();
            }
        });
    }

    abstract public void replyOk(ChannelHandlerContext ctx, int op_code);

    abstract public void replyError(ChannelHandlerContext ctx, int op_code, int error_code);
}
