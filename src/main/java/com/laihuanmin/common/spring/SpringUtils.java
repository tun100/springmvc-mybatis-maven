package com.laihuanmin.common.spring;

import com.laihuanmin.common.utils.CommonUtils;
import com.laihuanmin.project.define.ProjectSettingDefine;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.io.*;
import java.util.Locale;

public class SpringUtils {
    public static void init(Class envClass) throws IOException {
        XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
        File classFilePath = CommonUtils.getClassFilePath(envClass, "applicationContext.xml");
        applicationContext.setConfigLocation("file:"+classFilePath.getAbsolutePath());
        String webXmlAbsolutePath = getWebXmlAbsolutePath(envClass);
        WebAppContext webAppContext = getWebAppContext(ProjectSettingDefine.WEB_URL_PREFIX, webXmlAbsolutePath);
        applicationContext.setServletContext(webAppContext.getServletContext());
        applicationContext.refresh();
        setCtx(applicationContext);
    }

    public static String getWebXmlAbsolutePath(Class envClz) throws IOException {
        File webXmlFile = new File(CommonUtils.getClassFilePath(envClz).getParentFile().getParentFile(), "src/main/webapp/WEB-INF/web.xml");
        InputStream fis = new FileInputStream(webXmlFile);
        File tempWebXmlFile = File.createTempFile("web", ".xml");
        FileOutputStream fos = new FileOutputStream(tempWebXmlFile);
        IOUtils.copy(fis, fos);
        return tempWebXmlFile.getAbsolutePath();
    }

    public static WebAppContext getWebAppContext(String basepath, String webXmlFile) {
        WebAppContext webAppContext = new WebAppContext("webapp", "/" + basepath);
        webAppContext.setDescriptor(webXmlFile);
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setDisplayName(basepath);
        webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);
        webAppContext.addLocaleEncoding(Locale.CHINA.toString(), "UTF-8");
        return webAppContext;
    }

    public static XmlWebApplicationContext getCtx() {
        return ctx;
    }

    public static void setCtx(XmlWebApplicationContext ctx) {
        SpringUtils.ctx = ctx;
    }

    public static XmlWebApplicationContext ctx;
}
