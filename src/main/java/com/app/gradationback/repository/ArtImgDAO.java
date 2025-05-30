package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.mapper.ArtImgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArtImgDAO {

    private final ArtImgMapper artImgMapper;

//    작품 이미지 등록
    public void save(ArtImgVO artImgVO) {
        artImgMapper.insert(artImgVO);
    }

    public List<ArtImgVO> findAllByArtId(Long artId) {
        return artImgMapper.selectAllByArtId(artId);
    }

//    작품 이미지 전체 삭제 (작품 ID로)
    public void deleteAllByArtId(Long artId) {
        artImgMapper.deleteAllByArtId(artId);
    }

}
