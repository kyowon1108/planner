package com.example.planner.service;

import com.example.planner.domain.posts.Posts;
import com.example.planner.domain.posts.PostsRepository;
import com.example.planner.web.dto.PostsSaveRequestDto;
import com.example.planner.web.dto.PostsUpdateRequestDto;
import com.example.planner.web.dto.PostsResponseDto;
import com.example.planner.web.dto.PostsListResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // Posts 엔티티의 update 메서드를 사용하여 데이터 업데이트
        posts.update(requestDto.getTitle(), requestDto.getContent());

        // save 메서드로 업데이트 내용 저장
        postsRepository.save(posts);

        return id;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

}