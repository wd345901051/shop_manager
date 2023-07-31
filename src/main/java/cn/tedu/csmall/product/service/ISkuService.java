package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.pojo.dto.SkuAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * SKU（Stock Keeping Unit）业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface ISkuService {

    /**
     * 增加SKU信息
     *
     * @param skuAddNewDTO 新增的SKU对象
     */
    void addNew(SkuAddNewDTO skuAddNewDTO);

    /**
     * 删除SKU
     *
     * @param id SKU id
     */
    void deleteById(Long id);

}
