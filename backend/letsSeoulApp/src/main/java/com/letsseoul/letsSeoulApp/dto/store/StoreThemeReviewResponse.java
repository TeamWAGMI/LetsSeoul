package com.letsseoul.letsSeoulApp.dto.store;

import com.letsseoul.letsSeoulApp.domain.Review;
import com.letsseoul.letsSeoulApp.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public  class StoreThemeReviewResponse{
    private final Long userId;
    private final String userEmoji;
    private final String userNickname;
    private final Long reviewId;
    private final Integer reviewScore;
    private final String reviewContent;
    private final LocalDateTime createDatetime;
    private final LocalDateTime modifiedDatetime;
    private List<String> reviewImages;

  /*  public static List<StoreThemeReviewResponse> of(User user, List<Review> reviewList){
        List<StoreThemeReviewResponse> list = new ArrayList<>();
        for(Review review : reviewList){
            list.add(new StoreThemeReviewResponse(
                    user.getId(),
                    user.getEmoji(),
                    user.getNickname(),
                    review.getId(),
                    review.getScore(),
                    review.getContent(),
                    review.getCreatedDatetime().toString(),
                    review.getModifiedDatetime().toString(),
                    new ArrayList<>(){{add("path");add("path");}}
            ));
        }
        return list;
    }
*/
}