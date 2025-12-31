package org.csmcms.db.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Content extends CmsEntity {

    private String name;
    private String description;

    @Column(name="content_type")
    private String contentType;
    @Column(name="content_path")
    private String contentPath;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "content_tags",
            joinColumns = @JoinColumn(name = "column_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public String getContentType() {
        return contentType;
    }

    public String getContentPath() {
        return contentPath;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public static NameSetter builder() {
        return new Builder();
    }

    public interface NameSetter {
        DescriptionSetter setName(String name);
    }

    public interface DescriptionSetter {
        ContentTypeSetter setDescription(String description);
    }

    public interface ContentTypeSetter {
        ContentPathSetter setContentType(String contentType);
    }

    public interface ContentPathSetter {
        TagsSetter setContentPath(String path);
    }

    public interface CreateTagSet {
        TagsSetter initTags();
    }

    public interface TagsSetter {
        TagSetter initTags();
        Content build();
    }

    public interface TagSetter {
        TagSetter addTag(String tag);
        TagSetter addTag(Tag tag);
        Content build();
    }

    private static class Builder implements NameSetter, DescriptionSetter, ContentTypeSetter, ContentPathSetter, TagsSetter, TagSetter {

        private String name;
        private String description;

        private String contentType;
        private String contentPath;

        Set<Tag> tags = new HashSet<Tag>();

        @Override
        public DescriptionSetter setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public ContentTypeSetter setDescription(String description) {
            this.description = description;
            return this;
        }

        @Override
        public ContentPathSetter setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        @Override
        public TagsSetter setContentPath(String path) {
            this.contentPath = path;
            return this;
        }

        @Override
        public TagSetter initTags() {
            if (this.tags == null) {
                this.tags = new HashSet<Tag>();
            }
            return this;
        }

        @Override
        public TagSetter addTag(Tag tag) {
            if (this.tags != null) {
                this.tags.add(tag);
            }

            return this;
        }

        @Override
        public TagSetter addTag(String tag) {
            if (this.tags != null) {
                this.tags.add(Tag.builder().setName(tag).build());
            }

            return this;
        }
        @Override
        public Content build() {
            Content content = new Content();
            content.setName(name);
            content.setDescription(description);
            content.setContentType(contentType);
            content.setContentPath(contentPath);
            content.setTags(tags);

            return content;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Content content)) return false;
        return this.getId() == content.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }
}
