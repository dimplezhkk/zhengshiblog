package cn.zh.blog.dao;

import cn.zh.blog.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author 鄭豪
 * @Date 2020/4/7 17:02
 **/
public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {

    /**
     * 查询推荐博文Top榜
     * @param pageable
     * @return
     */
    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    /**
     * 全局查询
     * @param query
     * @param pageable
     * @return
     */
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query,Pageable pageable);

    /**
     * 更新查看次数
     * @param id
     */
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    void updateViews(Long id);

    /**
     * 查询博客的年份放入一个List<String>中
     * 查询到的数据格式为：
     * 2020
     * 2019
     * 2018
     * @return
     */
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYears();

    /**
     * 通过年份去查询博客文章
     * @param year
     * @return
     */
    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
