package pers.caijx.elasticsearch.dto;

import java.io.Serializable;

/**
 * @ClassName CDADoc
 * @Description: TODO
 * @Author Think
 * @Date 2019/9/27
 * @Version V1.0
 **/
public class CDADoc implements Serializable {

    private Integer id;

    private String content;

    private String publishDate;

    public String getContent() {
        return content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "CDADoc{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
