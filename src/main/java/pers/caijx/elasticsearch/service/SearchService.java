package pers.caijx.elasticsearch.service;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pers.caijx.elasticsearch.dto.Novel;
import pers.caijx.elasticsearch.repository.NovelSearchRepository;

/**
 * @ClassName SerarchService
 * @Description: TODO
 * @Author Think
 * @Date 2019/9/27
 * @Version V1.0
 **/
//@Service
//public class SearchService {
//
////    @Autowired
////    NovelSearchRepository novelSearchRepository;
//
//    private TransportClient client;
//
//    public Page<Novel> getNovelByAuthor(String author) {
////        Page<Novel> novels = novelSearchRepository.findNovelByAuthor(author, PageRequest.of(0,10));
//        client.
//        return null;
//    }
//}
