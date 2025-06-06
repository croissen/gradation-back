package com.app.gradationback.service;


import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.ArtVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArtService {

//    작품 등록
    public void registerArt(ArtVO artVO);

//    작품 단일 조회
    public Optional<ArtVO> getArt(Long id);

//    작품 삭제
    public void removeArtById(Long id);

//    관리자용 승인 대기 목록
    public List<ArtDTO> getAllArtPending();

//    관리자용 상세
    public Optional<ArtDTO> getArtPendingById(Long id);

//    관리자 승인/반려 처리
    public void updateArtStatus(ArtDTO artDTO);

}
