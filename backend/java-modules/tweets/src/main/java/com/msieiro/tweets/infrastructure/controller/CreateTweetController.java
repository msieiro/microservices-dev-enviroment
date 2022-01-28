package com.msieiro.tweets.infrastructure.controller;

import com.msieiro.tweets.application.CreateTweetRequest;
import com.msieiro.tweets.application.TweetCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tweets")
public final class CreateTweetController {

    @Autowired
    private TweetCreator tweetCreator;

    @PostMapping
    public ResponseEntity<?> createTweet(@RequestBody final CreateTweetRequest createTweetRequest) {
        tweetCreator.createTweet(createTweetRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
