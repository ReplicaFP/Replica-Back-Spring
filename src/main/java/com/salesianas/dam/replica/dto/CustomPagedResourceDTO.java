package com.salesianas.dam.replica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Wrapper class for all responses that have to be paginated with HATEOAS format
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomPagedResourceDTO<T> implements Serializable {

    private List<T> content;
    private Map<String, Link> links;
    private PagedModel.PageMetadata page;

}
