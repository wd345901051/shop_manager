package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.pojo.dto.SpuAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * SPU（Standard Product Unit）业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface ISpuService {

    /**
     * 增加SPU
     *
     * @param spuAddNewDTO 新增的SPU对象
     */
    void addNew(SpuAddNewDTO spuAddNewDTO);

    /**
     * 删除SPU
     *
     * @param id SPU id
     */
    void deleteById(Long id);
}
