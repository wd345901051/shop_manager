package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.SkuAddNewDTO;
import cn.tedu.csmall.product.service.ISkuService;
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
 * 处理SKU相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "11. SKU管理模块")
@Slf4j
@RestController
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private ISkuService skuService;

    public SkuController() {
        log.info("创建控制器：SkuController");
    }

    // http://localhost:9080/sku/add-new
    @ApiOperation("添加SKU")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(SkuAddNewDTO skuAddNewDTO) {
        log.debug("开始处理【添加SKU】的请求：{}", skuAddNewDTO);
        skuService.addNew(skuAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/sku/9527/delete
    @ApiOperation("根据id删除SKU")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除SKU】的请求：id={}", id);
        skuService.deleteById(id);
        return JsonResult.ok();
    }

}
