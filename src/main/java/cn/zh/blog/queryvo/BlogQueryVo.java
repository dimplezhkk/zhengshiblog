package cn.zh.blog.queryvo;

/**
 * @Author 郑豪
 * 查询实体类
 * @Date 2020/4/7 18:23
 **/
public class BlogQueryVo {

    /**
     * 查询博文标题
     */
    private String title;
    /**
     * 查询博文类型id
     */
    private Long typeId;
    /**
     * 查询是否推荐
     */
    private boolean recommend;

    public BlogQueryVo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
