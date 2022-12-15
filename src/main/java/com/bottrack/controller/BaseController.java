package com.bottrack.controller;

import com.bottrack.repositorymodel.ResponseModal;
import org.springframework.http.HttpStatus;

public class BaseController {

    public static ResponseModal BuildOk(Object data) {
        ResponseModal modal = new ResponseModal();
        modal.setResponseBody(data);
        modal.setHttpStatusCode(HttpStatus.OK);
        return modal;
    }
}
