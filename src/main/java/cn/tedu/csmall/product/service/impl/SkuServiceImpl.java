package cn.tedu.csmall.product.service.impl;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.mapper.SkuMapper;
import cn.tedu.csmall.product.pojo.dto.SkuAddNewDTO;
import cn.tedu.csmall.product.pojo.entity.Picture;
import cn.tedu.csmall.product.pojo.entity.Sku;
import cn.tedu.csmall.product.service.ISkuService;
import cn.tedu.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 处理SKU业务的实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Service
@Slf4j
public class SkuServiceImpl implements ISkuService {

    @Autowired
    private SkuMapper skuMapper;

    public SkuServiceImpl() {
        log.info("创建业务对象：SkuServiceImpl");
    }

    @Override
    public void addNew(SkuAddNewDTO skuAddNewDTO) {
        log.debug("开始处理【添加SKU】的业务，参数：{}", skuAddNewDTO);
        // 获取当前时间：LocalDateTime now = LocalDateTime.now()
        LocalDateTime now = LocalDateTime.now();
        // 创建对象
        Sku sku = new Sku();
        // 复制属性
        BeanUtils.copyProperties(skuAddNewDTO, sku);
        // 补全各属性的值：gmtCreate / gmtModified
        sku.setGmtCreate(now);
        sku.setGmtModified(now);
        // 执行插入数据
        int rows = skuMapper.insert(sku);
        if (rows != 1) {
            String message = "添加SKU失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除SKU】的业务，参数：{}", id);
        // 检查尝试删除的SKU是否存在
        Object queryResult = skuMapper.getStandardById(id);
        if (queryResult == null) {
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, "删除SKU失败，尝试访问的数据不存在！");
        }

        // 执行删除
        log.debug("即使删除id为{}的SKU……", id);
        int rows = skuMapper.deleteById(id);
        if (rows != 1) {
            throw new ServiceException(ServiceCode.ERR_DELETE, "删除SKU失败，服务器忙，请稍后再次尝试！");
        }
        log.debug("删除完成！");
    }

}
