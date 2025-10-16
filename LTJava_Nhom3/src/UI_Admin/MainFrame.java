package UI_Admin;

import Logic.controller.BaoCaoController;
import Logic.repository.VeRepository;
import Logic.entity.Ve;
import UI_Login.UI_Login;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.ZoneId;
import Logic.controller.VeController;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.category.DefaultCategoryDataset;


public class MainFrame extends javax.swing.JFrame {
    private final DefaultTableModel model;
    private final DefaultTableModel modelCus;
    private final DefaultTableModel modelDatVe;
    private final DefaultTableModel modelLichChieu;
    private final DefaultTableModel modelVe;
    private final DefaultTableModel modelGia;
    private final DefaultTableModel modelReport;
    VeController veController;
    BaoCaoController baoCaoController;
    int selectedRow = -1;

    public MainFrame() throws ClassNotFoundException {
        initComponents();
        model = (DefaultTableModel) tbFilm.getModel();
        modelCus = (DefaultTableModel) tbCus.getModel();
        modelDatVe = (DefaultTableModel) tbVe.getModel();
        modelLichChieu = (DefaultTableModel) tbLichChieu.getModel();
        modelVe = (DefaultTableModel) tbVe.getModel();
        modelGia = (DefaultTableModel) tbGia.getModel();
        modelReport = (DefaultTableModel) tbBaoCao.getModel();
        tbFilm.setDefaultEditor(Object.class, null);
        tbCus.setDefaultEditor(Object.class, null);
        tbVe.setDefaultEditor(Object.class, null);
        tbLichChieu.setDefaultEditor(Object.class, null);
        tbGia.setDefaultEditor(Object.class, null);
        tbBaoCao.setDefaultEditor(Object.class, null);
        loadFilmData();
        loadCustomerData();
        loadDatVeData();
        loadLichChieuData();
        loadGiaData();
    }
    
    private void loadGiaData() {
        modelGia.setRowCount(0);
        ArrayList<Gia> dsGia = GiaDAO.layDanhSachGia();
        for (Gia gia : dsGia) {
            modelGia.addRow(new Object[]{
                gia.getIdGia(),
                gia.getTieuChuan(),
                gia.getVip(),
                gia.getTriple()
            });
        }
    }
    private void loadLichChieuData() {
        modelLichChieu.setRowCount(0);
        ArrayList<LichChieu> dsLichChieu = LichChieuDAO.layDanhSachLichChieu();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.of("Asia/Ho_Chi_Minh"));
        for (LichChieu lc : dsLichChieu) {
            String formattedTime = lc.getGioChieu().toLocalDateTime().format(formatter);
            modelLichChieu.addRow(new Object[]{
                lc.getIdLichChieu(),
                lc.getIdPhim(),
                lc.getGioChieu().toLocalDateTime().format(formatter),
                lc.getIdPhongChieu(),
                lc.getSoGheConLai(),
                lc.getIdGia()
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        tpCus = new tabbedCustom.TabbedPaneCustom();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFilm = new javax.swing.JTable();
        btnAddFilm = new javax.swing.JButton();
        btnEditFilm = new javax.swing.JButton();
        btnDeleteFilm = new javax.swing.JButton();
        btnSearchFilm = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnRefreshFilm = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCus = new javax.swing.JTable();
        btnAddCus = new javax.swing.JButton();
        btnEditCus = new javax.swing.JButton();
        btnDeleteCus = new javax.swing.JButton();
        btnSearchCus = new javax.swing.JButton();
        btnRefreshCus = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnEditLichChieu = new javax.swing.JButton();
        btnDeleteLichChieu = new javax.swing.JButton();
        btnSearchLichChieu = new javax.swing.JButton();
        btnRefreshLichChieu = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbLichChieu = new javax.swing.JTable();
        btnAddLichChieu = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnHuyVe = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbVe = new javax.swing.JTable();
        btnLamMoi = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnEditGia = new javax.swing.JButton();
        btnDeleteGia = new javax.swing.JButton();
        btnSearchGia = new javax.swing.JButton();
        btnRefreshGia = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbGia = new javax.swing.JTable();
        btnAddGia = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnDisplayChart = new javax.swing.JButton();
        btnCreBaoCao = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbBaoCao = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtDay = new javax.swing.JTextField();
        txtMonth = new javax.swing.JTextField();
        txtYear = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Phim:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tbFilm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phim", "Tên phim", "Tác giả", "Thể loại", "Thời lượng", "Ngôn ngữ", "Diễn viên", "Mô tả", "Ảnh"
            }
        ));
        jScrollPane1.setViewportView(tbFilm);

        btnAddFilm.setText("Thêm");
        btnAddFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFilmActionPerformed(evt);
            }
        });

        btnEditFilm.setText("Sửa");
        btnEditFilm.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditFilmActionPerformed(evt);
            }
        });

        btnDeleteFilm.setText("Xóa");
        btnDeleteFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFilmActionPerformed(evt);
            }
        });

        btnSearchFilm.setText("Tìm kiếm");
        btnSearchFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchFilmActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH PHIM");
        jLabel1.setPreferredSize(new java.awt.Dimension(162, 50));

        btnRefreshFilm.setText("Làm mới");
        btnRefreshFilm.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRefreshFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshFilmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnAddFilm)
                .addGap(149, 149, 149)
                .addComponent(btnEditFilm)
                .addGap(116, 116, 116)
                .addComponent(btnRefreshFilm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteFilm)
                .addGap(135, 135, 135)
                .addComponent(btnSearchFilm)
                .addGap(46, 46, 46))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddFilm, btnDeleteFilm, btnEditFilm, btnSearchFilm});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteFilm, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(btnSearchFilm, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                    .addComponent(btnAddFilm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditFilm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefreshFilm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpCus.addTab("Quản lý phim", jPanel1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DANH SÁCH NGƯỜI XEM");
        jLabel2.setPreferredSize(new java.awt.Dimension(219, 50));

        tbCus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã người xem", "Số điện thoại", "Email", "Mật khẩu", "Họ đệm", "Tên", "Ngày sinh", "Địa chỉ", "Giới tính"
            }
        ));
        jScrollPane2.setViewportView(tbCus);
        if (tbCus.getColumnModel().getColumnCount() > 0) {
            tbCus.getColumnModel().getColumn(4).setResizable(false);
        }

        btnAddCus.setText("Thêm");
        btnAddCus.setMaximumSize(new java.awt.Dimension(72, 32));
        btnAddCus.setMinimumSize(new java.awt.Dimension(72, 32));
        btnAddCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCusActionPerformed(evt);
            }
        });

        btnEditCus.setText("Sửa");
        btnEditCus.setMaximumSize(new java.awt.Dimension(72, 32));
        btnEditCus.setMinimumSize(new java.awt.Dimension(72, 32));
        btnEditCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCusActionPerformed(evt);
            }
        });

        btnDeleteCus.setText("Xóa");
        btnDeleteCus.setMaximumSize(new java.awt.Dimension(72, 32));
        btnDeleteCus.setMinimumSize(new java.awt.Dimension(72, 32));
        btnDeleteCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCusActionPerformed(evt);
            }
        });

        btnSearchCus.setText("Tìm kiếm");
        btnSearchCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCusActionPerformed(evt);
            }
        });

        btnRefreshCus.setText("Làm mới");
        btnRefreshCus.setMaximumSize(new java.awt.Dimension(72, 32));
        btnRefreshCus.setMinimumSize(new java.awt.Dimension(72, 32));
        btnRefreshCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshCusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnAddCus, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(btnEditCus, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(btnRefreshCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(btnSearchCus, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddCus, btnDeleteCus, btnEditCus, btnSearchCus});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditCus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddCus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefreshCus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearchCus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteCus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddCus, btnDeleteCus, btnEditCus, btnSearchCus});

        tpCus.addTab("Quản lý người xem", jPanel2);

        btnEditLichChieu.setText("Sửa");
        btnEditLichChieu.setMaximumSize(new java.awt.Dimension(72, 32));
        btnEditLichChieu.setMinimumSize(new java.awt.Dimension(72, 32));
        btnEditLichChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditLichChieuActionPerformed(evt);
            }
        });

        btnDeleteLichChieu.setText("Xóa");
        btnDeleteLichChieu.setMaximumSize(new java.awt.Dimension(72, 32));
        btnDeleteLichChieu.setMinimumSize(new java.awt.Dimension(72, 32));
        btnDeleteLichChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteLichChieuActionPerformed(evt);
            }
        });

        btnSearchLichChieu.setText("Tìm kiếm");
        btnSearchLichChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchLichChieuActionPerformed(evt);
            }
        });

        btnRefreshLichChieu.setText("Làm mới");
        btnRefreshLichChieu.setMaximumSize(new java.awt.Dimension(72, 32));
        btnRefreshLichChieu.setMinimumSize(new java.awt.Dimension(72, 32));
        btnRefreshLichChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshLichChieuActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("DANH SÁCH LỊCH CHIẾU");
        jLabel6.setPreferredSize(new java.awt.Dimension(219, 50));

        tbLichChieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lịch chiếu", "Mã phim", "Ngày giờ", "Phòng chiếu", "Số ghế còn lại", "Mã giá"
            }
        ));
        jScrollPane4.setViewportView(tbLichChieu);

        btnAddLichChieu.setText("Thêm");
        btnAddLichChieu.setMaximumSize(new java.awt.Dimension(72, 32));
        btnAddLichChieu.setMinimumSize(new java.awt.Dimension(72, 32));
        btnAddLichChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLichChieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(btnAddLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(btnEditLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(btnRefreshLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(btnDeleteLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(btnSearchLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefreshLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearchLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        tpCus.addTab("Quản lý lịch chiếu", jPanel3);

        btnHuyVe.setText("Hủy vé");
        btnHuyVe.setMaximumSize(new java.awt.Dimension(72, 32));
        btnHuyVe.setMinimumSize(new java.awt.Dimension(72, 32));
        btnHuyVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyVeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH SÁCH VÉ");
        jLabel3.setPreferredSize(new java.awt.Dimension(219, 50));

        tbVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã vé", "Khách hàng", "Phim", "Lịch chiếu", "Ghế", "Phòng chiếu", "Giá vé"
            }
        ));
        jScrollPane6.setViewportView(tbVe);

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setMaximumSize(new java.awt.Dimension(72, 32));
        btnLamMoi.setMinimumSize(new java.awt.Dimension(72, 32));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(222, 222, 222)
                .addComponent(btnHuyVe, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnHuyVe, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        tpCus.addTab("Xem và hủy vé", jPanel4);

        btnEditGia.setText("Sửa");
        btnEditGia.setMaximumSize(new java.awt.Dimension(72, 32));
        btnEditGia.setMinimumSize(new java.awt.Dimension(72, 32));
        btnEditGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditGiaActionPerformed(evt);
            }
        });

        btnDeleteGia.setText("Xóa");
        btnDeleteGia.setMaximumSize(new java.awt.Dimension(72, 32));
        btnDeleteGia.setMinimumSize(new java.awt.Dimension(72, 32));
        btnDeleteGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteGiaActionPerformed(evt);
            }
        });

        btnSearchGia.setText("Tìm kiếm");
        btnSearchGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchGiaActionPerformed(evt);
            }
        });

        btnRefreshGia.setText("Làm mới");
        btnRefreshGia.setMaximumSize(new java.awt.Dimension(72, 32));
        btnRefreshGia.setMinimumSize(new java.awt.Dimension(72, 32));
        btnRefreshGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshGiaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("DANH SÁCH GIÁ");
        jLabel14.setPreferredSize(new java.awt.Dimension(219, 50));

        tbGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã giá", "Tiêu chuẩn", "VIP", "Triple"
            }
        ));
        jScrollPane5.setViewportView(tbGia);

        btnAddGia.setText("Thêm");
        btnAddGia.setMaximumSize(new java.awt.Dimension(72, 32));
        btnAddGia.setMinimumSize(new java.awt.Dimension(72, 32));
        btnAddGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnAddGia, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(btnEditGia, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(btnRefreshGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(btnDeleteGia, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(btnSearchGia, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditGia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddGia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefreshGia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearchGia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteGia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        tpCus.addTab("Quản lý giá", jPanel5);

        btnDisplayChart.setText("Hiển thị biểu đồ");
        btnDisplayChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayChartActionPerformed(evt);
            }
        });

        btnCreBaoCao.setText("Tạo báo cáo");
        btnCreBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreBaoCaoActionPerformed(evt);
            }
        });

        jLabel9.setText("Năm:");

        jLabel10.setText("Tháng:");

        jLabel11.setText("Ngày:");

        tbBaoCao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phim", "Số vé đã đặt"
            }
        ));
        jScrollPane7.setViewportView(tbBaoCao);

        jLabel4.setText("Từ ngày:");

        jLabel5.setText("Đến:");

        jButton2.setText("Báo cáo (From-to)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(93, 93, 93)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(btnDisplayChart))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addComponent(btnCreBaoCao))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDisplayChart)
                        .addComponent(jButton2))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
        );

        tpCus.addTab("Báo cáo", jPanel6);

        jToolBar1.setRollover(true);

        jButton1.setText("Đăng xuất");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshCusActionPerformed
        loadCustomerData();
    }//GEN-LAST:event_btnRefreshCusActionPerformed

    private void btnSearchCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCusActionPerformed
        SearchCustomer searchCustomer = new SearchCustomer(modelCus);
        searchCustomer.setVisible(true);
    }//GEN-LAST:event_btnSearchCusActionPerformed

    private void btnDeleteCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCusActionPerformed
        selectedRow = tbCus.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa khách hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String idTaiKhoan = modelCus.getValueAt(selectedRow, 0).toString();
            if (NguoiXemDAO.xoaNguoiXem(idTaiKhoan)) {
                modelCus.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại! Khách hàng có thể có vé đã đặt.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteCusActionPerformed

    private void btnEditCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCusActionPerformed
        selectedRow = tbCus.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        EditCustomer editCustomer = new EditCustomer(selectedRow, modelCus);
        editCustomer.setVisible(true);
    }//GEN-LAST:event_btnEditCusActionPerformed

    private void btnAddCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCusActionPerformed
        AddCustomer addCustomer = new AddCustomer(modelCus);
        addCustomer.setVisible(true);
    }//GEN-LAST:event_btnAddCusActionPerformed

    private void btnRefreshFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshFilmActionPerformed
        loadFilmData();
    }//GEN-LAST:event_btnRefreshFilmActionPerformed

    private void btnSearchFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchFilmActionPerformed
        SearchFilm sf = new SearchFilm(model);
        sf.setVisible(true);
    }//GEN-LAST:event_btnSearchFilmActionPerformed

    private void btnDeleteFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFilmActionPerformed
        selectedRow = tbFilm.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Cần chọn 1 dòng để xóa!", "Select row", JOptionPane.ERROR_MESSAGE);
        } else {
            int kt = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa dòng này không?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (kt == JOptionPane.YES_OPTION) {
                String maPhim = model.getValueAt(selectedRow, 0).toString();
                if (PhimDAO.xoaPhim(maPhim)) {
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(this, "Xóa phim thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại! Phim có thể đang được sử dụng.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteFilmActionPerformed

    private void btnEditFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditFilmActionPerformed
        selectedRow = tbFilm.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phim để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new EditFilm(selectedRow, model).setVisible(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                loadFilmData();
            }
        });
    }//GEN-LAST:event_btnEditFilmActionPerformed

    private void btnAddFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFilmActionPerformed
        AddFilm af = new AddFilm(model);
        af.setVisible(true);
        af.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                loadFilmData();
            }
        });
    }//GEN-LAST:event_btnAddFilmActionPerformed

    private void btnEditLichChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditLichChieuActionPerformed
        selectedRow = tbLichChieu.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Cần chọn 1 dòng để sửa!", "Select row", JOptionPane.ERROR_MESSAGE);
        } else {
            EditLC editLC = new EditLC(selectedRow, modelLichChieu);
            editLC.setVisible(true);
            editLC.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    loadLichChieuData();
                }
            });
        }
    }//GEN-LAST:event_btnEditLichChieuActionPerformed

    private void btnDeleteLichChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteLichChieuActionPerformed
        selectedRow = tbLichChieu.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn lịch chiếu để xóa!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    String idLichChieu = modelLichChieu.getValueAt(selectedRow, 0).toString();
    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa lịch chiếu " + idLichChieu + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        if (LichChieuDAO.xoaLichChieu(idLichChieu)) {
            JOptionPane.showMessageDialog(this, "Xóa lịch chiếu thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            modelLichChieu.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Xóa lịch chiếu thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnDeleteLichChieuActionPerformed

    private void btnSearchLichChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchLichChieuActionPerformed
        SearchLC searchLC = new SearchLC(modelLichChieu);
        searchLC.setVisible(true);
    }//GEN-LAST:event_btnSearchLichChieuActionPerformed

    private void btnRefreshLichChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshLichChieuActionPerformed
        loadLichChieuData();
    }//GEN-LAST:event_btnRefreshLichChieuActionPerformed

    private void btnAddLichChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLichChieuActionPerformed
        AddLC addLC = new AddLC(modelLichChieu);
        addLC.setVisible(true);
        addLC.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                loadLichChieuData();
            }
        });
    }//GEN-LAST:event_btnAddLichChieuActionPerformed

    private void btnEditGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditGiaActionPerformed
        selectedRow = tbGia.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giá để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new EditPrice(selectedRow, modelGia).setVisible(true);
    }//GEN-LAST:event_btnEditGiaActionPerformed

    private void btnDeleteGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteGiaActionPerformed
        selectedRow = tbGia.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giá để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa giá này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == confirm) {
            String idGia = modelGia.getValueAt(selectedRow, 0).toString();
            if (GiaDAO.xoaGia(idGia)) {
                modelGia.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Xóa giá thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Xóa giá thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteGiaActionPerformed

    private void btnSearchGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchGiaActionPerformed
        new SearchPrice(modelGia).setVisible(true);
    }//GEN-LAST:event_btnSearchGiaActionPerformed

    private void btnRefreshGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshGiaActionPerformed
        loadGiaData();
    }//GEN-LAST:event_btnRefreshGiaActionPerformed

    private void btnAddGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddGiaActionPerformed
        new AddPrice(modelGia).setVisible(true);
    }//GEN-LAST:event_btnAddGiaActionPerformed

    private void btnHuyVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyVeActionPerformed

        veController.deleteVe(this, modelDatVe, tbVe);
//        selectedRow = tbVe.getSelectedRow();
//        if (selectedRow < 0) {
//            JOptionPane.showMessageDialog(this, "Cần chọn 1 vé để hủy!", "Select row", JOptionPane.ERROR_MESSAGE);
//        } else {
//            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy vé này?", "Confirm", JOptionPane.YES_NO_OPTION);
//            if (confirm == JOptionPane.YES_OPTION) {
//                String idVe = modelDatVe.getValueAt(selectedRow, 0).toString();
//                try {
//                    if (VeRepository.huyVe(idVe)) {
//                        modelDatVe.removeRow(selectedRow);
//                        JOptionPane.showMessageDialog(this, "Hủy vé thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Hủy vé thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
    }//GEN-LAST:event_btnHuyVeActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        veController.getListVe(modelVe, tbVe);
//        XemChiTietVe xemChiTietVe = new XemChiTietVe();
//        xemChiTietVe.setVisible(true);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnDisplayChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayChartActionPerformed
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < tbBaoCao.getRowCount(); i++) {
            String tenPhim = tbBaoCao.getValueAt(i, 0).toString();
            int soVe = Integer.parseInt(tbBaoCao.getValueAt(i, 1).toString());
            dataset.addValue(soVe, "Số vé", tenPhim);
        }

        MyChartFrame chartFrame = new MyChartFrame(dataset);
        chartFrame.setVisible(true);
    }//GEN-LAST:event_btnDisplayChartActionPerformed

    // Hưng
    private void btnCreBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreBaoCaoActionPerformed
        String day = txtDay.getText().trim();
        String month = txtMonth.getText().trim();
        String year = txtYear.getText().trim();
        String errorMessage = "";
        if(!baoCaoController.ktraHopLe(day, month, year, errorMessage)){
            JOptionPane.showMessageDialog(this, errorMessage, "Lỗi ngày tháng!", ERROR);
            return;
        }
        try {
            baoCaoController.taoBaoCao(day, month, year, modelReport);
            } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
//        if (!day.isEmpty() && !day.matches("\\d+")) {
//            JOptionPane.showMessageDialog(this, "Ngày phải là số hợp lệ!");
//            return;
//        }
//        if (!month.isEmpty() && !month.matches("\\d+")) {
//            JOptionPane.showMessageDialog(this, "Tháng phải là số hợp lệ!");
//            return;
//        }
//        if (!year.isEmpty() && !year.matches("\\d+")) {
//            JOptionPane.showMessageDialog(this, "Năm phải là số hợp lệ!");
//            return;
//        }
//        int ngay = !day.isEmpty() ? Integer.parseInt(day) : -1;
//        int thang = !month.isEmpty() ? Integer.parseInt(month) : -1;
//        int nam = !month.isEmpty() ? Integer.parseInt(month) : -1;
//        
//        if(nam != -1 && nam < 1900){
//            JOptionPane.showMessageDialog(this, "Năm phải lớn hơn 1900!");
//            return;
//        }
//
//        if (thang != -1 && (thang < 1 || thang > 12)) {
//            JOptionPane.showMessageDialog(this, "Tháng phải nằm trong khoảng 1-12!");
//            return;
//        }
//        if (ngay != -1) {
//            if (thang == -1) {
//                JOptionPane.showMessageDialog(this, "Bạn cần nhập tháng để kiểm tra ngày!");
//                return;
//            }
//
//            List<Integer> thang31Ngay = List.of(1, 3, 5, 7, 8, 10, 12);
//            List<Integer> thang30Ngay = List.of(4, 6, 9, 11);
//
//            if (thang31Ngay.contains(thang)) {
//                if (ngay > 31) {
//                    JOptionPane.showMessageDialog(this, "Ngày không hợp lệ!");
//                    return;
//                }
//            } else if (thang30Ngay.contains(thang)) {
//                if (ngay > 30) {
//                    JOptionPane.showMessageDialog(this, "Ngày không hợp lệ!");
//                    return;
//                }
//            } else if (thang == 2) {
//                if (nam == -1) {
//                    JOptionPane.showMessageDialog(this, "Cần nhập năm để kiểm tra tháng 2!");
//                    return;
//                }
//                boolean isLeap = (nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0);
//                int maxDay = isLeap ? 29 : 28;
//                if (ngay > maxDay) {
//                    JOptionPane.showMessageDialog(this, "Ngày không hợp lệ với tháng 2 năm " + nam + "!");
//                    return;
//                }
//            }
//        }
//
//        int soGheToiDa = 30;
//
//        StringBuilder updateSql = new StringBuilder("""
//            UPDATE lich_chieu lc
//            SET lc.soGheConLai = ? - (
//                SELECT COUNT(*)
//                FROM ve v 
//                WHERE v.idLichChieu = lc.idLichChieu
//            )
//            WHERE 1=1
//        """);
//
//        List<Integer> updateParamValues = new ArrayList<>();
//
//        if (!year.isEmpty()) {
//            updateSql.append(" AND YEAR(lc.gioChieu) = ? ");
//            updateParamValues.add(Integer.parseInt(year));
//        }
//        if (!month.isEmpty()) {
//            updateSql.append(" AND MONTH(lc.gioChieu) = ? ");
//            updateParamValues.add(Integer.parseInt(month));
//        }
//        if (!day.isEmpty()) {
//            updateSql.append(" AND DAY(lc.gioChieu) = ? ");
//            updateParamValues.add(Integer.parseInt(day));
//        }
//
//        try (Connection conn = DBConnection.KetNoi()) {
//            if (conn == null) {
//                JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu!");
//                return;
//            }
//            try (PreparedStatement updatePs = conn.prepareStatement(updateSql.toString())) {
//                int index = 1;
//                updatePs.setInt(index++, soGheToiDa);
//                for (int val : updateParamValues) {
//                    updatePs.setInt(index++, val);
//                }
//                updatePs.executeUpdate();
//            }
//            StringBuilder reportSql = new StringBuilder("""
//                SELECT
//                    p.tenPhim,
//                    GREATEST(0, (? * COUNT(*)) - SUM(lc.soGheConLai)) AS so_ghe_da_dat
//                FROM
//                    lich_chieu lc
//                JOIN
//                    phim p ON lc.idPhim = p.idPhim
//                WHERE 1=1
//            """);
//
//            List<Integer> reportParamValues = new ArrayList<>();
//
//            if (!year.isEmpty()) {
//                reportSql.append(" AND YEAR(lc.gioChieu) = ? ");
//                reportParamValues.add(Integer.parseInt(year));
//            }
//            if (!month.isEmpty()) {
//                reportSql.append(" AND MONTH(lc.gioChieu) = ? ");
//                reportParamValues.add(Integer.parseInt(month));
//            }
//            if (!day.isEmpty()) {
//                reportSql.append(" AND DAY(lc.gioChieu) = ? ");
//                reportParamValues.add(Integer.parseInt(day));
//            }
//            reportSql.append("""
//                GROUP BY lc.idPhim, p.tenPhim
//                ORDER BY p.tenPhim
//            """);
//            try (PreparedStatement reportPs = conn.prepareStatement(reportSql.toString())) {
//                int index = 1;
//                reportPs.setInt(index++, soGheToiDa);
//                for (int val : reportParamValues) {
//                    reportPs.setInt(index++, val);
//                }
//
//                ResultSet rs = reportPs.executeQuery();
//                modelReport.setRowCount(0);
//                while (rs.next()) {
//                    String tenPhim = rs.getString("tenPhim");
//                    int soGheDaDat = rs.getInt("so_ghe_da_dat");
//                    modelReport.addRow(new Object[]{tenPhim, soGheDaDat});
//                }
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, "Lỗi SQL: " + ex.getMessage());
//            ex.printStackTrace();
//        }
    }//GEN-LAST:event_btnCreBaoCaoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int result = JOptionPane.showConfirmDialog(rootPane, "Bạn muốn đăng xuất không ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            dispose();
            UI_Login a = new UI_Login();
            a.setLocationRelativeTo(null);
            a.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Date DateFrom = DateFromChooser.getDate();
        Date DateTo = DateToChooser.getDate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void loadFilmData() {
        model.setRowCount(0);
        ArrayList<Phim> dsPhim = PhimDAO.layDanhSachPhim();
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
    }
    
    private void loadCustomerData() {
        modelCus.setRowCount(0);
        ArrayList<NguoiXem> dsNguoiXem = NguoiXemDAO.layDanhSachNguoiXem();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (NguoiXem nx : dsNguoiXem) {
            modelCus.addRow(new Object[]{
                nx.getIdTaiKhoan(),
                nx.getSoDienThoai(),
                nx.getEmail(),
                nx.getMatKhau(),
                nx.getHoDem(),
                nx.getTen(),
                nx.getNgaySinh() != null ? nx.getNgaySinh().toLocalDate().format(formatter) : "",
                nx.getDiaChi(),
                nx.getGioiTinh()
            });
        }
    }
    
    private void loadDatVeData() throws ClassNotFoundException {
        modelDatVe.setRowCount(0);
        ArrayList<Ve> dsDatVe = VeRepository.layDanhSachDatVe();
        for (Ve dv : dsDatVe) {
            modelDatVe.addRow(new Object[]{
                dv.getIdVe(),
                dv.getTenKhachHang(),
                dv.getTenPhim(),
                dv.getGioChieu()
            });
        }
    }
    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCus;
    private javax.swing.JButton btnAddFilm;
    private javax.swing.JButton btnAddGia;
    private javax.swing.JButton btnAddLichChieu;
    private javax.swing.JButton btnCreBaoCao;
    private javax.swing.JButton btnDeleteCus;
    private javax.swing.JButton btnDeleteFilm;
    private javax.swing.JButton btnDeleteGia;
    private javax.swing.JButton btnDeleteLichChieu;
    private javax.swing.JButton btnDisplayChart;
    private javax.swing.JButton btnEditCus;
    private javax.swing.JButton btnEditFilm;
    private javax.swing.JButton btnEditGia;
    private javax.swing.JButton btnEditLichChieu;
    private javax.swing.JButton btnHuyVe;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnRefreshCus;
    private javax.swing.JButton btnRefreshFilm;
    private javax.swing.JButton btnRefreshGia;
    private javax.swing.JButton btnRefreshLichChieu;
    private javax.swing.JButton btnSearchCus;
    private javax.swing.JButton btnSearchFilm;
    private javax.swing.JButton btnSearchGia;
    private javax.swing.JButton btnSearchLichChieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tbBaoCao;
    private javax.swing.JTable tbCus;
    private javax.swing.JTable tbFilm;
    private javax.swing.JTable tbGia;
    private javax.swing.JTable tbLichChieu;
    private javax.swing.JTable tbVe;
    private tabbedCustom.TabbedPaneCustom tpCus;
    private javax.swing.JTextField txtDay;
    private javax.swing.JTextField txtMonth;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
