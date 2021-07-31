package top.dreamyy.hrm.domain;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    private Integer id;        // 编号
    private String title;   // 零件名
    private String content; // 供应商批次号
    private Integer pcs;
    private String workorder;
    private java.util.Date feedtime; // 投料时间
    private java.util.Date createDate;  // 发布日期
    private User user;        // 发布人

    // 无参数构造器
    public Notice() {
        super();
        // TODO Auto-generated constructor stub
    }

    // setter和getter方法
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setPcs(Integer pcs) {
        this.pcs = pcs;
    }

    public Integer getPcs() {
        return pcs;
    }

    public void setWorkorder(String workorder) {
        this.workorder = workorder;
    }

    public String getWorkorder() {
        return workorder;
    }

    public void setFeedtime(Date feedtime) {
        this.feedtime = feedtime;
    }

    public Date getFeedtime() {
        return feedtime;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Notice [id=" + id + ", title=" + title + ", content=" + content
                + ", createDate=" + createDate + ", user=" + user + "]";
    }
}
