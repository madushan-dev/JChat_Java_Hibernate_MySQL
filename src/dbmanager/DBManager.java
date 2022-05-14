/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanager;

import controller.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Groups;
import pojos.Users;

/**
 *
 * @author Madushan
 */
public class DBManager {
    private static DBManager dbm;

    private DBManager() {
    }

    static {
        dbm = new DBManager();
    }

    public static DBManager getDBM() {
        return dbm;
    }
    
        public List loginHandler(String username, String password) {
        Session sess = Connection.getSessionFactory().openSession();
        String sql = "FROM Users WHERE username='" + username + "' AND password='" + password + "'";
        Query qu = sess.createQuery(sql);
        List User = qu.list();
        return User;
    }
        
        
        public boolean insert(byte[] avatar64based, String email, String username, String nickname, String password) {
        Session s = Connection.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Users user = new Users();

        if (avatar64based != null) {
            user.setProfileImage(avatar64based);
        }
        user.setEmail(email);
        user.setUsername(username);
        user.setNickname(nickname);
         user.setUserType("normal");
        user.setPassword(password);

        s.save(user);
        t.commit();
        s.close();
        return true;

    }
        
      public void update(byte[] img, String username, String nickname,String password, int id) {
        Session s = Connection.getSessionFactory().openSession();
        Transaction tran = s.beginTransaction();

        Users user = (Users) s.load(Users.class, id);
        user.setProfileImage(img);
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);
        
        
        s.update(user);
        tran.commit();
        System.out.println("User updated successfully..");
        s.close();
    }
        
      public boolean create_chat_group(String chatname, String chatdes) {
        Session s = Connection.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Groups groups = new Groups();

        Time time = Time.valueOf(LocalTime.now());
        Date date = Date.valueOf(LocalDate.now());

        groups.setName(chatname);
        groups.setDescription(chatdes);
        groups.setCreatedDate(date);
        groups.setCreatedTime(time);
        groups.setStatus(false);

        try {
            s.save(groups);
            t.commit();
            s.close();
            
//            this.init_msg_file(chatname);
            
            return true;
        } catch (Exception e) {
            return false;
        }

    }
        
    
    
}
