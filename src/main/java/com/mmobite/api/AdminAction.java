package com.mmobite.api;

public class AdminAction implements AdminActionImpl {
    public boolean kickPlayer(int char_id, String admin_name) {
        print("kickPlayer: char_id=" + char_id + " admin_name=" + admin_name);
        return false;
    }

    public boolean deleteUserPost(int char_id, int account_id, String admin_name) {
        print("deleteUserPost: char_id=" + char_id + " account_id=" + account_id + " admin_name=" + admin_name);
        return false;
    }

    public boolean punishChar(int char_id, int punish_type, int time, String admin_name) {
        print("punishChar: char_id=" + char_id + " punish_type=" + punish_type + " time=" + time + " admin_name=" + admin_name);
        return false;
    }

    public boolean sendMessageToGame(int msg_id, String[] s_params_, int time_, int delay_, String admin_name) {
        String to_params = "[Empty]";
        if (s_params_ != null)
            for (String p : s_params_)
                to_params += ("[" + p + "],");
        print("sendMessageToGame: msg_id=" + msg_id + " s_params_=" + to_params + " time_=" + time_ + " delay_=" + delay_ + " admin_name=" + admin_name);
        return false;
    }

    public void print(String txt) {
        System.out.println(txt);
    }
}
