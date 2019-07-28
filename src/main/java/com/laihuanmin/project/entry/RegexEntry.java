package com.laihuanmin.project.entry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEntry {
    public static void main(String[] args) {
        String str = "jdbc:mysql://127.0.0.1:3306/dbtest?autoReconnect=true&failOverReadOnly=false";
        Pattern pattern = Pattern.compile("mysql\\://[^\\:]+\\:\\d+/(.*)\\?+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        } else {
            System.out.println("not found");
        }
    }
}
