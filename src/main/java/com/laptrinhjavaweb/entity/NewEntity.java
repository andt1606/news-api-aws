package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "new")
public class NewEntity extends BaseEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail", length = 1000000)
    private byte[] thumbnail;

    @Column(name = "shortdescription")
    private String shortDescription;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "categoryid")
    private CategoryEntity category;

    @OneToMany(mappedBy = "newentity")
    private List<CommentEntity> comments = new ArrayList<>();



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }
}
