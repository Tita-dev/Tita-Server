package com.example.jwt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder

@Table(name ="forum")
public class Forum extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_idx")
    private Long forumIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private User user;

    @Size(min = 4, max = 16)
    @Column(name = "forum_name",unique = true,nullable = false)
    private String forumName;

    @Size(max = 500)
    @Column(name = "explanation",nullable = true)
    private String explanation;
}
