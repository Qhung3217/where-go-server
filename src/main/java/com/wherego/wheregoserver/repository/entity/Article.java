package com.wherego.wheregoserver.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article")
@NamedQueries({
        @NamedQuery(name = "select.All.Article", query = "SELECT a FROM Article a"),
        @NamedQuery(name = "select.Random.Article", query = "SELECT a FROM Article a ORDER BY " +
                "rand() LIMIT :quantity"),
        @NamedQuery(name = "search.Article", query = "SELECT a FROM Article a WHERE lower(a" +
                ".title) LIKE lower(:keyword)"),
})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "article_title", nullable = false)
    private String title;

    @Column(name = "article_thumbnail", nullable = false, length=512)
    private String thumbnail;

    @Column(name = "article_content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "article_short_description", nullable = false, length=800)
    private String shortDescription;

    @Column(name = "article_create_date", nullable = false)
    private Date createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_email", nullable = false)
    private Writer writer;
    // if name column is same reference column, then referenceColumnName can ignore

}
