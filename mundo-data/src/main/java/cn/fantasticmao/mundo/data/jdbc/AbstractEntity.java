package cn.fantasticmao.mundo.data.jdbc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

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
public abstract class AbstractEntity<PK extends Number> {
    @ReadOnlyProperty
    private LocalDateTime createTime;
    @ReadOnlyProperty
    private LocalDateTime updateTime;

    public abstract PK getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
            "createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
