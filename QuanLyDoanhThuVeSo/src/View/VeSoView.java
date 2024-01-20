package View;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import Controller.VeSoModel;
import Model.VeSo;
public class VeSoView extends JFrame {
    private VeSoModel model;
    private JTextField txtMaVe, txtSoLuong, txtDoanhThu, txtNgayThang;
    private JComboBox<String> cmbVungMien;
    private DefaultTableModel tableModel;
    private JTable dataTable;
    private JPanel contentPane;
    private JTextField txt_Tim;
    public VeSoView(VeSoModel model) {
        super("Quản Lý Vé Số");
        this.model = model;
        initializeUI();
        loadDataToTable();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 500);
        contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Mã Vé");
        tableModel.addColumn("Vùng Miền");
        tableModel.addColumn("Số Lượng");
        tableModel.addColumn("Doanh Thu");
        tableModel.addColumn("Ngày Tháng");

        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);

        cmbVungMien = new JComboBox<>(new String[]{"Miền Bắc", "Miền Trung", "Miền Nam"});
        txtMaVe = new JTextField(10);
        txtSoLuong = new JTextField(10);
        txtDoanhThu = new JTextField(10);
        txtNgayThang = new JTextField(10);

        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        JButton btnTim = new JButton("Tìm");

        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themVeSo();
                loadDataToTable();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                suaVeSo();
                loadDataToTable();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xoaVeSo();
                loadDataToTable();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timVeSo();
            }
        });


//
        // Set bounds for each component
        scrollPane.setBounds(10, 78, 600, 190);
        cmbVungMien.setBounds(33, 343, 150, 20);
        txtMaVe.setBounds(56, 296, 121, 20);
        txtSoLuong.setBounds(452, 316, 106, 20);
        txtDoanhThu.setBounds(262, 296, 121, 20);
        txtNgayThang.setBounds(262, 343, 121, 20);

        btnThem.setBounds(77, 374, 100, 30);
        btnSua.setBounds(262, 374, 100, 30);
        btnXoa.setBounds(421, 374, 100, 30);
        btnTim.setBounds(382, 21, 100, 30);

        // Add components to the frame
        getContentPane().add(scrollPane);
        getContentPane().add(cmbVungMien);
        getContentPane().add(txtMaVe);
        getContentPane().add(txtSoLuong);
        getContentPane().add(txtDoanhThu);
        getContentPane().add(txtNgayThang);
        getContentPane().add(btnThem);
        getContentPane().add(btnSua);
        getContentPane().add(btnXoa);
        getContentPane().add(btnTim);
        
        JLabel lblNewLabel = new JLabel("Mã Vé");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 299, 46, 14);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Số lượng");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(392, 319, 64, 14);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Doanh thu");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(181, 299, 86, 14);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Ngày Tháng");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(187, 346, 80, 14);
        contentPane.add(lblNewLabel_3);
        
        txt_Tim = new JTextField();
        txt_Tim.setBounds(232, 26, 130, 20);
        contentPane.add(txt_Tim);
        txt_Tim.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Mã vé");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNewLabel_4.setBounds(155, 23, 67, 28);
        contentPane.add(lblNewLabel_4);

//        pack();
//        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadDataToTable() {
        List<VeSo> veSoList = model.getAllVeSo();

        tableModel.setRowCount(0);

        for (VeSo veSo : veSoList) {
            Object[] row = {
                    veSo.getId(),
                    veSo.getMaVe(),
                    veSo.getVungMien(),
                    veSo.getSoLuong(),
                    veSo.getDoanhThu(),
                    veSo.getNgayThang()
            };
            tableModel.addRow(row);
        }
    }

    private void themVeSo() {
        String maVe = txtMaVe.getText();
        String vungMien = (String) cmbVungMien.getSelectedItem();
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        int doanhThu = Integer.parseInt(txtDoanhThu.getText());
        String ngayThang = txtNgayThang.getText();

        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngayThang);
            VeSo veSo = new VeSo(0, maVe, vungMien, soLuong, doanhThu, new Date(date.getTime()));
            model.addVeSo(veSo);
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ngày tháng không hợp lệ!");
        }
    }

    private void suaVeSo() {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một dòng để sửa!");
            return;
        }

        int selectedID = (int) tableModel.getValueAt(selectedRow, 0);
        String maVe = txtMaVe.getText();
        String vungMien = (String) cmbVungMien.getSelectedItem();
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        int doanhThu = Integer.parseInt(txtDoanhThu.getText());
        String ngayThang = txtNgayThang.getText();

        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngayThang);
            VeSo veSo = new VeSo(selectedID, maVe, vungMien, soLuong, doanhThu, new Date(date.getTime()));
            model.updateVeSo(veSo);
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ngày tháng không hợp lệ!");
        }
    }

    private void xoaVeSo() {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một dòng để xóa!");
            return;
        }

        int selectedID = (int) tableModel.getValueAt(selectedRow, 0);
        model.deleteVeSo(selectedID);
    }

    private void timVeSo() {
    	String maVeCanTim = txt_Tim.getText();

        
        VeSo veSoTimThay = model.timVeSoTheoMa(maVeCanTim);

        if (veSoTimThay != null) {
            // Hiển thị thông tin vé số tìm thấy
            txtMaVe.setText(veSoTimThay.getMaVe());
            cmbVungMien.setSelectedItem(veSoTimThay.getVungMien());
            txtSoLuong.setText(String.valueOf(veSoTimThay.getSoLuong()));
            txtDoanhThu.setText(String.valueOf(veSoTimThay.getDoanhThu()));
            txtNgayThang.setText(new SimpleDateFormat("yyyy-MM-dd").format(veSoTimThay.getNgayThang()));
        } else {
            // Thông báo không tìm thấy vé số
            JOptionPane.showMessageDialog(this, "Không tìm thấy vé số có mã " + maVeCanTim);
        }
    }


        
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VeSoView(new VeSoModel());
            }
        });
    }
}
