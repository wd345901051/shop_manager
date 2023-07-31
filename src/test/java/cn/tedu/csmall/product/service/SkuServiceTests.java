package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.pojo.dto.SkuAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SkuServiceTests {

    @Autowired
    ISkuService service;

    @Test
    void testAddNew() {
        SkuAddNewDTO skuAddNewDTO = new SkuAddNewDTO();
        skuAddNewDTO.setId(99999L);
        skuAddNewDTO.setTitle("这是测试的SKU数据");

        try {
            service.addNew(skuAddNewDTO);
            log.debug("添加SKU成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    void testDeleteById() {
        try {
            Long id = 1L;
            service.deleteById(id);
            log.debug("根据id={}删除SKU成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

}
