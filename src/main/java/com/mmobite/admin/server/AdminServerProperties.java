package com.mmobite.admin.server;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminServerProperties {

    private static Logger log = LoggerFactory.getLogger(AdminServerProperties.class.getName());

    public static boolean Enabled;
    public static int Port;

    public static void load(String configPath)
    {
        try
        {
            Properties prop = new Properties();
            InputStream stream = new FileInputStream(configPath);
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
