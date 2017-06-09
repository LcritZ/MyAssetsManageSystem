package entity;

import java.util.Date;

/**
 * Created by Lcrit_Z on 2017/5/5.
 */
public class ReturnBack {
    private int assets_id;

    private String assets_name;

    private int admin_id;

    private int user_id;

    private String user_name;

    private Date date;

    private String remark;

    public ReturnBack(){
        super();
    }

    @Override
    public String toString() {
        return "ReturnBack{" +
                "assets_id=" + assets_id +
                ", assets_name='" + assets_name + '\'' +
                ", admin_id=" + admin_id +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", date=" + date +
                ", remark='" + remark + '\'' +
                '}';
    }
}
