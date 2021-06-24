package com.zpj.config.filter;

import com.alibaba.fastjson.JSON;
import com.zpj.AES;
import com.zpj.rsaaes.AESUtil;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class AESRequestWrapper extends HttpServletRequestWrapper {

    private String body;

    public AESRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        return new SIS(new ByteArrayInputStream(body.getBytes()));
    }

    public String body(){
        //body = AESUtil.decrypt(IOUtils.toString(super.getInputStream()));
        try {
            String replace = JSON.parseObject(IOUtils.toString(super.getInputStream()).replace("\\", ""), String.class);
            System.out.println(replace);
            body=AES.aesDecrypt(replace);
            System.out.println("这是body："+body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    class SIS extends ServletInputStream {

        private ByteArrayInputStream bais;

        public SIS(ByteArrayInputStream bais) {
            this.bais = bais;
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener read_listener) {

        }

        @Override
        public int read() throws IOException {
            return bais.read();
        }
    }
}
