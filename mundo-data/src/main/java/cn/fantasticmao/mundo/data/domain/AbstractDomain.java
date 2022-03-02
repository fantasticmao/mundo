package cn.fantasticmao.mundo.data.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * AbstractDomain
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/3/5
 */
@MappedSuperclass
public abstract class AbstractDomain<ID extends Number> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id; // 逻辑主键
    @Column(insertable = false, updatable = false)
    private Timestamp createTime; // 创建时间
    @Column(insertable = false, updatable = false)
    private Timestamp modifyTime; // 修改时间

    @Override
    public String toString() {
        return "AbstractDomain{" +
            "id=" + id +
            ", createTime=" + createTime +
            ", modifyTime=" + modifyTime +
            '}';
    }

    public ID getId() {
        return id;
    }

    protected void setId(final ID id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    protected void setCreateTime(final Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    protected void setModifyTime(final Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }
}
