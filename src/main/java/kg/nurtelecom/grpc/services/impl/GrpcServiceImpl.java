package kg.nurtelecom.grpc.services.impl;

import com.google.common.util.concurrent.ListenableFuture;
import kg.nurtelecom.grpc.ApiServiceGrpc;
import kg.nurtelecom.grpc.ApiServiceRequest;
import kg.nurtelecom.grpc.ApiServiceResponse;
import kg.nurtelecom.grpc.services.ExtApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Qualifier("grpcService")
public class GrpcServiceImpl implements ExtApiService {

    @Autowired
    @Qualifier("vostroBlockingStub")
    private ApiServiceGrpc.ApiServiceBlockingStub vostroBlockingStub;

    @Override
    public List<String> sendMessage(List<String> text) {
        ApiServiceResponse response = vostroBlockingStub.withCompression("gzip").getResponse(ApiServiceRequest.newBuilder()
                .addAllText(text)
                .build());
        return response.getAnswerList();
    }
}
