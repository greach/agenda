package io.micronaut.views;

import io.micronaut.http.HttpRequest;

import javax.annotation.Nonnull;
import java.util.Map;

public interface ModelDecorator {

    @Nonnull
    Map<String, Object> modelForRequest(HttpRequest request);
}