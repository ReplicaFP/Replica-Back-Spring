package com.salesianas.dam.replica.controller.impl;

import com.salesianas.dam.replica.controller.InternshipControllerRest;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import com.salesianas.dam.replica.response.ReplicaResponseStatus;
import com.salesianas.dam.replica.service.impl.FinalProjectServiceImpl;
import com.salesianas.dam.replica.service.impl.InternshipServiceImpl;
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
@Tag(name = "Internship", description = "Internship rest")
@RequestMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_INTERNSHIPS)
public class InternshipControllerRestImpl implements InternshipControllerRest {

    @Autowired
    private InternshipServiceImpl internshipService;
    @Override
    @GetMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<InternshipRest>> internshipDetails(@PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Internship successfully recovered")
                .data(internshipService.getInternship(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<InternshipRest>> modifyInternship(@RequestBody InternshipRest internship, @PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Internship successfully updated")
                .data(internshipService.modifyInternship(internship, id))
                .build();

        return ResponseEntity.accepted().body(response);
    }

    @Override
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ID)
    public ResponseEntity deleteInternship(@PathVariable Long id) throws ReplicaException {
        internshipService.deleteInternship(id);

        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Internship successfully deleted")
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ReplicaResponse<InternshipRest>> createInternship(@RequestBody InternshipRest internshipRest) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Internship successfully created")
                .data(internshipService.createInternship(internshipRest))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<InternshipRest>>> listInternships(@Parameter(hidden=true)Pageable pageable) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Internships successfully recovered")
                .data(internshipService.listInternships(pageable))
                .build();

        return ResponseEntity.ok(response);
    }
}
