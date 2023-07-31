package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Picture;
import cn.tedu.csmall.product.pojo.vo.PictureStandardVO;
import org.springframework.stereotype.Repository;

/**
 * 图片Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface PictureMapper {

    /**
     * 插入图片数据
     *
     * @param picture 图片数据
     * @return 受影响的行数
     */
    int insert(Picture picture);

    /**
     * 根据id删除图片
     *
     * @param id 被删除的图片的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据多个id批量删除图片数据
     *
     * @param ids 期望删除的若干个图片数据的id
     * @return 受影响的行数，将返回成功删除的数据量
     */
    int deleteByIds(Long... ids);

    /**
     * 根据id获取图片的标准信息
     *
     * @param id 图片id
     * @return 返回匹配的图片的标准信息，如果没有匹配的数据，将返回null
     */
    PictureStandardVO getStandardById(Long id);

}
