package cn.tedu.csmall.product.preload;

import cn.tedu.csmall.product.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class CacheLoader implements ApplicationRunner {

    @Autowired
    private IBrandService brandService;

    public CacheLoader() {
        log.debug("创建ApplicationRunner：CacheLoader");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("CacheLoader.run()");
        log.debug("加载品牌数据到缓存……");
        brandService.loadBrandToCache();
    }

}
