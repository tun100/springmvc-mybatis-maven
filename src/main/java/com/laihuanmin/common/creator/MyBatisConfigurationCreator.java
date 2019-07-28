package com.laihuanmin.common.creator;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFactory;
import com.laihuanmin.common.spring.SpringUtils;
import com.laihuanmin.common.utils.CommonUtils;
import org.apache.commons.io.FileUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class MyBatisConfigurationCreator {
    public static void create(Class envClz) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        // 配置声明
        String encoding = "UTF-8";
        // 声明class目录与源码目录
        File classDirectory = CommonUtils.getClassFilePath(envClz);
        File sourceDirectory = new File(classDirectory.getParentFile().getParentFile(), "src/main/java");
        // 加载Spring容器环境
        SpringUtils.init(envClz);
        XmlWebApplicationContext ctx = SpringUtils.getCtx();
        // 加载完毕Spring容器环境之后，获取所有DataSource的Bean实例
        Map<String, DruidDataSource> dataSourceMap = ctx.getBeansOfType(DruidDataSource.class);
        Collection<DruidDataSource> dataSourceCollection = dataSourceMap.values();
        Iterator<DruidDataSource> iterator = dataSourceCollection.iterator();
        while (iterator.hasNext()) {
            DruidDataSource druidDataSource = iterator.next();
            String driverClassName = druidDataSource.getDriverClassName();
            // 初始化JdbcTemplate，并且根据驱动名来配置查询语句与匹配键值对
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(druidDataSource);
            String querySQL = null;
            Map paramMap = new HashMap();
            // 处理mysql的查表语句
            if (driverClassName.contains("mysql")) {

            }
            System.out.println();
        }
        // 初始化并执行MyBatis Generator工具
        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        File generatorConfigFile = CommonUtils.getClassFilePath(envClz, "generatorConfig.xml");
        Configuration configuration = cp.parseConfiguration(generatorConfigFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
        myBatisGenerator.generate(null);
        // 将daotemp包融合至dao包里面
        List<File> daotempDirectories = CommonUtils.iterateFileRecursion(sourceDirectory, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().equalsIgnoreCase("daotemp");
            }
        });
        for (int i = 0; i < daotempDirectories.size(); i++) {
            File daotempFile = daotempDirectories.get(i);
            if (daotempFile.isDirectory()) {
                File[] daoFiles = daotempFile.listFiles();
                for (File newDaoFile : daoFiles) {
                    String interfaceName = newDaoFile.getName().replace(".java", "");
                    System.out.println("handle interface: " + interfaceName);
                    File oldDaoFile = new File(newDaoFile.getAbsolutePath().replace("daotemp", "dao"));
                    String newFileStr = FileUtils.readFileToString(newDaoFile, encoding);
                    newFileStr = newFileStr.replaceAll("daotemp", "dao");
                    if (!oldDaoFile.exists()) {
                        FileUtils.writeStringToFile(oldDaoFile, newFileStr, encoding);
                    } else {
//                        此处原本想用AST来解析，但是目前看来没有必要，暂时跳过
//                        String oldFileStr = FileUtils.readFileToString(oldDaoFile, encoding);
//                        CompilationUnit oldUnit = StaticJavaParser.parse(oldFileStr);
//                        CompilationUnit newUnit = StaticJavaParser.parse(newFileStr);
//                        Optional<ClassOrInterfaceDeclaration> oldInterface = oldUnit.getInterfaceByName(interfaceName);
//                        Optional<ClassOrInterfaceDeclaration> newInterface = newUnit.getInterfaceByName(interfaceName);
//                        ClassOrInterfaceDeclaration oldInterfaceBodyDeclare = oldInterface.get();
//                        ClassOrInterfaceDeclaration newInterfaceBodyDeclare = newInterface.get();
//                        NodeList<BodyDeclaration<?>> oldMembers = oldInterfaceBodyDeclare.getMembers();
//                        NodeList<BodyDeclaration<?>> newMembers = newInterfaceBodyDeclare.getMembers();
//                        NodeList<BodyDeclaration<?>> needAddMemberList = new NodeList<>();
//                        for (int oldMemberIndex = 0; oldMemberIndex < oldMembers.size(); oldMemberIndex++) {
//                            BodyDeclaration<?> crtOldMember = oldMembers.get(oldMemberIndex);
//                        }
//                        System.out.println(oldUnit.toString()); // print code
                    }
                }
                FileUtils.deleteDirectory(daotempFile);
            }
        }
    }
}
