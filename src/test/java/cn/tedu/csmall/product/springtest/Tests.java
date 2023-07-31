package cn.tedu.csmall.product.springtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class Tests {

    @Autowired
    TestController testController;
    @Resource(name = "testService1")
    ITestService service;

    @Test
    void contextLoads() {
        System.out.println("testController = " + testController);
        System.out.println("testService = " + service);
    }

}
