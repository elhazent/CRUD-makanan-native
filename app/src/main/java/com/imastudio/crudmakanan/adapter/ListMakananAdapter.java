package com.imastudio.crudmakanan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imastudio.crudmakanan.R;
import com.imastudio.crudmakanan.helper.MyConstant;
import com.imastudio.crudmakanan.model.DataMakananItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListMakananAdapter extends RecyclerView.Adapter<ListMakananAdapter.MYViewHolder> {

    Context context;
    List<DataMakananItem> listdatamakanan;

    private aksiklikitem clicked;

    public interface aksiklikitem{
        void onItemClick(int position);
    }
    public void setOnClick(aksiklikitem onClick){
        clicked =onClick;
    }


    public ListMakananAdapter(Context c, List<DataMakananItem> listdatamakanan) {
        context =c;
        this.listdatamakanan =listdatamakanan;
    }

    //set tampilan layout/UI yang akan ditampilkan
    @Override
    public MYViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.tampilanlistmakanan,parent,false);
        MYViewHolder holder = new MYViewHolder(v);

        return holder;
    }

    //set data ke masing" view
    @Override
    public void onBindViewHolder(MYViewHolder holder, final int position) {
        holder.txtnamamakanan.setText(listdatamakanan.get(position).getMakanan());
        Picasso.with(context).load(MyConstant.IMAGE_URL+listdatamakanan.get(position).getFotoMakanan()).
                error(R.drawable.noimage).placeholder(R.drawable.noimage).into(holder.imgmakanan);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.onItemClick(position);
            }
        });
    }

    //menghitung jumlah array dari database yang akan ditampilkan
    @Override
    public int getItemCount() {
        return listdatamakanan.size();
    }

    //pendeklarasikan dan inisialisasi
    public class MYViewHolder extends RecyclerView.ViewHolder {
        ImageView imgmakanan;
        TextView txtnamamakanan;
        public MYViewHolder(View itemView) {
            super(itemView);
            imgmakanan =(ImageView)itemView.findViewById(R.id.imgmakanan);
            txtnamamakanan =(TextView) itemView.findViewById(R.id.txtmakanan);

        }
    }
}
