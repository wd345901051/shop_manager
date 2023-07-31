package cn.tedu.csmall.product.service.impl;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.mapper.*;
import cn.tedu.csmall.product.pojo.dto.SpuAddNewDTO;
import cn.tedu.csmall.product.pojo.entity.Spu;
import cn.tedu.csmall.product.pojo.entity.SpuDetail;
import cn.tedu.csmall.product.pojo.vo.AlbumStandardVO;
import cn.tedu.csmall.product.pojo.vo.BrandStandardVO;
import cn.tedu.csmall.product.pojo.vo.CategoryStandardVO;
import cn.tedu.csmall.product.service.ISpuService;
import cn.tedu.csmall.product.util.IdUtils;
import cn.tedu.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 处理SPU业务的实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Service
@Slf4j
public class SpuServiceImpl implements ISpuService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private AlbumMapper albumMapper;

    public SpuServiceImpl() {
        log.info("创建业务对象：SpuServiceImpl");
    }

    @Override
    public void addNew(SpuAddNewDTO spuAddNewDTO) {
        log.debug("开始处理【添加SKU】的业务，参数：{}", spuAddNewDTO);

        // 检查类别是否存在
        CategoryStandardVO category = categoryMapper.getStandardById(spuAddNewDTO.getCategoryId());
        if (category == null) {
            String message = "新增SPU失败！选择的类别不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 检查类别是否不包含子级类别
        if (category.getIsParent() == 1) {
            String message = "新增SPU失败！选择的类别不允许包含子级类别！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_BAD_REQUEST, message);
        }

        // 检查品牌是否存在
        BrandStandardVO brand = brandMapper.getStandardById(spuAddNewDTO.getBrandId());
        if (brand == null) {
            String message = "新增SPU失败！选择的品牌不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 检查相册是否存在
        AlbumStandardVO album = albumMapper.getStandardById(spuAddNewDTO.getAlbumId());
        if (album == null) {
            String message = "新增SPU失败！选择的相册不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 准备id
        Long id = IdUtils.getId();

        // 创建对象
        Spu spu = new Spu();
        // 复制属性
        BeanUtils.copyProperties(spuAddNewDTO, spu);
        // 【重要】补全id
        spu.setId(id);
        // 补全/调整属性
        spu.setCategoryName(category.getName());
        spu.setBrandName(brand.getName());
        spu.setSales(0);
        spu.setCommentCount(0);
        spu.setPositiveCommentCount(0);
        spu.setIsDeleted(0);
        spu.setIsPublished(0);
        spu.setIsNewArrival(0);
        spu.setIsRecommend(0);
        spu.setIsChecked(0);
        // 执行插入SPU数据
        int rows = spuMapper.insert(spu);
        if (rows != 1) {
            String message = "添加SKU失败！服务器忙，请稍后再次尝试！[错误代码：1]";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }

        // 执行插入SPU详情数据
        SpuDetail spuDetail = new SpuDetail();
        spuDetail.setSpuId(id);
        spuDetail.setDetail(spuAddNewDTO.getDetail());
        rows = spuDetailMapper.insert(spuDetail);
        if (rows != 1) {
            String message = "添加SKU失败！服务器忙，请稍后再次尝试！[错误代码：2]";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除SPU】的业务，参数：{}", id);
        // 检查尝试删除的SPU是否存在
        Object queryResult = spuMapper.getStandardById(id);
        if (queryResult == null) {
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, "删除SPU失败，尝试访问的数据不存在！");
        }

        // 执行删除
        log.debug("即使删除id为{}的SPU……", id);
        int rows = spuMapper.deleteById(id);
        if (rows != 1) {
            throw new ServiceException(ServiceCode.ERR_DELETE, "删除SPU失败，服务器忙，请稍后再次尝试！");
        }
        log.debug("删除完成！");
    }

}
