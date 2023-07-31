package cn.tedu.csmall.product.service.impl;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.mapper.PictureMapper;
import cn.tedu.csmall.product.pojo.dto.PictureAddNewDTO;
import cn.tedu.csmall.product.pojo.entity.Attribute;
import cn.tedu.csmall.product.pojo.entity.Picture;
import cn.tedu.csmall.product.service.IPictureService;
import cn.tedu.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 处理图片业务的实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Service
@Slf4j
public class PictureServiceImpl implements IPictureService {

    @Autowired
    private PictureMapper pictureMapper;

    public PictureServiceImpl() {
        log.info("创建业务对象：PictureServiceImpl");
    }

    @Override
    public void addNew(PictureAddNewDTO pictureAddNewDTO) {
        log.debug("开始处理【添加图片】的业务，参数：{}", pictureAddNewDTO);
        // 获取当前时间：LocalDateTime now = LocalDateTime.now()
        LocalDateTime now = LocalDateTime.now();
        // 创建对象
        Picture picture = new Picture();
        // 复制属性
        BeanUtils.copyProperties(pictureAddNewDTO, picture);
        // 补全各属性的值：gmtCreate / gmtModified
        picture.setGmtCreate(now);
        picture.setGmtModified(now);
        // 执行插入数据
        int rows = pictureMapper.insert(picture);
        if (rows != 1) {
            String message = "添加图片失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除图片】的业务，参数：{}", id);
        // 检查尝试删除的图片是否存在
        Object queryResult = pictureMapper.getStandardById(id);
        if (queryResult == null) {
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, "删除图片失败，尝试访问的数据不存在！");
        }

        // 执行删除
        log.debug("即使删除id为{}的图片……", id);
        int rows = pictureMapper.deleteById(id);
        if (rows != 1) {
            throw new ServiceException(ServiceCode.ERR_DELETE, "删除图片失败，服务器忙，请稍后再次尝试！");
        }
        log.debug("删除完成！");
    }
}
