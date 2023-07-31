package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Album;
import cn.tedu.csmall.product.pojo.vo.AlbumListItemVO;
import cn.tedu.csmall.product.pojo.vo.AlbumStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 相册数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface AlbumMapper {

    /**
     * 插入相册数据
     *
     * @param album 相册数据
     * @return 受影响的行数
     */
    int insert(Album album);

    /**
     * 批量插入相册数据
     *
     * @param albums 多个相册数据的集合
     * @return 受影响的行数
     */
    int insertBatch(List<Album> albums);

    /**
     * 根据id删除相册
     *
     * @param id 被删除的相册的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据多个id批量删除相册
     *
     * @param ids 期望删除的多个相册数据的id
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 根据id修改相册数据详情
     *
     * @param album 封装了id与新数据的相册对象
     * @return 受影响的行数
     */
    int update(Album album);

    /**
     * 统计当前表中相册数据的数量
     *
     * @return 当前表中相册数据的数量
     */
    int count();

    /**
     * 根据相册名称统计当前表中相册数据的数量
     *
     * @param name 相册名称
     * @return 当前表中匹配名称的相册数据的数量
     */
    int countByName(String name);

    /**
     * 根据id获取相册的标准信息
     *
     * @param id 相册id
     * @return 返回匹配的相册的标准信息，如果没有匹配的数据，将返回null
     */
    AlbumStandardVO getStandardById(Long id);

    /**
     * 查询相册列表
     *
     * @return 相册列表
     */
    List<AlbumListItemVO> list();

}
