package greachconf;

import io.micronaut.http.HttpRequest;
import io.micronaut.i18n.LocaleResolver;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Singleton;
import java.util.Locale;

@Singleton
public class DefaultLocaleResolver implements LocaleResolver {

    @Override
    public @Nonnull Locale resolveLocale(@Nullable HttpRequest request) {
        return Locale.getDefault();
    }
}
