package Logic.controller;

import Logic.entity.Phim;
import Logic.repository.PhimRepository;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PhimController {
    private final PhimRepository repo;

    public PhimController() {
        this.repo = new PhimRepository();
    }

    public boolean themPhim(Phim phim) throws FileNotFoundException, Exception {
        if (phim == null) throw new IllegalArgumentException("Phim null");
        if (phim.getIdPhim() == null || phim.getIdPhim().trim().isEmpty())
            throw new IllegalArgumentException("idPhim required");
        return repo.themPhim(phim);
    }

    public boolean capNhatPhim(Phim phim) throws Exception {
        if (phim == null || phim.getIdPhim() == null || phim.getIdPhim().trim().isEmpty())
            throw new IllegalArgumentException("Invalid phim");
        return repo.capNhatPhim(phim);
    }

    public boolean xoaPhim(String idPhim) throws Exception {
        if (idPhim == null || idPhim.trim().isEmpty()) throw new IllegalArgumentException("idPhim required");
        return repo.xoaPhim(idPhim);
    }

    public ArrayList<Phim> layDanhSachPhim() throws Exception {
        return repo.layDanhSachPhim();
    }
}
