package com.repotracker.client.error;

import com.repotracker.controller.error.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new UserNotFoundException("User not found in GitHub");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}