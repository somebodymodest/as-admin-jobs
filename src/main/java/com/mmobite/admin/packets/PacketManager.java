package com.mmobite.admin.packets;

import com.mmobite.admin.handlers.*;
import com.mmobite.admin.model.packet.ReadPacket;
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
        packets.put(OpcodeCS.SendMessageToGameIL, SendMessageToGamePacketIL.class);
        packets.put(OpcodeCS.SendMessageToGameGF, SendMessageToGamePacketGF.class);
        packets.put(OpcodeCS.DeleteUserPost, DeleteUserPostPacket.class);
    }

    public static void addPacket(int type, Class<? extends ReadPacket> packet) {
        packets.put(type, packet);
    }

    public static ReadPacket getPacket(int opcode) {
        try {
            return packets.get(opcode).newInstance();
        } catch (Exception ex) {
            return new DummyReadPacket();
        }
    }
}
