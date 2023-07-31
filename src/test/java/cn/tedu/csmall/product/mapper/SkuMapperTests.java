package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Sku;
import cn.tedu.csmall.product.pojo.vo.SkuStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SkuMapperTests {

    @Autowired
    SkuMapper mapper;

    @Test
    public void testInsert() {
        Sku sku = new Sku();
        sku.setId(11000L); // 重要，必须
        sku.setTitle("小米13黑色512G");

        log.debug("插入数据之前，参数={}", sku);
        int rows = mapper.insert(sku);
        log.debug("rows = {}", rows);
        log.debug("插入数据之后，参数={}", sku);
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
        SkuStandardVO sku = mapper.getStandardById(id);
        log.debug("根据id={}查询完成，查询结果={}", id, sku);
    }

}