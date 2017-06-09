package dao;

import entity.Admin;

/**
 * Created by Lcrit_Z on 2017/5/5.
 * 管理员的信息
 */
public interface AdminDao {

    public boolean changePasswd(Admin admin, String newpasswd);

    public boolean login(Admin admin);

}
