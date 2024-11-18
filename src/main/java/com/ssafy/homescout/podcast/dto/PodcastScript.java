package com.ssafy.homescout.podcast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PodcastScript {
    private String script;
    private List<Participant> participants;
}