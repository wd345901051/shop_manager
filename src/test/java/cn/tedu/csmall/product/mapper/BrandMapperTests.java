package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Brand;
import cn.tedu.csmall.product.pojo.vo.BrandListItemVO;
import cn.tedu.csmall.product.pojo.vo.BrandStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class BrandMapperTests {

    @Autowired
    BrandMapper mapper;

    @Test
    public void testInsert() {
        Brand brand = new Brand();
        brand.setName("绿米");
        brand.setPinyin("lvmi");

        log.debug("插入数据之前，参数={}", brand);
        int rows = mapper.insert(brand); // 插入成功后，brand.setId(10L);
        log.debug("rows = {}", rows);
        log.debug("插入数据之后，参数={}", brand);
    }

    @Test
    public void testDeleteById() {
        Long id = 24L;
        int rows = mapper.deleteById(id);
        log.debug("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testDeleteByIds() {
        int rows = mapper.deleteByIds(1L, 3L, 5L, 7L, 9L);
        log.debug("批量删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        Long id = 8L;
        String name = "红米2022";
        String logo = "http://www.xiaomi.com/hongmi.png";
        // String pinyin = "XinHongMi";
        Brand brand = new Brand();
        //brand.setId(id);
        brand.setName(name);
        brand.setLogo(logo);
        // brand.setPinyin(pinyin);

        int rows = mapper.update(brand);
        log.debug("修改品牌信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount() {
        int count = mapper.count();
        log.debug("统计品牌数量完成，统计结果={}", count);
    }

    @Test
    public void testCountByName() {
        String name = "西瓜";
        int count = mapper.countByName(name);
        log.debug("根据名称【{}】统计品牌数量完成，统计结果={}", name, count);
    }

    @Test
    public void testGetById() {
        Long id = 8L;
        BrandStandardVO brand = mapper.getStandardById(id);
        log.debug("根据id={}查询完成，结果={}", id, brand);
    }

    @Test
    public void testList() {
        List<BrandListItemVO> list = mapper.list();
        log.debug("查询列表完成，结果集中的数据的数量={}", list.size());
        for (BrandListItemVO item : list) {
            log.debug("{}", item);
        }
    }

}