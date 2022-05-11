package cn.fantasticmao.mundo.data.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate createTime; // 创建时间
    @Column(insertable = false, updatable = false)
    private LocalDate modifyTime; // 修改时间

    @Override
    public String toString() {
        return "AbstractDomain{" +
            "id=" + id +
            ", createTime=" + createTime +
            ", modifyTime=" + modifyTime +
            '}';
    }

}
