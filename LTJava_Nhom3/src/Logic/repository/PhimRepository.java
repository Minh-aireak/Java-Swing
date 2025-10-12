package Logic.repository;

import Logic.entity.Phim;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public interface PhimRepository {

    
    boolean themPhim(Phim phim) throws FileNotFoundException, Exception;

    
    boolean capNhatPhim(Phim phim) throws Exception;

    
    boolean xoaPhim(String idPhim) throws Exception;

    
    ArrayList<Phim> layDanhSachPhim() throws Exception;
}

