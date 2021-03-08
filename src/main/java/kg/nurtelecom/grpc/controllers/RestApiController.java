package kg.nurtelecom.grpc.controllers;

import kg.nurtelecom.grpc.models.RestRequestModel;
import kg.nurtelecom.grpc.models.RestResponseModel;
import kg.nurtelecom.grpc.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RestApiController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping(value = "/grpc-response/{cycles}")
    public Long getRequestFromGrpcServer(@PathVariable Integer cycles) throws IOException {
        return evaluationService.evaluateGrpc(cycles);
    }

    @GetMapping(value = "/rest-response/{cycles}")
    public Long getResponseByRest(@PathVariable Integer cycles) throws IOException {
        return evaluationService.evaluateRest(cycles);
    }

    @PostMapping(value = "/callback",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseModel callback(@RequestBody RestRequestModel requestModel) {
        return new RestResponseModel(requestModel.getText());
    }

}
