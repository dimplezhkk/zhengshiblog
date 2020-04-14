package cn.zh.blog.service;

import cn.zh.blog.pojo.Comment;

import java.util.List;

/**
 * @Author 鄭豪
 * @Date 2020/4/8 17:52
 **/
public interface CommentService {

    /**
     * 通过博文id查询该博文的评论列表
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存博文评论
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);
}
