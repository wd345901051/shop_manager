package cn.tedu.csmall.product.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 类别与属性模板关联
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
public class CategoryAttributeTemplateAddNewDTO implements Serializable {

    /**
     * 记录id
     */
    private Long id;

    /**
     * 类别id
     */
    private Long categoryId;

    /**
     * 属性模板id
     */
    private Long attributeTemplateId;

}