package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.AttributeTemplate;
import cn.tedu.csmall.product.pojo.vo.AttributeTemplateListItemVO;
import cn.tedu.csmall.product.pojo.vo.AttributeTemplateStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class AttributeTemplateMapperTests {

    @Autowired
    AttributeTemplateMapper mapper;

    @Test
    public void testInsert() {
        AttributeTemplate attributeTemplate = new AttributeTemplate();
        attributeTemplate.setName("小米13的属性模版");

        log.debug("插入数据之前，参数={}", attributeTemplate);
        int rows = mapper.insert(attributeTemplate);
        log.debug("rows = {}", rows);
        log.debug("插入数据之后，参数={}", attributeTemplate);
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
    public void testCountByName() {
        String name = "小米15的属性模板";
        int count = mapper.countByName(name);
        log.debug("根据名称【{}】统计相册数量完成，统计结果={}", name, count);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        AttributeTemplateStandardVO attributeTemplate = mapper.getStandardById(id);
        log.debug("根据id={}查询完成，查询结果={}", id, attributeTemplate);
    }

    @Test
    public void testList() {
        List<AttributeTemplateListItemVO> list = mapper.list();
        log.debug("查询列表完成，结果集中的数据的数量=" + list.size());
        for (AttributeTemplateListItemVO item : list) {
            log.debug("{}", item);
        }
    }

}