package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.CategoryAttributeTemplateAddNewDTO;
import cn.tedu.csmall.product.service.ICategoryAttributeTemplateService;
import cn.tedu.csmall.product.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理类别与属性模版的关联相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "08. 类别与属性模版的关联管理模块")
@Slf4j
@RestController
@RequestMapping("/category-attribute-template")
public class CategoryAttributeTemplateController {

    @Autowired
    private ICategoryAttributeTemplateService categoryAttributeTemplateService;

    public CategoryAttributeTemplateController() {
        log.info("创建控制器：CategoryAttributeTemplateController");
    }

    // http://localhost:9080/category-attribute-template/add-new
    @ApiOperation("添加类别与属性模版的关联")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(CategoryAttributeTemplateAddNewDTO categoryAttributeTemplateAddNewDTO) {
        log.debug("开始处理【添加类别与属性模版的关联】的请求：{}", categoryAttributeTemplateAddNewDTO);
        categoryAttributeTemplateService.addNew(categoryAttributeTemplateAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/category-attribute-template/9527/delete
    @ApiOperation("根据id删除类别与属性模版的关联")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除类别与属性模版的关联】的请求：id={}", id);
        categoryAttributeTemplateService.deleteById(id);
        return JsonResult.ok();
    }

}
