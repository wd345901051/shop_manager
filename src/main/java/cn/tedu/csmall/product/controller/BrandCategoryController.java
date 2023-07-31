package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.BrandCategoryAddNewDTO;
import cn.tedu.csmall.product.service.IBrandCategoryService;
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
 * 处理品牌与类别关联相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "03. 品牌与类别关联管理模块")
@Slf4j
@RestController
@RequestMapping("/brand-category")
public class BrandCategoryController {

    @Autowired
    private IBrandCategoryService brandCategoryService;

    public BrandCategoryController() {
        log.info("创建控制器：BrandCategoryController");
    }

    // http://localhost:9080/brand-category/add-new
    @ApiOperation("添加品牌与类别关联")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(BrandCategoryAddNewDTO brandCategoryAddNewDTO) {
        log.debug("开始处理【添加品牌与类别关联】的请求：{}", brandCategoryAddNewDTO);
        brandCategoryService.addNew(brandCategoryAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/brand-category/9527/delete
    @ApiOperation("根据id删除品牌与类别关联")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除品牌与类别关联】的请求：id={}", id);
        brandCategoryService.deleteById(id);
        return JsonResult.ok();
    }

}
