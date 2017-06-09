package util;

import java.util.Scanner;

/**
 * Created by Lcrit_Z on 2017/5/22.
 */
public class DataInput {
    public Scanner scanner; 
    
    public DataInput(){
       this.scanner = new Scanner(System.in);
    }
    
    public int getInt(String info){
        //scanner.
        System.out.println(info);
        int id = -1;
        if (this.scanner.hasNextInt()){
            id = this.scanner.nextInt();
        }else {
            System.out.println("input wrong!");
        }
        return id;
    }
    
    public String getString(String info){
        String str = null;
        System.out.println(info);
        if (this.scanner.hasNext()){
            str = this.scanner.nextLine();
        }else {
            System.out.println("input wrong");
        }
        return str;
    }

    
}
