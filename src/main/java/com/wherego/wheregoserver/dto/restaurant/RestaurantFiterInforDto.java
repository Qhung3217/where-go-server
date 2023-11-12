package com.wherego.wheregoserver.dto.restaurant;

import com.wherego.wheregoserver.repository.entity.Cuisine;
import com.wherego.wheregoserver.repository.entity.Feature;
import com.wherego.wheregoserver.repository.entity.Meal;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NotBlank
public class RestaurantFiterInforDto {
    private List<Cuisine> cuisines;
    private List<Feature> features;
    private List<Meal> meals;
}
