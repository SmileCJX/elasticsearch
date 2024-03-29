package pers.caijx.elasticsearch.domain;

/**
 * http请求返回的最外层对象
 * Created by caijx on 2017/9/5/005.
 */
public class Result<T>{

    private Integer code; /*错误码*/

    private String msg; /*提示信息*/

    private T data; /*具体的内容*/

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
