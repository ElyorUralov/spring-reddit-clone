package com.jeshco.ngredditclone.service;

import com.jeshco.ngredditclone.dto.SubredditDto;
import com.jeshco.ngredditclone.exceptions.SpringRedditException;
import com.jeshco.ngredditclone.mapper.SubredditMapper;
import com.jeshco.ngredditclone.model.Subreddit;
import com.jeshco.ngredditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
         return subredditRepository.findAll()
                 .stream()
                 .map(subredditMapper::mapSubredditToDto)
                 .collect(toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with this id"));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
