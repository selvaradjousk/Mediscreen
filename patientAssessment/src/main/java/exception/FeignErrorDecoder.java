package exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() == 404) {

            return new ResourceNotFoundException("Patient not found");
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}