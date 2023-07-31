package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Spu;
import cn.tedu.csmall.product.pojo.vo.SpuStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SpuMapperTests {

    @Autowired
    SpuMapper mapper;

    @Test
    public void testInsert() {
        Spu spu = new Spu();
        spu.setId(11000L); // 重要，必须
        spu.setName("小米13");

        log.debug("插入数据之前，参数={}", spu);
        int rows = mapper.insert(spu);
        log.debug("rows = {}", rows);
        log.debug("插入数据之后，参数={}", spu);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        int rows = mapper.deleteById(id);
        log.debug("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testDeleteByIds() {
        int rows = mapper.deleteByIds(1L, 3L, 5L, 7L, 9L);
        log.debug("批量删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        SpuStandardVO spu = mapper.getStandardById(id);
        log.debug("根据id={}查询完成，查询结果={}", id, spu);
    }

}