package test;

import daoImpl.UserDaoImpl;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Lcrit_Z on 2017/5/6.
 */
public class TsetUser {
    public static void main(String[] args) {
        User user  = new User();
        user.setName("aaa");
        user.setJob("doctor");
        user.setRemark("hhh");
        UserDaoImpl udi = new UserDaoImpl();
        System.out.println(udi.addUser(user));
    }

    public static int[] twosum(int[] num,int target){
        HashMap<Integer,Integer> map = new HashMap<>();  //构建hashmap
        for (int i = 0;i<num.length;i++){
            if (map.containsKey(target-num[i])){   //判断当前map中有木有与num[i]和为target的键，如果有则找到这对键值，
                 return new int[]{map.get(target-num[i]),i+1};  // 和当前下标组成结果
            }else {
                map.put(num[i],i);
            }
        }
        return null;
    }

}
