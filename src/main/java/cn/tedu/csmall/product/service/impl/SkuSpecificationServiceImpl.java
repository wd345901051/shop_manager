package cn.tedu.csmall.product.service.impl;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.mapper.SkuSpecificationMapper;
import cn.tedu.csmall.product.pojo.dto.SkuSpecificationAddNewDTO;
import cn.tedu.csmall.product.pojo.entity.SkuSpecification;
import cn.tedu.csmall.product.service.ISkuSpecificationService;
import cn.tedu.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 处理SKU数据业务的实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Service
@Slf4j
public class SkuSpecificationServiceImpl implements ISkuSpecificationService {

    @Autowired
    private SkuSpecificationMapper skuSpecificationMapper;

    public SkuSpecificationServiceImpl() {
        log.info("创建业务对象：SkuSpecificationServiceImpl");
    }

    @Override
    public void addNew(SkuSpecificationAddNewDTO skuSpecificationAddNewDTO) {
        log.debug("开始处理【添加SKU数据】的业务，参数：{}", skuSpecificationAddNewDTO);
        // 获取当前时间：LocalDateTime now = LocalDateTime.now()
        LocalDateTime now = LocalDateTime.now();
        // 创建对象
        SkuSpecification skuSpecification = new SkuSpecification();
        // 复制属性
        BeanUtils.copyProperties(skuSpecificationAddNewDTO, skuSpecification);
        // 补全各属性的值：gmtCreate / gmtModified
        skuSpecification.setGmtCreate(now);
        skuSpecification.setGmtModified(now);
        // 执行插入数据
        int rows = skuSpecificationMapper.insert(skuSpecification);
        if (rows != 1) {
            String message = "添加SKU数据失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除SKU数据】的业务，参数：{}", id);
        // 检查尝试删除的SKU数据是否存在
        Object queryResult = skuSpecificationMapper.getStandardById(id);
        if (queryResult == null) {
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, "删除SKU数据失败，尝试访问的数据不存在！");
        }

        // 执行删除
        log.debug("即使删除id为{}的SKU数据……", id);
        int rows = skuSpecificationMapper.deleteById(id);
        if (rows != 1) {
            throw new ServiceException(ServiceCode.ERR_DELETE, "删除SKU数据失败，服务器忙，请稍后再次尝试！");
        }
        log.debug("删除完成！");
    }
}
