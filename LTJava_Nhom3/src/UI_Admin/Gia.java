package UI_Admin;

public class Gia {
    private String idGia;
    private int tieuChuan;
    private int vip;
    private int triple;

    public Gia(String idGia, int tieuChuan, int vip, int triple) {
        this.idGia = idGia;
        this.tieuChuan = tieuChuan;
        this.vip = vip;
        this.triple = triple;
    }

    // Getters và Setters
    public String getIdGia() { return idGia; }
    public void setIdGia(String idGia) { this.idGia = idGia; }
    public int getTieuChuan() { return tieuChuan; }
    public void setTieuChuan(int tieuChuan) { this.tieuChuan = tieuChuan; }
    public int getVip() { return vip; }
    public void setVip(int vip) { this.vip = vip; }
    public int getTriple() { return triple; }
    public void setTriple(int triple) { this.triple = triple; }
}