package com.bottrack.model;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    public String token;
    public Object data;
    public int statusCode;
    public String message;

    public ApiResponse(String token) {
        this.token = token;
    }

    public ApiResponse() { }

    public static ApiResponse Ok(Object data) {
        ApiResponse response = new ApiResponse();
        response.setData(data);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("successfull");
        return response;
    }

    public static ApiResponse Ok(Object data, String token) {
        ApiResponse response = new ApiResponse();
        response.setData(data);
        response.setMessage("successfull");
        response.setToken(token);
        response.setStatusCode(HttpStatus.OK.value());
        return response;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
