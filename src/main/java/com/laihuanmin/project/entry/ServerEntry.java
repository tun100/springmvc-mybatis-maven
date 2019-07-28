package com.laihuanmin.project.entry;

import com.laihuanmin.common.utils.CommonUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Locale;

/**
 * 仅用于开发环境测试Server程序
 */
public class ServerEntry {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ServerEntry serverEntry = new ServerEntry();
        serverEntry.initServer();
    }

    public void initServer() throws Exception {
        Integer port = 12345;
        String basepath = "task";
        Server server = new Server(port);
        File webXmlFile = new File(CommonUtils.getClassFilePath(this.getClass()).getParentFile().getParentFile(), "src/main/webapp/WEB-INF/web.xml");
        InputStream fis = new FileInputStream(webXmlFile);
        File tempWebXmlFile = File.createTempFile("web", ".xml");
        FileOutputStream fos = new FileOutputStream(tempWebXmlFile);
        IOUtils.copy(fis, fos);
        WebAppContext webAppContext = new WebAppContext("webapp", "/" + basepath);
        webAppContext.setDescriptor(tempWebXmlFile.getAbsolutePath());
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setDisplayName(basepath);
        webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);
        webAppContext.addLocaleEncoding(Locale.CHINA.toString(), "UTF-8");
        server.setHandler(webAppContext);
        System.out.println("Server Will Start.");
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Server Already Started.");
    }
}
