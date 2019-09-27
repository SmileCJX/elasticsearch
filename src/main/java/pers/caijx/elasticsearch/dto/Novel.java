package pers.caijx.elasticsearch.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @ClassName Novel
 * @Description: TODO
 * @Author Think
 * @Date 2019/9/27
 * @Version V1.0
 **/
@Document(indexName = "book",type = "novel")
public class Novel implements Serializable {

    @Id
    private Integer id;

    private String author;

    private String title;

    private String wordCount;

    private String publishDate;
}
