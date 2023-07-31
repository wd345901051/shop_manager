package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.AlbumAddNewDTO;
import cn.tedu.csmall.product.pojo.vo.AlbumListItemVO;
import cn.tedu.csmall.product.pojo.vo.AlbumStandardVO;
import cn.tedu.csmall.product.security.LoginPrincipal;
import cn.tedu.csmall.product.service.IAlbumService;
import cn.tedu.csmall.product.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 处理相册相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Api(tags = "04. 相册管理模块")
@Slf4j
@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private IAlbumService albumService;

    public AlbumController() {
        log.info("创建控制器：AlbumController");
    }

    // 添加相册
    // http://localhost:9080/albums/add-new?name=XiaoMi&description=TestDescription&sort=69
    @ApiOperation("添加相册")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(@Validated AlbumAddNewDTO albumAddNewDTO) {
        log.debug("开始处理【添加相册】的请求：{}", albumAddNewDTO);
        albumService.addNew(albumAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9080/albums/9527/delete
    @ApiOperation("根据id删除相册")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "相册id", dataType = "long", required = true)
    })
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除属性】的请求：id={}", id);
        albumService.deleteById(id);
        return JsonResult.ok();
    }

    // http://localhost:9080/albums/1
    @ApiOperation("根据id查询相册详情")
    @ApiOperationSupport(order = 400)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "相册id", required = true, dataType = "long")
    })
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<AlbumStandardVO> getStandardById(@PathVariable Long id) {
        log.debug("开始处理【根据id查询相册详情】的请求");
        AlbumStandardVO album = albumService.getStandardById(id);
        return JsonResult.ok(album);
    }

    // http://localhost:9080/albums
    @ApiOperation("查询相册列表")
    @ApiOperationSupport(order = 401)
    @GetMapping("")
    public JsonResult<List<AlbumListItemVO>> list(
            @ApiIgnore @AuthenticationPrincipal LoginPrincipal loginPrincipal) {
        log.debug("开始处理【查询相册列表】的请求");
        log.debug("当前登录的用户（当事人）的id：{}", loginPrincipal.getId());
        log.debug("当前登录的用户（当事人）的用户名：{}", loginPrincipal.getUsername());
        List<AlbumListItemVO> albums = albumService.list();
        return JsonResult.ok(albums);
    }

    // http://localhost:9080/albums/huawei/delete
    @ApiOperation("根据名称删除相册（未实现）")
    @ApiOperationSupport(order = 900)
    @PostMapping("/{name:[a-zA-Z]+}/delete")
    public String delete(@PathVariable String name) {
        throw new RuntimeException("尝试处理【根据名称删除相册】的请求，但此请求仅作为测试使用，并未实现！");
    }

    // http://localhost:9080/albums/test/delete
    @ApiOperation("测试删除相册（未实现）")
    @ApiOperationSupport(order = 901)
    @PostMapping("/test/delete")
    public String delete() {
        throw new RuntimeException("尝试处理【测试删除相册】的请求，但此请求仅作为测试使用，并未实现！");
    }

}