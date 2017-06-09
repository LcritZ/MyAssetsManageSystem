package service;

import daoImpl.AdminDaoImpl;
import entity.Admin;
import util.DataInput;

/**
 * Created by Lcrit_Z on 2017/5/25.
 */
public class AdminService {
    public static AdminDaoImpl adi;

    public AdminService() {
        adi = new AdminDaoImpl();
    }

    public boolean adminManage(){
        boolean flag = true;
        DataInput input = new DataInput();
        System.out.println("welcome to manage administrator");
        int choice = input.getInt("please select choice to do:\n" +
                "1:add  2:logout  3:modify  0:exit" );
        switch (choice){
            case 1:
                addAdmin();
                break;
            case 2:
                
                break;
                
            case 3:
                break;
            case 0:
                flag = false;
                break;
            default:
                break;
                
        }
        return flag;
    }
    
    public boolean addAdmin(){
        DataInput input = new DataInput();
        String name = input.getString("please input name:");
        String passwd = input.getString("please input password:");
        Admin admin = new Admin();
        admin.setName(name);
        admin.setPasswd(passwd);
        return adi.add(admin);
    }
    
}
