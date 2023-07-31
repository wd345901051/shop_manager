package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.pojo.dto.BrandAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class BrandServiceTests {

    @Autowired
    IBrandService service;

    @Test
    void testAddNew() {
        BrandAddNewDTO brandAddNewDTO = new BrandAddNewDTO();
        brandAddNewDTO.setName("华为");
        brandAddNewDTO.setPinyin("HuaWei");
        brandAddNewDTO.setLogo("http://www.huawei.com/logo.png");
        brandAddNewDTO.setKeywords("手机,电脑,网络通信");
        brandAddNewDTO.setDescription("这是华为品牌的简介");
        brandAddNewDTO.setSort(71);
        brandAddNewDTO.setEnable(1);

        try {
            service.addNew(brandAddNewDTO);
            log.debug("添加品牌成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    void testDeleteById() {
        try {
            Long id = 1L;
            service.deleteById(id);
            log.debug("根据id={}删除品牌成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    void testGetStandardById() {
        try {
            Long id = 5L;
            Object queryResult = service.getStandardById(id);
            log.debug("根据id={}查询品牌成功，结果：{}", id, queryResult);
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    public void testList() {
        List<?> list = service.list();
        log.debug("查询品牌列表完成，结果集中的数据的数量={}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

}
