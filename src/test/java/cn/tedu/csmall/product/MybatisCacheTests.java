package cn.tedu.csmall.product;

import cn.tedu.csmall.product.mapper.BrandMapper;
import cn.tedu.csmall.product.pojo.entity.Brand;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MybatisCacheTests {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    void testL1Cache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper1 = sqlSession.getMapper(BrandMapper.class);
        Long id = 2L;
        log.debug("执行id={}的第1次查询……", id);
        brandMapper1.getStandardById(id);
        log.debug("执行id={}的第2次查询……", id);
        brandMapper1.getStandardById(id);
        log.debug("调用了SqlSession的clearCache()方法……");
        sqlSession.clearCache();
        log.debug("执行id={}的第3次查询……", id);
        brandMapper1.getStandardById(id);

        id = 5L;
        log.debug("执行id={}的第1次查询……", id);
        brandMapper1.getStandardById(id);

//        log.debug("执行修改其它数据……");
//        Brand brand = new Brand();
//        brand.setId(4L);
//        brand.setName("红米");
//        brandMapper1.update(brand);

        log.debug("执行id={}的第2次查询……", id);
        brandMapper1.getStandardById(id);

        id = 2L;
        log.debug("执行id={}的第4次查询……", id);
        brandMapper1.getStandardById(id);
        log.debug("执行id={}的第5次查询……", id);
        brandMapper1.getStandardById(id);

        log.debug("接下来，重新获取BrandMapper……");
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        BrandMapper brandMapper2 = sqlSession2.getMapper(BrandMapper.class);
        log.debug("执行id={}的第6次查询……", id);
        brandMapper2.getStandardById(id);
        log.debug("执行id={}的第7次查询……", id);
        brandMapper2.getStandardById(id);
    }

    @Autowired
    BrandMapper brandMapper;

    @Test
    public void testL2Cache() {
        Long id = 2L;
        log.debug("执行id=2的第1次查询");
        brandMapper.getStandardById(id);
        log.debug("执行id=2的第2次查询");
        brandMapper.getStandardById(id);

        log.debug("执行修改其它数据……");
        Brand brand = new Brand();
        brand.setId(4L);
        brand.setName("红米");
        brandMapper.update(brand);

        log.debug("执行id=2的第3次查询");
        brandMapper.getStandardById(id);
        log.debug("执行id=2的第4次查询");
        brandMapper.getStandardById(id);

        id = 5L;
        log.debug("执行id=5的第1次查询");
        brandMapper.getStandardById(id);
    }

}
