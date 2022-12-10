package cn.fantasticmao.mundo.data.jdbc;

import lombok.Getter;
import lombok.Setter;

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
public abstract class AbstractDomain<PK extends Number> {
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

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
            ", updateTime=" + updateTime +
            "}";
    }
}
