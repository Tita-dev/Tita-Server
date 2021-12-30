package com.example.jwt.model.post.repository;

import com.example.jwt.model.post.Post;
import com.example.jwt.model.post.dto.PostDto;
import com.example.jwt.model.post.like.QPostLike;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

import static com.example.jwt.model.post.QPost.post;

@RequiredArgsConstructor
public class PostCustomRepositoryMpl implements PostCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostDto> findPostPostLikeOrder() {
       return queryFactory
               .select(Projections.constructor(PostDto.class,
                       post.postName,
                       post.content))
               .from(post)
               .orderBy(post.postLikeList.size().desc())
               .limit(5)
               .fetch();
    }
}
