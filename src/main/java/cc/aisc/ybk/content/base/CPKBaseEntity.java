package cc.aisc.ybk.content.base;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sjf on 16-1-12.
 */

@MappedSuperclass
public abstract class CPKBaseEntity <ID extends Serializable> extends AbstractEntity<ID>{

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ID id;

    @Column(name = "version")
    @Version
    private Integer version;

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(final ID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    private void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "CPKBaseEntity{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CPKBaseEntity<?> that = (CPKBaseEntity<?>) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), id, version);
    }
}
