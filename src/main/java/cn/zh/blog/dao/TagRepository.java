package cn.zh.blog.dao;


import cn.zh.blog.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author 郑豪
 * @Date 2020/4/7 1:29
 **/
public interface TagRepository extends JpaRepository<Tag,Long>, JpaSpecificationExecutor<Tag> {

    /**
     * 通过标签名称查询
     * @param name
     * @return
     */
    Tag findByName(String name);

    /**
     * 查询标签top榜
     * @param pageable
     * @return
     */
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
