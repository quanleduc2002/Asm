package com.example.asm_ph10517.Model;

import java.io.Serializable;
import java.util.Date;

public class KhoaHoc  {
    private String maKhoaHoc;
    private String tenKhoaHoc;
    private String GiangVien;
    private String mota;
    private Date ngayNhapHoc;
    private Date ngayKetThuc;

    public KhoaHoc(String maKhoaHoc, String tenKhoaHoc, String giangVien, String mota, Date ngayNhapHoc, Date ngayKetThuc) {
        this.maKhoaHoc = maKhoaHoc;
        this.tenKhoaHoc = tenKhoaHoc;
        GiangVien = giangVien;
        this.mota = mota;
        this.ngayNhapHoc = ngayNhapHoc;
        this.ngayKetThuc = ngayKetThuc;
    }

    public KhoaHoc() {
    }

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getGiangVien() {
        return GiangVien;
    }

    public void setGiangVien(String giangVien) {
        GiangVien = giangVien;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Date getNgayNhapHoc() {
        return ngayNhapHoc;
    }

    public void setNgayNhapHoc(Date ngayNhapHoc) {
        this.ngayNhapHoc = ngayNhapHoc;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}

