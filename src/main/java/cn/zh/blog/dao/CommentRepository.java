package cn.zh.blog.dao;

import cn.zh.blog.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author 鄭豪
 * @Date 2020/4/8 17:55
 **/
public interface CommentRepository extends JpaRepository<Comment,Long> {

    /**
     * 通过博文id查询评论列表并按时间排序
     * @param blogId
     * @param sort
     * @return
     */
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

    /**
     * 通过博文id查询评论总数
     * @param id
     * @return
     */
    Integer countByBlogId(Long id);
}
