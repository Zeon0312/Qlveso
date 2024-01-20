package Model;

import java.sql.Date;

public class VeSo {
    private int id;
    private String maVe;
    private String vungMien;
    private int soLuong;
    private int doanhThu;
    private Date ngayThang;

    public VeSo(int id, String maVe, String vungMien, int soLuong, int doanhThu, Date ngayThang) {
        this.id = id;
        this.maVe = maVe;
        this.vungMien = vungMien;
        this.soLuong = soLuong;
        this.doanhThu = doanhThu;
        this.ngayThang = ngayThang;
    }

    public int getId() {
        return id;
    }

    public String getMaVe() {
        return maVe;
    }

    public String getVungMien() {
        return vungMien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getDoanhThu() {
        return doanhThu;
    }

    public Date getNgayThang() {
        return ngayThang;
    }
}

