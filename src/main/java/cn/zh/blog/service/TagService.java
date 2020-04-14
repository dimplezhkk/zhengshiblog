package cn.zh.blog.service;

import cn.zh.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
/**
 * @Author 郑豪
 * @Date 2020/4/7 1:29
 **/
public interface TagService {

    /**
     * 保存标签
     * @param type
     * @return
     */
    Tag saveTag(Tag type);

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 根据标签名称查询标签
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 查询标签并分页
     * @param pageable
     * @param name
     * @return
     */
    Page<Tag> listTag(Pageable pageable,String name);

    /**
     * 查询全部标签
     * @return
     */
    List<Tag> listTag();

    /**
     * 查询标签top榜
     * @param size
     * @return
     */
    List<Tag> listTagTop(Integer size);

    /**
     * 通过多个标签id查询标签列表
     * @param ids
     * @return
     */
    List<Tag> listTag(String ids);

    /**
     * 修改标签
     * @param id
     * @param type
     * @return
     */
    Tag updateTag(Long id, Tag type);

    /**
     * 删除标签
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 查询标签总数
     * @return
     */
    Long countTag();
}
