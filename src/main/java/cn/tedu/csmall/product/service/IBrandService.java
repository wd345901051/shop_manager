package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.pojo.dto.BrandAddNewDTO;
import cn.tedu.csmall.product.pojo.vo.BrandListItemVO;
import cn.tedu.csmall.product.pojo.vo.BrandStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 品牌业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface IBrandService {

    /**
     * 增加品牌
     *
     * @param brandDTO 新增的品牌对象
     */
    void addNew(BrandAddNewDTO brandDTO);

    /**
     * 删除品牌
     *
     * @param id 被删除的品牌的id
     */
    void deleteById(Long id);

    /**
     * 根据id查询品牌详情
     *
     * @param id 品牌id
     * @return 匹配的品牌详情，如果没有匹配的数据，将抛出异常
     */
    BrandStandardVO getStandardById(Long id);

    /**
     * 查询品牌列表
     *
     * @return 品牌列表的集合
     */
    List<BrandListItemVO> list();

    /**
     * 加载品牌数据到缓存中
     */
    void loadBrandToCache();

}
