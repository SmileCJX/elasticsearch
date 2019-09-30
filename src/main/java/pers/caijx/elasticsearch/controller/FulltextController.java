package pers.caijx.elasticsearch.controller;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.caijx.elasticsearch.constant.ESConstant;
import pers.caijx.elasticsearch.domain.JSONResult;
import pers.caijx.elasticsearch.dto.CDADoc;
import pers.caijx.elasticsearch.dto.Fulltext;
import pers.caijx.elasticsearch.enums.ResultEnum;
import pers.caijx.elasticsearch.exception.CDAException;
import pers.caijx.elasticsearch.utils.ElasticSearchUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName FulltextController
 * @Description: TODO
 * @Author Think
 * @Date 2019/9/30
 * @Version V1.0
 **/
@RestController
public class FulltextController {

    @Autowired
    private TransportClient transportClient;

    @PostMapping(value =  "/fulltexts")
    public JSONResult createFulltexts(@ModelAttribute("fulltext") Fulltext fulltext, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null != fulltext
                && null == fulltext.getId()) {
            throw new CDAException(ResultEnum.UNKNOW_ERROR);
        }
        XContentBuilder xContentBuilder = ElasticSearchUtil.getFulltextBuileder(fulltext);
        IndexResponse indexResponse = transportClient.prepareIndex(ESConstant.INDEX_INDEX_NAME,
                ESConstant.INDEX_INDEX_TYPE, String.valueOf(fulltext.getId())).setSource(xContentBuilder).execute().get();
        // 文档不存在(CREATED) 存在更新(OK)
        System.out.println(indexResponse.status());
        return JSONResult.ok();
    }
}
