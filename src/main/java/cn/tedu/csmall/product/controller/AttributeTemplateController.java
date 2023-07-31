package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.AttributeTemplateAddNewDTO;
import cn.tedu.csmall.product.pojo.vo.AttributeTemplateListItemVO;
import cn.tedu.csmall.product.pojo.vo.AttributeTemplateStandardVO;
import cn.tedu.csmall.product.service.IAttributeTemplateService;
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
 * 处理属性模板相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "06. 属性模板管理模块")
@Slf4j
@RestController
@RequestMapping("/attribute-template")
public class AttributeTemplateController {

    @Autowired
    private IAttributeTemplateService attributeTemplateService;

    public AttributeTemplateController() {
        log.info("创建控制器：AttributeTemplateController");
    }

    // http://localhost:9080/attribute-template/add-new
    @ApiOperation("添加属性模板")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(AttributeTemplateAddNewDTO attributeTemplateAddNewDTO) {
        log.debug("开始处理【添加属性模板】的请求：{}", attributeTemplateAddNewDTO);
        attributeTemplateService.addNew(attributeTemplateAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/attribute-template/9527/delete
    @ApiOperation("根据id删除属性模板")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除属性模板】的请求：id={}", id);
        attributeTemplateService.deleteById(id);
        return JsonResult.ok();
    }

    // http://localhost:9080/attributes-template/1
    @ApiOperation("根据id查询属性模板详情")
    @ApiOperationSupport(order = 400)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性模板id", required = true, dataType = "long")
    })
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<AttributeTemplateStandardVO> getStandardById(@PathVariable Long id) {
        log.debug("开始处理【根据id查询属性模板详情】的请求");
        AttributeTemplateStandardVO attributeTemplate = attributeTemplateService.getStandardById(id);
        return JsonResult.ok(attributeTemplate);
    }

    // http://localhost:9080/attributes-template
    @ApiOperation("查询属性模板列表")
    @ApiOperationSupport(order = 401)
    @GetMapping("")
    public JsonResult<List<AttributeTemplateListItemVO>> list() {
        log.debug("开始处理【查询属性模板列表】的请求");
        List<AttributeTemplateListItemVO> attributes = attributeTemplateService.list();
        return JsonResult.ok(attributes);
    }

}
