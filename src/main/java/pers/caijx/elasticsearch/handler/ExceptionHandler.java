package pers.caijx.elasticsearch.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.caijx.elasticsearch.domain.Result;
import pers.caijx.elasticsearch.enums.ResultEnum;
import pers.caijx.elasticsearch.exception.PatientException;
import pers.caijx.elasticsearch.utils.ResultUtil;

@ControllerAdvice
public class ExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof PatientException) {
            PatientException patientException = (PatientException)e;
            return ResultUtil.error(patientException.getCode(),patientException.getMessage());
        } else {
            LOGGER.error(" 【系统异常】 {}", e);
            return ResultUtil.error(ResultEnum.UNKNOW_ERROR.getCode(),ResultEnum.UNKNOW_ERROR.getMsg());
        }
    }
}
