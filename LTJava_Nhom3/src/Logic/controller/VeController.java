package Logic.controller;

import ConnectDatabase.DatabaseConnection;
import Logic.dto.request.DataGetLichChieuRequest;
import Logic.dto.response.BillResponse;
import Logic.dto.response.ChiTietBillResponse;
import Logic.dto.response.DataGetLichChieuResponse;
import Logic.entity.Phim;
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
    
    public List<String> createVe(Phim chosenPhim) {
        return veService.createVe(chosenPhim);
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
        return veService.getChiTietBill(idBill);
    }
    
    public DataGetLichChieuResponse getChiTietLichChieu(DataGetLichChieuRequest request) {
        return veService.getChiTietLichChieu(request);
    }
}
