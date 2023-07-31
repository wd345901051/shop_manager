package cn.tedu.csmall.product;

import cn.tedu.csmall.product.pojo.entity.Brand;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    // 使用Redis时，Key是自由命名的
    // 建议Key（名称）是分多段的
    // 例如“品牌列表”，应该由 brand 和 list 这2个单词组成
    // 并且，多个单词之间推荐使用英文的冒号进行分隔，例如：brand:list
    // 对于同一种类型的数据，Key的第1段应该是相同的
    // 例如，id=6对应的品牌数据的Key应该中：brand:item:6
    // keys brand*

    @Test
    void testValueOpsSet() {
        // ValueOperations：用于实现string（Redis）的读写
        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        // 向Redis中“存入” / “修改”数据
        String key = "username";
        String value = "admin";
        ops.set(key, value);
        log.debug("已经向Redis中写入Key={}且Value={}的数据！", key, value);
    }

    @Test
    void testValueOpsSetObject() {
        // ValueOperations：用于实现string（Redis）的读写
        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        // 向Redis中“存入” / “修改”数据
        String key = "brand:item:1";
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("大白象");
        brand.setEnable(1);
        ops.set(key, brand);
        log.debug("已经向Redis中写入Key={}且Value={}的数据！", key, brand);

        key = "brand:item:2";
        brand = new Brand();
        brand.setId(2L);
        brand.setName("大白兔");
        brand.setEnable(1);
        ops.set(key, brand);
        log.debug("已经向Redis中写入Key={}且Value={}的数据！", key, brand);

        key = "brand:item:3";
        brand = new Brand();
        brand.setId(3L);
        brand.setName("大白熊");
        brand.setEnable(1);
        ops.set(key, brand);
        log.debug("已经向Redis中写入Key={}且Value={}的数据！", key, brand);
    }

    @Test
    void testValueOpsSetX() {
        // ValueOperations：用于实现string（Redis）的读写
        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        // 向Redis中“存入” / “修改”数据
        String key = "phone";
        String value = "13800138001";
        ops.set(key, value, 1, TimeUnit.MINUTES);
        log.debug("已经向Redis中写入Key={}且Value={}的数据！", key, value);
    }

    @Test
    void testValueOpsGet() {
        // ValueOperations：用于实现string（Redis）的读写
        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        // 从Redis中读取数据
        String key = "username";
        Serializable value = ops.get(key);
        log.debug("已经从Redis中读取Key={}的数据，Value={}", key, value);
    }

    @Test
    void testValueOpsGetObject() {
        // ValueOperations：用于实现string（Redis）的读写
        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        // 从Redis中读取数据
        String key = "brand1";
        Serializable value = ops.get(key);
        log.debug("已经从Redis中读取Key={}的数据，Value={}", key, value);
        log.debug("读取到的数据的类型是：{}", value.getClass().getName());
        Brand brand = (Brand) value;
        log.debug("执行类型转换成功：{}", brand);
    }

    @Test
    void testDelete() {
        String key = "name998";
        Boolean result = redisTemplate.delete(key);
        log.debug("在Redis中删除了Key={}的数据，结果为：{}", key, result);
    }

    @Test
    void testKeys() {
        Set<String> keys = redisTemplate.keys("brand*");
        for (String key : keys) {
            log.debug("{}", key);
        }
    }

    @Test
    void testListRightPush() {
        List<Brand> brands = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Brand brand = new Brand();
            brand.setId(i + 0L);
            brand.setName("测试品牌" + i);
            brands.add(brand);
        }

        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        String key = "brand:list";
        for (Brand brand : brands) {
            ops.rightPush(key, brand);
        }
    }

    @Test
    void testListSize() {
        // 获取列表数据的长度
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        String key = "brands";
        Long size = ops.size(key);
        log.debug("在Redis中Key={}的列表数据的长度为：{}", key, size);
    }

    @Test
    void testListRange() {
        // 取出列表
        // 在列表中的每个元素都有2个下标
        // 正数的下标是从第1个元素以0作为下标，开始递增的编号
        // 负数的下标是从最后一个元素以-1作为下标，开始递减的编号
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        String key = "brands";
        long start = 0L;
        long end = -1L;
        List<Serializable> list = ops.range(key, start, end);
        log.debug("从Redis中获取到的结果：{}", list);
        for (Serializable serializable : list) {
            log.debug("{}", serializable);
        }
    }

}
