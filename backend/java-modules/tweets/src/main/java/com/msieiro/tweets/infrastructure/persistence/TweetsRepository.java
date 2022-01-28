package com.msieiro.tweets.infrastructure.persistence;

import com.msieiro.tweets.domain.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetsRepository
    extends JpaRepository<Tweet, Integer> {
}
