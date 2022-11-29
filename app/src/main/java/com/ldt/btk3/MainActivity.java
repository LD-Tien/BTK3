package com.ldt.btk3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NgonNguAdapter.OnSinhVienListener {

    private FloatingActionButton btnAddSV;
    private List<NgonNgu> ngonNguList;
    private RecyclerView recyclerView;
    private NgonNguAdapter ngonNguAdapter;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();

        mData = FirebaseDatabase.getInstance().getReference();
        ngonNguList = new ArrayList<>();
//        mData = FirebaseDatabase.getInstance().getReference();

        ngonNguList = getDS_NgonNgu();
        ngonNguAdapter = new NgonNguAdapter(this, this);
        ngonNguAdapter.setData(ngonNguList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ngonNguAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }



//    private List<NgonNgu> createData() {
//        List<NgonNgu> ngonNguList = new ArrayList<>();
//        ngonNguList.add(new NgonNgu("Việt", "Đây là ví dụ tiếng việt"));
//        ngonNguList.add(new NgonNgu("Anh", ""));
//        ngonNguList.add(new NgonNgu("Pháp", ""));
//        ngonNguList.add(new NgonNgu("Nga", ""));
//        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
//
//        for(NgonNgu sv : ngonNguList) {
//            mData.child("DS_NgonNgu").push().setValue(sv);
//        }
//        return ngonNguList;
//    }

    private List<NgonNgu> getDS_NgonNgu() {
        List<NgonNgu> dssv = new ArrayList<>();
        mData.child("DS_NgonNgu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ngonNguList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    dssv.add(dataSnapshot.getValue(NgonNgu.class));
                }
                ngonNguAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return dssv;
    }

    private void anhXa() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void onSinhVienClick(int position) {
        Intent intent = new Intent(MainActivity.this, XemChiTietActivity.class);
        intent.putExtra("NgonNgu", ngonNguList.get(position));
        startActivity(intent);
    }
}