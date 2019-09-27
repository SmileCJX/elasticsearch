package pers.caijx.elasticsearch.controller;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.caijx.elasticsearch.constant.ESConstant;
import pers.caijx.elasticsearch.domain.JSONResult;
import pers.caijx.elasticsearch.dto.Novel;
import pers.caijx.elasticsearch.enums.ResultEnum;
import pers.caijx.elasticsearch.exception.CDAException;
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

    /**
     * 添加文章
     * @param novel
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping(value =  "/novels")
    public JSONResult createNovels(@ModelAttribute("novel") Novel novel, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null != novel
                && null == novel.getId()) {
            throw new CDAException(ResultEnum.UNKNOW_ERROR);
        }
        XContentBuilder xContentBuilder = ElasticSearchUtil.getBuileder(novel);
        IndexResponse indexResponse = transportClient.prepareIndex(ESConstant.DATA_INDEX_NAME,
                ESConstant.DATA_INDEX_TYPE, String.valueOf(novel.getId())).setSource(xContentBuilder).execute().get();
        // 文档不存在(CREATED) 存在更新(OK)
        System.out.println(indexResponse.status());
        return JSONResult.ok();
    }

    /**
     * 通过id删除文档
     * @param id
     * @return
     */
    @DeleteMapping(value = "/novels/{id}")
    public JSONResult deleteNovelsById(@PathVariable("id") String id) {
        if (null == id
                || id.length() == 0) {
            throw new CDAException(ResultEnum.UNKNOW_ERROR);
        }
        DeleteResponse deleteResponse = transportClient
                                            .prepareDelete(ESConstant.DATA_INDEX_NAME,ESConstant.DATA_INDEX_TYPE,id)
                                            .get();
        System.out.println(deleteResponse.status());
        return JSONResult.ok();
    }

}
