package pers.caijx.elasticsearch.utils;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import pers.caijx.elasticsearch.dto.CDADoc;
import pers.caijx.elasticsearch.dto.Fulltext;
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
                    .field("author", novel.getAuthor())
                    .field("title", novel.getTitle())
                    .field("word_count", novel.getWordCount())
                    .field("publish_date",novel.getPublishDate())
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xContentBuilder;
    }

    public static XContentBuilder getCDABuileder(CDADoc cdaDoc) {
        XContentBuilder xContentBuilder = null;
        try {
            xContentBuilder = XContentFactory.jsonBuilder().startObject()
                    .field("content", cdaDoc.getContent())
                    .field("publish_date",cdaDoc.getPublishDate())
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xContentBuilder;
    }

    public static XContentBuilder getFulltextBuileder(Fulltext fulltext) {
        XContentBuilder xContentBuilder = null;
        try {
            xContentBuilder = XContentFactory.jsonBuilder().startObject()
                    .field("content", fulltext.getContent())
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xContentBuilder;
    }
}
