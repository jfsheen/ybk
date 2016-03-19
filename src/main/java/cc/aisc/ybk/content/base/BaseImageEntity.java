package cc.aisc.ybk.content.base;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Created by sjf on 16-1-27.
 */
@MappedSuperclass
public abstract class BaseImageEntity<ID extends Serializable> extends BaseEntity<ID> {

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "path")
    private String path;

    @Column(name = "label")
    private String label;

    @Column(name = "order_num")
    private String orderNum;

    @Override
    public String toString() {
        return "BaseImageEntity{" +
                "fileName='" + fileName + '\'' +
                ", path='" + path + '\'' +
                ", label='" + label + '\'' +
                ", orderNum='" + orderNum + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseImageEntity that = (BaseImageEntity) o;
        return Objects.equal(fileName, that.fileName) &&
                Objects.equal(path, that.path) &&
                Objects.equal(label, that.label) &&
                Objects.equal(orderNum, that.orderNum);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), fileName, path, label, orderNum);
    }

    public String getOrderNum() {

        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getFileName() {

        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
