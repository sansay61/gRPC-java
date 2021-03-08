package kg.nurtelecom.grpc.components;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import kg.nurtelecom.grpc.ApiServiceGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Component
public class GrpcMacChannelBean {

    @Value("${grpc.mac.ip}")
    private String ip;
    @Value("${grpc.mac.port}")
    private int port;


    private ManagedChannel channel;

    @PostConstruct
    public void createChannel() {
        this.channel = ManagedChannelBuilder.forAddress(ip, port)
                .usePlaintext()
                .build();
    }

    @Bean("macBlockingStub")
    public ApiServiceGrpc.ApiServiceBlockingStub vostroApiStub() {
        return ApiServiceGrpc.newBlockingStub(channel);
    }

    @PreDestroy
    public void preDestroy() {
        channel.shutdown();
        try {
            if (channel.awaitTermination(5000, TimeUnit.MILLISECONDS))
                channel.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
