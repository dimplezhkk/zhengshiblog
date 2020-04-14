package cn.zh.blog.dto;

/**
 * @Author 郑豪
 * DTO:数据传送对象
 * 系统需要一种在客户端和服务器端之间高效、安全地进 行数据传输的技术。
 * DTO(Data Transfer Object，数据传送对象)是解决这个问题的比较好的方式。
 * DTO是一个普通的Java类，它封装了要传送的批量的数据。
 * 当客户端需要读取服务器端的数 据的时候，服务器端将数据封装在DTO中，这样客户端就可以在一个网络调用中获得它需要的所有数据。
 * @Date 2020/3/28 11:48
 **/
public class ResponseResult {
    private Integer success;
    private String Message;
    private String url;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
