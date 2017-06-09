package test;

import daoImpl.AdminDaoImpl;
import entity.Admin;

/**
 * Created by Lcrit_Z on 2017/5/7.
 */
public class TestAdmin {
    public static void main(String[] args) {
        AdminDaoImpl adi = new AdminDaoImpl();
        Admin admin = new Admin();
        admin.setName("zzz");
        admin.setPasswd("234");
        boolean change = adi.changePasswd(admin,"111");
        System.out.println(change);


    }
}
