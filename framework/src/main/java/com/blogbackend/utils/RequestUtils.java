package com.blogbackend.utils;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author ZCH
 */
public class RequestUtils {
    public static <T> T parseRequestBody(HttpServletRequest request,Class<T> clazz) throws IOException {
        BufferedReader requestReader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = requestReader.readLine()) != null) {
            requestBody.append(line);
        }
        requestReader.close();
        String requestBodyString = requestBody.toString();
        return JSON.parseObject(requestBodyString, clazz);
    }
}
