package com.wherego.wheregoserver.respository.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "writer")
public class Writer {

    @Id
    @Column(name = "writer_email", nullable = false)
    private String email;

    @Column(name = "writer_name", nullable = false)
    private String name;

    @Column(name = "writer_tels", nullable = false, length=10)
    private String tels;

    @Column(name = "writer_avatar", nullable = true, length=512)
    private String avatar;

    @Column(name = "writer_dob", nullable = false)
    private Date dob;

    @Column(name = "writer_password", nullable = false)
    private String password;

    @Column(name = "writer_username", nullable = false)
    private String username;

    public Writer(String email, String name, String tels, String avatar, Date dob, String password, String username) {
        this.email = email;
        this.name = name;
        this.tels = tels;
        this.avatar = avatar;
        this.dob = dob;
        this.password = password;
        this.username = username;
    }

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Article> articles;

}
