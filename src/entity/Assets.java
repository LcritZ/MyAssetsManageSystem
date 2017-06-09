package entity;

import java.util.Date;

/**
 * Created by Lcrit_Z on 2017/5/5.
 */
public class Assets {
    private int id;

    private String name;

    private String categoryname;

    private String subCategoryname;

    private String value;

    private String userName;

    private Date purchaseDate;

    private int state;

    private int user;

    private int category;

    public String getSubCategoryname() {
        return subCategoryname;
    }

    public void setSubCategoryname(String subCategoryname) {
        this.subCategoryname = subCategoryname;
    }


    public Assets() {
        id = 0;
        name = null;
        categoryname = null;
        subCategoryname = null;
        value = null;
        userName = null;
        purchaseDate = null;
        state = -1;
    }

    /**
     * no compeleted
     */
    public String getStateCase(){
        switch (state){
            case 1:
                return "available";
            case 0:
                return "lended out";
            case -1:
                return "brokdown";
        }
        return null;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getSubClassname() {
        return subCategoryname;
    }

    public void setSubClassname(String subClassname) {
        this.subCategoryname = subClassname;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Assets{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryname='" + categoryname + '\'' +
                ", subCategoryname='" + subCategoryname + '\'' +
                ", value='" + value + '\'' +
                ", userName='" + userName + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", state=" + state + '\'' +
                '}';
    }
}
