package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.pojo.dto.AttributeAddNewDTO;
import cn.tedu.csmall.product.pojo.vo.AttributeListItemVO;
import cn.tedu.csmall.product.pojo.vo.AttributeStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 属性业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface IAttributeService {

    /**
     * 添加属性
     *
     * @param attributeAddNewDTO 添加的商性对象
     */
    void addNew(AttributeAddNewDTO attributeAddNewDTO);

    /**
     * 删除商品属性
     *
     * @param id 被删除的商品属性的id
     */
    void deleteById(Long id);

    /**
     * 根据id查询属性详情
     *
     * @param id 属性id
     * @return 匹配的属性详情，如果没有匹配的数据，将抛出异常
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
