package cn.tedu.csmall.product.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
public class BrandListItemVO implements Serializable {

    /**
     * 记录id
     */
    private Long id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌logo的URL
     */
    private String logo;

}