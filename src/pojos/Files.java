package pojos;
// Generated 14-May-2022 16:48:58 by Hibernate Tools 4.3.1



/**
 * Files generated by hbm2java
 */
public class Files  implements java.io.Serializable {


     private int id;
     private String name;
     private String path;

    public Files() {
    }

    public Files(int id, String name, String path) {
       this.id = id;
       this.name = name;
       this.path = path;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return this.path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }




}

