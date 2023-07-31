package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.pojo.dto.SpuDetailAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SpuDetailServiceTests {

    @Autowired
    ISpuDetailService service;

    @Test
    void testAddNew() {
        SpuDetailAddNewDTO spuDetailAddNewDTO = new SpuDetailAddNewDTO();
        spuDetailAddNewDTO.setSpuId(99999L);
        spuDetailAddNewDTO.setDetail("这是某个SPU的详情");

        try {
            service.addNew(spuDetailAddNewDTO);
            log.debug("添加SPU详情成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    void testDeleteById() {
        try {
            Long id = 1L;
            service.deleteById(id);
            log.debug("根据id={}删除SPU详情成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

}
