package cn.tedu.csmall.product.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌类别关联
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
public class BrandCategoryAddNewDTO implements Serializable {

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 类别id
     */
    private Long categoryId;

}
