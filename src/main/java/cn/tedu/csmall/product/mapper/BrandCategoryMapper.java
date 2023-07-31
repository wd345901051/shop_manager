package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.BrandCategory;
import cn.tedu.csmall.product.pojo.vo.BrandCategoryStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 品牌类别关联Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface BrandCategoryMapper {

    /**
     * 插入品牌类别关联数据
     *
     * @param brandCategory 品牌类别关联数据
     * @return 受影响的行数
     */
    int insert(BrandCategory brandCategory);

    /**
     * 根据id删除品牌类别关联
     *
     * @param id 被删除的品牌类别关联的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据多个id批量删除品牌类别关联数据
     *
     * @param ids 期望删除的若干个品牌类别关联数据的id
     * @return 受影响的行数，将返回成功删除的数据量
     */
    int deleteByIds(Long... ids);

    /**
     * 根据品牌统计关联数据的数量
     *
     * @param brandId 类别id
     * @return 此类别关联的数据的数量
     */
    int countByBrand(Long brandId);

    /**
     * 根据类别统计关联数据的数量
     *
     * @param categoryId 类别id
     * @return 此类别关联的数据的数量
     */
    int countByCategory(Long categoryId);

    /**
     * 根据品牌与类别统计关联数据的数量
     *
     * @param brandId    品牌id
     * @param categoryId 类别id
     * @return 此属性模板关联的数据的数量
     */
    int countByBrandAndCategory(@Param("brandId") Long brandId, @Param("categoryId") Long categoryId);

    /**
     * 根据id获取品牌类别关联的标准信息
     *
     * @param id 品牌类别关联id
     * @return 返回匹配的品牌类别关联的标准信息，如果没有匹配的数据，将返回null
     */
    BrandCategoryStandardVO getStandardById(Long id);

}
