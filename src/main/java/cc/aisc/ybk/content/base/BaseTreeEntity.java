package cc.aisc.ybk.content.base;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjf on 16-1-27.
 */
@MappedSuperclass
public abstract class BaseTreeEntity<ID extends Serializable, T extends BaseEntity> extends BaseEntity<ID> {

    @Column(name = "tree_lvl")
    private Integer level = 0;

    @Column(name =  "sort")
    private Integer sort = 0;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private T parent;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "parent")
    private List<T> children = new ArrayList<>();

    public BaseTreeEntity() {
    }

    public BaseTreeEntity(Integer level, T parent) {
        this.level = level + 1;
        this.parent = parent;
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public Integer getLevel() {

        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "BaseTreeEntity{" +
                "level=" + level +
                ", sort=" + sort +
                ", parent=" + parent +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseTreeEntity<?, ?> that = (BaseTreeEntity<?, ?>) o;
        return Objects.equal(level, that.level) &&
                Objects.equal(sort, that.sort) &&
                Objects.equal(parent, that.parent) &&
                Objects.equal(children, that.children);
    }



    /*@Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), level, sort, parent, children);
    }*/
}