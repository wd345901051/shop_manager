package cn.tedu.csmall.product.service.impl;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.mapper.BrandCategoryMapper;
import cn.tedu.csmall.product.mapper.CategoryAttributeTemplateMapper;
import cn.tedu.csmall.product.mapper.CategoryMapper;
import cn.tedu.csmall.product.pojo.dto.CategoryAddNewDTO;
import cn.tedu.csmall.product.pojo.entity.Category;
import cn.tedu.csmall.product.pojo.vo.CategoryListItemVO;
import cn.tedu.csmall.product.pojo.vo.CategoryStandardVO;
import cn.tedu.csmall.product.service.ICategoryService;
import cn.tedu.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 处理类别业务的实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandCategoryMapper brandCategoryMapper;
    @Autowired
    private CategoryAttributeTemplateMapper categoryAttributeTemplateMapper;

    public CategoryServiceImpl() {
        log.info("创建业务对象：CategoryServiceImpl");
    }

    @Override
    public void addNew(CategoryAddNewDTO categoryAddNewDTO) {
        log.debug("开始处理【添加类别】的业务，参数：{}", categoryAddNewDTO);
        // 调用Mapper对象的int countByName(String name)方法统计此名称的类别的数量
        String name = categoryAddNewDTO.getName();
        int countByName = categoryMapper.countByName(name);
        log.debug("尝试添加的类别名称是：{}，在数据库中此名称的类别数量为：{}", name, countByName);
        // 判断统计结果是否大于0
        if (countByName > 0) {
            // 是：类别名称已经存在，抛出RuntimeException异常
            String message = "添加类别失败！类别名称【" + name + "】已存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 如果parentId不为0，则需要检查父级类别是否存在
        Long parentId = categoryAddNewDTO.getParentId();
        CategoryStandardVO parentCategory = null;
        if (parentId != 0) {
            parentCategory = categoryMapper.getStandardById(parentId);
            if (parentCategory == null) {
                String message = "添加类别失败！父级类别不存在！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
            }
        }

        // 确定当前尝试添加的类别的“深度”：无父级=1，有父级=父级深度+1
        Integer depth = 1;
        depth += parentCategory == null ? 0 : parentCategory.getDepth();
        // 确定isParent属性的值：0
        Integer isParent = 0;

        // 创建对象
        Category category = new Category();
        // 复制属性
        BeanUtils.copyProperties(categoryAddNewDTO, category);
        // 补全相关属性值
        category.setDepth(depth);
        category.setIsParent(isParent);
        // 执行插入数据
        int rows = categoryMapper.insert(category);
        if (rows != 1) {
            String message = "添加类别失败！服务器忙，请稍后再次尝试！[错误代码：1]";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }

        // 如果当前添加的新类别存在父级，父级的isParent如果是0，则需要更新为1
        if (parentCategory != null && parentCategory.getIsParent() == 0) {
            Category updateParentCategory = new Category();
            updateParentCategory.setId(parentId);
            updateParentCategory.setIsParent(1);
            rows = categoryMapper.update(updateParentCategory);
            if (rows != 1) {
                String message = "添加类别失败！服务器忙，请稍后再次尝试！[错误代码：2]";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_UPDATE, message);
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除类别】的业务，参数：{}", id);
        // 检查尝试删除的类别是否存在
        CategoryStandardVO currentCategory = categoryMapper.getStandardById(id);
        if (currentCategory == null) {
            String message = "删除类别失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 判断当前尝试删除的类别是否包含子级：直接判断is_parent
        if (currentCategory.getIsParent() == 1) {
            String message = "删除类别失败！当前类别仍包含子级类别！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 如果此类别关联了品牌，则不允许删除
        {
            int count = brandCategoryMapper.countByCategory(id);
            if (count > 0) {
                String message = "删除类别失败，当前类别仍关联了品牌！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_DELETE, message);
            }
        }

        // 如果此类别关联了属性模板，则不允许删除
        {
            int count = categoryAttributeTemplateMapper.countByCategory(id);
            if (count > 0) {
                String message = "删除类别失败，当前类别仍关联了属性模板！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_DELETE, message);
            }
        }

        // 执行删除
        {
            log.debug("即使删除id为{}的类别……", id);
            int rows = categoryMapper.deleteById(id);
            if (rows != 1) {
                String message = "删除类别失败，服务器忙，请稍后再次尝试！[错误代码：1]";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_DELETE, message);
            }
        }

        // 如果被删除的类别的父级类别没有子级类别了，则父级类别isParent改为0
        if (currentCategory.getParentId() != 0) {
            int count = categoryMapper.countByParentId(currentCategory.getParentId());
            if (count == 0) {
                Category updateParentCategory = new Category();
                updateParentCategory.setId(currentCategory.getParentId());
                updateParentCategory.setIsParent(0);
                int rows = categoryMapper.update(updateParentCategory);
                if (rows != 1) {
                    String message = "删除类别失败，服务器忙，请稍后再次尝试！[错误代码：2]";
                    log.warn(message);
                    throw new ServiceException(ServiceCode.ERR_DELETE, message);
                }
            }
        }

        log.debug("删除完成！");
    }

    @Override
    public void setEnable(Long id) {
        log.debug("开始处理【启用类别】的业务：id={}", id);
        // 根据id查询类别数据
        CategoryStandardVO queryResult = categoryMapper.getStandardById(id);
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 是：ServiceException：NOT_FOUND
            String message = "启用类别失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 判断查询结果中的enable是否为1
        if (queryResult.getEnable() == 1) {
            // 是：ServiceException：CONFLICT
            String message = "启用类别失败！当前类别已经启用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 准备更新
        Category category = new Category();
        category.setId(id);
        category.setEnable(1);
        // 执行更新，获取返回值
        int rows = categoryMapper.update(category);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 是：ServiceException：UPDATE
            String message = "启用类别失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public void setDisable(Long id) {
        log.debug("开始处理【禁用类别】的业务：id={}", id);
        // 根据id查询类别数据
        CategoryStandardVO queryResult = categoryMapper.getStandardById(id);
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 是：ServiceException：NOT_FOUND
            String message = "禁用类别失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 判断查询结果中的enable是否为1
        if (queryResult.getEnable() == 0) {
            // 是：ServiceException：CONFLICT
            String message = "禁用类别失败！当前类别已经禁用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 准备更新
        Category category = new Category();
        category.setId(id);
        category.setEnable(0);
        // 执行更新，获取返回值
        int rows = categoryMapper.update(category);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 是：ServiceException：UPDATE
            String message = "禁用类别失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public void setDisplay(Long id) {
        log.debug("开始处理【开启类别显示在导航栏】的业务：id={}", id);
        // 根据id查询类别数据
        CategoryStandardVO queryResult = categoryMapper.getStandardById(id);
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 是：ServiceException：NOT_FOUND
            String message = "开启类别显示在导航栏失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 判断查询结果中的enable是否为1
        if (queryResult.getIsDisplay() == 1) {
            // 是：ServiceException：CONFLICT
            String message = "开启类别显示在导航栏失败！当前类别已经开启显示！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 准备更新
        Category category = new Category();
        category.setId(id);
        category.setIsDisplay(1);
        // 执行更新，获取返回值
        int rows = categoryMapper.update(category);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 是：ServiceException：UPDATE
            String message = "开启类别显示在导航栏失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public void setHidden(Long id) {
        log.debug("开始处理【禁止类别显示在导航栏】的业务：id={}", id);
        // 根据id查询类别数据
        CategoryStandardVO queryResult = categoryMapper.getStandardById(id);
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 是：ServiceException：NOT_FOUND
            String message = "开启类别显示在导航栏失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 判断查询结果中的enable是否为1
        if (queryResult.getIsDisplay() == 0) {
            // 是：ServiceException：CONFLICT
            String message = "禁止类别显示在导航栏失败！当前类别已经禁止显示！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 准备更新
        Category category = new Category();
        category.setId(id);
        category.setIsDisplay(0);
        // 执行更新，获取返回值
        int rows = categoryMapper.update(category);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 是：ServiceException：UPDATE
            String message = "禁止类别显示在导航栏失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public CategoryStandardVO getStandardById(Long id) {
        log.debug("开始处理【根据id查询类别详情】的业务");
        CategoryStandardVO category = categoryMapper.getStandardById(id);
        if (category == null) {
            String message = "查询类别详情失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        return category;
    }

    @Override
    public List<CategoryListItemVO> listByParentId(Long parentId) {
        log.debug("开始处理【根据父级类别的id查询类别列表】的业务");
        return categoryMapper.listByParentId(parentId);
    }
}
