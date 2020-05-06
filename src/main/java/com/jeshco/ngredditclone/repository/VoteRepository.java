package com.jeshco.ngredditclone.repository;

import com.jeshco.ngredditclone.model.Post;
import com.jeshco.ngredditclone.model.User;
import com.jeshco.ngredditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
