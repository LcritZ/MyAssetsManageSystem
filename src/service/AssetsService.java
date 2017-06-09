package service;

import daoImpl.AssetsDaoImpl;
import entity.Assets;
import util.DataInput;

import java.awt.geom.FlatteningPathIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Lcrit_Z on 2017/5/25.
 */
public class AssetsService {
    private static AssetsDaoImpl adi ;

    public AssetsService() {
        this.adi = new AssetsDaoImpl();
    }
    
    public boolean assetsManage(){
        boolean flag = true;
        DataInput input = new DataInput();
        System.out.println("------welcome to manage assets------");
        int choice = input.getInt("please select choice whice to do\n" +
                "1:add assets  2:delete assets  3:modify info  4:retrieve info  5:lend out  6:return back  0:exit");
        switch (choice){
            case 1:
                addAssets();
                break;
            case 2:
                deleteAssets();
                break;
            case 3:
                modifyInfo();
                break;
            case 4:
                boolean res = true;
                while (res){
                    res = retrieve();
                }
                break;
            case 5:
                lendOut();
                break;
            case 6:
                returnBack();
                break;
            case 0:
                flag = false;
                break;
            default:
                break;
        }
        return flag;
    }
    
    public boolean addAssets(){
        boolean flag = false;
        DataInput input = new DataInput();
        String name = input.getString("please input assets name:");
        String categoryName = input.getString("please input category:");
        String subCategoryName = input.getString("please input subcategory:");
        String value = input.getString("please input value:");
        Assets assets = new Assets();
        assets.setName(name);
        assets.setSubCategoryname(subCategoryName);
        assets.setCategoryname(categoryName);
        assets.setValue(value);
        flag = adi.add(assets);
        if (flag){ 
            System.out.println("add successfully");
        }else {
            System.out.println("add failed");
        }
        return flag;
    }
    
    public boolean deleteAssets(){
        boolean flag = true;
        DataInput input = new DataInput();
        String assetsName = input.getString("please input assets name which to delete:");
        flag = adi.delete(assetsName);
        if (flag){
            System.out.println("delete successfully");
        }else {
            System.out.println("delete failed");
        }
        return flag;
    }
    
    public boolean modifyInfo(){
        boolean flag = true;
        DataInput input = new DataInput();
        String name = input.getString("please input assets name");
        int choice = input.getInt("please which to modify:\n" +
                "1:category  2:subcategory  3:value  0:exit");
        String property = "";
        String value = "";
        switch (choice){
            
            case 1:
                DataInput input2 = new DataInput();
                value = input2.getString("please input new category:");
                adi.alter(name,"assets_category",value);
                break;
            case 2:
                value = input.getString("please input new subcategory:");
                adi.alter(name,"assets_subcategory",value);
                break;
            case 3:
                DataInput input3 = new DataInput();
                value = input3.getString("please input new value:");
                System.out.println(value);
                adi.alter(name,"assets_value",value);
                break;
            case 0:
                flag = false;
                break;
            default:
                break;
        }
        if (flag){
            System.out.println("modify successfully");
        }else {
            System.out.println("modify failed");
        }
        return flag;
    }
    
    
    public boolean lendOut(){
        boolean flag = true;
        DataInput input = new DataInput();
        String assets_name = input.getString("please input assets name:");
        String user_name = input.getString("please input user name:");
        String remark = input.getString("please input remark");
        flag = adi.LendOut(assets_name,user_name,1,remark);
        if (flag){
            System.out.println("lend out successfully");
        }else {
            System.out.println("lend out  failed");
        }
        return flag;
    }
    
    public boolean returnBack(){
        boolean flag = true;
        DataInput input = new DataInput();
        String name = input.getString("please input name of you want to return");
        String user_name = input.getString("please input name of user");
        String remark = input.getString("please input remark:");
        int admin_id = input.getInt("please input admin id:");
        flag = adi.returnBack(name,user_name,admin_id,remark);
        if (flag){
            System.out.println("return  successfully");
        }else {
            System.out.println("return  failed");
        }
        return flag;
    }
            
    public boolean retrieve(){
        boolean flag = true;
        DataInput input = new DataInput();
        int choice = input.getInt("please input which to search:\n" +
                "1:search all  2:search by category  3: search by user 0:exit");
        switch (choice){
            case 1:
                searchAll();
                break;
            case 2:
                searchByCategory();
                break;
            case 3:
                searchByUser();
                break;
            case 0:
                flag = false;
                break;
            default: 
                break;
        }
        return flag;
    }
    
    public boolean searchAll(){
        boolean flag = true;
        //DataInput input = new DataInput();
        List<Assets> assetsList = new ArrayList<>();
        assetsList = adi.searchAll2();
        if (assetsList != null){
            Iterator iterator = assetsList.iterator();
            while (iterator.hasNext()){
                System.out.println("in service: "+iterator.next());
            }
        }else {
            flag = false;
        }
        
        return flag;
    }
    
    public boolean searchByCategory(){
        boolean flag = true;
        DataInput input = new DataInput();
        String name = input.getString("please input category name:");
        List<Assets> assetsList = new ArrayList<>();
        assetsList = adi.searchByCategory(name);
        if (assetsList != null){
            Iterator<Assets> iterator = assetsList.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }else {
            flag = false;
        }
        return flag;
    }
    
    public boolean searchByUser(){
        boolean flag = true;
        DataInput input = new DataInput();
        String user_name = input.getString("please input user name:");
        List<Assets> assetsList = new ArrayList<>();
        assetsList = adi.searchByUser(user_name);
        if (assetsList != null){
            Iterator<Assets> iterator = assetsList.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }else {
            flag = false;
        }
        return flag;
    }
    
}
