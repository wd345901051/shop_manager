package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.CategoryAttributeTemplate;
import cn.tedu.csmall.product.pojo.vo.CategoryAttributeTemplateStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class CategoryAttributeTemplateMapperTests {

    @Autowired
    CategoryAttributeTemplateMapper mapper;

    @Test
    public void testInsert() {
        CategoryAttributeTemplate categoryAttributeTemplate = new CategoryAttributeTemplate();
        categoryAttributeTemplate.setCategoryId(1L);
        categoryAttributeTemplate.setAttributeTemplateId(1L);

        log.debug("插入数据之前，参数={}", categoryAttributeTemplate);
        int rows = mapper.insert(categoryAttributeTemplate);
        log.debug("rows = {}", rows);
        log.debug("插入数据之后，参数={}", categoryAttributeTemplate);
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
    public void testCountByCategory() {
        Long categoryId = 1L;
        int count = mapper.countByCategory(categoryId);
        log.debug("根据类别【{}】统计关联数据的数量：{}", categoryId, count);
    }

    @Test
    public void testCountByByAttributeTemplate() {
        Long attributeTemplateId = 1L;
        int count = mapper.countByAttributeTemplate(attributeTemplateId);
        log.debug("根据属性模板【{}】统计关联数据的数量：{}", attributeTemplateId, count);
    }

    @Test
    public void testCountByCategoryAndAttributeTemplate() {
        Long categoryId = 1L;
        Long attributeTemplateId = 1L;
        int count = mapper.countByCategoryAndAttributeTemplate(categoryId, attributeTemplateId);
        log.debug("根据类别【{}】与属性模板【{}】统计关联数据的数量：{}", categoryId, attributeTemplateId, count);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        CategoryAttributeTemplateStandardVO categoryAttributeTemplate
                = mapper.getStandardById(id);
        log.debug("根据id={}查询完成，查询结果={}", id, categoryAttributeTemplate);
    }
}