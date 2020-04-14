package cn.zh.blog.service;

import cn.zh.blog.pojo.Blog;
import cn.zh.blog.queryvo.BlogQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Author 郑豪
 * @Date 2020/4/7 16:57
 **/
public interface BlogService {

    /**
     * 通过id分析博客
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 通过查询条件查询博客并分页
     * @param pageable
     * @param blog
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, BlogQueryVo blog);

    /**
     * 新增博客文章
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 修改博客文章
     * @param id
     * @param blog
     * @return
     */
    Blog updateBlog(Long id,Blog blog);

    /**
     * 通过id删除博客文章
     * @param id
     */
    void deleteBlog(Long id);

    /**
     * 分页查询博客文章
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Pageable pageable);

    /**
     * 查询博客Top榜
     * @param size
     * @return
     */
    List<Blog> listBlogTop(Integer size);

    /**
     * 全局查询
     * @param pageable
     * @param query
     * @return
     */
    Page<Blog> listBlog(Pageable pageable,String query);

    /**
     * 获取博文内容，并且转换
     * @param id
     * @return
     */
    Blog getAndConvert(Long id);

    /**
     * 通过标签id查询博文
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    /**
     * 博文归档
     * @return
     */
    Map<String,List<Blog>> archiveBlog();

    /**
     * 获取博客总条数
     * @return
     */
    Long countBlog();
}
