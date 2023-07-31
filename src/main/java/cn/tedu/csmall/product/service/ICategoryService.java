package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.pojo.dto.CategoryAddNewDTO;
import cn.tedu.csmall.product.pojo.vo.CategoryListItemVO;
import cn.tedu.csmall.product.pojo.vo.CategoryStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 类别业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface ICategoryService {

    /**
     * 添加类别
     *
     * @param categoryAddNewDTO 添加的类别对象
     */
    void addNew(CategoryAddNewDTO categoryAddNewDTO);

    /**
     * 根据id删除类别
     *
     * @param id 被删除的类别的id
     */
    void deleteById(Long id);

    /**
     * 启用类别
     *
     * @param id 类别id
     */
    void setEnable(Long id);

    /**
     * 禁用类别
     *
     * @param id 类别id
     */
    void setDisable(Long id);


    /**
     * 开启类别显示在导航栏
     *
     * @param id 类别id
     */
    void setDisplay(Long id);

    /**
     * 禁止类别显示在导航栏
     *
     * @param id 类别id
     */
    void setHidden(Long id);

    /**
     * 根据id查询类别详情
     *
     * @param id 类别id
     * @return 匹配的类别详情，如果没有匹配的数据，将抛出异常
     */
    CategoryStandardVO getStandardById(Long id);

    /**
     * 根据父级类别的id查询类别列表
     *
     * @param parentId 父级类别的id
     * @return 类别列表
     */
    List<CategoryListItemVO> listByParentId(Long parentId);

}
