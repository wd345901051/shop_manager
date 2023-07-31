package cn.tedu.csmall.product.service.impl;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.mapper.BrandCategoryMapper;
import cn.tedu.csmall.product.mapper.BrandMapper;
import cn.tedu.csmall.product.pojo.dto.BrandAddNewDTO;
import cn.tedu.csmall.product.pojo.entity.Brand;
import cn.tedu.csmall.product.pojo.vo.BrandListItemVO;
import cn.tedu.csmall.product.pojo.vo.BrandStandardVO;
import cn.tedu.csmall.product.repository.IBrandRedisRepository;
import cn.tedu.csmall.product.service.IBrandService;
import cn.tedu.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 处理品牌业务的实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private BrandCategoryMapper brandCategoryMapper;
    @Autowired
    private IBrandRedisRepository brandRedisRepository;

    public BrandServiceImpl() {
        log.info("创建业务对象：BrandServiceImpl");
    }

    @Override
    public void addNew(BrandAddNewDTO brandAddNewDTO) {
        log.debug("开始处理【添加品牌】的业务，参数：{}", brandAddNewDTO);
        // 调用BrandMapper对象的int countByName(String name)方法统计此名称的品牌的数量
        String name = brandAddNewDTO.getName();
        int countByName = brandMapper.countByName(name);
        log.debug("尝试添加的品牌名称是：{}，在数据库中此名称的品牌数量为：{}", name, countByName);
        // 判断统计结果是否大于0
        if (countByName > 0) {
            // 是：品牌名称已经存在，抛出RuntimeException异常
            String message = "添加品牌失败！品牌名称【" + name + "】已存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 获取当前时间：LocalDateTime now = LocalDateTime.now()
        LocalDateTime now = LocalDateTime.now();
        // 创建Brand对象
        Brand brand = new Brand();
        // 补全Brand对象中各属性的值：来自DTO参数
        BeanUtils.copyProperties(brandAddNewDTO, brand);
        // 补全Brand对象中各属性的值：sales / productCount / commentCount / positiveCommentCount
        brand.setSales(0);
        brand.setProductCount(0);
        brand.setCommentCount(0);
        brand.setPositiveCommentCount(0);
        // 补全各属性的值：gmtCreate / gmtModified
        brand.setGmtCreate(now);
        brand.setGmtModified(now);
        // 调用BrandMapper对象的int insert(brand brand)方法插入品牌数据
        log.debug("即将向数据库中插入数据：{}", brand);
        int rows = brandMapper.insert(brand);
        // 判断插入数据时受影响的行数是否有误
        if (rows != 1) {
            String message = "添加品牌失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除品牌】的业务，参数：{}", id);
        // 检查尝试删除的品牌是否存在
        Object queryResult = brandMapper.getStandardById(id);
        if (queryResult == null) {
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, "删除品牌失败，尝试访问的数据不存在！");
        }

        // 检查此品牌是否关联了类别
        {
            int count = brandCategoryMapper.countByBrand(id);
            if (count > 0) {
                String message = "删除品牌失败！当前品牌仍关联了类别！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        // 执行删除
        log.debug("即使删除id为{}的品牌……", id);
        int rows = brandMapper.deleteById(id);
        if (rows != 1) {
            throw new ServiceException(ServiceCode.ERR_DELETE, "删除品牌失败，服务器忙，请稍后再次尝试！");
        }
        log.debug("删除完成！");
    }

    @Override
    public BrandStandardVO getStandardById(Long id) {
        log.debug("开始处理【根据id查询品牌详情】的业务");

        log.debug("尝试从Redis中获取数据……");
        // 根据id从Redis中获取数据
        BrandStandardVO brand = brandRedisRepository.get(id);
        // 判断获取结果是否不为null
        if (brand != null) {
            // 是：直接返回
            log.debug("从Redis中获取到有效结果，将返回：{}", brand);
            return brand;
        }

        log.debug("Redis中没有匹配的数据，将从数据库中查询……");
        // Redis中无此数据，调用Mapper查询
        BrandStandardVO queryResult = brandMapper.getStandardById(id);
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 是：抛出异常
            String message = "查询品牌详情失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 将查询结果存入到Redis，并返回此结果
        log.debug("从数据库中查询到有效结果！");
        log.debug("向Redis中存入缓存数据，并返回：{}", queryResult);
        brandRedisRepository.put(queryResult, 1, TimeUnit.MINUTES);
        return queryResult;
    }

    @Override
    public List<BrandListItemVO> list() {
        log.debug("开始处理【查询品牌列表】的业务");

        // 从Redis中读取品牌列表
        List<BrandListItemVO> brands = brandRedisRepository.getList();
        // 如果读取到有效列表，表示Redis中存在
        if (brands.size() > 0) {
            // 直接返回
            return brands;
        }

        // 如果读取到的结果为空列表，表示Redis中无此数据
        // 调用Mapper从数据库中查询，并存入到Redis，并返回
        List<BrandListItemVO> list = brandMapper.list();
        brandRedisRepository.putList(list);
        return list;
    }

    @Override
    public void loadBrandToCache() {
        // 删除原有的缓存的品牌数据
        Set<String> keys = brandRedisRepository.getAllKeys();
        brandRedisRepository.deleteAll(keys);

        // 调用Mapper查询品牌列表
        List<BrandListItemVO> list = brandMapper.list();
        // 调用RedisRepository将品牌列表写入到缓存中
        brandRedisRepository.putList(list);

        // 以上Mapper查询结果包含所有品牌的数据，每个数据中都有id
        // 遍历Mapper查询结果，并调用Mapper根据id查询每个品牌数据
        // 调用RedisRepository将查询到的数据写入到缓存中
        for (BrandListItemVO brand : list) {
            BrandStandardVO brandStandardVO = brandMapper.getStandardById(brand.getId());
            brandRedisRepository.put(brandStandardVO);
        }
    }

}