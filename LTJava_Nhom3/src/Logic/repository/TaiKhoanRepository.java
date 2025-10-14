package Logic.repository;

import ConnectDatabase.DatabaseConnection;
import Logic.entity.TaiKhoan;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanRepository {
    private static final Logger LOGGER = Logger.getLogger(TaiKhoanRepository.class.getName());
    
    public TaiKhoan dangNhap(String soDienThoai, String matKhau) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idTaiKhoan, ten, hoDem, email, ngaySinh, diaChi, gioiTinh FROM tai_khoan WHERE soDienThoai = ? AND matKhau = ?";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TaiKhoan tk = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, soDienThoai);
            ps.setString(2, matKhau);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                tk.setIdTaiKhoan(rs.getString("idTaiKhoan"));
                tk.setHoDem(rs.getString("hoDem"));
                tk.setEmail(rs.getString("email"));
                tk.setNgaySinh(rs.getString("ngaySinh"));
                tk.setDiaChi(rs.getString("diaChi"));
                tk.setGioiTinh(rs.getString("gioiTinh"));
                tk.setSoDienThoai(soDienThoai);
                tk.setTen(rs.getString("ten"));
            }
            return tk;
            
        } catch (SQLException ex) {
            throw new SQLException("Lỗi truy vấn database: " + ex.getMessage());
        } finally {
            closeResources(rs, ps, conn);
        }
    }
    
    public boolean kiemTraTrungSoDienThoaiHoacEmail(String soDienThoai, String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM tai_khoan WHERE soDienThoai = ? OR email = ?";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, soDienThoai);
            ps.setString(2, email);
            
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
            
        }
//        catch (ClassNotFoundException ex) {
//            LOGGER.log(Level.SEVERE, "Lỗi không tìm thấy driver database", ex);
//            throw new ClassNotFoundException("Lỗi kết nối database: " + ex.getMessage());
//        } 
        catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi kiểm tra trùng", ex);
            throw new SQLException("Lỗi truy vấn database: " + ex.getMessage());
        } finally {
            closeResources(rs, ps, conn);
        }
    }
    
    public String taoMaTaiKhoanMoi() throws SQLException, ClassNotFoundException {
        String sqlCheck = "SELECT idTaiKhoan FROM tai_khoan WHERE idTaiKhoan = ?";
        
        Connection conn = null;
        PreparedStatement checkStmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            checkStmt = conn.prepareStatement(sqlCheck);
            
            int stt = 1;
            String newID;
            while (true) {
                newID = String.format("KH%04d", stt);
                checkStmt.setString(1, newID);
                rs = checkStmt.executeQuery();
                if (!rs.next()) {
                    break;
                }
                rs.close();
                stt++;
            }
            return newID;
            
        }
//        catch (ClassNotFoundException ex) {
//            LOGGER.log(Level.SEVERE, "Lỗi không tìm thấy driver database", ex);
//            throw new ClassNotFoundException("Lỗi kết nối database: " + ex.getMessage());
//        }
        catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi tạo mã tài khoản", ex);
            throw new SQLException("Lỗi truy vấn database: " + ex.getMessage());
        } finally {
            closeResources(rs, checkStmt, conn);
        }
    }
    
    public boolean dangKyTaiKhoan(TaiKhoan taiKhoan) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tai_khoan (idTaiKhoan, soDienThoai, email, matKhau, hoDem, ten, ngaySinh, diaChi, gioiTinh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, TaiKhoan.getIdTaiKhoan());
            stmt.setString(2, taiKhoan.getSoDienThoai());
            stmt.setString(3, taiKhoan.getEmail());
            stmt.setString(4, taiKhoan.getMatKhau());
            stmt.setString(5, taiKhoan.getHoDem());
            stmt.setString(6, taiKhoan.getTen());
            
            if (taiKhoan.getNgaySinh() != null) {
                stmt.setDate(7, new java.sql.Date(taiKhoan.getNgaySinh().getTime()));
            } else {
                stmt.setNull(7, java.sql.Types.DATE);
            }
            
            if (taiKhoan.getDiaChi() != null && !taiKhoan.getDiaChi().isEmpty()) {
                stmt.setString(8, taiKhoan.getDiaChi());
            } else {
                stmt.setNull(8, java.sql.Types.VARCHAR);
            }
            
            stmt.setString(9, taiKhoan.getGioiTinh());
            
            return stmt.executeUpdate() > 0;
            
        } 
//        catch (ClassNotFoundException ex) {
//            LOGGER.log(Level.SEVERE, "Lỗi không tìm thấy driver database", ex);
//            throw new ClassNotFoundException("Lỗi kết nối database: " + ex.getMessage());
//        }
        catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi đăng ký tài khoản", ex);
            throw new SQLException("Lỗi truy vấn database: " + ex.getMessage());
        } finally {
            closeResources(null, stmt, conn);
        }
    }
    
    public boolean doiMatKhau(String idTaiKhoan, String matKhauMoi) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tai_khoan SET matKhau = ? WHERE idTaiKhoan = ?";
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, matKhauMoi);
            ps.setString(2, idTaiKhoan);
            
            return ps.executeUpdate() > 0;
            
        }
//        catch (ClassNotFoundException ex) {
//            LOGGER.log(Level.SEVERE, "Lỗi không tìm thấy driver database", ex);
//            throw new ClassNotFoundException("Lỗi kết nối database: " + ex.getMessage());
//        } 
        catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi đổi mật khẩu", ex);
            throw new SQLException("Lỗi truy vấn database: " + ex.getMessage());
        } finally {
            closeResources(null, ps, conn);
        }
    }
    
    public boolean kiemTraMatKhauCu(String idTaiKhoan, String matKhauCu) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM tai_khoan WHERE idTaiKhoan = ? AND matKhau = ?";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idTaiKhoan);
            ps.setString(2, matKhauCu);
            
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
            
        }
//        catch (ClassNotFoundException ex) {
//            LOGGER.log(Level.SEVERE, "Lỗi không tìm thấy driver database", ex);
//            throw new ClassNotFoundException("Lỗi kết nối database: " + ex.getMessage());
//        }
        catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi kiểm tra mật khẩu cũ", ex);
            throw new SQLException("Lỗi truy vấn database: " + ex.getMessage());
        } finally {
            closeResources(rs, ps, conn);
        }
    }
    
    public boolean capNhatThongTin(TaiKhoan taiKhoan) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tai_khoan SET ten = ?, hoDem = ?, ngaySinh = ?, diaChi = ?, gioiTinh = ? WHERE idTaiKhoan = ?";
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, taiKhoan.getTen());
            ps.setString(2, taiKhoan.getHoDem());
            
            if (taiKhoan.getNgaySinh() != null) {
                ps.setDate(3, new java.sql.Date(taiKhoan.getNgaySinh().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }
            
            ps.setString(4, taiKhoan.getDiaChi());
            ps.setString(5, taiKhoan.getGioiTinh());
            ps.setString(6, TaiKhoan.getIdTaiKhoan());
            
            return ps.executeUpdate() > 0;
            
        } 
//        catch (ClassNotFoundException ex) {
//            LOGGER.log(Level.SEVERE, "Lỗi không tìm thấy driver database", ex);
//            throw new ClassNotFoundException("Lỗi kết nối database: " + ex.getMessage());
//        } 
        catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi cập nhật thông tin", ex);
            throw new SQLException("Lỗi truy vấn database: " + ex.getMessage());
        } finally {
            closeResources(null, ps, conn);
        }
    }
    
    public TaiKhoan getThongTinTaiKhoan(String idTaiKhoan) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tai_khoan WHERE idTaiKhoan = ?";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idTaiKhoan);
            
            rs = ps.executeQuery();
            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                TaiKhoan.setIdTaiKhoan(rs.getString("idTaiKhoan"));
                TaiKhoan.setTenHienThi(rs.getString("ten"));
                tk.setSoDienThoai(rs.getString("soDienThoai"));
                tk.setEmail(rs.getString("email"));
                tk.setHoDem(rs.getString("hoDem"));
                tk.setTen(rs.getString("ten"));
                tk.setNgaySinh(rs.getDate("ngaySinh"));
                tk.setDiaChi(rs.getString("diaChi"));
                tk.setGioiTinh(rs.getString("gioiTinh"));
                return tk;
            }
            return null;
        }
//        catch (ClassNotFoundException ex) {
//            LOGGER.log(Level.SEVERE, "Lỗi không tìm thấy driver database", ex);
//            throw new ClassNotFoundException("Lỗi kết nối database: " + ex.getMessage());
//        }
        catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi lấy thông tin tài khoản", ex);
            throw new SQLException("Lỗi truy vấn database: " + ex.getMessage());
        } finally {
            closeResources(rs, ps, conn);
        }
    }
    
    // Phương thức hỗ trợ đóng resources
    private void closeResources(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Lỗi khi đóng ResultSet", e);
        }
        
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Lỗi khi đóng PreparedStatement", e);
        }
        
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Lỗi khi đóng Connection", e);
        }
    }
}