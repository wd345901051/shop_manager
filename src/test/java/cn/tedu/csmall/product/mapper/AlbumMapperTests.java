package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Album;
import cn.tedu.csmall.product.pojo.vo.AlbumListItemVO;
import cn.tedu.csmall.product.pojo.vo.AlbumStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AlbumMapperTests {

    @Autowired
    AlbumMapper mapper;

    @Test
    public void testInsert() {
        Album album = new Album();
        album.setName("小米13的相册");

        log.info("插入数据之前，参数={}", album);
        int rows = mapper.insert(album);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", album);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数=" + rows);
    }

    @Test
    public void testDeleteByIds() {
        Long[] ids = {1L, 3L, 5L, 7L, 9L};
        int rows = mapper.deleteByIds(ids);
        log.info("批量删除完成，受影响的行数=" + rows);
    }

    @Test
    public void testCount() {
        int count = mapper.count();
        log.debug("统计数量完成，统计结果=" + count);
    }

    @Test
    public void testCountByName() {
        String name = "小米15的相册";
        int count = mapper.countByName(name);
        log.debug("根据名称【{}】统计相册数量完成，统计结果={}", name, count);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        AlbumStandardVO album = mapper.getStandardById(id);
        log.debug("根据id={}查询完成，查询结果={}", id, album);
    }

    @Test
    public void testList() {
        List<AlbumListItemVO> list = mapper.list();
        log.debug("查询列表完成，结果集中的数据的数量=" + list.size());
        for (AlbumListItemVO item : list) {
            log.debug("{}", item);
        }
    }

}