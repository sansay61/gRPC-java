package kg.nurtelecom.grpc.api;

import com.google.protobuf.ProtocolStringList;
import io.grpc.stub.StreamObserver;
import kg.nurtelecom.grpc.ApiServiceGrpc;
import kg.nurtelecom.grpc.ApiServiceRequest;
import kg.nurtelecom.grpc.ApiServiceResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiServiceImpl extends ApiServiceGrpc.ApiServiceImplBase {

    @Override
    public void getResponse(ApiServiceRequest request,
                            StreamObserver<ApiServiceResponse> responseObserver) {
        ProtocolStringList answer = request.getTextList();

        ApiServiceResponse response=ApiServiceResponse.newBuilder().addAllAnswer(answer).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}