package kg.nurtelecom.grpc.components;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import kg.nurtelecom.grpc.api.ApiServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class GrpcServerBean {

    @Value("${grpc.port}")
    private int grpcPort;

    private Server server;

    @PostConstruct
    public void createServer() {
        server = ServerBuilder.forPort(grpcPort)
                .addService(new ApiServiceImpl()).build();

        log.info(String.format("Starting grpc server on port %d", grpcPort));
        try {
            server.start();
        } catch (Throwable e) {
            log.error("Unable to start grpc server");
            throw new RuntimeException("Unable to start grpc server");
        }
        log.info("Grpc server started");

    }

    @PreDestroy
    public void predestroy(){
        server.shutdown();
        try {
            if (!server.awaitTermination(5000,TimeUnit.MILLISECONDS)){
                server.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
