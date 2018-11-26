package com.github.carniwar.springboot.scalable.api.endpoint;

import com.github.carniwar.springboot.scalable.api.dto.DiffResultDTO;
import com.github.carniwar.springboot.scalable.api.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotBlank;

@Api(value = "DiffEndpoint", description = "Save values em computes diff between left and right data for an ID")
public interface DiffEndpoint {

    String PATH_V1 = "/v1/diff/{id}";

    @ApiOperation(value = "Save right value to be compared", response = Boolean.class)
    ResponseEntity<String> saveRight(@NotBlank String id, @NotBlank String data) throws BusinessException;

    @ApiOperation(value = "Save left value to be compared", response = Boolean.class)
    ResponseEntity<String> saveLeft(@NotBlank String id, @NotBlank String data) throws BusinessException;

    @ApiOperation(value = "Comparation between right and left data", response = DiffResultDTO.class)
    ResponseEntity<DiffResultDTO> compare(@NotBlank String id) throws BusinessException;

}