package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.pojo.dto.SpuDetailAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * SPU详情业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface ISpuDetailService {

    /**
     * 添加SPU详情
     *
     * @param spuDetailAddNewDTO 添加的SPU详情对象
     */
    void addNew(SpuDetailAddNewDTO spuDetailAddNewDTO);

    /**
     * 删除SPU详情
     *
     * @param id 被删除的SPU详情的id
     */
    void deleteById(Long id);

}
