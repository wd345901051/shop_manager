package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.SpuDetail;
import cn.tedu.csmall.product.pojo.vo.SpuDetailStandardVO;
import org.springframework.stereotype.Repository;

/**
 * SPU详情Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface SpuDetailMapper {

    /**
     * 插入SPU详情数据
     *
     * @param spuDetail SPU详情数据
     * @return 受影响的行数
     */
    int insert(SpuDetail spuDetail);

    /**
     * 根据id删除SPU详情
     *
     * @param id 被删除的SPU详情的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据多个id批量删除SPU详情数据
     *
     * @param ids 期望删除的若干个SPU详情数据的id
     * @return 受影响的行数，将返回成功删除的数据量
     */
    int deleteByIds(Long... ids);

    /**
     * 根据id获取SPU详情的标准信息
     *
     * @param id SPU详情id
     * @return 返回匹配的SPU详情的标准信息，如果没有匹配的数据，将返回null
     */
    SpuDetailStandardVO getStandardById(Long id);

}
