package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.pojo.dto.AlbumAddNewDTO;
import cn.tedu.csmall.product.pojo.vo.AlbumListItemVO;
import cn.tedu.csmall.product.pojo.vo.AlbumStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 相册业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface IAlbumService {

    /**
     * 添加相册
     *
     * @param albumAddNewDTO 添加的相册对象
     */
    void addNew(AlbumAddNewDTO albumAddNewDTO);

    /**
     * 删除相册
     *
     * @param id 被删除的相册的id
     */
    void deleteById(Long id);

    /**
     * 根据id查询相册详情
     *
     * @param id 相册id
     * @return 匹配的相册详情，如果没有匹配的数据，将抛出异常
     */
    AlbumStandardVO getStandardById(Long id);

    /**
     * 查询相册列表
     *
     * @return 相册列表的集合
     */
    List<AlbumListItemVO> list();

}
