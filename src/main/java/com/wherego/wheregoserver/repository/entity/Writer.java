package com.wherego.wheregoserver.repository.entity;

import com.wherego.wheregoserver.constant.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "writer")
@NamedQueries({
        @NamedQuery(name = "select.Username.Writer", query = "SELECT w FROM Writer w WHERE w.username = " +
                ":username"),
        @NamedQuery(name = "select.Email.Writer", query = "SELECT w FROM Writer w WHERE w.email = " +
                ":email"),
})
public class Writer {

    @Id
    @Column(name = "writer_email", nullable = false)
    private String email;

    @Column(name = "writer_name", nullable = false)
    private String name;

    @Column(name = "writer_tels", nullable = false, length = 10)
    private String tels;

    @Column(name = "writer_avatar", nullable = true, length = 512)
    private String avatar;

    @Column(name = "writer_dob", nullable = false)
    private Date dob;

    @Column(name = "writer_password", nullable = false)
    private String password;

    @Column(name = "writer_username", nullable = false, unique = true)
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


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(UserRole.ROLE_WRITER));
    }

}
