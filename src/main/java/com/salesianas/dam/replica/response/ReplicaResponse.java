package com.salesianas.dam.replica.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Wrapper class for all Replica responses
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplicaResponse <T> implements Serializable {
    private ReplicaResponseStatus status;
    private String message;
    private T data;
}
