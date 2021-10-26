package com.all.Projectforall.exceptions.custExcep;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OverWeightException extends Exception{
    private static final Long serialVersionUID=1L;
    public OverWeightException(String message){super(message);}
}
