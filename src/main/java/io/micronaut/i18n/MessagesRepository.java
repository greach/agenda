package io.micronaut.i18n;

import io.micronaut.core.io.ResourceLoader;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import io.micronaut.http.HttpRequest;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.model.ViewModelProcessor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Singleton
public class MessagesRepository implements MessageSource, ViewModelProcessor {

    public static final String KEY_MESSAGES = "messages";
    private final ResourceLoader resourceLoader;
    private Properties defaultProperties = new Properties();

    public MessagesRepository(ClassPathResourceLoader classPathResourceLoader) {
        this.resourceLoader = classPathResourceLoader;
    }

    @PostConstruct
    void initialize() {
        String propertiesPath = "i18n/messages.properties";
        Optional<InputStream> inputStreamOptional = resourceLoader.getResourceAsStream(propertiesPath);
        if (inputStreamOptional.isPresent()) {
            try {
                defaultProperties.load(inputStreamOptional.get());
            } catch (IOException ex) {
            }
        }
    }

    @Nullable
    @Override
    public String getMessage(String code,
                             @Nullable Object[] args,
                             @Nullable String defaultMessage,
                             Locale locale) {
        if (defaultProperties == null) {
            return null;
        }
        Object obj = defaultProperties.get(code);
        return (obj instanceof String) ? (String) obj : null;
    }

    private Map<String, Object> propertiesToMap(Properties properties) {
        Map<String, Object> result = new HashMap<>();
        for (Object k : defaultProperties.keySet()) {
            if (k instanceof String) {
                result.put((String)k, defaultProperties.get(k));
            }
        }
        return result;
    }

    @Override
    public void process(@Nonnull HttpRequest<?> request, @Nonnull ModelAndView<Map<String, Object>> modelAndView) {
        if (defaultProperties != null) {

        Map<String, Object> viewModel = modelAndView.getModel().orElseGet(() -> {
            final HashMap<String, Object> newModel = new HashMap<>(1);
            modelAndView.setModel(newModel);
            return newModel;
        });
        viewModel.putIfAbsent(KEY_MESSAGES, propertiesToMap(defaultProperties));
        }
    }
}
