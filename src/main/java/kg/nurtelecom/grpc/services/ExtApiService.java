package kg.nurtelecom.grpc.services;

import java.io.IOException;
import java.util.List;

public interface ExtApiService {
    List<String> sendMessage(List<String> text) throws IOException;
}
