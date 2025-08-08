package com.apexon.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Nagaraj Kharvi
 * @version 1.0
 * @since 29/07/2025
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private Integer code;
    private String message;
    private Object data;

    public Response(Integer code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity<Response> response(Response response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    // Create (201 Created)
    public static ResponseEntity<Response> createResponse(Object data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.builder()
                        .code(201)
                        .message("Resource created successfully")
                        .data(data)
                        .build());
    }

    // Update (200 OK)
    public static ResponseEntity<Response> updateResponse(Object data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.builder()
                        .code(200)
                        .message("Resource updated successfully")
                        .data(data)
                        .build());
    }

    // Get (200 OK)
    public static ResponseEntity<Response> getResponse(Object data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.builder()
                        .code(200)
                        .message("Resource fetched successfully")
                        .data(data)
                        .build());
    }

    // List (200 OK)
    public static ResponseEntity<Response> listResponse(Object data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.builder()
                        .code(200)
                        .message("Resources listed successfully")
                        .data(data)
                        .build());
    }


    // Delete (200 OK)
    public static ResponseEntity<Response> deleteResponse(Object data) {
        return ResponseEntity
                .status(HttpStatus.OK) // No content response
                .body(Response.builder()
                        .code(200)
                        .message("Resource deleted successfully")
                        .data(data)
                        .build());
    }

    // API Basic Response
    public static ResponseEntity<Response> response(String message) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.builder()
                        .code(200)
                        .message(message)
                        .build());
    }

    // API Basic Response
    public static ResponseEntity<Response> response(int status, String message) {
        return ResponseEntity
                .status(status)
                .body(Response.builder()
                        .code(status)
                        .message(message)
                        .build());
    }
}