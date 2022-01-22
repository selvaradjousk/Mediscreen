package exception;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * The Class FeignErrorDecoder.
 */
public class FeignErrorDecoder implements ErrorDecoder {

    /** The default error decoder. */
    private final ErrorDecoder defaultErrorDecoder = new Default();

    /**
     * Decode.
     *
     * @param methodKey the method key
     * @param response the response
     * @return the exception
     */
    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() == 404) {

            return new ResourceNotFoundException("Patient not found");
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}