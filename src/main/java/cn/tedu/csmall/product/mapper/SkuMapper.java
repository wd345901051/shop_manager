package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Sku;
import cn.tedu.csmall.product.pojo.vo.SkuStandardVO;
import org.springframework.stereotype.Repository;

/**
 * SKU（Stock Keeping Unit）Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface SkuMapper {

    /**
     * 插入SKU数据
     *
     * @param sku SKU数据
     * @return 受影响的行数
     */
    int insert(Sku sku);

    /**
     * 根据id删除SKU
     *
     * @param id 被删除的SKU的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据多个id批量删除SKU数据
     *
     * @param ids 期望删除的若干个SKU数据的id
     * @return 受影响的行数，将返回成功删除的数据量
     */
    int deleteByIds(Long... ids);

    /**
     * 根据id获取SKU的标准信息
     *
     * @param id SKU id
     * @return 返回匹配的SKU的标准信息，如果没有匹配的数据，将返回null
     */
    SkuStandardVO getStandardById(Long id);

}
