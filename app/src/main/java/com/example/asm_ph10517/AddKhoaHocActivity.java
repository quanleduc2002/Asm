package com.example.asm_ph10517;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asm_ph10517.DAO.KhoaHocDao;
import com.example.asm_ph10517.Model.KhoaHoc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddKhoaHocActivity extends AppCompatActivity {
EditText edMaKhoaHoc,edTenKhoaHoc,edGiangVien,edMoTa,edNgayNhapHoc,edNgayKetThuc;
Button btnNgayNhapHoc,btnNgayKetThuc,btnThem,btnUpdate;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
KhoaHocDao khoaHocDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_khoa_hoc);
        edMaKhoaHoc=findViewById(R.id.edtMaKhoaHoc);
        edTenKhoaHoc=findViewById(R.id.edtTenKhoaHoc);
        edGiangVien=findViewById(R.id.edtGiangVien);
        edMoTa=findViewById(R.id.edtMoTa);
        edNgayNhapHoc=findViewById(R.id.edNgayNhapHoc);
        edNgayKetThuc=findViewById(R.id.edtNgayKetThuc);
        btnNgayNhapHoc=findViewById(R.id.btnNgayNhapHoc);
        btnNgayKetThuc=findViewById(R.id.btnNgayKetThuc);
        btnThem=findViewById(R.id.btnThem);
        btnUpdate=findViewById(R.id.btnUpdate);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b != null){
            edMaKhoaHoc.setText(b.getString("makhoahoc"));
            edTenKhoaHoc.setText(b.getString("tenkhoahoc"));
            edGiangVien.setText(b.getString("giangvien"));
            edMoTa.setText(b.getString("mota"));
            edNgayNhapHoc.setText(b.getString("ngaynhaphoc"));
            edNgayKetThuc.setText(b.getString("ngaykethuc"));

        }
    }
    public void Add(View view) throws ParseException {
        if(validate()==-1){
            Toast.makeText(getApplicationContext(),"Bạn cần nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }
        else {
            khoaHocDao=new KhoaHocDao(AddKhoaHocActivity.this);
        KhoaHoc khoaHoc=new KhoaHoc(edMaKhoaHoc.getText().toString(),edTenKhoaHoc.getText().toString(),edGiangVien.getText().toString(),edMoTa.getText().toString(),simpleDateFormat.parse(edNgayNhapHoc.getText().toString()),simpleDateFormat.parse(edNgayKetThuc.getText().toString()));
         if(khoaHocDao.InsertKhoaHoc(khoaHoc)==1){
            Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
         }
         else {
            Toast.makeText(getApplicationContext(),"Thêm không thành công",Toast.LENGTH_SHORT).show();
        }
        }

//        if(khoaHocDao.InsertKhoaHoc(khoaHoc)>0){
//            Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT);
//        }
//        else {
//            Toast.makeText(getApplicationContext(),"Thêm không thành công",Toast.LENGTH_SHORT);
//        }
    }
    public void upDate(View view) throws ParseException {
        if(validate()==-1){
            Toast.makeText(getApplicationContext(),"Bạn cần nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }
        else {
            khoaHocDao=new KhoaHocDao(AddKhoaHocActivity.this);
            KhoaHoc khoaHoc=new KhoaHoc(edMaKhoaHoc.getText().toString(),edTenKhoaHoc.getText().toString(),edGiangVien.getText().toString(),edMoTa.getText().toString(),simpleDateFormat.parse(edNgayNhapHoc.getText().toString()),simpleDateFormat.parse(edNgayKetThuc.getText().toString()));
            if(khoaHocDao.updateKhoaHoc(khoaHoc)>0){
                Toast.makeText(getApplicationContext(),"Update Thành Công",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Update Không Thành Công",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public int validate(){
        int check;
        if(edMaKhoaHoc.getText().toString().length()==0 || edTenKhoaHoc.getText().toString().length()==0 || edGiangVien.getText().toString().length()==0 ||edMoTa.getText().toString().length()==0 || edNgayNhapHoc.getText().toString().length()==0 || edNgayKetThuc.getText().toString().length()==0){
            check=-1;
        }
        else {
            check=1;
        }
        if(check==-1){
            return -1;
        }else {
            return 1;
        }
    }
    public void NgayNhapHoc(View view){
        final Calendar calendar=Calendar.getInstance();
        final int year= calendar.get(calendar.YEAR);
        final int month= calendar.get(calendar.MONTH);
        final int day= calendar.get(calendar.DAY_OF_MONTH);
DatePickerDialog datePickerDialog=new DatePickerDialog(AddKhoaHocActivity.this, new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar=new GregorianCalendar(year,month,dayOfMonth);
        setDate(calendar);
    }
},year,month,day);
datePickerDialog.show();
}
    public void setDate(final Calendar calendar){
        edNgayNhapHoc.setText(simpleDateFormat.format(calendar.getTime()));
    }

    //Ngay Ket Thuc
    public void NgayKetThuc(View view){
        final Calendar calendar=Calendar.getInstance();
        final int yearkt= calendar.get(calendar.YEAR);
        final int monthkt= calendar.get(calendar.MONTH);
        final int daykt= calendar.get(calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(AddKhoaHocActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar=new GregorianCalendar(year,month,dayOfMonth);
                setDate1(calendar);
            }
        },yearkt,monthkt,daykt);
        datePickerDialog.show();
    }
    public void setDate1(final Calendar calendar){
        edNgayKetThuc.setText(simpleDateFormat.format(calendar.getTime()));
    }
}

