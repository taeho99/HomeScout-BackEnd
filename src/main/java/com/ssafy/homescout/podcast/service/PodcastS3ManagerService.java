package com.ssafy.homescout.podcast.service;

import com.ssafy.homescout.user.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PodcastS3ManagerService {

    private final S3Service s3Service;


     // 지역과 역할에 따라 S3 객체 키를 생성합니다.
     // role   사용자 역할 ("일반" 또는 "공인중개사")
     // 생성된 S3 객체 키 리턴
     public String generateObjectKey(String region, String role) {
         return String.format("%s_%s.mp3", region, role);
     }


    //S3 객체 키를 기반으로 S3 URL을 반환합니다.
    public String getPodcastUrl(String objectKey) {
        //mp3 객체 URL 반환
        return s3Service.getPublicUrl(objectKey);
    }


     // S3에 팟캐스트 파일을 업로드하고, 공개 URL을 반환합니다.
     // filePath  업로드할 파일의 로컬 경로
     // objectKey S3 객체 키
    public String uploadPodcast(String filePath, String objectKey) {
        //s3Service.uploadFile(filePath, objectKey); // S3Service에 uploadFile 메서드 추가 필요
        // 공개 URL 반환
        return s3Service.getPublicUrl(objectKey);
    }
}