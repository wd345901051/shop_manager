package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.BrandAddNewDTO;
import cn.tedu.csmall.product.pojo.vo.BrandListItemVO;
import cn.tedu.csmall.product.pojo.vo.BrandStandardVO;
import cn.tedu.csmall.product.service.IBrandService;
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
 * 处理品牌相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "01. 品牌管理模块")
@Slf4j
@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    public BrandController() {
        log.info("创建控制器：BrandController");
    }

    // http://localhost:9080/brands/add-new
    @ApiOperation("添加品牌")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(BrandAddNewDTO brandAddNewDTO) {
        log.debug("开始处理【添加品牌】的请求：{}", brandAddNewDTO);
        brandService.addNew(brandAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/brands/9527/delete
    @ApiOperation("根据id删除品牌")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除品牌】的请求：id={}", id);
        brandService.deleteById(id);
        return JsonResult.ok();
    }

    // http://localhost:9080/brands/1
    @ApiOperation("根据id查询品牌详情")
    @ApiOperationSupport(order = 400)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "品牌id", required = true, dataType = "long")
    })
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<BrandStandardVO> getStandardById(@PathVariable Long id) {
        log.debug("开始处理【根据id查询品牌详情】的请求");
        BrandStandardVO brand = brandService.getStandardById(id);
        return JsonResult.ok(brand);
    }

    // http://localhost:9080/brands
    @ApiOperation("查询品牌列表")
    @ApiOperationSupport(order = 401)
    @GetMapping("")
    public JsonResult<List<BrandListItemVO>> list() {
        log.debug("开始处理【查询品牌列表】的请求");
        List<BrandListItemVO> brands = brandService.list();
        return JsonResult.ok(brands);
    }

}
