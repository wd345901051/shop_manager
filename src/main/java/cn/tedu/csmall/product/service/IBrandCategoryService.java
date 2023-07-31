package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.pojo.dto.BrandCategoryAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 品牌类别关联数据的业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface IBrandCategoryService {

    /**
     * 添加属性与类别的绑定
     *
     * @param brandCategoryAddNewDTO 属性与类别的绑定
     */
    void addNew(BrandCategoryAddNewDTO brandCategoryAddNewDTO);

    /**
     * 删除属性与类别的绑定
     *
     * @param id 属性与类别的绑定的数据id
     */
    void deleteById(Long id);

}
