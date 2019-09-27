package pers.caijx.elasticsearch.controller;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.caijx.elasticsearch.constant.ESConstant;

/**
 * @ClassName NovelController
 * @Description: TODO
 * @Author Think
 * @Date 2019/9/27
 * @Version V1.0
 **/
@RestController
public class NovelController {

    @Autowired
    private TransportClient transportClient;

    @GetMapping(value =  "/addTest")
    public void addTest() throws Exception {
        String id = "10000";
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject()
                .field("author", "张铭")
                .field("title", "Swift从入门到入土")
                .field("word_count", "5000")
                .field("publish_date","2007-08-23")
                .endObject();
        IndexResponse indexResponse = transportClient.prepareIndex(ESConstant.DATA_INDEX_NAME,
                ESConstant.DATA_INDEX_TYPE, id).setSource(xContentBuilder).execute().get();
        // 文档不存在(CREATED) 存在更新(OK)
        System.out.println(indexResponse.status());
    }
}
