package pers.caijx.elasticsearch.controller;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.caijx.elasticsearch.constant.ESConstant;
import pers.caijx.elasticsearch.domain.JSONResult;
import pers.caijx.elasticsearch.dto.CDADoc;
import pers.caijx.elasticsearch.dto.Novel;
import pers.caijx.elasticsearch.enums.ResultEnum;
import pers.caijx.elasticsearch.exception.CDAException;
import pers.caijx.elasticsearch.utils.ElasticSearchUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CDADocController
 * @Description: TODO
 * @Author Think
 * @Date 2019/9/27
 * @Version V1.0
 **/
@RestController
public class CDADocController {

    @Autowired
    private TransportClient transportClient;

    /**
     * 获取所有的共享文档id
     * @return
     */
    @GetMapping(value = "/cdas")
    public JSONResult getCdas() {
        List<String> ids = new ArrayList<>();
        SearchResponse response = transportClient.prepareSearch(ESConstant.CDA_INDEX_NAME)
//                .addSort("content", SortOrder.ASC)
                .setScroll(new TimeValue(3000))
                .setSize(1000)
                .get();  // 每次获取1000条就返回
        do {
            System.out.println("=====begin=====");
            for (SearchHit hit : response.getHits().getHits()) {
                ids.add(hit.getId());
                System.out.println(hit.getSourceAsString());
            }
            System.out.println("=====end=====");
            response = transportClient.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(30000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);
        return JSONResult.ok(ids.toString());
    }

    /**
     * 获取CDA
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/cdas/{id}")
    public String getCdasById(@PathVariable String id) throws Exception{
        if (null == id
                || 0 == id.length()) {
            throw new CDAException(ResultEnum.UNKNOW_ERROR);
        }
        GetResponse response = transportClient.prepareGet(ESConstant.CDA_INDEX_NAME,ESConstant.CDA_INDEX_TYPE,id).get();
        return response.getSourceAsString();
    }

    /**
     * 创建CDA
     * @param cdaDoc
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value =  "/cdas")
    public JSONResult createCdas(@ModelAttribute("cdaDoc") CDADoc cdaDoc, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null != cdaDoc
                && null == cdaDoc.getId()) {
            throw new CDAException(ResultEnum.UNKNOW_ERROR);
        }
        XContentBuilder xContentBuilder = ElasticSearchUtil.getCDABuileder(cdaDoc);
        IndexResponse indexResponse = transportClient.prepareIndex(ESConstant.CDA_INDEX_NAME,
                ESConstant.CDA_INDEX_TYPE, String.valueOf(cdaDoc.getId())).setSource(xContentBuilder).execute().get();
        // 文档不存在(CREATED) 存在更新(OK)
        System.out.println(indexResponse.status());
        return JSONResult.ok();
    }
}
