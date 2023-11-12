package com.wherego.wheregoserver.dto.place;

import com.wherego.wheregoserver.repository.entity.PlaceType;
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
public class PlaceFilterInforDto {
    List<PlaceType> placeTypes;
}
