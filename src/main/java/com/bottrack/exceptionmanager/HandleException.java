package com.bottrack.exceptionmanager;

import com.bottrack.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
public class HandleException extends RuntimeException {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public static ResponseEntity<ApiResponse> ThrowBadRequest(Exception ex) {
        var response = new ApiResponse(ex.getMessage());
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
