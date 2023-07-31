package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.AttributeTemplate;
import cn.tedu.csmall.product.pojo.vo.AttributeTemplateListItemVO;
import cn.tedu.csmall.product.pojo.vo.AttributeTemplateStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性模板Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface AttributeTemplateMapper {

    /**
     * 插入属性模板数据
     *
     * @param attributeTemplate 属性模板数据
     * @return 受影响的行数
     */
    int insert(AttributeTemplate attributeTemplate);

    /**
     * 根据id删除属性模板
     *
     * @param id 被删除的属性模板的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据多个id批量删除属性模板数据
     *
     * @param ids 期望删除的若干个属性模板数据的id
     * @return 受影响的行数，将返回成功删除的数据量
     */
    int deleteByIds(Long... ids);

    /**
     * 根据属性模板名称统计当前表中属性模板数据的数量
     *
     * @param name 属性模板名称
     * @return 当前表中匹配名称的属性模板数据的数量
     */
    int countByName(String name);

    /**
     * 根据id获取属性模板的标准信息
     *
     * @param id 属性模板id
     * @return 返回匹配的属性模板的标准信息，如果没有匹配的数据，将返回null
     */
    AttributeTemplateStandardVO getStandardById(Long id);

    /**
     * 查询属性模板列表
     *
     * @return 属性模板列表的集合
     */
    List<AttributeTemplateListItemVO> list();

}
