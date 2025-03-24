package dw.gameshop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameRating { // enum: 숫자화 가능한 문자와 상수
    VERY_BAD(1, "매우 나쁨"),
    BAD(2, "나쁨"),
    AVERAGE(3, "보통"),
    GOOD(4, "좋음"),
    EXCELLENT(5, "훌륭함");

    private final int ratingValue;
    private final String ratingDescription;
}
