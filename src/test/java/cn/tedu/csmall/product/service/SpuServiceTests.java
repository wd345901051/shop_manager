package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.pojo.dto.SpuAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SpuServiceTests {

    @Autowired
    ISpuService service;

    @Test
    void testAddNew() {
        SpuAddNewDTO spuAddNewDTO = new SpuAddNewDTO();
        spuAddNewDTO.setTitle("这是某个SPU的标题");

        try {
            service.addNew(spuAddNewDTO);
            log.debug("添加SPU成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    void testDeleteById() {
        try {
            Long id = 1L;
            service.deleteById(id);
            log.debug("根据id={}删除SPU成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

}
