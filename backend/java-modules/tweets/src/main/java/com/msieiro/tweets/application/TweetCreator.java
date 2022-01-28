package com.msieiro.tweets.application;

import com.msieiro.tweets.domain.Tweet;
import com.msieiro.tweets.infrastructure.persistence.TweetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class TweetCreator {

    @Autowired
    private TweetsRepository tweetsRepository;

    public void createTweet(final CreateTweetRequest request) {
        Tweet tweet = Tweet.builder().userId(request.getUserId()).content(request.getContent()).build();
        tweetsRepository.save(tweet);
    }
}
