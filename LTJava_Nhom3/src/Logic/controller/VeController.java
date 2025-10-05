package Logic.controller;

import ConnectDatabase.DatabaseConnection;
import Logic.dto.response.BillResponse;
import Logic.dto.response.ChiTietBillResponse;
import Logic.entity.Ve;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Logic.service.VeService;
import static UI_KhachHang.DatVe_DAO.getLichChieu;
import UI_KhachHang.UI_KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VeController {
    VeService veService;
    ArrayList<String> listGheVIP = new ArrayList<>(Arrays.asList("B3,B4,B5,C3,C4,C5".split(",")));
    ArrayList<String> listGheTriple = new ArrayList<>(Arrays.asList("D1,D2,D3".split(",")));
    ArrayList<String> listGheForPay = new ArrayList<>();
    ArrayList<Timestamp> listTimestamps = new ArrayList<>();
    
    public void thanhToan() {
        
    }
    
    public void createVe(Component c, JLabel jLabel, JPanel leftGhe
            , JPanel rightGhe, JComboBox<String> dv_cbo_LichChieu) {
        listTimestamps.clear();
        if(jLabel.getText().isEmpty()){
            JOptionPane.showMessageDialog(c, "Bạn chưa chọn phim!");
            return;
        }
        veService.resetGhe(leftGhe, rightGhe, listGheVIP);
        dv_cbo_LichChieu.removeAllItems();
        dv_lab_TenPhim.setText("<html>" + "PHIM: " + chosenPhim.getTenPhim() + "</html>");
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT gioChieu " +
                                             "FROM lich_chieu lc " +
                                             "JOIN phim p ON lc.idPhim = p.idPhim " +
                                             "WHERE lc.idPhim = ? " +
                                             "AND lc.gioChieu >= DATE_ADD(NOW(), INTERVAL 1 HOUR) " +
                                             "ORDER BY gioChieu ASC");
            ps.setString(1, chosenPhim.getIdPhim());
            ResultSet rs = ps.executeQuery();
            boolean flag = false;
            while (rs.next()){
                flag = true;
                Timestamp ts = rs.getTimestamp("gioChieu");
                SimpleDateFormat newDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String displayItem = newDate.format(ts);
                listTimestamps.add(ts);
                dv_cbo_LichChieu.addItem("Ngày chiếu: " + displayItem);
            }
            if(!flag && !getLichChieu(table.getValueAt(table.getSelectedRow(), 0).toString())){
                JOptionPane.showMessageDialog(rootPane, "Phim hiện tại chưa cập nhật lịch chiếu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }else if(!flag && getLichChieu(table.getValueAt(table.getSelectedRow(), 0).toString())){
                JOptionPane.showMessageDialog(rootPane, "Phim hiện tại đã qua thời gian đặt vé", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            tabbed.setSelectedIndex(2);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UI_KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteVe(Component parent, DefaultTableModel defaultTableModel, JTable jTable){
        int selectedRow = jTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(parent, "Cần chọn 1 vé để hủy!", "Select row", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(parent, "Bạn có chắc muốn hủy vé này?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String idVe = defaultTableModel.getValueAt(selectedRow, 0).toString();
                if (veService.deleteVe(idVe)) {
                    defaultTableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(parent, "Hủy vé thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parent, "Hủy vé thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public void getListVe(DefaultTableModel defaultTableModel, JTable jTable){
        defaultTableModel.setRowCount(0);
        ArrayList<Ve> dsVe = veService.getListVe();
        for (Ve ve : dsVe) {
            defaultTableModel.addRow(new Object[]{
                ve.getIdVe(),
                ve.getTenKhachHang(),
                ve.getTenPhim(),
                ve.getGioChieu(), 
                ve.getGhe(),
                ve.getTenPhong(),
                ve.getGiaVe()
            });
        }
        jTable.setModel(defaultTableModel);
    }
    
    public List<BillResponse> getListBill(){
        return veService.getListBill();
    }
    
    public ChiTietBillResponse getChiTietBill(String idBill) {
        ChiTietBillResponse data = null;
        try {
            data = veService.getChiTietBill(idBill);
        } catch (Exception e) {
            Logger.getLogger(VeController.class.getName()).log(Level.SEVERE, null, e);
        }
        return data;
    }
}
