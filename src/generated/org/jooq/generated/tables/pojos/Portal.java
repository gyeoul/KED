/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.pojos;


import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Portal implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID guid;
    private Object point;
    private String name;
    private String image;
    private LocalDateTime lastUpdate;
    private Long late6;
    private Long lnge6;

    public Portal() {}

    public Portal(Portal value) {
        this.guid = value.guid;
        this.point = value.point;
        this.name = value.name;
        this.image = value.image;
        this.lastUpdate = value.lastUpdate;
        this.late6 = value.late6;
        this.lnge6 = value.lnge6;
    }

    public Portal(
        UUID guid,
        Object point,
        String name,
        String image,
        LocalDateTime lastUpdate,
        Long late6,
        Long lnge6
    ) {
        this.guid = guid;
        this.point = point;
        this.name = name;
        this.image = image;
        this.lastUpdate = lastUpdate;
        this.late6 = late6;
        this.lnge6 = lnge6;
    }

    /**
     * Getter for <code>ingress.portal.guid</code>.
     */
    @NotNull
    public UUID getGuid() {
        return this.guid;
    }

    /**
     * Setter for <code>ingress.portal.guid</code>.
     */
    public Portal setGuid(UUID guid) {
        this.guid = guid;
        return this;
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public Object getPoint() {
        return this.point;
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public Portal setPoint(Object point) {
        this.point = point;
        return this;
    }

    /**
     * Getter for <code>ingress.portal.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>ingress.portal.name</code>.
     */
    public Portal setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Getter for <code>ingress.portal.image</code>.
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Setter for <code>ingress.portal.image</code>.
     */
    public Portal setImage(String image) {
        this.image = image;
        return this;
    }

    /**
     * Getter for <code>ingress.portal.last_update</code>.
     */
    public LocalDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * Setter for <code>ingress.portal.last_update</code>.
     */
    public Portal setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    /**
     * Getter for <code>ingress.portal.late6</code>.
     */
    @NotNull
    public Long getLate6() {
        return this.late6;
    }

    /**
     * Setter for <code>ingress.portal.late6</code>.
     */
    public Portal setLate6(Long late6) {
        this.late6 = late6;
        return this;
    }

    /**
     * Getter for <code>ingress.portal.lnge6</code>.
     */
    @NotNull
    public Long getLnge6() {
        return this.lnge6;
    }

    /**
     * Setter for <code>ingress.portal.lnge6</code>.
     */
    public Portal setLnge6(Long lnge6) {
        this.lnge6 = lnge6;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Portal other = (Portal) obj;
        if (this.guid == null) {
            if (other.guid != null)
                return false;
        }
        else if (!this.guid.equals(other.guid))
            return false;
        if (this.point == null) {
            if (other.point != null)
                return false;
        }
        else if (!this.point.equals(other.point))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.image == null) {
            if (other.image != null)
                return false;
        }
        else if (!this.image.equals(other.image))
            return false;
        if (this.lastUpdate == null) {
            if (other.lastUpdate != null)
                return false;
        }
        else if (!this.lastUpdate.equals(other.lastUpdate))
            return false;
        if (this.late6 == null) {
            if (other.late6 != null)
                return false;
        }
        else if (!this.late6.equals(other.late6))
            return false;
        if (this.lnge6 == null) {
            if (other.lnge6 != null)
                return false;
        }
        else if (!this.lnge6.equals(other.lnge6))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.guid == null) ? 0 : this.guid.hashCode());
        result = prime * result + ((this.point == null) ? 0 : this.point.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.image == null) ? 0 : this.image.hashCode());
        result = prime * result + ((this.lastUpdate == null) ? 0 : this.lastUpdate.hashCode());
        result = prime * result + ((this.late6 == null) ? 0 : this.late6.hashCode());
        result = prime * result + ((this.lnge6 == null) ? 0 : this.lnge6.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Portal (");

        sb.append(guid);
        sb.append(", ").append(point);
        sb.append(", ").append(name);
        sb.append(", ").append(image);
        sb.append(", ").append(lastUpdate);
        sb.append(", ").append(late6);
        sb.append(", ").append(lnge6);

        sb.append(")");
        return sb.toString();
    }
}