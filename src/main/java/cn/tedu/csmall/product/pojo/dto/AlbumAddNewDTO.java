package cn.tedu.csmall.product.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 相册
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
public class AlbumAddNewDTO implements Serializable {

    /**
     * 相册名称
     */
    @ApiModelProperty(value = "相册名称", example = "小米80的相册", required = true)
    @NotNull(message = "必须提交相册名称！")
    private String name;

    /**
     * 相册简介
     */
    @ApiModelProperty(value = "相册简介", example = "小米80的相册的简介", required = true)
    @NotNull(message = "必须提交相册简介！")
    private String description;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", example = "88", required = true)
    @NotNull(message = "必须提交自定义排序序号！")
    @Range(max = 99, message = "自定义排序序号必须是0~99之间的值！")
    private Integer sort;

}