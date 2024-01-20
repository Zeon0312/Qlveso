package Controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.VeSo;
public class VeSoModel {
    private Connection connection;

    public VeSoModel() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanliveso", "root", "Nhat@2005");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS ve_so (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "ma_ve VARCHAR(255)," +
                    "vung_mien VARCHAR(255)," +
                    "so_luong INT," +
                    "doanh_thu INT," +
                    "ngay_thang DATE)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<VeSo> getAllVeSo() {
        List<VeSo> veSoList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ve_so");

            while (resultSet.next()) {
                VeSo veSo = new VeSo(
                        resultSet.getInt("id"),
                        resultSet.getString("ma_ve"),
                        resultSet.getString("vung_mien"),
                        resultSet.getInt("so_luong"),
                        resultSet.getInt("doanh_thu"),
                        resultSet.getDate("ngay_thang")
                );
                veSoList.add(veSo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return veSoList;
    }

    public void addVeSo(VeSo veSo) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ve_so (ma_ve, vung_mien, so_luong, doanh_thu, ngay_thang) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, veSo.getMaVe());
            statement.setString(2, veSo.getVungMien());
            statement.setInt(3, veSo.getSoLuong());
            statement.setInt(4, veSo.getDoanhThu());
            statement.setDate(5, veSo.getNgayThang());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateVeSo(VeSo veSo) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ve_so SET ma_ve=?, vung_mien=?, so_luong=?, doanh_thu=?, ngay_thang=? WHERE id=?");
            statement.setString(1, veSo.getMaVe());
            statement.setString(2, veSo.getVungMien());
            statement.setInt(3, veSo.getSoLuong());
            statement.setInt(4, veSo.getDoanhThu());
            statement.setDate(5, veSo.getNgayThang());
            statement.setInt(6, veSo.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteVeSo(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ve_so WHERE id=?");
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public VeSo timVeSoTheoMa(String maVe) {
        VeSo veSo = null;
        String query = "SELECT * FROM ve_so WHERE ma_ve = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maVe);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                veSo = new VeSo(
                        resultSet.getInt("id"),
                        resultSet.getString("ma_ve"),
                        resultSet.getString("vung_mien"),
                        resultSet.getInt("so_luong"),
                        resultSet.getInt("doanh_thu"),
                        resultSet.getDate("ngay_thang")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veSo;
    }

}

