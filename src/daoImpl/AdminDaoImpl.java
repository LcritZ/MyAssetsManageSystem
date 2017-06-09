package daoImpl;

import dao.AdminDao;
import dao.BaseDao;
import entity.Admin;
import org.apache.commons.dbutils.QueryRunner;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Lcrit_Z on 2017/5/7.
 */
public class AdminDaoImpl extends BaseDao<Admin> implements AdminDao{

    @Override
    public boolean add(Admin admin) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "insert into admin values(0,?,?)";
            String[] param = new String[2];
            param[0] = admin.getName();
            param[1] = admin.getPasswd();
            QueryRunner qr = new QueryRunner();
            int res = qr.update(conn,sql,param);
            if (res>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        
    }



    @Override
    public boolean changePasswd(Admin admin, String newpasswd) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String validateAdmin = "select admin_passwd from admin where admin_name = '"+admin.getName()+"'";
            PreparedStatement prestmt = conn.prepareStatement(validateAdmin);
            ResultSet rs = prestmt.executeQuery();
            while (rs.next()){
                if (!admin.getPasswd().equals(rs.getString(1))){
                    System.out.println("原密码错误");
                    rs.close();
                    prestmt.close();
                    conn.close();
                    return false;
                }else {
                    continue;
                }
            }
            String sql = "update admin set admin_passwd = '"+newpasswd+"'"+"where admin_name = '"+admin.getName()+"'";
            int flag = prestmt.executeUpdate(sql);
            rs.close();
            prestmt.close();
            conn.close();
            if (flag>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("更改失败哦");
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean login(Admin admin) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select * from admin where admin_name = '"+admin.getName()+"' and admin_passwd = '"+admin.getPasswd()+"'";
            PreparedStatement prestmt = conn.prepareStatement(sql);
            ResultSet rs = prestmt.executeQuery();
            boolean flag = false;
            while (rs.next()){
                if (rs.getString(2).equals(admin.getName())&&rs.getString(3).equals(admin.getPasswd())) {
                    flag = true;
                    break;
                }
            }
            rs.close();
            prestmt.close();
            conn.close();
            return flag;
        } catch (SQLException e) {
            System.out.println("登陆失败");
            e.printStackTrace();
        }

        return false;
    }
}
