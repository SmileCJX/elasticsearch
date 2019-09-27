package pers.caijx.elasticsearch.controller;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.caijx.elasticsearch.constant.ESConstant;
import pers.caijx.elasticsearch.dto.Novel;
import pers.caijx.elasticsearch.utils.ElasticSearchUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public void addTest(@ModelAttribute("novel") Novel novel, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = "10000";
        XContentBuilder xContentBuilder = ElasticSearchUtil.getBuileder(novel);
        IndexResponse indexResponse = transportClient.prepareIndex(ESConstant.DATA_INDEX_NAME,
                ESConstant.DATA_INDEX_TYPE, id).setSource(xContentBuilder).execute().get();
        // 文档不存在(CREATED) 存在更新(OK)
        System.out.println(indexResponse.status());
    }
}
