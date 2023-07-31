package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.pojo.dto.BrandCategoryAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class BrandCategoryServiceTests {

    @Autowired
    IBrandCategoryService service;

    @Test
    void testAddNew() {
        BrandCategoryAddNewDTO brandCategoryAddNewDTO
                = new BrandCategoryAddNewDTO();
        brandCategoryAddNewDTO.setBrandId(1L);
        brandCategoryAddNewDTO.setCategoryId(1L);

        try {
            service.addNew(brandCategoryAddNewDTO);
            log.debug("添加品牌与类别的关联成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    void testDeleteById() {
        try {
            Long id = 1L;
            service.deleteById(id);
            log.debug("根据id={}删除品牌与类别的关联成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

}
