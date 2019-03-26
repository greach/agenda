package greachconf.views;

import greachconf.vm.Speaker;
import greachconf.vm.Talk;
import io.micronaut.core.beans.BeanMap;
import io.micronaut.http.HttpRequest;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.model.ViewModelProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class MarkdownViewModelProcessor implements ViewModelProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(MarkdownViewModelProcessor.class);

    public static final String KEY_TALK = "talk";
    public static final String KEY_ABOUT = "about";
    public static final String KEY_SPEAKER = "speaker";
    public static final String KEY_BIO = "bio";
    public static final String KEY_LOCATION = "location";

    @Override
    public void process(@Nonnull HttpRequest<?> request, @Nonnull ModelAndView<Map<String, Object>> modelAndView) {
        modelAndView.getModel().ifPresent(viewModel -> {
            if (LOG.isTraceEnabled()) {
                LOG.trace("model {}", viewModel.getClass().getSimpleName());
            }

            if (viewModel.containsKey(KEY_TALK) && viewModel.get(KEY_TALK) instanceof Talk) {
                if (LOG.isTraceEnabled()) {
                    LOG.trace("model is talk");
                }

                Map<String, Object> m = new HashMap<>(BeanMap.of(((Talk) viewModel.get(KEY_TALK))));

                List<String> about = ((Talk) viewModel.get(KEY_TALK)).getAbout();
                m.put(KEY_ABOUT, about.stream()
                        .map(MarkdownUtil::htmlFromMarkdown)
                        .collect(Collectors.toList()));
                String location = ((Talk) viewModel.get(KEY_TALK)).getLocation();
                if (location != null) {
                    m.put(KEY_LOCATION, MarkdownUtil.htmlFromMarkdown(location));
                }

                viewModel.replace(KEY_TALK, m);
            }
            if (viewModel.containsKey(KEY_SPEAKER) &&
                    viewModel.get(KEY_SPEAKER) instanceof Speaker) {
                if (LOG.isTraceEnabled()) {
                    LOG.trace("model is speaker");
                }
                Map<String, Object> m = new HashMap<>(BeanMap.of(((Speaker) viewModel.get(KEY_SPEAKER))));

                List<String> bio = ((Speaker) viewModel.get(KEY_SPEAKER)).getBio();

                if (LOG.isTraceEnabled()) {
                    LOG.trace("bio: {}", bio != null ? String.join(". ", bio) : "bio is null");
                }
                if (bio != null) {
                    m.put(KEY_BIO, bio.stream()
                            .map(MarkdownUtil::htmlFromMarkdown)
                            .collect(Collectors.toList()));
                }

                viewModel.replace(KEY_SPEAKER, m);
            }
        });
    }
}
