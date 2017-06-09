package main;

import daoImpl.AdminDaoImpl;
import daoImpl.AssetsDaoImpl;
import entity.Admin;
import service.AdminService;
import service.AssetsService;
import util.DataInput;
import util.DataOutput;

/**
 * Created by Lcrit_Z on 2017/5/22.
 */
public class Login {
    
    public Login(){
        while (true){
            start();
        }
    }
    
    public static void welcome(){
        System.out.println("-------Welcome to Assets Management System----------");
        System.out.println();
    }
    
    public boolean adminLogin(){        
        DataInput input = new DataInput();
        Admin admin = new Admin();
        String name = input.getString("admin:");
        admin.setName(name);
        String passwd = input.getString("password:");
        admin.setPasswd(passwd);        
        AdminDaoImpl adi = new AdminDaoImpl();
        boolean res = adi.login(admin);
        return res;
    }
    
    
    public void start(){
        DataInput input = new DataInput();
        welcome();
        int isLogin = -1;
        while (true){
           isLogin = input.getInt("---please input your choices: 1: login, 0: exit");
           if (isLogin==1||isLogin==0){
               if (isLogin == 1) {
                   boolean canLogin = adminLogin();
                   if (canLogin){
                       System.out.println("login successfully");
                       break;
                   }else {
                       System.out.println("login failed");
                       continue;
                   }
               }else {
                   System.exit(10);
               }
           }else {
               System.out.println("input wrong!");
               continue;
           }
           
        }
        boolean flag = true;
        while (flag){
            flag = manage();
        }
        
    }
    
    public boolean manage(){
        boolean flag = true;
        DataInput input = new DataInput();
        int manageChoice = input.getInt("---please chooes which to manage:\n" +
                "1:Assets  2:Users  3:Administrator 0:exit");
        boolean res = true;
        switch (manageChoice){
            case 1:
                AssetsService assetsService = new AssetsService();
                while (res){
                    res = assetsService.assetsManage();
                }
                break;
            case 2:
                
                break;
            case 3:
                AdminService adminService = new AdminService();
                while (res) {
                    res = adminService.adminManage();
                }
                break;
            case 0:
                flag = false;
                break;
            default:
                break;
        }
        return flag;
    }
    
    
    
    public static void main(String[] args) {
        Login login = new Login();
        login.start();
    }
    
}
