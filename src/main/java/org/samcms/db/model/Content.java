package org.samcms.db.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;
    private String description;

    @Column(name="created_at") @CreationTimestamp
    private Date createdAt;
    @Column(name="updated_at") @UpdateTimestamp
    private Date updatedAt;

    @Column(name="content_type")
    private String contentType;
    @Column(name="content_path")
    private String contentPath;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "content_tags",
            joinColumns = @JoinColumn(name = "column_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    public long getId() {
        return id;
    }

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

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
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

    public interface TagsSetter {
        TagsSetter setTags(Set<Tag> tags);
        Content build();
    }

    private static class Builder implements NameSetter, DescriptionSetter, ContentTypeSetter, ContentPathSetter, TagsSetter {

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
            this.contentPath = contentType;
            return this;
        }

        @Override
        public TagsSetter setContentPath(String path) {
            this.contentPath = path;
            return this;
        }

        @Override
        public TagsSetter setTags(Set<Tag> tags) {
            this.tags = tags;
            return this;
        }

        @Override
        public Content build() {
            Content content = new Content();
            content.setName(name);
            content.setDescription(description);
            content.setContentType(contentType);
            content.setContentPath(contentPath);

            return content;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Content content)) return false;
        return id == content.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
