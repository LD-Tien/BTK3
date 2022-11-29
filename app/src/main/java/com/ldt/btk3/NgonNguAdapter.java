package com.ldt.btk3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NgonNguAdapter extends RecyclerView.Adapter<NgonNguAdapter.SinhVienViewHolder>{
    private Context context;
    private List<NgonNgu> sinhVienList;
    private OnSinhVienListener onMovieListener;

    public NgonNguAdapter(FragmentActivity context, OnSinhVienListener onMovieListener) {
        this.context = context;
        this.onMovieListener = onMovieListener;
    }

    public void setData(List<NgonNgu> sinhVienList) {
        this.sinhVienList = sinhVienList;
        notifyDataSetChanged(); // bin/ load dữ liệu vào MovieAdapter
    }

    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngonngu,parent,false);
        return new SinhVienViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
        NgonNgu sv = sinhVienList.get(position);
        if(sv == null) {
            return;
        }
        holder.tvMSV.setText("Ngôn ngữ: " + sv.getTenNN());
        holder.tvHoTen.setText("VD: " +sv.getVidu());
    }

    @Override
    public int getItemCount() {
        if(sinhVienList != null)
            return sinhVienList.size();
        return 0;
    }

    public class SinhVienViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvMSV, tvHoTen;
        OnSinhVienListener onSinhVienListener;
        public SinhVienViewHolder(@NonNull View itemView, OnSinhVienListener onMovieListener) {
            super(itemView);
            this.onSinhVienListener = onMovieListener;
            tvMSV = itemView.findViewById(R.id.tvMSV);
            tvHoTen = itemView.findViewById(R.id.tvHoTen);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSinhVienListener.onSinhVienClick(getAdapterPosition());
        }
    }

    public interface OnSinhVienListener {
        void onSinhVienClick(int position);
    }

}
