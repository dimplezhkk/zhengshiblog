package cn.zh.blog.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 郑豪
 * 博文实体类
 * @Date 2020/4/6 19:11
 **/
@Entity
@Table(name = "t_blog")
public class Blog implements Serializable {
    /**
     * 博文id
     * strategy=GenerationType.IDENTITY 自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 博文标题
     */
    private String title;
    /**
     * 博文内容
     * @LOb 这个注解表示会把数据库的这段设置为longText类型
     * @Basic(fetch = FetchType.LAZY) 这个表示懒加载 只有使用的时候才去加载它 节约资源
     */
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    /**
     * 博文首图
     */
    private String firstPicture;
    /**
     * 博文标记
     */
    private String flag;
    /**
     * 浏览数
     */
    private Integer views;
    /**
     * 评论数
     */
    private Integer comments;
    /**
     * 点赞数
     */
    private Integer likes;
    /**
     * 打赏是否开启
     */
    private boolean appreciation;
    /**
     * 转载声明是否开启
     */
    private boolean shareStatement;
    /**
     * 评论是否开启
     */
    private boolean commentabled;
    /**
     * 是否发布或者保存为草稿
     */
    private boolean published;
    /**
     * 是否推荐
     */
    private boolean recommend;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * @Transient 该注解表示这只是一个属性值，不会保存到数据库
     */
    @Transient
    private String tagIds;

    /**
     * 博客描述
     */
    private String description;

    /**
     * 空构造
     */
    public Blog() {
    }

    /**
     * 多对一
     * Blog是多的一端
     * Type是一的一端
     * 多个Blog(博文)属于一个Type(分类)
     * 多的一端是关系维护端
     * 这是关系维护端
     */
    @ManyToOne
    private Type type;

    /**
     * 多对多
     * 一篇文章有多个标签
     * 多个标签属于一篇文章
     * 增加blog时 有新的tag 会把这个新的tag保存到数据库里
     * 级联新增
     */
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    /**
     * 多个Blog(博文)属于一个(User)用户
     */
    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comment = new ArrayList<>();

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 格式化标签值
     */
    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

    /**
     * 记录日志可以利用这样的格式输出
     * @return
     */
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", comments=" + comments +
                ", likes=" + likes +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tagIds='" + tagIds + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", tags=" + tags +
                ", user=" + user +
                ", comment=" + comment +
                '}';
    }
}
