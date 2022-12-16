package com.mysite.nexfilx.Contents.service;

import com.mysite.nexfilx.Contents.dao.ContentRepository;
import com.mysite.nexfilx.Contents.domain.NetflixContentDetails;
import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.mysite.nexfilx.Contents.dto.NetflixDetailDto;
import com.mysite.nexfilx.Contents.dto.NetflixDto;
import com.mysite.nexfilx.User.dao.UserRepository;
import com.mysite.nexfilx.User.domain.User;
import com.mysite.nexfilx.likelist.domain.LikeList;
import com.mysite.nexfilx.likelist.repository.LikeListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContentService {
    private final ContentRepository contentRepository;

    private final UserRepository userRepository;

    private final LikeListRepository likeListRepository;



    public List<NetflixDto> getNetflixContentsByContentNum(String keyword) {
        return contentRepository.findByContentNumContaining(keyword).stream()
                .map(content -> {
                    NetflixDto netflixDto = new NetflixDto(content);
                    return netflixDto;
                })
                .toList();
    }

    public List<NetflixDto> getNetflixContentsBySearch(String keyword) {
        return contentRepository.findByContentNameContaining(keyword).stream()
                .map(content -> {
                    NetflixDto netflixDto = new NetflixDto(content);
                    return netflixDto;
                })
                .toList();
    }


    public List<NetflixDto> getNetflixContent() {
        return contentRepository.findAll().stream()
                .map(content -> {
                    NetflixDto netflixDto = new NetflixDto(content);
                    return netflixDto;
                })
                .toList();

//        return contentRepository.findAll();
    }

    public List<NetflixDto> checkicon(String useremail) {

        // 컨텐츠 전부 조회를 해줘서 컨텐츠들을 가져온다.
        List<NetflixContents> netflixContents = contentRepository.findAll();
        // 가져온 유저의 email 값을 전체 조회 할 수 있는 User로 변경해준뒤
        Optional<User> user = userRepository.findByUseremail(useremail);
        // 위에서 변경해준 user의 id값을 가져와서 likelist도 전체 조회를 할 수 있게 해준다.
        List<LikeList> likeLists = likeListRepository.findByUserId(user.get().getId());


        return netflixContents.stream()
            .map(contents -> {
                // 람다식에서 boolean 값을 사용하게 해주는 멀티쓰레드환경 동시성 보장 자료형이며 기본값은 기존 boolean과 같이 false이다.
                AtomicBoolean likeStatus = new AtomicBoolean();
                //  forEach문을 사용해 likeLists의 배열을 순회해준다.
                likeLists.forEach(likeList -> {
                    // 순회를 할때 cotentsID값이 같다면 true로 변경해주는 조건문
                    if(likeList.getNetflixContents().getId() == contents.getId()){
                        likeStatus.set(true);
                    }
                });
                // bulider를 이용해서 likeStatus의 값을 업데이트 해준다.
                NetflixDto netflixDto = NetflixDto.builder()
                        .id(contents.getId())
                        .contentNum(contents.getContentNum())
                        .age(contents.getAge())
                        .contentImg(contents.getContentImg())
                        .contentName(contents.getContentName())
                        .contentVideo(contents.getContentVideo())
                        .actor(contents.getActor())
                        .director(contents.getDirector())
                        .mainStory(contents.getMainStory())
                        .likeStatus(likeStatus.get())
                        .detailImg(contents.getDetailImg())
                        .detailTextImg(contents.getDetailImg())
                        .episodes(contents.getEpisodes())
                        .date(contents.getDate())
                        .contentId(contents.getContentId())
                        .rankingImg(contents.getRankingImg())
                        .videoLink(contents.getVideoLink())

                        .build();

                return netflixDto;
            }).collect(Collectors.toList());

    }
    public List<NetflixContentDetails> detailcheck(Long id){
        Optional<NetflixContents> netflixContents = this.contentRepository.findById(id);
        List<NetflixContentDetails> netflixContentDetails = netflixContents.get().getDetails();
        return netflixContentDetails;
    }

    public List<NetflixDto> originSearch(){
        return contentRepository.findAll().stream()
                .map(content -> {
                    NetflixDto netflixDto = new NetflixDto(content);
                    return netflixDto;
                })
                .toList();
    }
}