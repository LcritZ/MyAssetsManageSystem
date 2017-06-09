package dao;

import java.util.List;

/**
 * Created by Lcrit_Z on 2017/5/5.
 */
public class BaseDao<T> {
    public boolean add(T bean){
        return false;
    }

    public boolean alter(T bean){
        return false;
    }

    public T get(T bean){
        return null;
    }

    public List<T> getAll(T str){
        return null;
    }
}
