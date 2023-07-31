package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Attribute;
import cn.tedu.csmall.product.pojo.vo.AttributeListItemVO;
import cn.tedu.csmall.product.pojo.vo.AttributeStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface AttributeMapper {

    /**
     * 插入属性数据
     *
     * @param attribute 属性数据
     * @return 受影响的行数
     */
    int insert(Attribute attribute);

    /**
     * 根据id删除属性
     *
     * @param id 被删除的属性的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据多个id批量删除属性数据
     *
     * @param ids 期望删除的若干个属性数据的id
     * @return 受影响的行数，将返回成功删除的数据量
     */
    int deleteByIds(Long... ids);

    /**
     * 根据属性名称和属性模板统计当前表中属性数据的数量
     *
     * @param name       属性名称
     * @param templateId 属性模板id
     * @return 当前表中匹配名称的属性数据的数量
     */
    int countByNameAndTemplate(@Param("name") String name, @Param("templateId") Long templateId);

    /**
     * 根据属性模板统计属性数据的数量
     *
     * @param templateId 属性模板id
     * @return 此属性模板中属性数据的数量
     */
    int countByTemplateId(Long templateId);

    /**
     * 根据id获取属性的标准信息
     *
     * @param id 属性id
     * @return 返回匹配的属性的标准信息，如果没有匹配的数据，将返回null
     */
    AttributeStandardVO getStandardById(Long id);

    /**
     * 根据属性模板id查询属性列表
     *
     * @param templateId 属性模板id
     * @return 属性列表的集合
     */
    List<AttributeListItemVO> listByTemplateId(Long templateId);

}