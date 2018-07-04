package com.mmobite.admin.packets;

import com.mmobite.admin.handlers.*;
import com.mmobite.admin.model.packet.ReadPacket;
import com.mmobite.admin.network.admin_channel.handlers.*;
import com.mmobite.admin_jobs.network.admin_channel.handlers.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 23.03.2018.
 */
public class PacketManager {

    private final static Map<Integer, Class<? extends ReadPacket>> packets = new HashMap<>();

    static {
        packets.put(OpcodeCS.CheckVersion, CheckVersionPacket.class);
        packets.put(OpcodeCS.KickCharacter, KickCharacterPacket.class);
        packets.put(OpcodeCS.PunishChar, PunishCharPacket.class);
        packets.put(OpcodeCS.SendMessageToGame, SendMessageToGamePacket.class);
        packets.put(OpcodeCS.DeleteUserPost, DeleteUserPostPacket.class);
    }

    public static ReadPacket getPacket(int opcode) {
        try {
            return packets.get(opcode).newInstance();
        } catch (Exception ex) {
            return new CheckVersionPacket();
        }
    }
}
