package com.sample.microservices.webfluxmongodb.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.sample.microservices.webfluxmongodb.model.TestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "Test APIs", description = "Test APIs for demo purpose")
@RequestMapping("/webfluxmongodb")
public class TestController {

    @GetMapping("/test")
    @Operation(description = "Get a test model demo", parameters = {
            @Parameter(name = "name", in = ParameterIn.QUERY, required = true, description = "name parameter")
    })
    public Mono<TestDto> getTestDto(final @RequestParam String name,
                              final ServerWebExchange exchange) {
        TestDto testDto = new TestDto();
        testDto.setName(name);
        testDto.setAge(0);
        testDto.setName("Welcome "+name);
        return Mono.just(testDto);
    }

    @PostMapping("/test")
    @Operation(description = "Create a test model demo", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
    public Mono<TestDto> postTestDto(@Valid @RequestBody final TestDto testDto,
                                     final ServerWebExchange exchange) {
        return Mono.just(testDto);
    }

}