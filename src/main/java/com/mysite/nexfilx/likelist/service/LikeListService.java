package com.mysite.nexfilx.likelist.service;

import com.mysite.nexfilx.Contents.dao.ContentRepository;
import com.mysite.nexfilx.Contents.domain.NetflixContents;


import com.mysite.nexfilx.Contents.dto.NetflixDto;
import com.mysite.nexfilx.Contents.service.ContentService;
import com.mysite.nexfilx.User.dao.ProfileRepository;
import com.mysite.nexfilx.User.dao.UserRepository;
import com.mysite.nexfilx.User.domain.ProfileName;
import com.mysite.nexfilx.User.domain.User;
import com.mysite.nexfilx.User.service.ProfileNameService;
import com.mysite.nexfilx.likelist.domain.LikeList;
import com.mysite.nexfilx.likelist.dto.LikeListDto;
import com.mysite.nexfilx.likelist.repository.LikeListRepository;
import com.mysite.nexfilx.likelist.repository.LikeListRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeListService {
    @Autowired
    private LikeListRepository likeListRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private LikeListRepositoryImpl likeListRepositoryImpl;


    @Autowired
    private ContentService contentService;

    @Autowired
    private ProfileRepository profileRepository;





    // 찜한 콘텐츠에 값을 보내는 함수
    public boolean list(String useremail, Long contentsid){
        User user = userRepository.findByUseremail(useremail).orElseThrow(()-> new NullPointerException());
        NetflixContents netflixContents = contentRepository.findById(contentsid).orElseThrow(()-> new NullPointerException());

        //좋아요 체크 상태 함수
        boolean rs = checkLike(user,netflixContents);
        //그에 따른 if문으로 작성.
        LikeList likeList = new LikeList();
//        List<NetflixContents> netflixContentsList = contentRepository.findByLikeStatus(netflixContents.getId());
        NetflixDto netflixDto = new NetflixDto();
        if(!rs) {
            System.out.println("추가 실행됨");
            likeList.setUser(user);
            likeList.setNetflixContents(netflixContents);

            likeListRepository.save(likeList);


            return true;
        }else{
            System.out.println("삭제 실행됨");
            likeList=likeListRepository.findByUserIdAndNetflixContentsId(user.getId(),netflixContents.getId()).orElseThrow(()-> new NullPointerException());

            likeListRepository.delete(likeList);

            return false;
        }
    }
    // 찜한 컨텐츠에 유저의 값과 컨텐츠의 값의 유무를 확인하는 함수
    public boolean checkLike(User user, NetflixContents netflixContents){
        boolean check = likeListRepository.findByUserIdAndNetflixContentsId(user.getId(),netflixContents.getId()).isPresent();
        System.out.println("값 확인 : "+check);
        return check;
    }
    // 찜한 콘텐츠들을 불러 오는 함수
    public List<NetflixContents> checklist(String useremail){
        User user = userRepository.findByUseremail(useremail).orElseThrow(()-> new NullPointerException());
        List<NetflixContents> likeList = likeListRepositoryImpl.getQslUserLike(user.getId());
        return likeList;
    }

}
