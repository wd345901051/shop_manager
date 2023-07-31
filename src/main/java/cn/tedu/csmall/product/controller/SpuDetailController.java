package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.SpuDetailAddNewDTO;
import cn.tedu.csmall.product.service.ISpuDetailService;
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
 * 处理SPU详情相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "10. SPU详情管理模块")
@Slf4j
@RestController
@RequestMapping("/spu-detail")
public class SpuDetailController {

    @Autowired
    private ISpuDetailService spuDetailService;

    public SpuDetailController() {
        log.info("创建控制器：SpuDetailController");
    }

    // http://localhost:9080/spu-detail/add-new
    @ApiOperation("添加SPU详情")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(SpuDetailAddNewDTO spuDetailAddNewDTO) {
        log.debug("开始处理【添加SPU详情】的请求：{}", spuDetailAddNewDTO);
        spuDetailService.addNew(spuDetailAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/spu-detail/9527/delete
    @ApiOperation("根据id删除SPU详情")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除SPU详情】的请求：id={}", id);
        spuDetailService.deleteById(id);
        return JsonResult.ok();
    }

}
