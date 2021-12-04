package com.example.asm_ph10517.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.asm_ph10517.AddKhoaHocActivity;
import com.example.asm_ph10517.DAO.KhoaHocDao;
import com.example.asm_ph10517.KhoaHocActivity;
import com.example.asm_ph10517.Model.KhoaHoc;
import com.example.asm_ph10517.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class KhoaHocAdapter extends BaseAdapter {
    private List<KhoaHoc> khoaHocList;
    private Activity context;
    private LayoutInflater inflater;
    KhoaHocDao khoaHocDao;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public KhoaHocAdapter(List<KhoaHoc> khoaHocList, Activity context) {
        this.khoaHocList = khoaHocList;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.khoaHocDao = new KhoaHocDao(context);
    }

    @Override
    public int getCount() {
        return khoaHocList.size();
    }

    @Override
    public Object getItem(int position) {
        return khoaHocList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
public class viewHolder{
        TextView tvTenKhoaHoc;
        TextView tvGiangVien;
        TextView tvMaKhoaHoc;
        Button btnXoa;
        Button btnSua;
}
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_khoa_hoc,null);
            viewHolder=new viewHolder();
            viewHolder.tvTenKhoaHoc=convertView.findViewById(R.id.tvTenKhoaHoc);
            viewHolder.tvGiangVien=convertView.findViewById(R.id.tvGiangVien);
            viewHolder.tvMaKhoaHoc=convertView.findViewById(R.id.tvMaKhoaHoc);
            viewHolder.btnSua=convertView.findViewById(R.id.btnSua);
            viewHolder.btnXoa=convertView.findViewById(R.id.btnXoa);
            viewHolder.btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    khoaHocDao.DeleteKhoaHocByID(khoaHocList.get(position).getMaKhoaHoc());
                    khoaHocList.remove(position);
                  notifyDataSetChanged();
                }
            });
viewHolder.btnSua.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,AddKhoaHocActivity.class);
        Bundle bundle = new Bundle();
                bundle.putString("makhoahoc", khoaHocList.get(position).getMaKhoaHoc());
                bundle.putString("tenkhoahoc", khoaHocList.get(position).getTenKhoaHoc());
                bundle.putString("giangvien", khoaHocList.get(position).getGiangVien());
                bundle.putString("mota", khoaHocList.get(position).getMota());
                bundle.putString("ngaynhaphoc", simpleDateFormat.format(khoaHocList.get(position).getNgayNhapHoc()));
                bundle.putString("ngaykethuc", simpleDateFormat.format(khoaHocList.get(position).getNgayKetThuc()));
                intent.putExtras(bundle);
        context.startActivity(intent);
    }
});
convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (viewHolder) convertView.getTag();
        }
        KhoaHoc khoaHoc=khoaHocList.get(position);
        viewHolder.tvTenKhoaHoc.setText(khoaHoc.getTenKhoaHoc());
        viewHolder.tvGiangVien.setText(khoaHoc.getGiangVien());
        viewHolder.tvMaKhoaHoc.setText(khoaHoc.getMaKhoaHoc());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataSet(List<KhoaHoc> khoaHocList1){
        this.khoaHocList=khoaHocList1;
        notifyDataSetChanged();
    }

}
