package com.laihuanmin.project.convertor;

import java.nio.charset.Charset;

public class WebStringConverter extends org.springframework.http.converter.StringHttpMessageConverter{
    public WebStringConverter(Charset defaultCharset) {
        super(defaultCharset);
        this.setWriteAcceptCharset(false);
    }
}
