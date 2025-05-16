package controller;
import java.util.List;
import DAOdataperpustakaan.dataperpustakaanDAO;
import DAOImplement.dataperpustakaanimplement;
import model.*;
import view.MainView;

public class dataperpustakaancontroller {
    MainView frame;
    dataperpustakaanimplement impldataperpustakaan;
    List<dataperpustakaan> dp;
    
    public dataperpustakaancontroller (MainView frame){
        this.frame = frame;
        impldataperpustakaan = new dataperpustakaanDAO();
        dp = impldataperpustakaan.getAll();
    }
    public void isitabel(){
        dp = impldataperpustakaan.getAll();
        modeltabeldataperpustakaan mp = new modeltabeldataperpustakaan(dp);
        frame.getTabelDataperpustakaan().setModel(mp);
    }
    
    public void insert(){
        dataperpustakaan dp = new dataperpustakaan();
        dp.setJudul(frame.getJTxtjudul().getText());
        dp.setGenre(frame.getJTxtgenre().getText());
        dp.setPenulis(frame.getJTxtpenulis().getText());
        dp.setPenerbit(frame.getJTxtpenerbit().getText());
        dp.setLokasi(frame.getJTxtlokasi().getText());
        dp.setStock(Integer.parseInt(frame.getJTxtstock().getText()));
        impldataperpustakaan.insert(dp);
    }
    
    public void update(){
        dataperpustakaan dp = new dataperpustakaan();
        dp.setJudul(frame.getJTxtjudul().getText());
        dp.setGenre(frame.getJTxtgenre().getText());
        dp.setPenulis(frame.getJTxtpenulis().getText());
        dp.setPenerbit(frame.getJTxtpenerbit().getText());
        dp.setLokasi(frame.getJTxtlokasi().getText());
        dp.setStock(Integer.parseInt(frame.getJTxtstock().getText()));
        dp.setId_buku(Integer.parseInt(frame.getJTxtid().getText()));
        impldataperpustakaan.update(dp);
    }
    
    public void delete(){
        int id = Integer.parseInt(frame.getJTxtid().getText());
        impldataperpustakaan.delete(id);
    }
}
