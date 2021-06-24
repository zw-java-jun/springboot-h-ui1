package com.zpj.config.filter;

import com.zpj.AES;
import com.zpj.rsaaes.RSAEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.io.IOUtils;
import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AESFilter implements Filter {

//    @Value("${spring.profiles.active}")
//    String active;

    public String key;

//    @PostConstruct
//    private void initialize() {
//        key = "p".equals(active) ? "123456" : "123456";
//    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hsr = (HttpServletRequest) request;
        key= hsr.getHeader("key");
        System.out.println("这是没解密的AES秘钥:"+key);
        AES.key = RSAEncryptor.rsaEncry(key);
        System.out.println("这是在filter里面解密的AES秘钥:"+AES.key);
        AESRequestWrapper aes_request = new AESRequestWrapper(hsr);
        aes_request.setAttribute("body", aes_request.body());
        chain.doFilter(aes_request, response);

    }
}
