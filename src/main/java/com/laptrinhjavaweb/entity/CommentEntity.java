package com.laptrinhjavaweb.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity{

    @Column(name = "content")
    private String content;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "new_id")
    private NewEntity newentity;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public NewEntity getNewentity() {
        return newentity;
    }

    public void setNewentity(NewEntity newentity) {
        this.newentity = newentity;
    }
}
