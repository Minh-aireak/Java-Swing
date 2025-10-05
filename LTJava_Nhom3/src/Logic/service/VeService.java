package Logic.service;

import Logic.dto.response.BillResponse;
import Logic.dto.response.ChiTietBillResponse;
import Logic.entity.Ve;
import Logic.repository.VeRepository;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class VeService {
    Logic.repository.VeRepository veRepository;
    String idTaiKhoan = null;
    
    public void selectGhe() {
        
    }
    
    public void resetGhe(JPanel dv_jp_LeftGhe, JPanel dv_jp_RightGhe, ArrayList<String> listGheVIP) {
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
    
    public void updateQuantityAndSumForPay() {
        
    }
    
    public void createVe(){
         
    }
    
    public boolean deleteVe(String idVe) {
        return veRepository.huyVe(idVe);
    }
    
    public ArrayList<Ve> getListVe() {
        return veRepository.layDanhSachDatVe();
    }
    
    public List<BillResponse> getListBill() {
        return veRepository.getListBill(idTaiKhoan);
    }
    
    public ChiTietBillResponse getChiTietBill(String idBill) throws SQLException {
        var reponse = veRepository.getChiTietBill(idBill, idTaiKhoan);
        return reponse;
    }
    
//    public Object getListGheDaDat
}
