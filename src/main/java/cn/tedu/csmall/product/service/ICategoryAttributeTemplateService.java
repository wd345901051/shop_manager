package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.pojo.dto.CategoryAttributeTemplateAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类别与属性模板的关联业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface ICategoryAttributeTemplateService {

    /**
     * 添加类别与属性模板的关联
     *
     * @param categoryAttributeTemplateAddNewDTO 添加的类别与属性模板的关联对象
     */
    void addNew(CategoryAttributeTemplateAddNewDTO categoryAttributeTemplateAddNewDTO);

    /**
     * 删除类别与属性模板的关联
     *
     * @param id 被删除的类别与属性模板的关联的id
     */
    void deleteById(Long id);

}
