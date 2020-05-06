package com.jeshco.ngredditclone.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.jeshco.ngredditclone.dto.PostRequest;
import com.jeshco.ngredditclone.dto.PostResponse;
import com.jeshco.ngredditclone.model.Post;
import com.jeshco.ngredditclone.model.Subreddit;
import com.jeshco.ngredditclone.model.User;
import com.jeshco.ngredditclone.repository.CommentRepository;
import com.jeshco.ngredditclone.repository.VoteRepository;
import com.jeshco.ngredditclone.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {return commentRepository.findByPost(post).size();}

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

}
