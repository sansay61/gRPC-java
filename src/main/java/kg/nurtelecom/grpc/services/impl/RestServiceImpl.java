package kg.nurtelecom.grpc.services.impl;

import kg.nurtelecom.grpc.models.RestRequestModel;
import kg.nurtelecom.grpc.models.RestResponseModel;
import kg.nurtelecom.grpc.services.ExtApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@Service
@Qualifier("restService")
public class RestServiceImpl implements ExtApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rest.vostro.ip}")
    private String ip;
    @Value("${rest.vostro.port}")
    private int port;

    @Override
    public List<String> sendMessage(List<String> text) {
        ResponseEntity<RestResponseModel> restResponseModelResponseEntity = restTemplate.postForEntity(String.format("http://%s:%d/callback",ip,port), new RestRequestModel(text), RestResponseModel.class);
        return restResponseModelResponseEntity.getBody().getAnswer();
    }
}
