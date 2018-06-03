package com.rhythm.usercrossings.controllers.helpers;

import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleMVCExceptions(Exception e) {
        return ExceptionBody.from(e);
    }

    @Data
    public static class ExceptionBody {

        private String summarizedMessage;

        private ExceptionBody(String summarizedMessage) {
            this.summarizedMessage = summarizedMessage;
        }

        public static ExceptionBody from(Exception e) {
            return new ExceptionBody(ExceptionUtils.getMessage(e));
        }
    }
}
