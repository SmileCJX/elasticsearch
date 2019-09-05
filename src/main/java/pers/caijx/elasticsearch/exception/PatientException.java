package pers.caijx.elasticsearch.exception;

import pers.caijx.elasticsearch.enums.ResultEnum;

/**
 * Spring只对RuntimeException进行事务回滚，对于Exception不进行事务回滚
 * Created by caijx on 2018/7/3/003.
 */
public class PatientException extends RuntimeException{

    private Integer code;

    public PatientException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
