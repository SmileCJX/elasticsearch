package pers.caijx.elasticsearch.utils;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import pers.caijx.elasticsearch.dto.Novel;

import java.io.IOException;

/**
 * @ClassName ElasticSearchUtil
 * @Description: TODO
 * @Author Think
 * @Date 2019/9/27
 * @Version V1.0
 **/
public class ElasticSearchUtil {

    public static XContentBuilder getBuileder(Novel novel) {
        XContentBuilder xContentBuilder = null;
        try {
            xContentBuilder = XContentFactory.jsonBuilder().startObject()
                    .field("author", "张铭")
                    .field("title", "Swift从入门到入土")
                    .field("word_count", "5000")
                    .field("publish_date","2007-08-23")
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xContentBuilder;
    }
}
