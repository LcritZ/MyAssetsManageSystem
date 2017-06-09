package entity;

import java.util.Date;

/**
 * Created by Lcrit_Z on 2017/5/5.
 */
public class LendOut {
    private int assets_id;

    private String assets_name;

    private int admin_id;

    private int user_id;

    private String user_name;

    private Date date;

    private String usage;

    private String remark;

    private int state;

    private String stateCase(){
        switch (state){
            case 1:
                return "returned";
            case 0:
                return "lendout";
            case -1:
                return null;
        }
        return null;
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

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LendOut{" +
                "assets_id=" + assets_id +
                ", assets_name='" + assets_name + '\'' +
                ", admin_id=" + admin_id +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", date=" + date +
                ", usage='" + usage + '\'' +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                '}';
    }
}
