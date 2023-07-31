package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.SpuAddNewDTO;
import cn.tedu.csmall.product.service.ISpuService;
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
 * 处理SPU相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "09. SPU管理模块")
@Slf4j
@RestController
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private ISpuService spuService;

    public SpuController() {
        log.info("创建控制器：SpuController");
    }

    // http://localhost:9080/spu/add-new
    @ApiOperation("添加SPU")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(SpuAddNewDTO spuAddNewDTO) {
        log.debug("开始处理【添加SPU】的请求：{}", spuAddNewDTO);
        spuService.addNew(spuAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/spu/9527/delete
    @ApiOperation("根据id删除SPU")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除SPU】的请求：id={}", id);
        spuService.deleteById(id);
        return JsonResult.ok();
    }

}
