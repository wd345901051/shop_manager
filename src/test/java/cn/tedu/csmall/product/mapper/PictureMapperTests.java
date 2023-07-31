package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Picture;
import cn.tedu.csmall.product.pojo.vo.PictureStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PictureMapperTests {

    @Autowired
    PictureMapper mapper;

    @Test
    public void testInsert() {
        Picture picture = new Picture();
        picture.setUrl("http://www.tedu.cn/logo.png");

        log.debug("插入数据之前，参数={}", picture);
        int rows = mapper.insert(picture);
        log.debug("rows = {}", rows);
        log.debug("插入数据之后，参数={}", picture);
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
        PictureStandardVO picture = mapper.getStandardById(id);
        log.debug("根据id={}查询完成，查询结果={}", id, picture);
    }

}