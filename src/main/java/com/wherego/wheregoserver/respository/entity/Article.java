package com.wherego.wheregoserver.respository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "article_title", nullable = false)
    private String title;

    @Column(name = "article_thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "article_content", nullable = false)
    private String content;

    @Column(name = "article_short_description", nullable = false)
    private String shortDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_email", nullable = false)
    private Writer writer;
    // if name column is same reference column, then referenceColumnName can ignore

}
