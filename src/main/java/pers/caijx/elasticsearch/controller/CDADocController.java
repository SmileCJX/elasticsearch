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
import pers.caijx.elasticsearch.dto.Novel;
import pers.caijx.elasticsearch.enums.ResultEnum;
import pers.caijx.elasticsearch.exception.CDAException;
import pers.caijx.elasticsearch.utils.ElasticSearchUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
