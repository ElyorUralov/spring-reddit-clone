package com.jeshco.ngredditclone.model;

public enum  VoteType {
    UPVOTE(1), DOWNVOTE(-1),
    ;

    VoteType(int direction) {
    }
}
