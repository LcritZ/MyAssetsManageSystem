package daoImpl;

import dao.AssetsDao;
import dao.BaseDao;
import entity.*;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Lcrit_Z on 2017/5/7.
 */
public class AssetsDaoImpl extends BaseDao<Assets> implements AssetsDao {

    @Override
    public boolean add(Assets asset) {
        try {
            Connection conn = ConnectionFactory.getConn();//(assets_name,assets_catagory,assets_subcatagory,assets_value)
            String sql = "insert into assets (assets_name,assets_category,assets_subcategory,assets_value,lasthand_date) values(?,?,?,?,?)";
            PreparedStatement prestmt = conn.prepareStatement(sql);
            prestmt.setString(1,asset.getName());
            prestmt.setString(2, asset.getCategoryname());
            prestmt.setString(3,asset.getSubCategoryname());
            prestmt.setString(4,asset.getValue());
            prestmt.setDate(5,new java.sql.Date(new Date().getTime()));
            int res = prestmt.executeUpdate();
            if (res>0) {
                prestmt.close();
                conn.close();
                return true;
            }else {
                prestmt.close();
                conn.close();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("添加失败");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(String assetname) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "delete from assets where assets_name = '"+assetname+"'";
            PreparedStatement prestmt = conn.prepareStatement(sql);
            int res = prestmt.executeUpdate();
            prestmt.close();
            conn.close();
            if (res>0){
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

    @Override
    public boolean alter(String assets_name,String propertity, String value) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "update assets set "+propertity+"=? ,lasthand_date = ? where assets_name = ?";
            String sql2 = "update assets set value = ? lasthand_date = ? where assets_name = ?";
            QueryRunner qr = new QueryRunner();
            Object[] params = { value , new java.sql.Date(new Date().getTime()),assets_name};
            int res = qr.update(conn,sql,params); 
            conn.close();
            if (res>0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            System.out.println("更改失败");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean LendOut(String assets_name, String user_name, int admin_id,String remark) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select state,assets_user from assets where assets_name = '"+assets_name+"'";
            QueryRunner qr = new QueryRunner();
            Object[] res = qr.query(conn,sql,new ArrayHandler());
            if ((Integer)res[0]==1&&(String)res[1]==null){
                String updateassert = "update assets set assets_user = ?,state = 0 ,lasthand_date = ? where assets_name = ?";
                Object[] param = {user_name, new java.sql.Date(new Date().getTime()),assets_name};
               int updateassets = qr.update(conn,updateassert,param);
                String updateout = "insert into assets_out values(0,?,?,?,?,?)";
                Object[] upparam = new Object[]{assets_name,new java.sql.Date(new Date().getTime()),user_name,admin_id,remark};
                int isUpdate =  qr.update(conn,updateout,upparam);
                conn.close();
                if (isUpdate>0&&updateassets>0){
                    return true;
                }
            }else {
                if ((Integer)res[0] == 0){
                    System.out.println(assets_name+"has been lended out");
                }
                throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println(user_name+" is not exist");
            System.out.println("资产不可用");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean returnBack(String assets_name, String user_name,int admin_id,String remark) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "update assets set assets_user = null,state = 1,lasthand_date = ? where assets_name = ?";
            Object[] param = {new java.sql.Date(new Date().getTime()),assets_name};
            QueryRunner qr = new QueryRunner();
            qr.update(conn,sql,param);
            String updateout = "delete from assets_out where assets_name = '"+assets_name+"'";
            String updatein = "update assets_in set assets_remark = ?,assets_in_date = ? where assets_name = ?";
            Object[] params = {remark, new java.sql.Date(new Date().getTime()),assets_name};
            int out = qr.update(conn,updateout);
            int in = qr.update(conn,updatein,params);
            conn.close();
            if (in>0&&out>0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Assets> searchAll() {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select * from assets";
            QueryRunner qr = new QueryRunner();
            List<Assets> assetsList = new ArrayList<Assets>();
            List<Assetsindb> list = new ArrayList<>();

            Map<String, String> map = new HashMap<String, String>();
            map.put("assets_id", "assets_id");
            map.put("assets_name", "assets_name");
            map.put("assets_category", "assets_category");
            map.put("assets_subcategory", "assets_subcategory");
            map.put("assets_value", "assets_value");
            map.put("assets_user", "assets_user");
            map.put("lasthand_date", "lasthand_date");
            map.put("state", "state");
            BeanProcessor bean = new BeanProcessor(map);
// 将GenerousBeanProcessor对象传递给BasicRowProcessor
            RowProcessor processor = new BasicRowProcessor(bean);
            list = (List<Assetsindb>)qr.query(conn, sql, new BeanListHandler<Assetsindb>(Assetsindb.class,processor));
            Iterator<Assetsindb> iterator = list.iterator();
            while (iterator.hasNext()){
                System.out.println("in adi: "+iterator.next());
            }
            System.out.println();
            return assetsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Assets> searchAll2() {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select * from assets";
            QueryRunner qr = new QueryRunner();
            List<Assets> assetsList = new ArrayList<Assets>();
            //List<Assetsindb> list = new ArrayList<>();
            Map<String, String> map = new HashMap<String, String>();
            map.put("assets_id", "id");
            map.put("assets_name", "name");
            map.put("assets_category", "categoryname");
            map.put("assets_subcategory", "subCategoryname");
            map.put("assets_value", "value");
            map.put("assets_user", "username");
            map.put("lasthand_date", "purchaseDate");
            map.put("state", "state");
            BeanProcessor bean = new BeanProcessor(map);
// 将GenerousBeanProcessor对象传递给BasicRowProcessor
            RowProcessor processor = new BasicRowProcessor(bean);
            assetsList = (List<Assets>)qr.query(conn, sql, new BeanListHandler<Assets>(Assets.class,processor));
            Iterator<Assets> iterator = assetsList.iterator();
            while (iterator.hasNext()){
                System.out.println("in adi: "+iterator.next());
            }
            System.out.println();
            return assetsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    @Override
    public List<Assets> searchByCategory(String category_name) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select * from assets where assets_category = ?";
            QueryRunner qr = new QueryRunner();
            Object[] params = {category_name};
            List<Assets> assetsList = new ArrayList<>();
            Map<String, String> map = new HashMap<String, String>();
            map.put("assets_id", "id");
            map.put("assets_name", "name");
            map.put("assets_category", "categoryname");
            map.put("assets_subcategory", "subCategoryname");
            map.put("assets_value", "value");
            map.put("assets_user", "username");
            map.put("lasthand_date", "purchaseDate");
            map.put("state", "state");
            BeanProcessor bean = new BeanProcessor(map);
// 将GenerousBeanProcessor对象传递给BasicRowProcessor
            RowProcessor processor = new BasicRowProcessor(bean);
            assetsList = qr.query(conn, sql, new BeanListHandler<Assets>(Assets.class,processor),params);
            return assetsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Assets> searchBySubcategory(String sub_name) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select * from assets where assets_subcategory = ?";
            QueryRunner qr = new QueryRunner();
            Object[] params = {sub_name};
            List<Assets> assetsList = new ArrayList<>();
            Map<String, String> map = new HashMap<String, String>();
            map.put("assets_id", "id");
            map.put("assets_name", "name");
            map.put("assets_category", "categoryname");
            map.put("assets_subcategory", "subCategoryname");
            map.put("assets_value", "value");
            map.put("assets_user", "username");
            map.put("lasthand_date", "purchaseDate");
            map.put("state", "state");
            BeanProcessor bean = new BeanProcessor(map);
// 将GenerousBeanProcessor对象传递给BasicRowProcessor
            RowProcessor processor = new BasicRowProcessor(bean);
            assetsList = qr.query(conn, sql, new BeanListHandler<Assets>(Assets.class,processor),params);
            return assetsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Assets searchById(int assets_id) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select * from assets where assets_id = ?";
            QueryRunner qr = new QueryRunner();
            Object[] params = {assets_id};
            Assets assets ;
            Map<String, String> map = new HashMap<String, String>();
            map.put("assets_id", "id");
            map.put("assets_name", "name");
            map.put("assets_category", "categoryname");
            map.put("assets_subcategory", "subCategoryname");
            map.put("assets_value", "value");
            map.put("assets_user", "username");
            map.put("lasthand_date", "purchaseDate");
            map.put("state", "state");
            BeanProcessor bean = new BeanProcessor(map);
// 将GenerousBeanProcessor对象传递给BasicRowProcessor
            RowProcessor processor = new BasicRowProcessor(bean);
            assets = (Assets) qr.query(conn, sql, new BeanHandler<Assets>(Assets.class,processor),params);
            return assets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Assets searchByName(String assetsname) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select * from assets where assets_name = ?";
            QueryRunner qr = new QueryRunner();
            Object[] params = {assetsname};
            Assets assets ;
            Map<String, String> map = new HashMap<String, String>();
            map.put("assets_id", "id");
            map.put("assets_name", "name");
            map.put("assets_category", "categoryname");
            map.put("assets_subcategory", "subCategoryname");
            map.put("assets_value", "value");
            map.put("assets_user", "username");
            map.put("lasthand_date", "purchaseDate");
            map.put("state", "state");
            BeanProcessor bean = new BeanProcessor(map);
// 将GenerousBeanProcessor对象传递给BasicRowProcessor
            RowProcessor processor = new BasicRowProcessor(bean);
            assets = (Assets) qr.query(conn, sql, new BeanHandler<Assets>(Assets.class,processor),params);
            return assets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Assets> searchByUser(String user_name) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "select * from assets where assets_user = ?";
            QueryRunner qr = new QueryRunner();
            Object[] params = {user_name};
            List<Assets> assetsList = new ArrayList<>();
            Map<String, String> map = new HashMap<String, String>();
            map.put("assets_id", "id");
            map.put("assets_name", "name");
            map.put("assets_category", "categoryname");
            map.put("assets_subcategory", "subCategoryname");
            map.put("assets_value", "value");
            map.put("assets_user", "username");
            map.put("lasthand_date", "purchaseDate");
            map.put("state", "state");
            BeanProcessor bean = new BeanProcessor(map);
// 将GenerousBeanProcessor对象传递给BasicRowProcessor
            RowProcessor processor = new BasicRowProcessor(bean);
            assetsList = (List<Assets>)qr.query(conn, sql, new BeanListHandler<Assets>(Assets.class,processor),params);
            return assetsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
