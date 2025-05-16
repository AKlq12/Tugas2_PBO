package controller;

import java.util.List;
import DAOdataperpustakaan.dataperpustakaanDAO;
import DAOImplement.dataperpustakaanimplement;
import javax.swing.JOptionPane;
import model.*;
import view.MainView;

public class dataperpustakaancontroller {

    MainView frame;
    dataperpustakaanimplement impldataperpustakaan;
    List<dataperpustakaan> dp;

    public dataperpustakaancontroller(MainView frame) {
        this.frame = frame;
        impldataperpustakaan = new dataperpustakaanDAO();
        dp = impldataperpustakaan.getAll();
    }

    public void isitabel() {
        dp = impldataperpustakaan.getAll();
        modeltabeldataperpustakaan mp = new modeltabeldataperpustakaan(dp);
        frame.getTabelDataperpustakaan().setModel(mp);
    }

    public void insert() {
        try {
            dataperpustakaan db = new dataperpustakaan();
            db.setJudul(frame.getJTxtjudul().getText());
            db.setGenre(frame.getJTxtgenre().getText());
            db.setPenulis(frame.getJTxtpenulis().getText());
            db.setPenerbit(frame.getJTxtpenerbit().getText());
            db.setLokasi(frame.getJTxtlokasi().getText());
            db.setStock(Integer.parseInt(frame.getJTxtstock().getText()));
            

            if (((dataperpustakaanDAO) impldataperpustakaan).isDuplicate(db)) {
            JOptionPane.showMessageDialog(null, "Data buku dengan judul, penulis, dan penerbit yang sama sudah ada!");
            return;
            }

            impldataperpustakaan.insert(db);

            JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Stok harus berupa angka: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data: " + ex.getMessage());
        }

    }

    public void update() {
        dataperpustakaan db = new dataperpustakaan();
        db.setJudul(frame.getJTxtjudul().getText());
        db.setGenre(frame.getJTxtgenre().getText());
        db.setPenulis(frame.getJTxtpenulis().getText());
        db.setPenerbit(frame.getJTxtpenerbit().getText());
        db.setLokasi(frame.getJTxtlokasi().getText());
        db.setStock(Integer.parseInt(frame.getJTxtstock().getText()));
        db.setId_buku(Integer.parseInt(frame.getJTxtid().getText()));

        impldataperpustakaan.update(db);
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
    }

    public void delete() {
        int id_buku = Integer.parseInt(frame.getJTxtid().getText());
        impldataperpustakaan.delete(id_buku);
    }

    public void cari(String kategori, String keyword) {
        try {
            if (keyword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Silahkan masukkan kata kunci pencarian");
            } else {
                List<dataperpustakaan> allowed = impldataperpustakaan.search(kategori, keyword);
                if (allowed.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Data dengan kata kunci tersebut tidak ditemukan.");
                } else {
                    modeltabeldataperpustakaan mp = new modeltabeldataperpustakaan(allowed);
                    frame.getTabelDataperpustakaan().setModel(mp);
                }
            }
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mencari data: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

}
