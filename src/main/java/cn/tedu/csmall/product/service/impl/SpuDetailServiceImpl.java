package cn.tedu.csmall.product.service.impl;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.mapper.SpuDetailMapper;
import cn.tedu.csmall.product.pojo.dto.SpuDetailAddNewDTO;
import cn.tedu.csmall.product.pojo.entity.SpuDetail;
import cn.tedu.csmall.product.service.ISpuDetailService;
import cn.tedu.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 处理SPU详情业务的实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Service
@Slf4j
public class SpuDetailServiceImpl implements ISpuDetailService {

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    public SpuDetailServiceImpl() {
        log.info("创建业务对象：SpuDetailServiceImpl");
    }

    @Override
    public void addNew(SpuDetailAddNewDTO spuDetailAddNewDTO) {
        log.debug("开始处理【添加SPU详情】的业务，参数：{}", spuDetailAddNewDTO);
        // 获取当前时间：LocalDateTime now = LocalDateTime.now()
        LocalDateTime now = LocalDateTime.now();
        // 创建对象
        SpuDetail spuDetail = new SpuDetail();
        // 复制属性
        BeanUtils.copyProperties(spuDetailAddNewDTO, spuDetail);
        // 补全各属性的值：gmtCreate / gmtModified
        spuDetail.setGmtCreate(now);
        spuDetail.setGmtModified(now);
        // 执行插入数据
        int rows = spuDetailMapper.insert(spuDetail);
        if (rows != 1) {
            String message = "添加SPU详情失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除SPU详情】的业务，参数：{}", id);
        // 检查尝试删除的SPU详情是否存在
        Object queryResult = spuDetailMapper.getStandardById(id);
        if (queryResult == null) {
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, "删除SPU详情失败，尝试访问的数据不存在！");
        }

        // 执行删除
        log.debug("即使删除id为{}的SPU详情……", id);
        int rows = spuDetailMapper.deleteById(id);
        if (rows != 1) {
            throw new ServiceException(ServiceCode.ERR_DELETE, "删除SPU详情失败，服务器忙，请稍后再次尝试！");
        }
        log.debug("删除完成！");
    }
}
