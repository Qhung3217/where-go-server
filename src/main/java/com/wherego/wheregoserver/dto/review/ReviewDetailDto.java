package com.wherego.wheregoserver.dto.review;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NotBlank
public class ReviewDetailDto {
    private Long id;
    private String comment;
    private int rating;
    private String travelerName;
    private String travelerAvatar;
}
