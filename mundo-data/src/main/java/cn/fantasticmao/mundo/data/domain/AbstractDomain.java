package cn.fantasticmao.mundo.data.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * AbstractDomain
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/3/5
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractDomain<ID extends Number> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id; // 逻辑主键
    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime; // 创建时间
    @Column(insertable = false, updatable = false)
    private LocalDateTime modifyTime; // 修改时间

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractDomain<?> that = (AbstractDomain<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AbstractDomain{" +
            "id=" + id +
            ", createTime=" + createTime +
            ", modifyTime=" + modifyTime +
            '}';
    }

}
