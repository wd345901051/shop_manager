package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.CategoryAddNewDTO;
import cn.tedu.csmall.product.pojo.vo.CategoryListItemVO;
import cn.tedu.csmall.product.pojo.vo.CategoryStandardVO;
import cn.tedu.csmall.product.service.ICategoryService;
import cn.tedu.csmall.product.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 处理类别相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "02. 类别管理模块")
@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    public CategoryController() {
        log.info("创建控制器：CategoryController");
    }

    // http://localhost:9080/categories/add-new
    @ApiOperation("添加类别")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(CategoryAddNewDTO categoryAddNewDTO) {
        log.debug("开始处理【添加类别】的请求：{}", categoryAddNewDTO);
        categoryService.addNew(categoryAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/categories/9527/delete
    @ApiOperation("根据id删除类别")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除类别】的请求：id={}", id);
        categoryService.deleteById(id);
        return JsonResult.ok();
    }

    // http://localhost:9081/categories/5/enable
    @ApiOperation("启用类别")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParam(name = "id", value = "类别id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/enable")
    public JsonResult<Void> setEnable(@PathVariable Long id) {
        log.debug("准备处理【启用类别】的请求：id={}", id);
        categoryService.setEnable(id);
        return JsonResult.ok();
    }

    // http://localhost:9081/categories/5/disable
    @ApiOperation("禁用类别")
    @ApiOperationSupport(order = 301)
    @ApiImplicitParam(name = "id", value = "类别id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/disable")
    public JsonResult<Void> setDisable(@PathVariable Long id) {
        log.debug("准备处理【禁用类别】的请求：id={}", id);
        categoryService.setDisable(id);
        return JsonResult.ok();
    }

    // http://localhost:9081/categories/5/display
    @ApiOperation("开启类别显示在导航栏")
    @ApiOperationSupport(order = 302)
    @ApiImplicitParam(name = "id", value = "类别id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/display")
    public JsonResult<Void> setDisplay(@PathVariable Long id) {
        log.debug("准备处理【开启类别显示在导航栏】的请求：id={}", id);
        categoryService.setDisplay(id);
        return JsonResult.ok();
    }

    // http://localhost:9081/categories/5/hidden
    @ApiOperation("禁止类别显示在导航栏")
    @ApiOperationSupport(order = 303)
    @ApiImplicitParam(name = "id", value = "类别id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/hidden")
    public JsonResult<Void> setHidden(@PathVariable Long id) {
        log.debug("准备处理【禁止类别显示在导航栏】的请求：id={}", id);
        categoryService.setHidden(id);
        return JsonResult.ok();
    }

    // http://localhost:9080/categories/1
    @ApiOperation("根据id查询类别详情")
    @ApiOperationSupport(order = 400)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别id", required = true, dataType = "long")
    })
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<CategoryStandardVO> getStandardById(@PathVariable Long id) {
        log.debug("开始处理【根据id查询类别详情】的请求");
        CategoryStandardVO category = categoryService.getStandardById(id);
        return JsonResult.ok(category);
    }

    // http://localhost:9080/categories/list-by-parent
    @ApiOperation("根据父级查询子级类别列表")
    @ApiOperationSupport(order = 401)
    @GetMapping("/list-by-parent")
    public JsonResult<List<CategoryListItemVO>> listByParentId(Long parentId) {
        log.debug("开始处理【查询类别列表】的请求");
        if (parentId == null || parentId < 1) {
            parentId = 0L;
        }
        List<CategoryListItemVO> list = categoryService.listByParentId(parentId);
        return JsonResult.ok(list);
    }

}
