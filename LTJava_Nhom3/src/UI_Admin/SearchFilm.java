package UI_Admin;

import ConnectDatabase.DatabaseConnection;
import Logic.entity.Phim;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SearchFilm extends javax.swing.JFrame {
    private DefaultTableModel model;

    public SearchFilm(DefaultTableModel model) {
        this.model = model;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFilmName = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFilmID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFilmActor = new javax.swing.JTextField();
        txtFilmTG = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtFilmTL = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFilmLang = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFilmAnh = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtFilmDes = new javax.swing.JTextArea();
        ckbFunny = new javax.swing.JCheckBox();
        ckbHorror = new javax.swing.JCheckBox();
        ckbTamLy = new javax.swing.JCheckBox();
        ckbHoatHinh = new javax.swing.JCheckBox();
        ckbFamily = new javax.swing.JCheckBox();
        ckbCoTrang = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Tên phim:");

        jLabel2.setText("Thể loại:");

        btnSearch.setText("Tìm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TÌM KIẾM PHIM");

        jLabel4.setText("Mã phim:");

        jLabel6.setText("Tác giả:");

        jLabel7.setText("Diễn viên:");

        btnRefresh.setText("Làm mới");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel9.setText("Thời lượng:");

        jLabel10.setText("Ngôn ngữ:");

        jLabel11.setText("Ảnh:");

        jLabel12.setText("Mô tả:");

        txtFilmDes.setColumns(20);
        txtFilmDes.setRows(5);
        jScrollPane1.setViewportView(txtFilmDes);

        ckbFunny.setText("Hài hước");

        ckbHorror.setText("Kinh dị - Ma");

        ckbTamLy.setText("Tâm lý - Tình cảm");

        ckbHoatHinh.setText("Hoạt hình");

        ckbFamily.setText("Gia đình - Học đường");

        ckbCoTrang.setText("Cổ trang - Thần thoại");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbFunny, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckbHorror, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckbCoTrang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbHoatHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckbFamily)
                            .addComponent(ckbTamLy)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(txtFilmID))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFilmAnh)
                                    .addComponent(jScrollPane1)
                                    .addComponent(txtFilmActor)
                                    .addComponent(txtFilmTL)
                                    .addComponent(txtFilmLang)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFilmName)
                                    .addComponent(txtFilmTG))))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btnSearch)
                .addGap(58, 58, 58)
                .addComponent(btnRefresh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnRefresh, btnSearch});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFilmID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFilmName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilmTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ckbCoTrang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbFunny)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbHorror))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ckbFamily)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbHoatHinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbTamLy)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtFilmTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtFilmLang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFilmActor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtFilmAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(btnRefresh))
                .addGap(31, 31, 31))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnRefresh, btnSearch});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String idPhim = txtFilmID.getText().trim();
        String tenPhim = txtFilmName.getText().trim();
        String tacGia = txtFilmTG.getText().trim();
        String thoiLuong = txtFilmTL.getText().trim();
        String ngonNgu = txtFilmLang.getText().trim();
        String dienVien = txtFilmActor.getText().trim();
        String moTa = txtFilmDes.getText().trim();
        String anhPhim = txtFilmAnh.getText().trim();
        
        List<String> theLoaiList = new ArrayList<>();
        if (ckbCoTrang.isSelected()) theLoaiList.add("Cổ trang - Thần thoại");
        if (ckbFunny.isSelected())    theLoaiList.add("Hài hước");
        if (ckbHorror.isSelected())   theLoaiList.add("Kinh dị - Ma");
        if (ckbFamily.isSelected())   theLoaiList.add("Gia đình - Học đường");
        if (ckbHoatHinh.isSelected()) theLoaiList.add("Hoạt hình");
        if (ckbTamLy.isSelected())    theLoaiList.add("Tâm lý - Tình cảm");

        ArrayList<Phim> dsPhim = timKiemPhim(idPhim, tenPhim, theLoaiList, tacGia, dienVien, thoiLuong, ngonNgu, moTa, anhPhim);
        model.setRowCount(0);

        for (Phim phim : dsPhim) {
            model.addRow(new Object[]{
                phim.getIdPhim(),
                phim.getTenPhim(),
                phim.getTacGia(),
                String.join(", ", phim.getTheLoai()),
                phim.getThoiLuong(),
                phim.getNgonNgu(),
                phim.getDienVien(),
                phim.getMoTa(),
                phim.getAnhPhim()
            });
        }
        dispose();
    }//GEN-LAST:event_btnSearchActionPerformed

    private ArrayList<Phim> timKiemPhim(String idPhim, String tenPhim, List<String> theLoaiList,
                                    String tacGia, String dienVien, String thoiLuong,
                                    String ngonNgu, String moTa, String anhPhim) {
    ArrayList<Phim> ds = new ArrayList<>();
    Connection conn = DatabaseConnection.getConnection();
    StringBuilder sql = new StringBuilder(
        "SELECT p.idPhim, p.tenPhim, p.tacGia, p.thoiLuong, p.ngonNgu, p.dienVien, p.moTa, p.anhPhim, " +
        "GROUP_CONCAT(DISTINCT tl.tenTheLoai) AS the_loai " +
        "FROM phim p " +
        "LEFT JOIN phim_theloai pt ON p.idPhim = pt.idPhim " +
        "LEFT JOIN the_loai tl ON pt.idTheLoai = tl.idTheLoai " +
        "WHERE 1=1"
    );

    List<String> conditions = new ArrayList<>();
    List<Object> params = new ArrayList<>();

    if (!idPhim.isEmpty()) {
        conditions.add("p.idPhim LIKE ?");
        params.add("%" + idPhim + "%");
    }
    if (!tenPhim.isEmpty()) {
        conditions.add("p.tenPhim LIKE ?");
        params.add("%" + tenPhim + "%");
    }

    if (!theLoaiList.isEmpty()) {
        StringBuilder tlCond = new StringBuilder("p.idPhim IN (");
        tlCond.append("SELECT pt2.idPhim FROM phim_theloai pt2 ");
        tlCond.append("JOIN the_loai tl2 ON pt2.idTheLoai = tl2.idTheLoai ");
        tlCond.append("WHERE tl2.tenTheLoai IN (");
        tlCond.append(String.join(",", Collections.nCopies(theLoaiList.size(), "?")));
        tlCond.append(") GROUP BY pt2.idPhim HAVING COUNT(DISTINCT tl2.tenTheLoai) = ?");
        tlCond.append(")");
        conditions.add(tlCond.toString());

        params.addAll(theLoaiList);
        params.add(theLoaiList.size());
    }

    if (!tacGia.isEmpty()) {
        conditions.add("p.tacGia LIKE ?");
        params.add("%" + tacGia + "%");
    }
    if (!dienVien.isEmpty()) {
        conditions.add("p.dienVien LIKE ?");
        params.add("%" + dienVien + "%");
    }
    if (!thoiLuong.isEmpty()) {
        conditions.add("p.thoiLuong LIKE ?");
        params.add("%" + thoiLuong + "%");
    }
    if (!ngonNgu.isEmpty()) {
        conditions.add("p.ngonNgu LIKE ?");
        params.add("%" + ngonNgu + "%");
    }
    if (!moTa.isEmpty()) {
        conditions.add("p.moTa LIKE ?");
        params.add("%" + moTa + "%");
    }
    if (!anhPhim.isEmpty()) {
        conditions.add("p.anhPhim LIKE ?");
        params.add("%" + anhPhim + "%");
    }

    if (!conditions.isEmpty()) {
        sql.append(" AND ").append(String.join(" AND ", conditions));
    }
    sql.append(" GROUP BY p.idPhim");

    try {
        PreparedStatement ps = conn.prepareStatement(sql.toString());
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String theLoaiStr = rs.getString("the_loai");
            List<String> theLoai = theLoaiStr != null ? List.of(theLoaiStr.split(",")) : new ArrayList<>();

            Phim phim = new Phim(
                rs.getString("idPhim"),
                rs.getString("tenPhim"),
                rs.getString("tacGia"),
                theLoai,
                rs.getString("thoiLuong"),
                rs.getString("ngonNgu"),
                rs.getString("dienVien"),
                rs.getString("moTa"),
                rs.getString("anhPhim")
            );
            ds.add(phim);
        }
        conn.close();
    } catch (SQLException e) {
        System.out.println("Lỗi tìm kiếm phim: " + e.getMessage());
    }
    return ds;
}

    
    
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtFilmID.setText("");
        txtFilmName.setText("");
        txtFilmTG.setText("");
        txtFilmActor.setText("");
        txtFilmTL.setText("");
        txtFilmLang.setText("");
        txtFilmDes.setText("");
        txtFilmAnh.setText("");
        ckbCoTrang.setSelected(false);
        ckbFunny.setSelected(false);
        ckbHorror.setSelected(false);
        ckbFamily.setSelected(false);
        ckbHoatHinh.setSelected(false);
        ckbTamLy.setSelected(false);
    }//GEN-LAST:event_btnRefreshActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox ckbCoTrang;
    private javax.swing.JCheckBox ckbFamily;
    private javax.swing.JCheckBox ckbFunny;
    private javax.swing.JCheckBox ckbHoatHinh;
    private javax.swing.JCheckBox ckbHorror;
    private javax.swing.JCheckBox ckbTamLy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtFilmActor;
    private javax.swing.JTextField txtFilmAnh;
    private javax.swing.JTextArea txtFilmDes;
    private javax.swing.JTextField txtFilmID;
    private javax.swing.JTextField txtFilmLang;
    private javax.swing.JTextField txtFilmName;
    private javax.swing.JTextField txtFilmTG;
    private javax.swing.JTextField txtFilmTL;
    // End of variables declaration//GEN-END:variables
}
