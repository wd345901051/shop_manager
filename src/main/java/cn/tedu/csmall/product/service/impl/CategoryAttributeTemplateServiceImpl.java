package cn.tedu.csmall.product.service.impl;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.mapper.AttributeTemplateMapper;
import cn.tedu.csmall.product.mapper.CategoryAttributeTemplateMapper;
import cn.tedu.csmall.product.mapper.CategoryMapper;
import cn.tedu.csmall.product.pojo.dto.CategoryAttributeTemplateAddNewDTO;
import cn.tedu.csmall.product.pojo.entity.CategoryAttributeTemplate;
import cn.tedu.csmall.product.service.ICategoryAttributeTemplateService;
import cn.tedu.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理类别与属性模板的关联业务的实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Service
@Slf4j
public class CategoryAttributeTemplateServiceImpl implements ICategoryAttributeTemplateService {

    @Autowired
    private CategoryAttributeTemplateMapper categoryAttributeTemplateMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private AttributeTemplateMapper attributeTemplateMapper;

    public CategoryAttributeTemplateServiceImpl() {
        log.info("创建业务对象：CategoryAttributeTemplateServiceImpl");
    }

    @Override
    public void addNew(CategoryAttributeTemplateAddNewDTO categoryAttributeTemplateAddNewDTO) {
        log.debug("开始处理【添加类别与属性模板的关联】的业务，参数：{}", categoryAttributeTemplateAddNewDTO);
        Long categoryId = categoryAttributeTemplateAddNewDTO.getCategoryId();
        Long attributeTemplateId = categoryAttributeTemplateAddNewDTO.getAttributeTemplateId();

        // 检查类别是否存在
        {
            Object queryResult = categoryMapper.getStandardById(categoryId);
            if (queryResult == null) {
                String message = "添加类别与属性模板的关联失败！类别不存在！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
            }
        }

        // 检查属性模板是否存在
        {
            Object queryResult = attributeTemplateMapper.getStandardById(attributeTemplateId);
            if (queryResult == null) {
                String message = "添加类别与属性模板的关联失败！属性模板不存在！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
            }
        }

        // 检查关联是否已经存在
        {
            int count = categoryAttributeTemplateMapper.countByCategoryAndAttributeTemplate(categoryId, attributeTemplateId);
            if (count != 0) {
                String message = "添加类别与属性模板的关联失败！此关联关系已经存在！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        // 创建对象
        CategoryAttributeTemplate categoryAttributeTemplate = new CategoryAttributeTemplate();
        // 复制属性
        BeanUtils.copyProperties(categoryAttributeTemplateAddNewDTO, categoryAttributeTemplate);
        // 执行插入数据
        int rows = categoryAttributeTemplateMapper.insert(categoryAttributeTemplate);
        if (rows != 1) {
            String message = "添加类别与属性模板的关联失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除类别与属性模板的关联】的业务，参数：{}", id);
        // 检查尝试删除的类别与属性模板的关联是否存在
        Object queryResult = categoryAttributeTemplateMapper.getStandardById(id);
        if (queryResult == null) {
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, "删除类别与属性模板的关联失败，尝试访问的数据不存在！");
        }

        // 执行删除
        log.debug("即使删除id为{}的类别与属性模板的关联……", id);
        int rows = categoryAttributeTemplateMapper.deleteById(id);
        if (rows != 1) {
            throw new ServiceException(ServiceCode.ERR_DELETE, "删除类别与属性模板的关联失败，服务器忙，请稍后再次尝试！");
        }
        log.debug("删除完成！");
    }

}
