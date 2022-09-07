package cn.fantasticmao.mundo.data.jdbc;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Abstract base class for entities, providing some common attributes.
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017-03-05
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractDomain<PK extends Number> {
    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;
    @Column(insertable = false, updatable = false)
    private LocalDateTime modifyTime;

    public abstract PK getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractDomain)) {
            return false;
        }
        AbstractDomain<?> that = (AbstractDomain<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "AbstractDomain{" +
            "createTime=" + createTime +
            ", modifyTime=" + modifyTime +
            "}";
    }
}
