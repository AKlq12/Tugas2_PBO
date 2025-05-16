package DAOdataperpustakaan;

import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.dataperpustakaanimplement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dataperpustakaanDAO implements dataperpustakaanimplement {

    Connection connection;

    final String select = "SELECT * FROM db_perpustakaan;";
    final String insert = "INSERT INTO db_perpustakaan (judul, genre, penulis, penerbit, lokasi, stock) VALUES (?, ?, ?, ?, ? ,?);";
    final String update = "UPDATE db_perpustakaan set judul=?, genre=?, penulis=?, penerbit=?, lokasi=?, stock=? WHERE id=?";
    final String delete = "DELETE FROM db_perpustakaan WHERE id=?";

    public dataperpustakaanDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(dataperpustakaan p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getJudul());
            statement.setString(2, p.getGenre());
            statement.setString(3, p.getPenulis());
            statement.setString(4, p.getPenerbit());
            statement.setString(5, p.getLokasi());
            statement.setInt(6, p.getStock());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                p.setId_buku(rs.getInt(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(dataperpustakaan p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getJudul());
            statement.setString(2, p.getGenre());
            statement.setString(3, p.getPenulis());
            statement.setString(4, p.getPenerbit());
            statement.setString(5, p.getLokasi());
            statement.setInt(6, p.getStock());
            statement.setInt(7, p.getId_buku());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<dataperpustakaan> getAll() {
        List<dataperpustakaan> dp = null;
        try {
            dp = new ArrayList<dataperpustakaan>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                dataperpustakaan perpustakaan = new dataperpustakaan();
                perpustakaan.setId_buku(rs.getInt("id_buku"));
                perpustakaan.setJudul(rs.getString("judul"));
                perpustakaan.setGenre(rs.getString("genre"));
                perpustakaan.setPenulis(rs.getString("penulis"));
                perpustakaan.setPenerbit(rs.getString("penerbit"));
                perpustakaan.setLokasi(rs.getString("lokasi"));
                perpustakaan.setStock(rs.getInt("stock"));
                dp.add(perpustakaan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataperpustakaanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dp;
    }
}
