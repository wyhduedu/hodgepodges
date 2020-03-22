//package com.wy.hodgepodges.common.util;
//
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.HttpOutputMessage;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.AbstractHttpMessageConverter;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.http.converter.HttpMessageNotWritableException;
//
//import java.io.IOException;
//import java.nio.charset.Charset;
//
///**
// * @author wy
// * @version V1.0
// * @desc 自定义
// * @date 2020-03-22 18:18
// */
//public class HttpMessageConverter extends AbstractHttpMessageConverter {
//
//    public HttpMessageConverter() {
//        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
//    }
//
//    @Override
//    protected boolean supports(Class clazz) {
//        return false;
//    }
//
//    @Override
//    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
//        return null;
//    }
//
//    @Override
//    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
//
//    }
//}
