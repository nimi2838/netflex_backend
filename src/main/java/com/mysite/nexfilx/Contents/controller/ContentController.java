package com.mysite.nexfilx.Contents.controller;

import com.mysite.nexfilx.Contents.dao.ContentRepository;
import com.mysite.nexfilx.Contents.domain.NetflixContentDetails;
import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.mysite.nexfilx.Contents.dto.NetflixDetailDto;
import com.mysite.nexfilx.Contents.dto.NetflixDto;
import com.mysite.nexfilx.Contents.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentController {
    @Autowired
    private final ContentService contentService;

    private final ContentRepository contentRepository;

    @GetMapping("/contents")
    public List<NetflixDto> getAllContents(@RequestParam("kw") String keyword) {
        return contentService.getNetflixContentsByContentNum(keyword);
    }

    @GetMapping("/search")

    public List<NetflixDto> getKeywords(@RequestParam("q") String q) {

        return contentService.getNetflixContentsBySearch(q);
    }

    @GetMapping("/allcontent")
    public List<NetflixDto> getNetflixContents() {
        return contentService.getNetflixContent();
    }

    @GetMapping("/rank")
    public List<NetflixDto> getRankItems() {
        return  contentService.getNetflixContentsByContentNum("KR-RANK");
    }



    @GetMapping("/browse/my-list/check")
    public List<NetflixDto> getchecklike(@RequestParam String useremail){

        return contentService.checkicon(useremail);
    }
    @GetMapping("/detail")
    public List<NetflixContentDetails> getDetail(@RequestParam Long id){
        return contentService.detailcheck(id);
    }
    @GetMapping("/origin")
    public List<NetflixDto> originAudio(){
        return contentService.originSearch();
    }



}