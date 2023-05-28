package com.salesianas.dam.replica.dto;


import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Custom assembler for HATEOAS standard paged responses, use the
 * native hateoas assembler and then make link modifications to change
 * the base url also it is formatted to be wrapped in the CustomPagedResourceDTO class
 * @param <T>
 */
@Component
public class CustomPagedResourceAssembler<T> {

    private PagedResourcesAssembler<T> assembler;

    public CustomPagedResourceAssembler(PagedResourcesAssembler<T> assembler) {
        this.assembler = assembler;
    }

    /**
     * Formats CustomPagedResourceDTO with the paged response and the result
     * of the native PagedResourcesAssembler
     * @param data
     * @return CustomPagedResourceDTO
     */
    public CustomPagedResourceDTO toModel(Page<T> data) {

        PagedModel<EntityModel<T>> pagedModel = assembler.toModel(data);

        return CustomPagedResourceDTO.builder()
                .content((List<Object>) data.getContent())
                .links(ensambleLinks(pagedModel.getLinks()))
                .page(pagedModel.getMetadata())
                .build();
    }

    /**
     * Manage links to remove http://... and &size={size} from the link
     * Format e.g: "first": {
     *                  "rel": "self",
     *                  "href": "/user/test5?page=0?size=10"
     *             }
     *
     * @param links
     * @return Map<String, Link>
     */
    private Map<String, Link> ensambleLinks(Links links) {

        List<Link> linkList = links.toList();
        Map<String, Link> modifiedLinks = new LinkedHashMap<>();

        for (Link link : linkList) {
            URI uri = link.toUri();
            String query = uri.getQuery();

            URI newUri = UriComponentsBuilder.newInstance()
                    .path(uri.getPath())
                    .query(query)
                    .build()
                    .toUri();

            modifiedLinks.put(
                    link.getRel().value(),
                    Link.of(newUri.toString()));
        }

        return modifiedLinks;
    }

}