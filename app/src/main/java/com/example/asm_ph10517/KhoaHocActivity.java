package com.example.asm_ph10517;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asm_ph10517.Adapter.KhoaHocAdapter;
import com.example.asm_ph10517.DAO.KhoaHocDao;
import com.example.asm_ph10517.Model.KhoaHoc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class KhoaHocActivity extends AppCompatActivity {
    KhoaHocAdapter khoaHocAdapter;
    ListView listView;
    List<KhoaHoc> khoaHocs;
    KhoaHocDao khoaHocDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoa_hoc);
        listView = findViewById(R.id.lvKhoaHoc);
        overridePendingTransition(R.anim.toleft, R.anim.toright);
        khoaHocDao = new KhoaHocDao(KhoaHocActivity.this);
        try {
            khoaHocs = khoaHocDao.getAllKhoaHoc();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        khoaHocAdapter = new KhoaHocAdapter(khoaHocs, KhoaHocActivity.this);
        listView.setAdapter(khoaHocAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(KhoaHocActivity.this, AddKhoaHocActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("makhoahoc", khoaHocs.get(position).getMaKhoaHoc());
//                bundle.putString("tenkhoahoc", khoaHocs.get(position).getTenKhoaHoc());
//                bundle.putString("giangvien", khoaHocs.get(position).getGiangVien());
//                bundle.putString("mota", khoaHocs.get(position).getMota());
//                bundle.putString("ngaynhaphoc", simpleDateFormat.format(khoaHocs.get(position).getNgayNhapHoc()));
//                bundle.putString("ngaykethuc", simpleDateFormat.format(khoaHocs.get(position).getNgayKetThuc()));
//                intent.putExtras(bundle);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_khoa_hoc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(KhoaHocActivity.this, AddKhoaHocActivity.class);
        switch (item.getItemId()) {
            case R.id.them:
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        khoaHocs.clear();
        try {
            khoaHocs = khoaHocDao.getAllKhoaHoc();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        khoaHocAdapter.changeDataSet(khoaHocs);
    }

}