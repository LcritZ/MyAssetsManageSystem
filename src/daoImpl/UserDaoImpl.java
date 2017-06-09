package daoImpl;

import dao.BaseDao;
import dao.UserDao;
import entity.User;
import util.ConnectionFactory;
import util.JDBCPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lcrit_Z on 2017/5/5.
 */
public class UserDaoImpl extends BaseDao implements UserDao{


    @Override
    public boolean isRegester(User user) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select user_name from user where user_name = '"+user.getName()+"'";
            PreparedStatement prestmt = conn.prepareStatement(sql);
            ResultSet rs = prestmt.executeQuery();
            if (rs.next()){
                rs.close();
                prestmt.close();
                conn.close();
                return true;
            }
            rs.close();
            prestmt.close();
            conn.close();
            return false;
        } catch (SQLException e) {
            System.out.println("该用户未注册");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<User> getAllUser() {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select * from user";
            PreparedStatement prestmt = conn.prepareStatement(sql);
            ResultSet rs = prestmt.executeQuery();
            ArrayList<User> list = new ArrayList<User>();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("user_name"));
                user.setJob(rs.getString("job"));
                user.setRemark(rs.getString("remark"));
                list.add(user);
            }
            rs.close();
            prestmt.close();
            conn.close();
            return list;
        } catch (SQLException e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "insert into user values(0,"+"'"+user.getName()+"'"+",'"+user.getJob()+"'"+",'"+user.getRemark()+"' );";
            PreparedStatement prestmt = conn.prepareStatement(sql);
            int flag=  prestmt.executeUpdate(sql);
            prestmt.close();
            conn.close();
            if (flag>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("添加失败");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteUser(String name){
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "delete  from user where user_name = '"+name+"'";
            PreparedStatement prestmt = conn.prepareStatement(sql);
            int flag= prestmt.executeUpdate(sql);
            prestmt.close();
            conn.close();
            if (flag>0){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("删除失败");
            e.printStackTrace();
        }
        return false;
    }

    public int getIdByname(String name){
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select user_id from user where user_name = '"+name+"'";
            PreparedStatement prestmt = null;
            prestmt = conn.prepareStatement(sql);
            ResultSet rs = prestmt.executeQuery(sql);
            int user_id = 0;
            while (rs.next()){
                 user_id =rs.getInt(1);
                break;
            }
            prestmt.close();
            conn.close();
            return user_id;

        } catch (SQLException e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
        return -1;
    }

}
