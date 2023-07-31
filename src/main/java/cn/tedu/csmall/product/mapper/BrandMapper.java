package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Brand;
import cn.tedu.csmall.product.pojo.vo.BrandListItemVO;
import cn.tedu.csmall.product.pojo.vo.BrandStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 品牌数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface BrandMapper {

    /**
     * 插入品牌数据
     *
     * @param brand 品牌数据
     * @return 受影响的行数
     */
    int insert(Brand brand);

    /**
     * 根据品牌id，删除品牌数据
     *
     * @param id 期望删除的品牌数据的id
     * @return 受影响的行数，当删除成功时，将返回1，如果无此id对应的数据，将返回0
     */
    int deleteById(Long id);

    /**
     * 根据多个id批量删除品牌
     *
     * @param ids 期望删除的多个品牌数据的id
     * @return 受影响的行数
     */
    int deleteByIds(Long... ids);

    /**
     * 根据id修改品牌数据详情
     *
     * @param brand 封装了id与新数据的品牌对象
     * @return 受影响的行数
     */
    int update(Brand brand);

    /**
     * 统计当前表中品牌数据的数量
     *
     * @return 当前表中品牌数据的数量
     */
    int count();

    /**
     * 根据品牌名称统计当前表中品牌数据的数量
     *
     * @param name 品牌名称
     * @return 当前表中匹配名称的品牌数据的数量
     */
    int countByName(String name);

    /**
     * 根据id获取品牌的标准信息
     *
     * @param id 品牌id
     * @return 返回匹配的品牌的标准信息，如果没有匹配的数据，将返回null
     */
    BrandStandardVO getStandardById(Long id);

    /**
     * 获取品牌列表
     *
     * @return 品牌列表数据，如果没有匹配的数据，则返回长度为0的集合
     */
    List<BrandListItemVO> list();

}
