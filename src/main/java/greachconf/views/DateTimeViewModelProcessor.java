package greachconf.views;

import greachconf.vm.Talk;
import io.micronaut.core.beans.BeanMap;
import io.micronaut.http.HttpRequest;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.ViewsFilter;
import io.micronaut.views.model.ViewModelProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class DateTimeViewModelProcessor implements ViewModelProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(DateTimeViewModelProcessor.class);

    public static final String START = "start";
    public static final String START_DATE = "startDate";
    public static final String START_TIME = "startTime";
    public static final String DATE_SEPARATOR = "/";
    public static final String TIME_SEPARATOR = ":";
    public static final String KEY_TALK = "talk";

    @Override
    public void process(@Nonnull HttpRequest<?> request, @Nonnull ModelAndView<Map<String, Object>> modelAndView) {
        modelAndView.getModel().ifPresent(viewModel -> {
            if (viewModel.containsKey(KEY_TALK) &&
                    viewModel.get(KEY_TALK) instanceof Talk) {
                Map<String, Object> m = new HashMap<>(BeanMap.of(((Talk) viewModel.get(KEY_TALK))));
                LocalDateTime start = ((Talk) viewModel.get(KEY_TALK)).getStart();
                m.put(START_DATE, start.getDayOfMonth() + DATE_SEPARATOR + start.getMonth() + DATE_SEPARATOR + start.getYear());
                String minutes = start.getMinute() < 10 ? "0" + start.getMinute() : "" + start.getMinute();
                String hours = start.getHour() < 10 ? "0" + start.getHour() : "" + start.getHour();
                m.put(START_TIME, hours + TIME_SEPARATOR + minutes);
                viewModel.replace(KEY_TALK, m);
            }
        });
    }
}
