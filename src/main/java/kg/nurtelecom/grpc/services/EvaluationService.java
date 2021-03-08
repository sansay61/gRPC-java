package kg.nurtelecom.grpc.services;

import java.io.IOException;

public interface EvaluationService {
    Long evaluateGrpc(int cycles) throws IOException;
    Long evaluateRest(int cycles) throws IOException;
}
