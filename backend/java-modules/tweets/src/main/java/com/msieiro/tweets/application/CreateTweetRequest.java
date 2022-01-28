package com.msieiro.tweets.application;

import lombok.Data;

@Data
public final class CreateTweetRequest {
    private Integer userId;
    private String content;
}
