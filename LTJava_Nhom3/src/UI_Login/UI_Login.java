package UI_Login;

import Global.Session;
import Logic.controller.LoginController;
import javax.swing.JOptionPane;
public class UI_Login extends javax.swing.JFrame {
    
    public static Session session;
    private LoginController loginController;

    public UI_Login() {
         initComponents();
        // Khởi tạo controller
        loginController = new LoginController();
        // (tuỳ bạn) căn giữa màn hình
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        khongco = new javax.swing.JPanel();
        txtTenDangNhap = new javax.swing.JTextField();
        khongbiry = new javax.swing.JPanel();
        txtMatKhau = new javax.swing.JPasswordField();
        btn_DangNhap = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnDangKy = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("WELCOM TO N5CINEMA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel2)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        khongco.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập số điện thoại"));

        javax.swing.GroupLayout khongcoLayout = new javax.swing.GroupLayout(khongco);
        khongco.setLayout(khongcoLayout);
        khongcoLayout.setHorizontalGroup(
            khongcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khongcoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addContainerGap())
        );
        khongcoLayout.setVerticalGroup(
            khongcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khongcoLayout.createSequentialGroup()
                .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        khongbiry.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập mật khẩu:"));

        javax.swing.GroupLayout khongbiryLayout = new javax.swing.GroupLayout(khongbiry);
        khongbiry.setLayout(khongbiryLayout);
        khongbiryLayout.setHorizontalGroup(
            khongbiryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khongbiryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMatKhau)
                .addContainerGap())
        );
        khongbiryLayout.setVerticalGroup(
            khongbiryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khongbiryLayout.createSequentialGroup()
                .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_DangNhap.setText("ĐĂNG NHẬP");
        btn_DangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangNhapActionPerformed(evt);
            }
        });

        jLabel1.setText("Đăng kí tài khoản ngay! ");

        btnDangKy.setText("Đăng ký");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_DangNhap)
                        .addGap(209, 209, 209))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDangKy))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(khongbiry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(khongco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(63, 63, 63))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(khongco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(khongbiry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnDangKy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btn_DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btn_DangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangNhapActionPerformed
        String soDienThoai = txtTenDangNhap.getText().trim();
        char[] passwordChars = txtMatKhau.getPassword();
        String matKhau = new String(passwordChars);

        // 1. Kiểm tra input cơ bản
        if (soDienThoai.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập đầy đủ số điện thoại và mật khẩu.",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // (tuỳ bạn) kiểm tra format số điện thoại
        if (!soDienThoai.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Số điện thoại phải là số và đủ 10 chữ số.",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            // 2. Gọi controller để đăng nhập
            LoginController.LoginResponse response = loginController.dangNhap(soDienThoai, matKhau);
            
            // 4. Nếu không tìm thấy tài khoản (sai mật khẩu / không tồn tại)
            if (response.getTaiKhoan() == null) {
                JOptionPane.showMessageDialog(
                        this,
                        response.getMessage(),   // ví dụ: "Số điện thoại hoặc mật khẩu không đúng"
                        "Đăng nhập thất bại",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            session.setCurrentUser(response.getTaiKhoan());
            // 5. Đăng nhập thành công
            JOptionPane.showMessageDialog(
                    this,
                    response.getMessage(),       // ví dụ: "Đăng nhập thành công"
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // TODO: mở màn hình chính sau khi đăng nhap
            // UI_Main uiMain = new UI_Main();
            // uiMain.setLocationRelativeTo(null);
            // uiMain.setVisible(true);
            // this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Lỗi khi đăng nhập: " + ex.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btn_DangNhapActionPerformed

    
    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed

        DangKy dialog = new DangKy(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnDangKyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangKy;
    private javax.swing.JButton btn_DangNhap;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel khongbiry;
    private javax.swing.JPanel khongco;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
