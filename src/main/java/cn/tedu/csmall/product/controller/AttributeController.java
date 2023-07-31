package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.AttributeAddNewDTO;
import cn.tedu.csmall.product.pojo.vo.AttributeListItemVO;
import cn.tedu.csmall.product.pojo.vo.AttributeStandardVO;
import cn.tedu.csmall.product.service.IAttributeService;
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
 * 处理属性相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "07. 属性管理模块")
@Slf4j
@RestController
@RequestMapping("/attributes")
public class AttributeController {

    @Autowired
    private IAttributeService attributeService;

    public AttributeController() {
        log.info("创建控制器：AttributeController");
    }

    // http://localhost:9080/attributes/add-new
    @ApiOperation("添加属性")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(AttributeAddNewDTO attributeAddNewDTO) {
        log.debug("开始处理【添加属性】的请求：{}", attributeAddNewDTO);
        attributeService.addNew(attributeAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/attributes/9527/delete
    @ApiOperation("根据id删除属性")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除属性】的请求：id={}", id);
        attributeService.deleteById(id);
        return JsonResult.ok();
    }

    // http://localhost:9080/attributes/1
    @ApiOperation("根据id查询属性详情")
    @ApiOperationSupport(order = 400)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性id", required = true, dataType = "long")
    })
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<AttributeStandardVO> getStandardById(@PathVariable Long id) {
        log.debug("开始处理【根据id查询属性详情】的请求");
        AttributeStandardVO attribute = attributeService.getStandardById(id);
        return JsonResult.ok(attribute);
    }

    // http://localhost:9080/attributes/list-by-template
    @ApiOperation("根据属性模板id查询属性列表")
    @ApiOperationSupport(order = 401)
    @GetMapping("/list-by-template")
    public JsonResult<List<AttributeListItemVO>> listByParentId(Long templateId) {
        log.debug("开始处理【根据属性模板id查询属性列表】的请求");
        List<AttributeListItemVO> list = attributeService.listByTemplateId(templateId);
        return JsonResult.ok(list);
    }

}
