package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.SkuSpecification;
import cn.tedu.csmall.product.pojo.vo.SkuSpecificationStandardVO;
import org.springframework.stereotype.Repository;

/**
 * SKU属性数据Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface SkuSpecificationMapper {

    /**
     * 插入SKU属性数据
     *
     * @param skuSpecification SKU属性数据
     * @return 受影响的行数
     */
    int insert(SkuSpecification skuSpecification);

    /**
     * 根据id删除SKU属性
     *
     * @param id 被删除的SKU属性的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据多个id批量删除SKU属性数据
     *
     * @param ids 期望删除的若干个SKU属性数据的id
     * @return 受影响的行数，将返回成功删除的数据量
     */
    int deleteByIds(Long... ids);

    /**
     * 根据id获取SKU属性的标准信息
     *
     * @param id SKU属性id
     * @return 返回匹配的SKU属性的标准信息，如果没有匹配的数据，将返回null
     */
    SkuSpecificationStandardVO getStandardById(Long id);

}
