package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class UpcyclingDTO {
//    TBL_UPCYCLING
    private Long id;
    private String upcyclingAddress;
    private String upcyclingDetailAddress;
    private String upcyclingEmail;
    private String upcyclingPhone;
    private String upcyclingDate;
    private int upcyclingSizeSmall;
    private int upcyclingSizeMedium;
    private int upcyclingSizeLarge;
    private String upcyclingMaterials;
    private String upcyclingSignificant;
    private String upcyclingStatus;
    private String upcyclingImgName;
    private String upcyclingImgPath;
    private String upcyclingRejectReason;
    private Date upcyclingRequestDate;
    private Long userId;

//    TBL_USER
    private String userImgName;
    private String userImgPath;
    private String userName;
    private String userEmail;
    private String userIdentification;
    private String userPassword;
    private String userPhone;
    private String userNickName;
    private String userAddress;
    private String userDetailAddress;
    private String postalCode;
    private boolean userSnsOk;
    private boolean userMailOk;
    private boolean userAgreementOk;
    private String userIntroduce;
    private String userInstagram;
    private String userYoutube;
    private String userBlog;
    private String userKakao;
    private String userGoogle;
    private String userNaver;
    private boolean userAdminOk;
    private boolean userBanOk;
    private String userMajorImgName;
    private String userMajorImgPath;
    private String userWriterStatus;
    private String userUniversityStatus;
    private String userArtCategory;
    private String userBackgroundImgName;
    private String userBackgroundImgPath;
    private String userProvider;
    private Long majorId;
}
