package pers.caijx.elasticsearch.dto;

import java.io.Serializable;

/**
 * @ClassName Fulltext
 * @Description: TODO
 * @Author Think
 * @Date 2019/9/30
 * @Version V1.0
 **/
public class Fulltext implements Serializable {

    private Integer id;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Fulltext{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
