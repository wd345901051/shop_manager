package cn.tedu.csmall.product.repository;

import cn.tedu.csmall.product.pojo.vo.BrandListItemVO;
import cn.tedu.csmall.product.pojo.vo.BrandStandardVO;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 处理品牌数据缓存的仓库接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
public interface IBrandRedisRepository {

    /**
     * 品牌数据项的KEY的前缀
     */
    String BRAND_ITEM_KEY_PREFIX = "brand:item:";
    /**
     * 品牌列表的KEY
     */
    String BRAND_LIST_KEY = "brand:list";

    /**
     * 向Redis中存入品牌数据，或替换原有数据
     *
     * @param brand 品牌数据
     */
    void put(BrandStandardVO brand);

    /**
     * 向Redis中存入品牌数据，或替换原有数据，此次存入的数据仅在某段时间内有效的
     *
     * @param brand    品牌数据
     * @param t        存活时间值
     * @param timeUnit 存活时间单位
     */
    void put(BrandStandardVO brand, long t, TimeUnit timeUnit);

    /**
     * 从Redis中读取品牌数据
     *
     * @param id 品牌的id
     * @return 匹配的品牌数据，如果没有匹配的数据，则返回null
     */
    BrandStandardVO get(Long id);

    Boolean deleteItem(Long id);

    Boolean deleteList();

    Long deleteAll(Collection<String> keys);

    /**
     * 向Redis中存入品牌列表
     *
     * @param brands 品牌列表
     */
    void putList(List<BrandListItemVO> brands);

    /**
     * 从Redis中读取品牌列表
     *
     * @return 品牌列表，如果Redis中没有品牌列表，则返回长度为0的集合
     */
    List<BrandListItemVO> getList();

    Set<String> getAllKeys();

}
