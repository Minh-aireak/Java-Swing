package UI_KhachHang;

import Logic.entity.Phim;
import ConnectDatabase.DatabaseConnection;
import Global.Session;
import Logic.controller.PhimController;
import Logic.controller.TaiKhoanController;
import Logic.controller.VeController;
import Logic.dto.response.ChiTietLichChieuResponse;
import Logic.dto.response.ListLichChieuResponse;
import Logic.dto.response.LoginResponse;
import Logic.dto.response.SearchPhimResponse;
import Logic.entity.TaiKhoan;
import Logic.repository.PhimRepository;
import Logic.repository.TaiKhoanRepository;
import Logic.repository.VeRepository;
import Logic.service.PhimService;
import Logic.service.TaiKhoanService;
import Logic.service.VeService;
import UI_Login.UI_Login;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.util.Objects;

public class UI_KhachHang extends javax.swing.JFrame {
    VeController veController = new VeController(new VeService(new VeRepository()));
    DefaultTableModel model, model2;
    TaiKhoanController loginController = new TaiKhoanController(new TaiKhoanService(new TaiKhoanRepository()));
    static Session session;
    TaiKhoan currentUser = session.getCurrentUser();
    PhimController phimController = new PhimController(new PhimService(new PhimRepository()));
    Phim chosenPhim = new Phim();
    
    List<String> listGheVIP = new ArrayList<>(Arrays.asList("B3,B4,B5,C3,C4,C5".split(",")));
    List<String> listGheTriple = new ArrayList<>(Arrays.asList("D1,D2,D3".split(",")));
    List<String> listGheForPay = new ArrayList<>();
    List<Integer> listGiaVe = new ArrayList<>();
    List<Timestamp> listTimestamps = new ArrayList<>();
    String idGia = "";
    String idLichChieu = "";
    int selectedIndexLichChieuList = -1;
    int indexRow = -1;
    public UI_KhachHang() {
        try {
            initComponents();
            btn_LogOut.setIcon(new ImageIcon(getClass().getResource("/UI_KhachHang/icon/LogOut.png")));
            hscn_lab_AnhAccount.setIcon(new ImageIcon(getClass().getResource("/UI_KhachHang/icon/Account.png")));
            dv_lab_AnhManHinh.setIcon(new ImageIcon(getClass().getResource("/UI_KhachHang/icon/Screen.png")));
            displayChiTietPhim(false);
            displayChiTietBill(false);
            displayChiTietDon(false);
            this.model = (DefaultTableModel) table.getModel();
            this.model2 = (DefaultTableModel) ttb_Table.getModel();
            TableColumn table_column = table.getColumnModel().getColumn(0);
            TableColumn table_column1 = table.getColumnModel().getColumn(1);
            TableColumn ttb_TableColumn = ttb_Table.getColumnModel().getColumn(0);
            TableColumn ttb_TableColumn1 = ttb_Table.getColumnModel().getColumn(1);
            DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            DefaultTableCellRenderer headerRenderer2 = (DefaultTableCellRenderer) ttb_Table.getTableHeader().getDefaultRenderer();
            headerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);
            DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
            centerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            for (int i = 0; i < ttb_Table.getColumnCount(); i++) {
                ttb_Table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            table_column.setMinWidth(0);
            table_column.setMaxWidth(0);
            ttb_TableColumn.setMinWidth(0);
            ttb_TableColumn.setMaxWidth(0);
            table_column1.setMinWidth(500);
            table_column1.setMaxWidth(500);
            ttb_TableColumn1.setMinWidth(250);
            ttb_TableColumn1.setMaxWidth(250);
            table.setRowHeight(30);
            hscn_lab_XinChao.setText("Xin chào, " + session.getCurrentUser().getTen() +"!");
            themEvenListener();
            resetGhe();
            model.setRowCount(0);
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT p.idPhim, p.tenPhim, p.thoiLuong " +
                    "FROM phim p ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vector<String> v = new Vector<>();
                v.add(rs.getString("idPhim"));
                v.add(rs.getString("tenPhim"));
                v.add(rs.getString("thoiLuong"));
                model.addRow(v);
            }
            table.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(UI_KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        displayListBill();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bgr_GioiTinh = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        btn_LogOut = new javax.swing.JButton();
        tabbed = new tabbedCustom.TabbedPaneCustom();
        jpane_Home = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jp_DanhSachPhim = new javax.swing.JPanel();
        h_jp_TimKiemPhim = new javax.swing.JPanel();
        h_btn_TimKiem = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        h_txt_TenPhim = new javax.swing.JTextField();
        h_jp_TheLoai = new javax.swing.JPanel();
        h_chk_CoTrangThanThoai = new javax.swing.JCheckBox();
        h_chk_KinhDiMa = new javax.swing.JCheckBox();
        h_chk_HaiHuoc = new javax.swing.JCheckBox();
        h_chk_GiaDinhHocDuong = new javax.swing.JCheckBox();
        h_chk_HoatHinh = new javax.swing.JCheckBox();
        h_chk_TamLyTinhCam = new javax.swing.JCheckBox();
        jPanel20 = new javax.swing.JPanel();
        h_txt_TenTacGia = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jpane_ChiTietPhim = new javax.swing.JPanel();
        jp_ThongTinChiTietPhim = new javax.swing.JPanel();
        ctp_lab_TenPhim = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        ctp_lab_TacGia = new javax.swing.JLabel();
        ctp_lab_DienVien = new javax.swing.JLabel();
        ctp_lab_TheLoai = new javax.swing.JLabel();
        ctp_lab_NgonNgu = new javax.swing.JLabel();
        ctp_lab_MoTa = new javax.swing.JLabel();
        ctp_btn_DatVe = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        ctp_lab_ThoiLuong = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ctp_lab_AnhPhim = new javax.swing.JLabel();
        jpane_DatVe = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel14 = new javax.swing.JPanel();
        dv_jp_RightGhe = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton28 = new javax.swing.JToggleButton();
        dv_jp_LeftGhe = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jToggleButton11 = new javax.swing.JToggleButton();
        jToggleButton12 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jToggleButton15 = new javax.swing.JToggleButton();
        jToggleButton13 = new javax.swing.JToggleButton();
        jToggleButton14 = new javax.swing.JToggleButton();
        jToggleButton16 = new javax.swing.JToggleButton();
        jToggleButton17 = new javax.swing.JToggleButton();
        jToggleButton18 = new javax.swing.JToggleButton();
        jToggleButton19 = new javax.swing.JToggleButton();
        jToggleButton20 = new javax.swing.JToggleButton();
        jToggleButton21 = new javax.swing.JToggleButton();
        jToggleButton23 = new javax.swing.JToggleButton();
        jToggleButton24 = new javax.swing.JToggleButton();
        jToggleButton22 = new javax.swing.JToggleButton();
        jToggleButton25 = new javax.swing.JToggleButton();
        jToggleButton27 = new javax.swing.JToggleButton();
        jToggleButton26 = new javax.swing.JToggleButton();
        jToggleButton29 = new javax.swing.JToggleButton();
        jToggleButton30 = new javax.swing.JToggleButton();
        jPanel18 = new javax.swing.JPanel();
        dv_lab_TenPhong = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        dv_lab_AnhManHinh = new javax.swing.JLabel();
        split_Right = new javax.swing.JPanel();
        dv_lab_TenPhim = new javax.swing.JLabel();
        dv_cbo_LichChieu = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        dv_lab_TongTien = new javax.swing.JLabel();
        dv_lab_Tt = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel7 = new javax.swing.JPanel();
        dv_lab_ChiTietDonHang = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        dv_lab_Standard = new javax.swing.JLabel();
        dv_lab_VIP = new javax.swing.JLabel();
        dv_lab_Triple = new javax.swing.JLabel();
        dv_lab_VIPQuantity = new javax.swing.JLabel();
        dv_lab_TripleQuantity = new javax.swing.JLabel();
        dv_lab_StandardQuantity = new javax.swing.JLabel();
        dv_lab_VIPSum = new javax.swing.JLabel();
        dv_lab_TripleSum = new javax.swing.JLabel();
        dv_lab_StandardSum = new javax.swing.JLabel();
        dv_btn_ThanhToan = new javax.swing.JButton();
        jpane_HoSoCaNhan = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        hscn_lab_XinChao = new javax.swing.JLabel();
        hscn_lab_AnhAccount = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_Ten = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_Ho = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_NgaySinh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_DiaChi = new javax.swing.JTextField();
        radNam = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        radNu = new javax.swing.JRadioButton();
        btn_CapNhat = new javax.swing.JButton();
        btn_DoiMatKhau = new javax.swing.JButton();
        jpane_ThongTinBill = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ttb_Table = new javax.swing.JTable();
        ttb_jp_ChiTietBill = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        ttb_txt_SoGheDaDat = new javax.swing.JTextArea();
        ttb_txt_TenPhim = new javax.swing.JTextField();
        ttb_txt_ThoiGianDat = new javax.swing.JTextField();
        ttb_txt_PhongChieu = new javax.swing.JTextField();
        ttb_txt_XuatChieu = new javax.swing.JTextField();
        ttb_txt_TongTien = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        btn_LogOut.setText("LOG OUT");
        btn_LogOut.setFocusable(false);
        btn_LogOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_LogOut.setMargin(new java.awt.Insets(13, 14, 13, 14));
        btn_LogOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LogOutActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_LogOut);

        tabbed.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setText("HOME");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(471, 471, 471)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        h_jp_TimKiemPhim.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm phim"));

        h_btn_TimKiem.setText("TÌM KIẾM");
        h_btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h_btn_TimKiemActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên phim"));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(h_txt_TenPhim)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(h_txt_TenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        h_jp_TheLoai.setBorder(javax.swing.BorderFactory.createTitledBorder("Thể loại"));

        h_chk_CoTrangThanThoai.setText("Cổ trang - Thần thoại");
        h_chk_CoTrangThanThoai.setActionCommand("tl_CoTrangThanThoai");

        h_chk_KinhDiMa.setText("Kinh dị - Ma");
        h_chk_KinhDiMa.setActionCommand("tl_KinhDiMa");

        h_chk_HaiHuoc.setText("Hài hước");
        h_chk_HaiHuoc.setActionCommand("tl_HaiHuoc");

        h_chk_GiaDinhHocDuong.setActionCommand("tl_GiaDinhHocDuong");
        h_chk_GiaDinhHocDuong.setText("Gia đình học đường");
        h_chk_GiaDinhHocDuong.setActionCommand("tl_GiaDinhHocDuong");

        h_chk_HoatHinh.setText("Hoạt hình");
        h_chk_HoatHinh.setActionCommand("tl_HoatHinh");

        h_chk_TamLyTinhCam.setText("Tâm lý - Tình cảm");
        h_chk_TamLyTinhCam.setActionCommand("tl_TamLyTinhCam");

        javax.swing.GroupLayout h_jp_TheLoaiLayout = new javax.swing.GroupLayout(h_jp_TheLoai);
        h_jp_TheLoai.setLayout(h_jp_TheLoaiLayout);
        h_jp_TheLoaiLayout.setHorizontalGroup(
            h_jp_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(h_jp_TheLoaiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(h_jp_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(h_chk_CoTrangThanThoai)
                    .addComponent(h_chk_KinhDiMa)
                    .addComponent(h_chk_HaiHuoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(h_jp_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(h_chk_GiaDinhHocDuong)
                    .addComponent(h_chk_HoatHinh)
                    .addComponent(h_chk_TamLyTinhCam))
                .addContainerGap())
        );
        h_jp_TheLoaiLayout.setVerticalGroup(
            h_jp_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(h_jp_TheLoaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(h_jp_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(h_chk_CoTrangThanThoai)
                    .addComponent(h_chk_TamLyTinhCam))
                .addGap(12, 12, 12)
                .addGroup(h_jp_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(h_chk_KinhDiMa)
                    .addComponent(h_chk_HoatHinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(h_jp_TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(h_chk_HaiHuoc)
                    .addComponent(h_chk_GiaDinhHocDuong))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên tác giả"));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(h_txt_TenTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(h_txt_TenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout h_jp_TimKiemPhimLayout = new javax.swing.GroupLayout(h_jp_TimKiemPhim);
        h_jp_TimKiemPhim.setLayout(h_jp_TimKiemPhimLayout);
        h_jp_TimKiemPhimLayout.setHorizontalGroup(
            h_jp_TimKiemPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(h_jp_TimKiemPhimLayout.createSequentialGroup()
                .addGroup(h_jp_TimKiemPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(h_jp_TheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(h_jp_TimKiemPhimLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(h_btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        h_jp_TimKiemPhimLayout.setVerticalGroup(
            h_jp_TimKiemPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(h_jp_TimKiemPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(h_jp_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(h_btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách phim"));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "", null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, "", null},
                {null, null, null},
                {null, null, null},
                {null, "", null},
                {null, null, null},
                {null, "", null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã phim", "Tên Phim", "Thời lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jp_DanhSachPhimLayout = new javax.swing.GroupLayout(jp_DanhSachPhim);
        jp_DanhSachPhim.setLayout(jp_DanhSachPhimLayout);
        jp_DanhSachPhimLayout.setHorizontalGroup(
            jp_DanhSachPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DanhSachPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(h_jp_TimKiemPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jp_DanhSachPhimLayout.setVerticalGroup(
            jp_DanhSachPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DanhSachPhimLayout.createSequentialGroup()
                .addGroup(jp_DanhSachPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(h_jp_TimKiemPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpane_HomeLayout = new javax.swing.GroupLayout(jpane_Home);
        jpane_Home.setLayout(jpane_HomeLayout);
        jpane_HomeLayout.setHorizontalGroup(
            jpane_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpane_HomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpane_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_DanhSachPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpane_HomeLayout.setVerticalGroup(
            jpane_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpane_HomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jp_DanhSachPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tabbed.addTab("Home", jpane_Home);

        jp_ThongTinChiTietPhim.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết phim"));

        ctp_lab_TenPhim.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        ctp_lab_TenPhim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ctp_lab_TenPhim.setText("Tên phim");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Tác giả:");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Diễn viên:");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Thể loại:");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("Ngôn ngữ:");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("Mô tả:");

        ctp_lab_TacGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ctp_lab_DienVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ctp_lab_TheLoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ctp_lab_NgonNgu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ctp_lab_MoTa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ctp_btn_DatVe.setText("ĐẶT VÉ");
        ctp_btn_DatVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctp_btn_DatVeActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Thời lượng:");

        ctp_lab_ThoiLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jp_ThongTinChiTietPhimLayout = new javax.swing.GroupLayout(jp_ThongTinChiTietPhim);
        jp_ThongTinChiTietPhim.setLayout(jp_ThongTinChiTietPhimLayout);
        jp_ThongTinChiTietPhimLayout.setHorizontalGroup(
            jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                .addGap(764, 764, 764)
                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29))
            .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(ctp_btn_DatVe, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(ctp_lab_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(ctp_lab_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                                    .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(29, 29, 29)
                                    .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ctp_lab_ThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ctp_lab_DienVien, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ctp_lab_NgonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ctp_lab_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(ctp_lab_TenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_ThongTinChiTietPhimLayout.setVerticalGroup(
            jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                        .addComponent(ctp_lab_TenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ctp_lab_TacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctp_lab_DienVien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctp_lab_ThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctp_lab_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctp_lab_NgonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jp_ThongTinChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_ThongTinChiTietPhimLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ctp_lab_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ctp_btn_DatVe, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ảnh phim"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(ctp_lab_AnhPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(ctp_lab_AnhPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpane_ChiTietPhimLayout = new javax.swing.GroupLayout(jpane_ChiTietPhim);
        jpane_ChiTietPhim.setLayout(jpane_ChiTietPhimLayout);
        jpane_ChiTietPhimLayout.setHorizontalGroup(
            jpane_ChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpane_ChiTietPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jp_ThongTinChiTietPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpane_ChiTietPhimLayout.setVerticalGroup(
            jpane_ChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpane_ChiTietPhimLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jpane_ChiTietPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jp_ThongTinChiTietPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        tabbed.addTab("Chi tiết phim", jpane_ChiTietPhim);

        jSplitPane2.setDividerLocation(430);

        jLabel21.setText("A");

        jLabel22.setText("B");

        jLabel23.setText("C");

        jLabel24.setText("D");

        jToggleButton1.setActionCommand("A1");
        jToggleButton1.setText("1");

        jToggleButton2.setActionCommand("A2");
        jToggleButton2.setText("2");

        jToggleButton3.setActionCommand("A3");
        jToggleButton3.setText("3");

        jToggleButton5.setActionCommand("B2");
        jToggleButton5.setText("2");

        jToggleButton6.setActionCommand("B1");
        jToggleButton6.setText("1");

        jToggleButton4.setBackground(new java.awt.Color(182, 175, 225));
        jToggleButton4.setActionCommand("B3");
        jToggleButton4.setText("3");

        jToggleButton7.setBackground(new java.awt.Color(182, 175, 225));
        jToggleButton7.setActionCommand("C3");
        jToggleButton7.setText("3");

        jToggleButton8.setActionCommand("C2");
        jToggleButton8.setText("2");

        jToggleButton9.setActionCommand("C1");
        jToggleButton9.setText("1");

        jToggleButton28.setActionCommand("D1");
        jToggleButton28.setText("1");

        javax.swing.GroupLayout dv_jp_RightGheLayout = new javax.swing.GroupLayout(dv_jp_RightGhe);
        dv_jp_RightGhe.setLayout(dv_jp_RightGheLayout);
        dv_jp_RightGheLayout.setHorizontalGroup(
            dv_jp_RightGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dv_jp_RightGheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dv_jp_RightGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dv_jp_RightGheLayout.createSequentialGroup()
                        .addComponent(jToggleButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21))
                    .addGroup(dv_jp_RightGheLayout.createSequentialGroup()
                        .addComponent(jToggleButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22))
                    .addGroup(dv_jp_RightGheLayout.createSequentialGroup()
                        .addGroup(dv_jp_RightGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(dv_jp_RightGheLayout.createSequentialGroup()
                                .addComponent(jToggleButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton9))
                            .addComponent(jToggleButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dv_jp_RightGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23))))
                .addGap(16, 16, 16))
        );
        dv_jp_RightGheLayout.setVerticalGroup(
            dv_jp_RightGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dv_jp_RightGheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dv_jp_RightGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jToggleButton1)
                    .addComponent(jToggleButton2)
                    .addComponent(jToggleButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dv_jp_RightGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jToggleButton6)
                    .addComponent(jToggleButton5)
                    .addComponent(jToggleButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dv_jp_RightGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jToggleButton9)
                    .addComponent(jToggleButton8)
                    .addComponent(jToggleButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dv_jp_RightGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton28)
                    .addComponent(jLabel24))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel16.setText("A");

        jLabel17.setText("B");

        jLabel18.setText("C");

        jLabel19.setText("D");

        jToggleButton11.setActionCommand("A5");
        jToggleButton11.setText("5");

        jToggleButton12.setActionCommand("A4");
        jToggleButton12.setText("4");

        jToggleButton10.setActionCommand("A6");
        jToggleButton10.setText("6");

        jToggleButton15.setActionCommand("B6");
        jToggleButton15.setText("6");

        jToggleButton13.setBackground(new java.awt.Color(182, 175, 225));
        jToggleButton13.setActionCommand("B4");
        jToggleButton13.setText("4");

        jToggleButton14.setBackground(new java.awt.Color(182, 175, 225));
        jToggleButton14.setActionCommand("B5");
        jToggleButton14.setText("5");

        jToggleButton16.setBackground(new java.awt.Color(182, 175, 225));
        jToggleButton16.setActionCommand("C4");
        jToggleButton16.setText("4");

        jToggleButton17.setBackground(new java.awt.Color(182, 175, 225));
        jToggleButton17.setActionCommand("C5");
        jToggleButton17.setText("5");

        jToggleButton18.setActionCommand("C6");
        jToggleButton18.setText("6");

        jToggleButton19.setActionCommand("A7");
        jToggleButton19.setText("7");

        jToggleButton20.setActionCommand("A8");
        jToggleButton20.setText("8");

        jToggleButton21.setActionCommand("A9");
        jToggleButton21.setText("9");

        jToggleButton23.setActionCommand("B8");
        jToggleButton23.setText("8");

        jToggleButton24.setActionCommand("B7");
        jToggleButton24.setText("7");

        jToggleButton22.setActionCommand("B9");
        jToggleButton22.setText("9");

        jToggleButton25.setActionCommand("C9");
        jToggleButton25.setText("9");

        jToggleButton27.setActionCommand("C7");
        jToggleButton27.setText("7");

        jToggleButton26.setActionCommand("C8");
        jToggleButton26.setText("8");

        jToggleButton29.setActionCommand("D3");
        jToggleButton29.setText("3");

        jToggleButton30.setActionCommand("D2");
        jToggleButton30.setText("2");

        javax.swing.GroupLayout dv_jp_LeftGheLayout = new javax.swing.GroupLayout(dv_jp_LeftGhe);
        dv_jp_LeftGhe.setLayout(dv_jp_LeftGheLayout);
        dv_jp_LeftGheLayout.setHorizontalGroup(
            dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dv_jp_LeftGheLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dv_jp_LeftGheLayout.createSequentialGroup()
                        .addComponent(jToggleButton22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton13))
                    .addGroup(dv_jp_LeftGheLayout.createSequentialGroup()
                        .addComponent(jToggleButton21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton12))
                    .addGroup(dv_jp_LeftGheLayout.createSequentialGroup()
                        .addComponent(jToggleButton25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton16))
                    .addGroup(dv_jp_LeftGheLayout.createSequentialGroup()
                        .addComponent(jToggleButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        dv_jp_LeftGheLayout.setVerticalGroup(
            dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dv_jp_LeftGheLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jToggleButton19)
                        .addComponent(jToggleButton20)
                        .addComponent(jToggleButton21)
                        .addComponent(jLabel16))
                    .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jToggleButton12)
                        .addComponent(jToggleButton11)
                        .addComponent(jToggleButton10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jToggleButton24)
                        .addComponent(jToggleButton23)
                        .addComponent(jToggleButton22)
                        .addComponent(jLabel17))
                    .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jToggleButton13)
                        .addComponent(jToggleButton14)
                        .addComponent(jToggleButton15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jToggleButton27)
                        .addComponent(jToggleButton26)
                        .addComponent(jToggleButton25)
                        .addComponent(jLabel18))
                    .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jToggleButton16)
                        .addComponent(jToggleButton17)
                        .addComponent(jToggleButton18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dv_jp_LeftGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jToggleButton29)
                    .addComponent(jToggleButton30))
                .addGap(32, 32, 32))
        );

        dv_lab_TenPhong.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        dv_lab_TenPhong.setForeground(new java.awt.Color(0, 0, 0));
        dv_lab_TenPhong.setText("Phòng");

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton6.setBackground(new java.awt.Color(102, 255, 204));
        jButton6.setForeground(new java.awt.Color(0, 204, 204));
        jButton6.setText(" ");

        jLabel13.setText("Ghế đang chọn");

        jButton7.setForeground(new java.awt.Color(0, 204, 204));
        jButton7.setText(" ");

        jLabel14.setText("Ghế có thể chọn");

        jButton8.setForeground(new java.awt.Color(0, 204, 204));
        jButton8.setText(" ");

        jLabel15.setText("Ghế 3");

        jButton9.setBackground(new java.awt.Color(255, 153, 0));
        jButton9.setForeground(new java.awt.Color(0, 204, 204));
        jButton9.setText(" ");
        jButton9.setEnabled(false);

        jLabel20.setText("Ghế đã bán");

        jButton11.setBackground(new java.awt.Color(182, 175, 225));
        jButton11.setForeground(new java.awt.Color(0, 204, 204));
        jButton11.setText(" ");

        jLabel31.setText("Ghế VIP");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(28, 28, 28))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jLabel13)
                    .addComponent(jButton7)
                    .addComponent(jLabel14)
                    .addComponent(jButton9)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11)
                        .addComponent(jLabel31))
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton8)
                        .addComponent(jLabel15)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dv_lab_AnhManHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(dv_lab_TenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(dv_lab_TenPhong)
                .addGap(25, 25, 25)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dv_lab_AnhManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dv_jp_LeftGhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dv_jp_RightGhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dv_jp_LeftGhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dv_jp_RightGhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        jSplitPane2.setLeftComponent(jPanel14);

        dv_lab_TenPhim.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        dv_lab_TenPhim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dv_lab_TenPhim.setText("Tên Phim");

        dv_cbo_LichChieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dv_cbo_LichChieuItemStateChanged(evt);
            }
        });

        jLabel28.setText("Chọn suất chiếu:");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tổng thanh toán"));

        dv_lab_TongTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dv_lab_TongTien.setText("0");

        dv_lab_Tt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dv_lab_Tt.setText("Tổng tiền:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(342, Short.MAX_VALUE)
                .addComponent(dv_lab_Tt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(dv_lab_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dv_lab_TongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(dv_lab_Tt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết đơn"));

        jSplitPane3.setDividerLocation(250);
        jSplitPane3.setMaximumSize(new java.awt.Dimension(528, 155));
        jSplitPane3.setMinimumSize(new java.awt.Dimension(528, 155));
        jSplitPane3.setName(""); // NOI18N

        dv_lab_ChiTietDonHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        dv_lab_ChiTietDonHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dv_lab_ChiTietDonHang.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dv_lab_ChiTietDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(dv_lab_ChiTietDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane3.setLeftComponent(jPanel7);

        dv_lab_Standard.setText("Standard");

        dv_lab_VIP.setText("VIP");

        dv_lab_Triple.setText("Triple");

        dv_lab_VIPQuantity.setForeground(new java.awt.Color(204, 0, 0));
        dv_lab_VIPQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dv_lab_VIPQuantity.setText("0");

        dv_lab_TripleQuantity.setForeground(new java.awt.Color(204, 0, 0));
        dv_lab_TripleQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dv_lab_TripleQuantity.setText("0");

        dv_lab_StandardQuantity.setForeground(new java.awt.Color(204, 0, 0));
        dv_lab_StandardQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dv_lab_StandardQuantity.setText("0");

        dv_lab_VIPSum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dv_lab_VIPSum.setText("0");

        dv_lab_TripleSum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dv_lab_TripleSum.setText("0");

        dv_lab_StandardSum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dv_lab_StandardSum.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(dv_lab_Triple, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dv_lab_VIP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dv_lab_Standard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(dv_lab_StandardQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(dv_lab_StandardSum, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dv_lab_VIPQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dv_lab_TripleQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(dv_lab_TripleSum, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                    .addComponent(dv_lab_VIPSum, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dv_lab_Standard, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dv_lab_StandardQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dv_lab_StandardSum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dv_lab_VIP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dv_lab_VIPQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dv_lab_VIPSum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dv_lab_Triple, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dv_lab_TripleQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dv_lab_TripleSum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jSplitPane3.setRightComponent(jPanel8);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 2, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 43, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dv_btn_ThanhToan.setText("THANH TOÁN");
        dv_btn_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dv_btn_ThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout split_RightLayout = new javax.swing.GroupLayout(split_Right);
        split_Right.setLayout(split_RightLayout);
        split_RightLayout.setHorizontalGroup(
            split_RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(split_RightLayout.createSequentialGroup()
                .addGroup(split_RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(split_RightLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(split_RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(split_RightLayout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(jLabel28)
                            .addGap(18, 18, 18)
                            .addComponent(dv_cbo_LichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(split_RightLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(dv_lab_TenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, split_RightLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dv_btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        split_RightLayout.setVerticalGroup(
            split_RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(split_RightLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(dv_lab_TenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(split_RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dv_cbo_LichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(dv_btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jSplitPane2.setRightComponent(split_Right);

        javax.swing.GroupLayout jpane_DatVeLayout = new javax.swing.GroupLayout(jpane_DatVe);
        jpane_DatVe.setLayout(jpane_DatVeLayout);
        jpane_DatVeLayout.setHorizontalGroup(
            jpane_DatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );
        jpane_DatVeLayout.setVerticalGroup(
            jpane_DatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );

        tabbed.addTab("Đặt vé", jpane_DatVe);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setText("THÔNG TIN CÁ NHÂN");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane1.setDividerLocation(350);

        hscn_lab_XinChao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        hscn_lab_XinChao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        hscn_lab_AnhAccount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(hscn_lab_XinChao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(hscn_lab_AnhAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(hscn_lab_AnhAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hscn_lab_XinChao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        jSplitPane1.setLeftComponent(jPanel11);

        jLabel5.setText("Tên");

        jLabel6.setText("Họ Đệm");

        jLabel7.setText("Ngày Sinh");

        jLabel8.setText("Địa Chỉ");

        bgr_GioiTinh.add(radNam);
        radNam.setText("Nam");

        jLabel9.setText("Giới Tính");

        bgr_GioiTinh.add(radNu);
        radNu.setText("Nữ");

        btn_CapNhat.setText("CẬP NHẬT");
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        btn_DoiMatKhau.setText("ĐỔI MẬT KHẨU");
        btn_DoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DoiMatKhauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(radNam, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radNu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_DiaChi)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_NgaySinh)
                                .addComponent(txt_Ten)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(txt_Ho, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(155, 155, 155))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(btn_DoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Ho, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radNam)
                    .addComponent(radNu))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_DoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel12);

        javax.swing.GroupLayout jpane_HoSoCaNhanLayout = new javax.swing.GroupLayout(jpane_HoSoCaNhan);
        jpane_HoSoCaNhan.setLayout(jpane_HoSoCaNhanLayout);
        jpane_HoSoCaNhanLayout.setHorizontalGroup(
            jpane_HoSoCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpane_HoSoCaNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpane_HoSoCaNhanLayout.setVerticalGroup(
            jpane_HoSoCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpane_HoSoCaNhanLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1))
        );

        tabbed.addTab("Hồ sơ cá nhân", jpane_HoSoCaNhan);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("THÔNG TIN BILL ĐÃ THANH TOÁN");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Các Bill đã thanh toán"));

        ttb_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Bill", "Tên phim", "Tổng tiền", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ttb_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ttb_TableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ttb_Table);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );

        ttb_jp_ChiTietBill.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết Bill"));
        ttb_jp_ChiTietBill.setMaximumSize(new java.awt.Dimension(500, 377));
        ttb_jp_ChiTietBill.setMinimumSize(new java.awt.Dimension(500, 377));
        ttb_jp_ChiTietBill.setPreferredSize(new java.awt.Dimension(500, 377));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Số ghế đã đặt:");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setText("Tên phim:");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setText("Xuất chiếu:");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setText("Phòng chiếu:");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setText("Thời gian đặt:");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setText("Tổng tiền:");

        ttb_txt_SoGheDaDat.setEditable(false);
        ttb_txt_SoGheDaDat.setColumns(20);
        ttb_txt_SoGheDaDat.setLineWrap(true);
        ttb_txt_SoGheDaDat.setRows(5);
        ttb_txt_SoGheDaDat.setText("\n");
        ttb_txt_SoGheDaDat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ttb_txt_SoGheDaDat.setPreferredSize(new java.awt.Dimension(232, 84));
        jScrollPane6.setViewportView(ttb_txt_SoGheDaDat);

        ttb_txt_TenPhim.setEditable(false);
        ttb_txt_TenPhim.setPreferredSize(new java.awt.Dimension(232, 84));

        ttb_txt_ThoiGianDat.setEditable(false);
        ttb_txt_ThoiGianDat.setPreferredSize(new java.awt.Dimension(232, 84));

        ttb_txt_PhongChieu.setEditable(false);
        ttb_txt_PhongChieu.setPreferredSize(new java.awt.Dimension(232, 84));

        ttb_txt_XuatChieu.setEditable(false);
        ttb_txt_XuatChieu.setPreferredSize(new java.awt.Dimension(232, 84));

        ttb_txt_TongTien.setEditable(false);
        ttb_txt_TongTien.setPreferredSize(new java.awt.Dimension(232, 84));

        javax.swing.GroupLayout ttb_jp_ChiTietBillLayout = new javax.swing.GroupLayout(ttb_jp_ChiTietBill);
        ttb_jp_ChiTietBill.setLayout(ttb_jp_ChiTietBillLayout);
        ttb_jp_ChiTietBillLayout.setHorizontalGroup(
            ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ttb_jp_ChiTietBillLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ttb_jp_ChiTietBillLayout.createSequentialGroup()
                        .addGroup(ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ttb_jp_ChiTietBillLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ttb_jp_ChiTietBillLayout.createSequentialGroup()
                        .addGroup(ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ttb_txt_XuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttb_txt_TenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttb_txt_ThoiGianDat, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttb_txt_PhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttb_txt_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(386, 386, 386))
        );
        ttb_jp_ChiTietBillLayout.setVerticalGroup(
            ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ttb_jp_ChiTietBillLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(ttb_txt_TenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttb_txt_XuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ttb_jp_ChiTietBillLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel36)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttb_txt_PhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttb_txt_ThoiGianDat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(7, 7, 7)
                .addGroup(ttb_jp_ChiTietBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttb_txt_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addGap(21, 21, 21))
        );

        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ttb_jp_ChiTietBill, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ttb_jp_ChiTietBill, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpane_ThongTinBillLayout = new javax.swing.GroupLayout(jpane_ThongTinBill);
        jpane_ThongTinBill.setLayout(jpane_ThongTinBillLayout);
        jpane_ThongTinBillLayout.setHorizontalGroup(
            jpane_ThongTinBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpane_ThongTinBillLayout.createSequentialGroup()
                .addGroup(jpane_ThongTinBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpane_ThongTinBillLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpane_ThongTinBillLayout.setVerticalGroup(
            jpane_ThongTinBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpane_ThongTinBillLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbed.addTab("Thông tin Bill", jpane_ThongTinBill);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 1038, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displayChiTietPhim(boolean flag){
        if(!flag){
            for(Component comp : jp_ThongTinChiTietPhim.getComponents()){
                comp.setVisible(false);
            }
        }else{
            for(Component comp : jp_ThongTinChiTietPhim.getComponents()){
                comp.setVisible(true);
            }
        }
    }
    
    private void displayChiTietDon(boolean flag){
        dv_lab_Standard.setVisible(flag);
        dv_lab_StandardQuantity.setVisible(flag);
        dv_lab_StandardSum.setVisible(flag);
        dv_lab_VIP.setVisible(flag);
        dv_lab_VIPQuantity.setVisible(flag);
        dv_lab_VIPSum.setVisible(flag);
        dv_lab_Triple.setVisible(flag);
        dv_lab_TripleQuantity.setVisible(flag);
        dv_lab_TripleSum.setVisible(flag);
    }

    private void ctp_btn_DatVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctp_btn_DatVeActionPerformed
        listTimestamps.clear();
        resetGhe();
        dv_cbo_LichChieu.removeAllItems();
        dv_lab_TenPhim.setText("<html>" + "Phim: " + chosenPhim.getTenPhim() + "</html>");
        try {
            ListLichChieuResponse response = veController.getListLichChieu(chosenPhim.getIdPhim());
            
            if (!Objects.isNull(response.getMessage())) {
                JOptionPane.showMessageDialog(rootPane, response.getMessage(), "Notification!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            for (Timestamp ts : response.getListGioChieu()) {
                SimpleDateFormat newDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String displayItem = newDate.format(ts);
                listTimestamps.add(ts);
                dv_cbo_LichChieu.addItem("Ngày chiếu: " + displayItem);
            }
            
            tabbed.setSelectedIndex(2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ctp_btn_DatVeActionPerformed

    private void resetGhe(){
        for(Component comp : dv_jp_LeftGhe.getComponents()){
            if(comp instanceof JToggleButton jtbtn){
                jtbtn.setEnabled(true);
                if(listGheVIP.contains(jtbtn.getActionCommand())){
                    jtbtn.setBackground(new Color(182,175,225));
                }else{
                    jtbtn.setBackground(null);
                }
                jtbtn.setSelected(false);
            }
        }
        for(Component comp : dv_jp_RightGhe.getComponents()){
            if(comp instanceof JToggleButton jtbtn){
                jtbtn.setEnabled(true);
                if(listGheVIP.contains(jtbtn.getActionCommand())){
                    jtbtn.setBackground(new Color(182,175,225));
                }else{
                    jtbtn.setBackground(null);
                }
                jtbtn.setSelected(false);
            }
        }
    }

    private void updateQuantityAndSumForPay(int value1, int value2){
        JLabel[] listNameLabel = {dv_lab_Standard, dv_lab_VIP, dv_lab_Triple};
        JLabel[] listQuantity = {dv_lab_StandardQuantity, dv_lab_VIPQuantity, dv_lab_TripleQuantity};
        JLabel[] listSum = {dv_lab_StandardSum, dv_lab_VIPSum, dv_lab_TripleSum};
        
        int quantity = Integer.parseInt(listQuantity[value1].getText()) + value2;
        listQuantity[value1].setText(String.valueOf(quantity));
        
        int sum = Integer.parseInt(listSum[value1].getText()) + value2 * listGiaVe.get(value1);
        listSum[value1].setText(String.valueOf(sum));
        
        boolean flag = (quantity != 0);
        listNameLabel[value1].setVisible(flag);
        listQuantity[value1].setVisible(flag);
        listSum[value1].setVisible(flag);
        
        int total = 0;
        for(JLabel a : listSum){
            total += Integer.parseInt(a.getText());
        }
        
        if(total != 0){
            dv_lab_TongTien.setText(String.valueOf(total));
        }else{
            dv_lab_TongTien.setText("0");
        }
    }

    private void updateGheForPay(JToggleButton jgbtn){
        int value1;
        if(listGheVIP.contains(jgbtn.getActionCommand())){
            value1 = 1;
        }else if(listGheTriple.contains(jgbtn.getActionCommand())){
            value1 = 2;
        }else{
            value1 = 0;
        }
        if(!jgbtn.isEnabled()){
            jgbtn.setBackground(null);
        }else if(jgbtn.isSelected()){
            jgbtn.setBackground(new Color(102,255,204));
            listGheForPay.add(jgbtn.getActionCommand());
            updateQuantityAndSumForPay(value1, 1);
        }else if(!jgbtn.isSelected()){
            if(listGheVIP.contains(jgbtn.getActionCommand())){
                jgbtn.setBackground(new Color(182,175,225));
            }else{
                jgbtn.setBackground(null);
            }
            listGheForPay.removeIf(item -> item.equals(jgbtn.getActionCommand()));
            updateQuantityAndSumForPay(value1, -1);
        }
    }

    private void addEvenListener(JToggleButton jgbtn){
        jgbtn.addItemListener(e -> {
            if(!dv_lab_TenPhong.getText().equals("Phòng")){
                updateGheForPay(jgbtn);
            }else{
                if(!jgbtn.isSelected()){
                    jgbtn.setSelected(false);
                    return;
                }else{
                    JOptionPane.showMessageDialog(rootPane, "<html>Hãy chọn phim để đặt ghế</html>", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void themEvenListener(){
        for(Component comp : dv_jp_LeftGhe.getComponents()){
            if(comp instanceof JToggleButton jtbtn){
                addEvenListener(jtbtn);
            }
        }
        for(Component comp : dv_jp_RightGhe.getComponents()){
            if(comp instanceof JToggleButton jtbtn){
                addEvenListener(jtbtn);
            }
        }
    }
    
    private void updateGheAfterPay(int index) throws SQLException{
        try {
            var response = veController.getListGhe(listTimestamps.get(index));
            for(Component comp : dv_jp_LeftGhe.getComponents()){
                if(comp instanceof JToggleButton jtbtn){
                    if(response.contains(jtbtn.getActionCommand())){
                        jtbtn.setEnabled(false);
                    }
                }
            }
            for(Component comp : dv_jp_RightGhe.getComponents()){
                if(comp instanceof JToggleButton jtbtn){
                    if(response.contains(jtbtn.getActionCommand())){
                        jtbtn.setEnabled(false);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dv_cbo_LichChieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dv_cbo_LichChieuItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            resetGhe();
            listGiaVe.clear();
            listGheForPay.clear();
            selectedIndexLichChieuList = dv_cbo_LichChieu.getSelectedIndex();
            Timestamp gioChieu = listTimestamps.get(selectedIndexLichChieuList);
            List<String> listGheDaDat = new ArrayList<>();
            
            try {
                ChiTietLichChieuResponse response = veController.getChiTietLichChieu(chosenPhim, gioChieu);
                dv_lab_TenPhong.setText(response.getTenPhong());
                dv_lab_ChiTietDonHang.setText("<html>1, PHIM: " +
                chosenPhim.getTenPhim() + "<br><br>2, Suất chiếu: " +
                dv_cbo_LichChieu.getSelectedItem() + " - " +
                dv_lab_TenPhong.getText() + "</html>");
                if (!Objects.isNull(response.getIdGhe()))
                    listGheDaDat = response.getIdGhe();
                
                for(Component comp : dv_jp_LeftGhe.getComponents()){
                    if(comp instanceof JToggleButton jtbtn){
                        if(listGheDaDat.contains(jtbtn.getActionCommand())){
                            jtbtn.setEnabled(false);
                        }
                    }
                }
                for(Component comp : dv_jp_RightGhe.getComponents()){
                    if(comp instanceof JToggleButton jtbtn){
                        if(listGheDaDat.contains(jtbtn.getActionCommand())){
                            jtbtn.setEnabled(false);
                        }
                    }
                }
                idLichChieu = response.getIdLichChieu();
                idGia = response.getIdGia();
                listGiaVe = response.getListGias();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }  
    }//GEN-LAST:event_dv_cbo_LichChieuItemStateChanged

    private void url_Pay(String tongTien, String id) {
        try {
            URL url = new URL("http://localhost:8080/PaymentAPI/payment?"
                    + "&tongTien=" + URLEncoder.encode(tongTien, "UTF-8")
                    + "&id=" + URLEncoder.encode(id, "UTF-8"));
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String json = reader.readLine();
            reader.close();
            
            String paymentUrl = com.google.gson.JsonParser.parseString(json)
                    .getAsJsonObject()
                    .get("data")
                    .getAsString();
            Desktop.getDesktop().browse(new URI(paymentUrl));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi trong quá trình thanh toán!", "Thông báo!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void dv_btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dv_btn_ThanhToanActionPerformed
        
        try {
            String total = dv_lab_TongTien.getText();
            if (total.equals("0")) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn ghế!", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String result = veController.holdSeats(currentUser.getIdTaiKhoan(), idLichChieu, listGheForPay, idGia, total);
            
            if (Objects.isNull(result)) {
                JOptionPane.showMessageDialog(rootPane, "Lỗi dữ liệu hệ thống!", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            url_Pay(total, result);
            displayListBill();
            tabbed.setSelectedIndex(4);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Notification!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_dv_btn_ThanhToanActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        indexRow = table.getSelectedRow();
        String data = table.getValueAt(indexRow, 0).toString();
        tabbed.setSelectedIndex(1);
        
        try {
            chosenPhim = phimController.getChiTietPhim(data);

            ctp_lab_TenPhim.setText("PHIM - " + chosenPhim.getTenPhim());
            ctp_lab_TacGia.setText(chosenPhim.getTacGia());
            ctp_lab_DienVien.setText("<html>" + chosenPhim.getDienVien() + "</html>");
            ctp_lab_ThoiLuong.setText(chosenPhim.getThoiLuong());
            ctp_lab_TheLoai.setText(String.join(", ", chosenPhim.getTheLoai()));
            ctp_lab_NgonNgu.setText(chosenPhim.getNgonNgu());
            ctp_lab_MoTa.setText("<html>" + chosenPhim.getMoTa() + "</html>");
            String imageFileName = chosenPhim.getAnhPhim();
            setLabelImageFromPath(ctp_lab_AnhPhim, imageFileName);
            displayChiTietPhim(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Lỗi!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void h_btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h_btn_TimKiemActionPerformed

        model.setRowCount(0);
        String searchTen = h_txt_TenPhim.getText();
        String searchTacGia = h_txt_TenTacGia.getText();
        List<String> list_chosen = new ArrayList<>();
        for(Component a : h_jp_TheLoai.getComponents()){
            if(a instanceof JCheckBox jcb){
                if(jcb.isSelected()){
                    list_chosen.add(jcb.getActionCommand());
                }
            }
        }
        
        try {
            List<SearchPhimResponse> responses = phimController.search(searchTen, searchTacGia, list_chosen);
            
            for (SearchPhimResponse spr : responses) {
                Vector v = new Vector();
                v.add(spr.getIdPhim());
                v.add(spr.getTenPhim());
                v.add(spr.getThoiLuong());
                model.addRow(v);
            }
            
            table.setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_h_btn_TimKiemActionPerformed

    private void btn_DoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_DoiMatKhauActionPerformed
        DoiMatKhauDialog dialog = new DoiMatKhauDialog(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void btn_LogOutActionPerformed(java.awt.event.ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Bạn muốn đăng xuất không ?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            if (loginController != null) {
                loginController.logout();
            }

            this.dispose();
            UI_Login login = new UI_Login();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        }
    }

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {
         if (loginController == null) 
    if (currentUser == null || currentUser.getIdTaiKhoan() == null || currentUser.getIdTaiKhoan().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa đăng nhập hoặc thiếu ID tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String ten = txt_Ten.getText().trim();
    String hoDem = txt_Ho.getText().trim();
    String ngaySinhStr = txt_NgaySinh.getText().trim(); 
    String diaChi = txt_DiaChi.getText().trim();
    String gioiTinh = radNam.isSelected() ? "Nam" : (radNu.isSelected() ? "Nữ" : "");

    if (!ngaySinhStr.isEmpty()) {
        if (!ngaySinhStr.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            JOptionPane.showMessageDialog(this, "Định dạng ngày sinh phải là dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(ngaySinhStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    LoginResponse res = loginController.capNhatThongTin(
            currentUser.getIdTaiKhoan(),  
            hoDem,
            ten,
            ngaySinhStr,
            diaChi,
            gioiTinh
    );

    if (res == null || !res.isSuccess() || res.getTaiKhoan() == null) {
        String msg = (res != null && res.getMessage() != null) ? res.getMessage() : "Cập nhật thất bại!";
        JOptionPane.showMessageDialog(this, msg, "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    currentUser = res.getTaiKhoan();
    hscn_lab_XinChao.setText("Xin chào, " + currentUser.getTen() + "!");

    JOptionPane.showMessageDialog(this, res.getMessage() != null ? res.getMessage() : "Cập nhật thành công!");
    }

    private void displayListBill(){
        model2.setRowCount(0);
        try {
            var response = veController.getListBill();
            response.forEach((t) -> {
                Vector<String> v = new Vector<>();
                v.add(t.getIdBill());
                v.add(t.getTenPhim());
                v.add(String.valueOf(t.getTongTien()));
                v.add(t.getTrangThai());
                model2.addRow(v);
            });
            ttb_Table.setModel(model2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayChiTietBill(boolean flag){
        if(!flag){
            for(Component comp : ttb_jp_ChiTietBill.getComponents()){
                comp.setVisible(false);
            }
        }else{
            for(Component comp : ttb_jp_ChiTietBill.getComponents()){
                comp.setVisible(true);
            }
        }
    }
    
    private void ttb_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ttb_TableMouseClicked
        indexRow = ttb_Table.getSelectedRow();
        String idBill = ttb_Table.getValueAt(indexRow, 0).toString();
        try {
            var data = veController.getChiTietBill(idBill);
            ttb_txt_TenPhim.setText(data.getTenPhim());
            ttb_txt_XuatChieu.setText(data.getXuatChieu());
            ttb_txt_SoGheDaDat.setText(data.getSoGheDaDat());
            ttb_txt_PhongChieu.setText(data.getPhongChieu().substring(13));
            ttb_txt_ThoiGianDat.setText(data.getThoiGianDat());
            ttb_txt_TongTien.setText(String.valueOf(data.getTongTien()));
            displayChiTietBill(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ttb_TableMouseClicked

     private void setLabelImageFromPath(javax.swing.JLabel label, String imageFileName) {
        if (imageFileName == null) return;
        imageFileName = imageFileName.trim();
        if (imageFileName.isEmpty()) return;
        try {
            ImageIcon imgIcon = null;
            if (imageFileName.startsWith("http://") || imageFileName.startsWith("https://")) {
                java.net.URL url = new java.net.URL(imageFileName);
                imgIcon = new ImageIcon(url);
            } else {
                java.io.File f = new java.io.File(imageFileName);
                if (f.isAbsolute() && f.exists()) {
                    imgIcon = new ImageIcon(f.getAbsolutePath());
                } else {
                    System.out.println("Ảnh không phải đường dẫn tuyệt đối hoặc không tồn tại: " + imageFileName);
                }
            }
            if (imgIcon != null && imgIcon.getImage() != null) {
                Image img = imgIcon.getImage().getScaledInstance(label.getWidth() > 0 ? label.getWidth() : 200,
                        label.getHeight() > 0 ? label.getHeight() : 300, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));
            } else {
                System.out.println("Không tìm thấy ảnh cho: " + imageFileName);
            }
        } catch (Exception e) {
            System.out.println("Không thể load ảnh từ: " + imageFileName + " - " + e.getMessage());
        }
    }
     
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        displayListBill();
        resetGhe();
        try {
            updateGheAfterPay(selectedIndexLichChieuList);
        } catch (SQLException ex) {
            Logger.getLogger(UI_KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        dv_lab_StandardQuantity.setText("0");
        dv_lab_StandardSum.setText("0");
        dv_lab_VIPQuantity.setText("0");
        dv_lab_VIPSum.setText("0");
        dv_lab_TripleQuantity.setText("0");
        dv_lab_TripleSum.setText("0");
        dv_lab_TongTien.setText("0");
        displayChiTietDon(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgr_GioiTinh;
    private javax.swing.JButton btn_CapNhat;
    private javax.swing.JButton btn_DoiMatKhau;
    private javax.swing.JButton btn_LogOut;
    private javax.swing.JButton ctp_btn_DatVe;
    private javax.swing.JLabel ctp_lab_AnhPhim;
    private javax.swing.JLabel ctp_lab_DienVien;
    private javax.swing.JLabel ctp_lab_MoTa;
    private javax.swing.JLabel ctp_lab_NgonNgu;
    private javax.swing.JLabel ctp_lab_TacGia;
    private javax.swing.JLabel ctp_lab_TenPhim;
    private javax.swing.JLabel ctp_lab_TheLoai;
    private javax.swing.JLabel ctp_lab_ThoiLuong;
    private javax.swing.JButton dv_btn_ThanhToan;
    private javax.swing.JComboBox<String> dv_cbo_LichChieu;
    private javax.swing.JPanel dv_jp_LeftGhe;
    private javax.swing.JPanel dv_jp_RightGhe;
    private javax.swing.JLabel dv_lab_AnhManHinh;
    private javax.swing.JLabel dv_lab_ChiTietDonHang;
    private javax.swing.JLabel dv_lab_Standard;
    private javax.swing.JLabel dv_lab_StandardQuantity;
    private javax.swing.JLabel dv_lab_StandardSum;
    private javax.swing.JLabel dv_lab_TenPhim;
    private javax.swing.JLabel dv_lab_TenPhong;
    private javax.swing.JLabel dv_lab_TongTien;
    private javax.swing.JLabel dv_lab_Triple;
    private javax.swing.JLabel dv_lab_TripleQuantity;
    private javax.swing.JLabel dv_lab_TripleSum;
    private javax.swing.JLabel dv_lab_Tt;
    private javax.swing.JLabel dv_lab_VIP;
    private javax.swing.JLabel dv_lab_VIPQuantity;
    private javax.swing.JLabel dv_lab_VIPSum;
    private javax.swing.JButton h_btn_TimKiem;
    private javax.swing.JCheckBox h_chk_CoTrangThanThoai;
    private javax.swing.JCheckBox h_chk_GiaDinhHocDuong;
    private javax.swing.JCheckBox h_chk_HaiHuoc;
    private javax.swing.JCheckBox h_chk_HoatHinh;
    private javax.swing.JCheckBox h_chk_KinhDiMa;
    private javax.swing.JCheckBox h_chk_TamLyTinhCam;
    private javax.swing.JPanel h_jp_TheLoai;
    private javax.swing.JPanel h_jp_TimKiemPhim;
    private javax.swing.JTextField h_txt_TenPhim;
    private javax.swing.JTextField h_txt_TenTacGia;
    private javax.swing.JLabel hscn_lab_AnhAccount;
    private javax.swing.JLabel hscn_lab_XinChao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToggleButton jToggleButton15;
    private javax.swing.JToggleButton jToggleButton16;
    private javax.swing.JToggleButton jToggleButton17;
    private javax.swing.JToggleButton jToggleButton18;
    private javax.swing.JToggleButton jToggleButton19;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton20;
    private javax.swing.JToggleButton jToggleButton21;
    private javax.swing.JToggleButton jToggleButton22;
    private javax.swing.JToggleButton jToggleButton23;
    private javax.swing.JToggleButton jToggleButton24;
    private javax.swing.JToggleButton jToggleButton25;
    private javax.swing.JToggleButton jToggleButton26;
    private javax.swing.JToggleButton jToggleButton27;
    private javax.swing.JToggleButton jToggleButton28;
    private javax.swing.JToggleButton jToggleButton29;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton30;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel jp_DanhSachPhim;
    private javax.swing.JPanel jp_ThongTinChiTietPhim;
    private javax.swing.JPanel jpane_ChiTietPhim;
    private javax.swing.JPanel jpane_DatVe;
    private javax.swing.JPanel jpane_HoSoCaNhan;
    private javax.swing.JPanel jpane_Home;
    private javax.swing.JPanel jpane_ThongTinBill;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JPanel split_Right;
    private tabbedCustom.TabbedPaneCustom tabbed;
    private javax.swing.JTable table;
    private javax.swing.JTable ttb_Table;
    private javax.swing.JPanel ttb_jp_ChiTietBill;
    private javax.swing.JTextField ttb_txt_PhongChieu;
    private javax.swing.JTextArea ttb_txt_SoGheDaDat;
    private javax.swing.JTextField ttb_txt_TenPhim;
    private javax.swing.JTextField ttb_txt_ThoiGianDat;
    private javax.swing.JTextField ttb_txt_TongTien;
    private javax.swing.JTextField ttb_txt_XuatChieu;
    private javax.swing.JTextField txt_DiaChi;
    private javax.swing.JTextField txt_Ho;
    private javax.swing.JTextField txt_NgaySinh;
    private javax.swing.JTextField txt_Ten;
    // End of variables declaration//GEN-END:variables

}

