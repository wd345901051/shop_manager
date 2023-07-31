package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Category;
import cn.tedu.csmall.product.pojo.vo.CategoryListItemVO;
import cn.tedu.csmall.product.pojo.vo.CategoryStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class CategoryMapperTests {

    @Autowired
    CategoryMapper mapper;

    @Test
    public void testInsert() {
        Category category = new Category();
        category.setName("家电");

        log.debug("插入数据之前，参数={}", category);
        int rows = mapper.insert(category);
        log.debug("rows = {}", rows);
        log.debug("插入数据之后，参数={}", category);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        int rows = mapper.deleteById(id);
        log.debug("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testDeleteByIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        ids.add(5L);
        ids.add(7L);
        ids.add(9L);
        int rows = mapper.deleteByIds(ids);
        log.debug("批量删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdate() {
        Category category = new Category();
        category.setId(1L);
        category.setName("新的名称");

        int rows = mapper.update(category);
        log.debug("修改类别信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount() {
        int count = mapper.count();
        log.info("统计数量完成，统计结果={}", count);
    }

    @Test
    public void testCountByName() {
        String name = "某个新品牌";
        int count = mapper.countByName(name);
        log.debug("根据名称【{}】统计相册数量完成，统计结果={}", name, count);
    }

    @Test
    public void testCountByParentId() {
        Long parentId = 0L;
        int count = mapper.countByParentId(parentId);
        log.debug("根据父级类别【{}】统计相册数量完成，统计结果={}", parentId, count);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        CategoryStandardVO category = mapper.getStandardById(id);
        log.debug("根据id={}查询完成，查询结果={}", id, category);
    }

    @Test
    public void testListByParentId() {
        Long parentId = 0L;
        List<CategoryListItemVO> list = mapper.listByParentId(parentId);
        log.info("查询列表完成，结果集中的数据的数量=" + list.size());
        for (CategoryListItemVO item : list) {
            log.info("{}", item);
        }
    }

}