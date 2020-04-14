package cn.zh.blog.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 郑豪
 * 博文分类类
 * @Date 2020/4/6 19:29
 **/
@Entity
@Table(name = "t_type")
public class Type implements Serializable {

    /**
     * 博文分类id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 博文分类名称
     */
    @NotBlank(message = "博文分类名称不能为空-_-!!!")
    private String name;

    /**
     * 一对多
     * Blog是多的一端
     * Type是一的一端
     * 一个Type(分类)下有多篇Blog(博文)
     * 这是关系被维护端
     */
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
