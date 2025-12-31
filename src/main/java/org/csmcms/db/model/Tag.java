package org.csmcms.db.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
public class Tag extends CmsEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static NameSetter builder() {
        return new Builder();
    }

    public interface NameSetter {
        TagBuilder setName(String name);
    }

    public interface TagBuilder {
        Tag build();
    }

    private static class Builder implements NameSetter, TagBuilder {
        private String name;
        @Override
        public TagBuilder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public Tag build() {
            Tag tag = new Tag();
            tag.setName(name);
            return tag;
        }
    }
}
