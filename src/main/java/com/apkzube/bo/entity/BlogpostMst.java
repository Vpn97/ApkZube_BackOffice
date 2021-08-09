package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blogpost_mst")
@Entity
public class BlogpostMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blogpost_id", nullable = false)
    private Long blogpostId;

    @Column(name = "app_id ")
    private Long appId;

    @Column(name = "blogpost_title")
    private String title;

    @Column(name = "blogpost_sort_disc")
    private String sorDesc;

    @Column(name = "click_action_code")
    private String clickActionCode;

    @Column(name = "is_active")
    private boolean iaActive;

    @Column(name = "blog_url")
    private String blogURL;

    @Column(name = "url")
    private String url;

    @Column(name = "img_url")
    private String imgURL;

    @Column(name = "created_date")
    private Date createdDate;

    public Long getBlogpostId() {
        return blogpostId;
    }

    public void setBlogpostId(Long blogpostId) {
        this.blogpostId = blogpostId;
    }
}
