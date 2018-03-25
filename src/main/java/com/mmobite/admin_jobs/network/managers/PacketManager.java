package com.mmobite.admin_jobs.network.managers;

import com.mmobite.admin_jobs.network.packet.AdminRequestPacket;
import com.mmobite.admin_jobs.network.handlers.SendMessageToGamePacket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 23.03.2018.
 */
public class PacketManager {

    private final static Map<AdminRequestType, Class<? extends AdminRequestPacket>> packets = new HashMap<>();

    static {
        packets.put(AdminRequestType.Dummy, AdminRequestPacket.class);
        packets.put(AdminRequestType.SendMessageToGame, SendMessageToGamePacket.class);
    }

    public static AdminRequestPacket getPacket(short opcode) {
        try {
            return packets.get(AdminRequestType.valueOf((int)opcode)).newInstance();
        } catch (Exception ex) {
            return new AdminRequestPacket();
        }
    }
}
