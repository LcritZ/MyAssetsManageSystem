package test;

import daoImpl.AssetsDaoImpl;
import entity.Assets;

/**
 * Created by Lcrit_Z on 2017/5/13.
 */
public class TestAssets {

    public static void main(String[] args) {
        AssetsDaoImpl adi = new AssetsDaoImpl();
        Assets asset = new Assets();
        asset.setName("pad");
        asset.setCategoryname("office");
        asset.setSubCategoryname("eletric");
        asset.setValue("1000");
        //System.out.println(adi.add(asset));
        //System.out.println(adi.delete("computer"));
        //System.out.println(adi.alter(asset,"assets_name","pad"));
        String user_name = "zxh";
        int admin_id = 1;
        boolean res = adi.returnBack("",user_name,admin_id,"return computer");
        //boolean res = adi.LendOut(5,user_name,admin_id,"lendout computer");
        System.out.println(res);
    }
}
