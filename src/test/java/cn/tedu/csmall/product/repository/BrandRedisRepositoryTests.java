package cn.tedu.csmall.product.repository;

import cn.tedu.csmall.product.pojo.vo.BrandStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class BrandRedisRepositoryTests {

    @Autowired
    IBrandRedisRepository repository;

    @Test
    public void testPut() {
        BrandStandardVO brand = new BrandStandardVO();
        brand.setId(9L);
        brand.setName("缓存品牌9");

        repository.put(brand);
    }

    @Test
    public void testGet() {
        Long id = 9L;
        BrandStandardVO brand = repository.get(id);
        log.debug("根据id={}从Redis中读取数据，结果={}", id, brand);
    }

}
