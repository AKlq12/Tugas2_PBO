package koneksi;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class connector {
    static Connection con;
    
    public static Connection connection(){
        if(con == null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("perpustakaan");
            data.setUser("root");
            data.setPassword("");
            try {
                con = data.getConnection();
                System.out.println("Koneksi Berhasil");
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Koneksi Gagal");
            }
        }
        return con;
    }
}
