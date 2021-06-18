package com.zpj;

import cn.dev33.satoken.SaManager;
import com.cxytiandi.encrypt.springboot.annotation.EnableEncrypt;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.zpj")
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        //System.out.println("启动成功：sa-token配置如下：" + SaManager.getConfig());
    }

}
