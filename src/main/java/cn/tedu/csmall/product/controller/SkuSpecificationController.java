package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.SkuSpecificationAddNewDTO;
import cn.tedu.csmall.product.service.ISkuSpecificationService;
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
 * 处理SKU数据相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "12. SKU数据管理模块")
@Slf4j
@RestController
@RequestMapping("/sku-specification")
public class SkuSpecificationController {

    @Autowired
    private ISkuSpecificationService skuSpecificationService;

    public SkuSpecificationController() {
        log.info("创建控制器：SkuSpecificationController");
    }

    // http://localhost:9080/sku-specification/add-new
    @ApiOperation("添加SKU数据")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(SkuSpecificationAddNewDTO skuSpecificationAddNewDTO) {
        log.debug("开始处理【添加SKU数据】的请求：{}", skuSpecificationAddNewDTO);
        skuSpecificationService.addNew(skuSpecificationAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/sku-specification/9527/delete
    @ApiOperation("根据id删除SKU数据")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除SKU数据】的请求：id={}", id);
        skuSpecificationService.deleteById(id);
        return JsonResult.ok();
    }

}
