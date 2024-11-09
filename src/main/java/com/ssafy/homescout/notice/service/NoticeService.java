package com.ssafy.homescout.notice.service;

import com.ssafy.homescout.notice.dto.NoticeDetailResponseDto;
import com.ssafy.homescout.notice.dto.NoticeListResponseDto;
import com.ssafy.homescout.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public List<NoticeListResponseDto> getAllNotices() {
        return noticeMapper.selectAllNotice();
    }

    public NoticeDetailResponseDto getNoticeById(Long noticeId) {
        NoticeDetailResponseDto notice = noticeMapper.selectNoticeById(noticeId);

        //게시글 상세 조회 실패
        if(notice == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 게시글입니다.");
        }

        return noticeMapper.selectNoticeById(noticeId);
    }
}
