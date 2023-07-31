package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Attribute;
import cn.tedu.csmall.product.pojo.vo.AttributeListItemVO;
import cn.tedu.csmall.product.pojo.vo.AttributeStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class AttributeMapperTests {

    @Autowired
    AttributeMapper mapper;

    @Test
    public void testInsert() {
        Attribute attribute = new Attribute();
        attribute.setName("颜色");

        log.debug("插入数据之前，参数={}", attribute);
        int rows = mapper.insert(attribute);
        log.debug("rows = {}", rows);
        log.debug("插入数据之后，参数={}", attribute);
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
        String name = "小米15的颜色属性";
        Long templateId = 1L;
        int count = mapper.countByNameAndTemplate(name, templateId);
        log.debug("根据名称【{}】在属性模板【{}】中统计属性数量完成，统计结果={}", name, templateId, count);
    }

    @Test
    public void testCountByTemplateId() {
        Long templateId = 1L;
        int count = mapper.countByTemplateId(templateId);
        log.debug("根据属性模板【{}】统计属性数量完成，统计结果={}", templateId, count);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        AttributeStandardVO attribute = mapper.getStandardById(id);
        log.debug("根据id={}查询完成，查询结果={}", id, attribute);
    }

    @Test
    public void testListByParentId() {
        Long templateId = 1L;
        List<AttributeListItemVO> list = mapper.listByTemplateId(templateId);
        log.info("查询列表完成，结果集中的数据的数量=" + list.size());
        for (AttributeListItemVO item : list) {
            log.info("{}", item);
        }
    }

}