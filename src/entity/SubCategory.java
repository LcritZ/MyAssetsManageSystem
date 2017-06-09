package entity;

/**
 * Created by Lcrit_Z on 2017/5/5.
 */
public class SubCategory {
    private int id;

    private String subname;

    private String cate_id;

    public SubCategory(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", subname='" + subname + '\'' +
                ", cate_id='" + cate_id + '\'' +
                '}';
    }
}
