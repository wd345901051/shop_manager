package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.BrandCategory;
import cn.tedu.csmall.product.pojo.vo.BrandCategoryStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class BrandCategoryMapperTests {

    @Autowired
    BrandCategoryMapper mapper;

    @Test
    public void testInsert() {
        BrandCategory brandCategory = new BrandCategory();
        brandCategory.setBrandId(1L);
        brandCategory.setCategoryId(1L);

        log.debug("插入数据之前，参数={}", brandCategory);
        int rows = mapper.insert(brandCategory);
        log.debug("rows = {}", rows);
        log.debug("插入数据之后，参数={}", brandCategory);
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
    public void testCountByBrand() {
        Long brandId = 1L;
        int count = mapper.countByBrand(brandId);
        log.debug("根据品牌【{}】统计关联数据的数量：{}", brandId, count);
    }

    @Test
    public void testCountByCategory() {
        Long categoryId = 1L;
        int count = mapper.countByCategory(categoryId);
        log.debug("根据类别【{}】统计关联数据的数量：{}", categoryId, count);
    }

    @Test
    public void testCountByBrandAndCategory() {
        Long brandId = 1L;
        Long categoryId = 1L;
        int count = mapper.countByBrandAndCategory(brandId, categoryId);
        log.debug("根据品牌【{}】与类别【{}】统计关联数据的数量：{}", brandId, categoryId, count);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        BrandCategoryStandardVO brandCategory = mapper.getStandardById(id);
        log.debug("根据id={}查询完成，查询结果={}", id, brandCategory);
    }

}