package com.wherego.wheregoserver.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewCreateDto {
    private String comment;
    private int rating;
    private Long categoryId;
}
