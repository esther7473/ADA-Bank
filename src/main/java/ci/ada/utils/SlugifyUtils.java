package ci.ada.utils;

import com.github.slugify.Slugify;

import java.util.UUID;

public final class SlugifyUtils {
    private SlugifyUtils() {}
    public static String generate(String text) {
        final Slugify slg = Slugify.builder().underscoreSeparator(true)
                .build();
        return slg.slugify("%s,%s".formatted(text, UUID.randomUUID().toString()));
    }
}
