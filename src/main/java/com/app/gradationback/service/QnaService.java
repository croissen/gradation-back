package com.app.gradationback.service;

import com.app.gradationback.domain.FaqVO;
import com.app.gradationback.domain.QnaDTO;
import com.app.gradationback.domain.QnaVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface QnaService {

    //    문의 등록
    public void registraction(QnaVO qnaVO, MultipartFile file);

    //    단일 조회
    public Optional<QnaDTO> getQna(Long id);

    //    전체 조회
    public List<QnaDTO> getQnaList(String userEmail);

    //    수정
    public void modify(QnaVO qnaVO);

    //    삭제
    public void remove(Long id);

    // 관리자용 전체 문의 내역 조회
    public List<QnaDTO> getAllQnaListForAdmin();
}
