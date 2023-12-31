package cn.tedu.csmall.product.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SpuAddNewDTO implements Serializable {

    /**
     * SPU名称
     */
    private String name;

    /**
     * SPU编号
     */
    private String typeNumber;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String description;

    /**
     * 价格（显示在列表中）
     */
    private BigDecimal listPrice;

    /**
     * 当前库存（冗余）
     */
    private Integer stock;

    /**
     * 库存预警阈值（冗余）
     */
    private Integer stockThreshold;

    /**
     * 计件单位
     */
    private String unit;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 类别id
     */
    private Long categoryId;

    /**
     * 属性模板id
     */
    private Long attributeTemplateId;

    /**
     * 相册id
     */
    private Long albumId;

    /**
     * 组图URLs，使⽤JSON格式表示
     */
    private String pictures;

    /**
     * 关键词列表，各关键词使⽤英⽂的逗号分隔
     */
    private String keywords;

    /**
     * 标签列表，各标签使⽤英⽂的逗号分隔，原则上最多3个
     */
    private String tags;

    /**
     * ⾃定义排序序号
     */
    private Integer sort;

    /**
     * 详情
     */
    private String detail;

}
