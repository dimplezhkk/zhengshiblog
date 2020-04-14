package cn.zh.blog.service;

import cn.zh.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author 鄭豪
 * @Date 2020/4/7 1:04
 **/
public interface TypeService {

    /**
     * 新增博文分类
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 通过id查询博文分类
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 分类查询博文分类列表
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 通过id查询博文分类修改
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id,Type type);

    /**
     * 通过id删除博文分类
     * @param id
     */
    void deleteType(Long id);

    /**
     * 通过分类名称查询
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 查询全部分类
     * @return
     */
    List<Type> listType();

    /**
     * 查询分类top榜
     * @param size
     * @return
     */
    List<Type> listTypeTop(Integer size);

    /**
     * 查询分类总数
     * @return
     */
    Long countType();

    /**
     * 通过标签名称模糊查询
     * @param pageable
     * @param name
     * @return
     */
    Page<Type> listType(Pageable pageable, String name);
}
