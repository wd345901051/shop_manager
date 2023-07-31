package cn.tedu.csmall.product.repository.impl;

import cn.tedu.csmall.product.pojo.vo.BrandListItemVO;
import cn.tedu.csmall.product.pojo.vo.BrandStandardVO;
import cn.tedu.csmall.product.repository.IBrandRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class BrandRedisRepositoryImpl implements IBrandRedisRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public BrandRedisRepositoryImpl() {
        log.debug("创建了处理缓存的类：BrandRedisRepositoryImpl");
    }

    @Override
    public void put(BrandStandardVO brand) {
        String key = getItemKey(brand.getId());
        redisTemplate.opsForValue().set(key, brand);
    }

    @Override
    public void put(BrandStandardVO brand, long t, TimeUnit timeUnit) {
        String key = getItemKey(brand.getId());
        redisTemplate.opsForValue().set(key, brand, t, timeUnit);
    }

    @Override
    public BrandStandardVO get(Long id) {
        String key = getItemKey(id);
        Serializable serializable = redisTemplate.opsForValue().get(key);
        BrandStandardVO brand = null;
        if (serializable != null) {
            if (serializable instanceof BrandStandardVO) {
                brand = (BrandStandardVO) serializable;
            }
        }
        return brand;
    }

    @Override
    public Boolean deleteItem(Long id) {
        return redisTemplate.delete(getItemKey(id));
    }

    @Override
    public Boolean deleteList() {
        return redisTemplate.delete(BRAND_LIST_KEY);
    }

    @Override
    public Long deleteAll(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    @Override
    public void putList(List<BrandListItemVO> brands) {
        for (BrandListItemVO brand : brands) {
            redisTemplate.opsForList().rightPush(BRAND_LIST_KEY, brand);
        }
    }

    @Override
    public List<BrandListItemVO> getList() {
        List<Serializable> list = redisTemplate.opsForList().range(BRAND_LIST_KEY, 0, -1);
        List<BrandListItemVO> brands = new ArrayList<>();
        for (Serializable serializable : list) {
            brands.add((BrandListItemVO) serializable);
        }
        return brands;
    }

    @Override
    public Set<String> getAllKeys() {
        return redisTemplate.keys("brand:*");
    }

    private String getItemKey(Long id) {
        return BRAND_ITEM_KEY_PREFIX + id;
    }

}
