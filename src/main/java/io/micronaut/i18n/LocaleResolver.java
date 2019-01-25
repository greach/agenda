package io.micronaut.i18n;

import io.micronaut.http.HttpRequest;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public interface LocaleResolver {

    @Nonnull
    Locale resolveLocale(@Nullable HttpRequest request);
}
