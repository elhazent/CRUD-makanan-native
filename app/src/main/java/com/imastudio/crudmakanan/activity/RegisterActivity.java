package com.imastudio.crudmakanan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.imastudio.crudmakanan.R;
import com.imastudio.crudmakanan.helper.MyFunction;
import com.imastudio.crudmakanan.model.ResponseRegister;
import com.imastudio.crudmakanan.network.MyRetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends MyFunction {

    @BindView(R.id.edtnama)
    EditText edtnama;
    @BindView(R.id.edtalamat)
    EditText edtalamat;
    @BindView(R.id.edtnotelp)
    EditText edtnotelp;
    @BindView(R.id.spinjenkel)
    Spinner spinjenkel;
    @BindView(R.id.edtusername)
    EditText edtusername;
    @BindView(R.id.edtpassword)
    TextInputEditText edtpassword;
    @BindView(R.id.edtpasswordconfirm)
    TextInputEditText edtpasswordconfirm;
    @BindView(R.id.regAdmin)
    RadioButton regAdmin;
    @BindView(R.id.regUserbiasa)
    RadioButton regUserbiasa;
    @BindView(R.id.btnregister)
    Button btnregister;
    @BindView(R.id.edtusia)
    EditText edtusia;
    String[] jenkel = {"Laki - laki", "perempuan"};
    private String strJenkel;
    private String strLevel;
    private String strnama;
    private String stralamat;
    private String strnohp;
    private String strusername;
    private String strpassword;
    private String strconpassword;
    private String strusia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setJenisKelamin();
        if (regAdmin.isChecked()) {
            strLevel = "admin";
        } else {
            strLevel = "user biasa";
        }
    }

    private void setJenisKelamin() {

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, jenkel);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinjenkel.setAdapter(adapter);
        //aksi dipilih
        spinjenkel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strJenkel = jenkel[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.regAdmin, R.id.regUserbiasa, R.id.btnregister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regAdmin:
                strLevel = "admin";
                break;
            case R.id.regUserbiasa:
                strLevel = "user biasa";
                break;
            case R.id.btnregister:
                strnama = edtnama.getText().toString();
                stralamat = edtalamat.getText().toString();
                strnohp = edtnotelp.getText().toString();
                strusername = edtusername.getText().toString();
                strpassword = edtpassword.getText().toString();
                strconpassword = edtpasswordconfirm.getText().toString();
                strusia = edtusia.getText().toString();
                if (TextUtils.isEmpty(strnama)) {
                    edtnama.setError("nama tidak boleh kosong");
                    edtnama.requestFocus();
                    myanimation(edtnama);
                } else if (TextUtils.isEmpty(stralamat)) {
                    edtalamat.requestFocus();
                    edtalamat.setError("alamat tidak boleh kosong");
                    myanimation(edtalamat);
                } else if (TextUtils.isEmpty(strnohp)) {
                    edtnotelp.requestFocus();
                    myanimation(edtnotelp);
                    edtnotelp.setError("no hp tidak boleh kosong");
                }else if (TextUtils.isEmpty(strusia)) {
                    edtusia.requestFocus();
                    myanimation(edtusia);
                    edtnotelp.setError("usia tidak boleh kosong");
                }else if (TextUtils.isEmpty(strusername)) {
                    edtusername.requestFocus();
                    myanimation(edtusername);
                    edtusername.setError("username tidak boleh kosong");
                } else if (TextUtils.isEmpty(strpassword)) {
                    edtpassword.requestFocus();
                    myanimation(edtpassword);
                    edtpassword.setError("password tidak boleh kosong");
                } else if (strpassword.length() < 6) {
                    myanimation(edtpassword);
                    edtpassword.setError("password minimal 6 karakter");
                } else if (TextUtils.isEmpty(strconpassword)) {
                    edtpasswordconfirm.requestFocus();
                    myanimation(edtpasswordconfirm);
                    edtpasswordconfirm.setError("password confirm tidak boleh kosong");
                } else if (!strpassword.equals(strconpassword)) {
                    edtpasswordconfirm.requestFocus();
                    myanimation(edtpasswordconfirm);
                    edtpasswordconfirm.setError("password tidak sama");
                } else {
                    registeruser();
                }
                break;
        }
    }

    private void registeruser() {
        MyRetrofitClient.getInstaceRetrofit().registerUser(
                strnama,
                stralamat,
                strnohp,
                strJenkel,
                strusername,
                strpassword,
                strLevel,
                strusia
        ).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.isSuccessful()) {
                    String result = response.body().getResult();
                    String msg = response.body().getMsg();
                    if (result.equals("1")) {
                        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "masalah jaringan" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
