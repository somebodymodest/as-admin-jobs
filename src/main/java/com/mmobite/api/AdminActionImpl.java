package com.mmobite.api;

public interface AdminActionImpl {
    public abstract boolean kickPlayer(int char_id, String admin_name);

    public abstract boolean deleteUserPost(int char_id, int account_id, String admin_name);

    public abstract boolean punishChar(int char_id, int punish_type, int time, String admin_name);

    public abstract boolean sendMessageToGame(int msg_id, String[] s_params_, int time_, int delay_, String admin_name);
}
