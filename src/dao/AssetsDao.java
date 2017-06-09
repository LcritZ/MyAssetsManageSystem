package dao;

import entity.*;

import java.util.List;

/**
 * Created by Lcrit_Z on 2017/5/5.
 */
public interface AssetsDao {

    //public boolean add(Assets asset);

    public boolean delete(String assetname);

    public boolean alter(String assets_name,String propertity,String value);

    public boolean LendOut(String assets_name, String user_name,int admin_id, String remark);
    
    public boolean returnBack(String assets_name,String user_name,int admin_id,String remark);
    
    public List<Assets> searchAll();

    public List<Assets> searchByCategory(String category_name);

    public List<Assets> searchBySubcategory(String sub_name);

    public Assets searchById(int assets_id);

    public Assets searchByName(String assets_name);

    public List<Assets> searchByUser(String user_name);

}
