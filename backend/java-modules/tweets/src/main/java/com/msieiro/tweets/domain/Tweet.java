package com.msieiro.tweets.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public final class Tweet {

    @Id
    @SequenceGenerator(name = "tweet_id_sequence", sequenceName = "tweet_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweet_id_sequence")
    private Integer id;
    private Integer userId;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;
}
