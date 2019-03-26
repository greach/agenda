package greachconf.vm;

import greachconf.views.MarkdownUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class Speaker implements Comparable<Speaker> {

    @Nonnull
    private String uid;

    @Nonnull
    private String name;

    @Nullable
    private List<String> bio;

    @Nullable
    private String twitter;

    @Nullable
    private String image;

    public Speaker() {}

    @Nonnull
    public String getUid() {
        return uid;
    }

    public void setUid(@Nonnull String uid) {
        this.uid = uid;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    @Nullable
    public List<String> getBio() {
        //TODO should not be necessary
        return bio.stream().map(MarkdownUtil::htmlFromMarkdown).collect(Collectors.toList());
    }

    public void setBio(@Nullable List<String> bio) {
        this.bio = bio;
    }

    @Nullable
    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(@Nullable String twitter) {
        this.twitter = twitter;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(@Nullable String image) {
        this.image = image;
    }

    @Override
    public int compareTo(Speaker o) {
        return replaceAccents(getName().toLowerCase()).compareTo(replaceAccents(o.getName().toLowerCase()));
    }

    String replaceAccents(String str) {
        return str.replaceAll("á", "a")
                .replaceAll("é", "e")
                .replaceAll("ó", "o")
                .replaceAll("é", "e")
                .replaceAll("ú", "u");

    }
}
