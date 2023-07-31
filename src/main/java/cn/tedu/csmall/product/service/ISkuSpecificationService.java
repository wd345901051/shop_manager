package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.pojo.dto.SkuSpecificationAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * SKU详情业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface ISkuSpecificationService {

    /**
     * 添加SKU详情
     *
     * @param skuSpecificationAddNewDTO 添加的SKU详情对象
     */
    void addNew(SkuSpecificationAddNewDTO skuSpecificationAddNewDTO);

    /**
     * 删除SKU详情
     *
     * @param id 被删除的SKU详情的id
     */
    void deleteById(Long id);

}
