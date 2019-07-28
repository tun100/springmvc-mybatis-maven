package com.laihuanmin.project.entry;

import com.laihuanmin.common.creator.MyBatisConfigurationCreator;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;

import java.io.IOException;
import java.sql.SQLException;

public class MyBatisGeneratorEntry {
    public static void main(String[] args) throws InterruptedException, SQLException, InvalidConfigurationException, XMLParserException, IOException {
        MyBatisConfigurationCreator.create(MyBatisGeneratorEntry.class);
    }
}
