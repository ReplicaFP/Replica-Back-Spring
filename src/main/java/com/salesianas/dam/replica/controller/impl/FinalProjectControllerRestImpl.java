package com.salesianas.dam.replica.controller.impl;

import com.salesianas.dam.replica.controller.FinalProjectControllerRest;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.FinalProjectRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import com.salesianas.dam.replica.response.ReplicaResponseStatus;
import com.salesianas.dam.replica.service.impl.FinalProjectServiceImpl;
import com.salesianas.dam.replica.utils.constant.RestConstantsUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "FinalProject", description = "FinalProject rest")
@RequestMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_FINAL_PROJECTS)
public class FinalProjectControllerRestImpl implements FinalProjectControllerRest {

    @Autowired
    private FinalProjectServiceImpl finalProjectService;
    @Override
    @GetMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<FinalProjectRest>> finalProjectDetails( @PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("FinalProject successfully recovered")
                .data(finalProjectService.getFinalProject(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<FinalProjectRest>> modifyFinalProject(@RequestBody FinalProjectRest finalProject, @PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("FinalProject successfully updated")
                .data(finalProjectService.modifyFinalProject(finalProject, id))
                .build();

        return ResponseEntity.accepted().body(response);
    }

    @Override
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ID)
    public ResponseEntity deleteFinalProject(@PathVariable Long id) throws ReplicaException {
        finalProjectService.deleteFinalProject(id);

        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("FinalProject successfully deleted")
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ReplicaResponse<FinalProjectRest>> createFinalProject(@RequestBody FinalProjectRest finalProjectRest) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("FinalProject successfully created")
                .data(finalProjectService.createFinalProject(finalProjectRest))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<FinalProjectRest>>> listFinalProjects(@Parameter(hidden=true)Pageable pageable) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("FinalProjects successfully recovered")
                .data(finalProjectService.listFinalProject(pageable))
                .build();

        return ResponseEntity.ok(response);
    }
}
