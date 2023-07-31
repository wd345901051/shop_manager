package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Spu;
import cn.tedu.csmall.product.pojo.vo.SpuStandardVO;
import org.springframework.stereotype.Repository;

/**
 * SPU（Standard Product Unit）Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface SpuMapper {

    /**
     * 插入SPU数据
     *
     * @param spu SPU数据
     * @return 受影响的行数
     */
    int insert(Spu spu);

    /**
     * 根据id删除SPU
     *
     * @param id 被删除的SPU的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据多个id批量删除SPU数据
     *
     * @param ids 期望删除的多个SPU数据的id
     * @return 受影响的行数，将返回成功删除的数据量
     */
    int deleteByIds(Long... ids);

    /**
     * 根据id获取SPU的标准信息
     *
     * @param id SPU id
     * @return 返回匹配的SPU的标准信息，如果没有匹配的数据，将返回null
     */
    SpuStandardVO getStandardById(Long id);

}
