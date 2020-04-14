package cn.zh.blog.dao;

import cn.zh.blog.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author 郑豪
 * @Date 2020/4/7 1:13
 **/
public interface TypeRepository extends JpaRepository<Type,Long>, JpaSpecificationExecutor<Type> {

    /**
     * 通过分类名称查询分类
     * @param name
     * @return
     */
    Type findByName(String name);

    /**
     * 自定义查询分类top榜
     * @param pageable
     * @return
     */
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);

}
