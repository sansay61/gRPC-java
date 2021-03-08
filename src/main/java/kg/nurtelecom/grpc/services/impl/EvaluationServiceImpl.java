package kg.nurtelecom.grpc.services.impl;

import kg.nurtelecom.grpc.services.EvaluationService;
import kg.nurtelecom.grpc.services.ExtApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    @Qualifier("grpcService")
    private ExtApiService grpcService;

    @Autowired
    @Qualifier("restService")
    private ExtApiService restService;

    private List<String> readTextFromFile() throws IOException {
       return  Files.readAllLines(Paths.get("/home/sergey/Downloads/bible.txt"));
    }
    @Override
    public Long evaluateGrpc(int cycles) throws IOException {
        if (cycles<2)
            throw new IllegalArgumentException("Cycles must be greater than 2");
        List<String> txt=readTextFromFile();
        long startTs=new Date().getTime();
        IntStream.range(1,cycles).forEach(c->{
            try {
                grpcService.sendMessage(txt);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });
        return new Date().getTime()-startTs;
    }

    @Override
    public Long evaluateRest(int cycles) throws IOException {
        if (cycles<2)
            throw new IllegalArgumentException("Cycles must be greater than 2");
        List<String> txt = readTextFromFile();
        long startTs = new Date().getTime();
        IntStream.range(1,cycles).forEach(c->{
            try {
                restService.sendMessage(txt);
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return new Date().getTime() - startTs;
    }
}
