package entity;

import java.sql.Date;

/**
 * Created by Lcrit_Z on 2017/6/9.
 */
public class Assetsindb {
    private int assets_id;

    private String assets_name;

    private String assets_category;

    private String assets_subcategory;

    private String assets_value;

    private String assets_user;

    private Date lasthand_date;

    private int state;

    public Assetsindb(int assets_id, String assets_name, String assets_category, String assets_subcategory, String assets_value, String assets_user, Date lasthand_date, int state) {
        this.assets_id = assets_id;
        this.assets_name = assets_name;
        this.assets_category = assets_category;
        this.assets_subcategory = assets_subcategory;
        this.assets_value = assets_value;
        this.assets_user = assets_user;
        this.lasthand_date = lasthand_date;
        this.state = state;
    }

    public Assetsindb() {
        
    }

    public int getAssets_id() {
        return assets_id;
    }

    public void setAssets_id(int assets_id) {
        this.assets_id = assets_id;
    }

    public String getAssets_name() {
        return assets_name;
    }

    public void setAssets_name(String assets_name) {
        this.assets_name = assets_name;
    }

    public String getAssets_category() {
        return assets_category;
    }

    public void setAssets_category(String assets_category) {
        this.assets_category = assets_category;
    }

    public String getAssets_subcategory() {
        return assets_subcategory;
    }

    public void setAssets_subcategory(String assets_subcategory) {
        this.assets_subcategory = assets_subcategory;
    }

    public String getAssets_value() {
        return assets_value;
    }

    public void setAssets_value(String assets_value) {
        this.assets_value = assets_value;
    }

    public String getAssets_user() {
        return assets_user;
    }

    public void setAssets_user(String assets_user) {
        this.assets_user = assets_user;
    }

    public Date getLasthand_date() {
        return lasthand_date;
    }

    public void setLasthand_date(Date lasthand_date) {
        this.lasthand_date = lasthand_date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Assetsindb{" +
                "assets_id=" + assets_id +
                ", assets_name='" + assets_name + '\'' +
                ", assets_category='" + assets_category + '\'' +
                ", assets_subcategory='" + assets_subcategory + '\'' +
                ", assets_value='" + assets_value + '\'' +
                ", assets_userName='" + assets_user + '\'' +
                ", purchaseDate=" + lasthand_date +
                ", state=" + state +
                '}';
    }
}
