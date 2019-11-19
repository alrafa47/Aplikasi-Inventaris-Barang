package com.alrafa47.mobilebarang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.alrafa47.mobilebarang.DeleteBarang;
import com.alrafa47.mobilebarang.R;
import com.alrafa47.mobilebarang.UpdateBarang;
import com.alrafa47.mobilebarang.model.ModelBarang;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.ViewHolder> {

    @NonNull
    private Context mContext;
    private List<ModelBarang> barangList;
    private LayoutInflater inflater;
    private Intent i;
    public AdapterBarang(@NonNull Context mContext, List<ModelBarang> barangList) {
        this.mContext = mContext;
        this.barangList = barangList;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_barang, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         final ModelBarang modelBarang = barangList.get(position);

        holder.tvNama.setText(modelBarang.getNama());
        holder.tvJumlah.setText(modelBarang.getJumlah()+" Pcs/Buah");
        holder.tvHarga.setText("Rp. "+modelBarang.getHarga()+",-");
        if(modelBarang.getStatus().equalsIgnoreCase("Layak")){
            holder.imgStatus.setImageResource(R.drawable.ic_add_circle_black_24dp);
        }else{
            holder.imgStatus.setImageResource(R.drawable.ic_do_not_disturb_on_black_24dp);
        }
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.menu_data);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete:
                                DeleteBarang deleteBarang = new DeleteBarang(mContext ,modelBarang.getIdBarang());

                                break;
                            case R.id.update:
                                i = new Intent(mContext, UpdateBarang.class);
                                i.putExtra("idBarang", modelBarang.getIdBarang());
                                i.putExtra("nama", modelBarang.getNama());
                                i.putExtra("jumlah", modelBarang.getJumlah());
                                i.putExtra("harga", modelBarang.getHarga());
                                i.putExtra("status", modelBarang.getStatus());
                                mContext.startActivity(i);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvHarga, tvJumlah;
        private ImageView imgStatus, action;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvJumlah = itemView.findViewById(R.id.tv_jumlah);
            imgStatus = itemView.findViewById(R.id.img_status);
            action = itemView.findViewById(R.id.img_action);
        }
    }
}
