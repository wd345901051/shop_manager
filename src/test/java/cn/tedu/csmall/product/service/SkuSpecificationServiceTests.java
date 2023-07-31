package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.pojo.dto.SkuSpecificationAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SkuSpecificationServiceTests {

    @Autowired
    ISkuSpecificationService service;

    @Test
    void testAddNew() {
        SkuSpecificationAddNewDTO skuSpecificationAddNewDTO
                = new SkuSpecificationAddNewDTO();
        skuSpecificationAddNewDTO.setSkuId(99999L);

        try {
            service.addNew(skuSpecificationAddNewDTO);
            log.debug("添加SKU数据成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    void testDeleteById() {
        try {
            Long id = 1L;
            service.deleteById(id);
            log.debug("根据id={}删除SKU数据成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

}
