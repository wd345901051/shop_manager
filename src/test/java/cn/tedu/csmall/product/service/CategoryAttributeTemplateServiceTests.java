package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.pojo.dto.CategoryAttributeTemplateAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class CategoryAttributeTemplateServiceTests {

    @Autowired
    ICategoryAttributeTemplateService service;

    @Test
    void testAddNew() {
        CategoryAttributeTemplateAddNewDTO categoryAttributeTemplate
                = new CategoryAttributeTemplateAddNewDTO();
        categoryAttributeTemplate.setCategoryId(1L);
        categoryAttributeTemplate.setAttributeTemplateId(1L);

        try {
            service.addNew(categoryAttributeTemplate);
            log.debug("添加类别与属性模板的关联成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    void testDeleteById() {
        try {
            Long id = 1L;
            service.deleteById(id);
            log.debug("根据id={}删除类别与属性模板的关联成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

}
