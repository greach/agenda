package io.micronaut.i18n;

import javax.annotation.Nullable;
import java.util.Locale;

public interface MessageSource {

    @Nullable
    String getMessage(String code,
                      @Nullable Object[] args,
                      @Nullable String defaultMessage,
                                Locale locale);
}
