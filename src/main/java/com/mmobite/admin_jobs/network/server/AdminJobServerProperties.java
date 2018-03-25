package com.mmobite.admin_jobs.network.server;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminJobServerProperties {

    private static Logger log = LoggerFactory.getLogger(AdminJobServerProperties.class.getName());
    private final static String filename = "antispam/admin_job.properties";

    public static boolean Enabled;
    public static int Port;

    static
    {
        load();
    }

    private static void load()
    {
        try
        {
            Properties prop = new Properties();
            InputStream stream = new FileInputStream(filename);
            prop.load(stream);
            stream.close();

            Enabled = Boolean.parseBoolean(prop.getProperty("Enabled", "true"));
            Port = Integer.parseInt(prop.getProperty("Port", "2012"));
        }
        catch (Exception ex)
        {
            log.error("Load config exception: ", ex);
        }
    }

}
