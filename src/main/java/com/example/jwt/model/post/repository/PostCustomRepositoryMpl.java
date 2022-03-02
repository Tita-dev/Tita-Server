package com.example.jwt.model.post.repository;

import com.example.jwt.model.post.dto.PostDto;
import com.example.jwt.model.post.dto.PostResponseDto;
import com.example.jwt.model.post.dto.QPostResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.jwt.model.post.QPost.post;

@RequiredArgsConstructor
public class PostCustomRepositoryMpl implements PostCustomRepository{

    private final JPAQueryFactory queryFactory;

    /*@Override
    public List<PostResponseDto> findPostPostLikeOrder() {
       return null;
        queryFactory
               .select(Projections.constructor(PostResponseDto.class,
                       post.postName,
                       post.content)) 이런식으로도 되는듯?
               .select(new QPostResponseDto(
                       post.postName,
                       post.content)
               )
               .from(post)
               .orderBy(post.postLikeList.size().desc())
               .limit(5)
               .fetch();
    }*/
}
