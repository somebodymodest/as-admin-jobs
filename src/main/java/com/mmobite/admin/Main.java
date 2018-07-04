package com.mmobite.admin;

import com.mmobite.admin.server.AdminServer;

public class Main {
    public static void main(String... args) throws Exception {
        AdminServer.init("antispam/admin_job.properties");
    }
}
