package io.micronaut.i18n;

import io.micronaut.context.env.PropertySource;
import io.micronaut.core.io.ResourceLoader;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import io.micronaut.http.HttpRequest;
import io.micronaut.views.ModelDecorator;

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
public class MessagesRepository implements MessageSource, ModelDecorator {

    private final ResourceLoader resourceLoader;

    public MessagesRepository(ClassPathResourceLoader classPathResourceLoader) {
        this.resourceLoader = classPathResourceLoader;
    }

    Properties defaultProperties = new Properties();

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

    @Override
    public Map<String, Object> modelForRequest(HttpRequest request) {
        if (defaultProperties == null) {
            return new HashMap<>();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("messages", propertiesToMap(defaultProperties));
        return result;
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
}
