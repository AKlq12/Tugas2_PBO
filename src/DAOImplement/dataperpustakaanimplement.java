package DAOImplement;

import java.util.List;
import model.*;

public interface dataperpustakaanimplement {
    public void insert(dataperpustakaan p);
    public void update(dataperpustakaan p);
    public void delete(int id);
    public List<dataperpustakaan> getAll();
    public List<dataperpustakaan> search(String kategori, String keyword);
}
