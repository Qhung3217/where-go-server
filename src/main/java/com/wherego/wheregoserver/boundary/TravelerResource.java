package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.auth.ChangePasswordDto;
import com.wherego.wheregoserver.dto.review.ReviewCreateDto;
import com.wherego.wheregoserver.dto.traveler.DetailTravelerDto;
import com.wherego.wheregoserver.dto.traveler.TravelerUpdateDto;
import com.wherego.wheregoserver.exception.MissingParamsException;
import com.wherego.wheregoserver.service.ReviewService;
import com.wherego.wheregoserver.service.TravelerService;
import com.wherego.wheregoserver.utils.HeaderUtils;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@RestController
@RequestMapping("/traveler")
public class TravelerResource {
    @Autowired
    private TravelerService travelerService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/ping")
    public String ping() {
        return "ping";
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<DetailTravelerDto> getDetail(@RequestHeader("Authorization") String authorizationHeader) {
        String token = HeaderUtils.getToken(authorizationHeader);
        return ResponseEntity.ok(travelerService.getDetail(token));
    }

    @PostMapping(value = "/change-password")
    @Transactional
    public ResponseEntity<ResponseMessageDto> changePassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody ChangePasswordDto password
    ) {
        String token = HeaderUtils.getToken(authorizationHeader);
        return ResponseEntity.ok(travelerService.changePassword(token, password));
    }

    @PostMapping(value = "/update")
    @Transactional
    public ResponseEntity<ResponseMessageDto> update(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "tels", required = false) String tels,
            @RequestParam(value = "dob", required = false) Date dob,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "avatar", required = false) MultipartFile avatarFile
    ) {
        String token = HeaderUtils.getToken(authorizationHeader);
        TravelerUpdateDto updateTraveler = new TravelerUpdateDto(name, tels, dob, username, avatarFile);
        return ResponseEntity.ok(travelerService.update(token, updateTraveler));
    }

    @PostMapping(value = "/review")
    @Transactional
    public ResponseEntity<ResponseMessageDto> review(
            @PathParam("category") String category,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(value = "comment") String comment,
            @RequestParam(value = "rating") int rating,
            @RequestParam(value = "categoryId") Long categoryId
    ) {
        String token = HeaderUtils.getToken(authorizationHeader);
        ReviewCreateDto review = new ReviewCreateDto(comment, rating, categoryId);
        switch (category) {
            case "hotel":
                return reviewHotel(token, review);
            case "restaurant":
                return reviewRestaurant(token, review);
            case "place":
                return reviewPlace(token, review);
            default:
                throw new MissingParamsException(new String[]{"category"});
        }
    }

    private ResponseEntity<ResponseMessageDto> reviewHotel(String token, ReviewCreateDto review) {
        return ResponseEntity.ok(reviewService.reviewHotel(token, review));
    }
    private ResponseEntity<ResponseMessageDto> reviewRestaurant(String token, ReviewCreateDto review) {
        return ResponseEntity.ok(reviewService.reviewRestaurant(token, review));
    }
    private ResponseEntity<ResponseMessageDto> reviewPlace(String token, ReviewCreateDto review) {
        return ResponseEntity.ok(reviewService.reviewPlace(token, review));
    }
}
