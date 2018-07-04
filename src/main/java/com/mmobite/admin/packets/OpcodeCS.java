package com.mmobite.admin.packets;

public class OpcodeCS {
    /*
    format: "cS"
        c - opcode
        S - admin_name (UTF16-LE)
    */
    public static final int CheckVersion = 0x00;

    /*
    format: "cdS"
        c - opcode
        d - char_id
        S - admin_name (UTF16-LE)
    */
    public static final int KickCharacter = 0x05;

    /*
    format: "cdddS"
        c - opcode
        d - char_id
        d - punish_type (2 - mute chat)
        d - time (minutes)
        S - admin_name (UTF16-LE)
    */
    public static final int PunishChar = 0x12;

    /*
     Send Message to Game Server

     packet IL: 0x73
     packet GF+: 0x99

     format: "cd"
        c - opcode
        d - msg_id

     msg_id values:
     	1 - reload ban-list
     	2 - reload access-list
        3 - add ban to bans.xml, additional parameters:
     	    S - hwid (UTF-16LE string)
     	    S - account (UTF-16LE string)
     	    S - punish_actione = [REALTIME | TEMPORARY | DELAYED | DELAYEDTEMPORARY] (UTF-16LE string, see SmartGuard.ini DetectAction parameter)
     	    d - time (in minutes)
     	    d - delay (in minutes)
     	    S - comment (UTF-16LE string)
        4 - remove ban from bans.xml, additional parameters:
     	    S - hwid (UTF-16LE string)
     	    S - account (UTF-16LE string)
     */
    public static final int SendMessageToGame = 0x99;

    /*
    format: "cddS"
        c - opcode
        d - char_id
        d - account_id
        S - admin_name (UTF16-LE)
    */
    public static final int DeleteUserPost = 0x9A;
}
