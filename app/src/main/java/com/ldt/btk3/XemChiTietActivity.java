package com.ldt.btk3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class XemChiTietActivity extends AppCompatActivity {

    private TextView tvNgonNgu;
    private EditText edtVD;
    private Button btnLuu, btnHuy;
    private DatabaseReference myData = FirebaseDatabase.getInstance().getReference();
    NgonNgu nn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_chi_tiet);

        anhXa();
        nn = new NgonNgu();
        nn = (NgonNgu) getIntent().getSerializableExtra("NgonNgu");

        tvNgonNgu.setText(nn.getTenNN());
        edtVD.setText(nn.getVidu());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myData.child("DS_NgonNgu").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        NgonNgu m = snapshot.getValue(NgonNgu.class);
                        if(nn.getTenNN().equals(m.getTenNN())) {
                            nn.setVidu(edtVD.getText().toString());
                            myData.child("DS_NgonNgu").child(snapshot.getKey()).setValue(nn);
                            Toast.makeText(XemChiTietActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    private void anhXa() {
        tvNgonNgu = findViewById(R.id.tvNgonNgu);
        edtVD = findViewById(R.id.edtVD);
        btnLuu = findViewById(R.id.btnAdd);
        btnHuy = findViewById(R.id.btnCancel);
    }
}