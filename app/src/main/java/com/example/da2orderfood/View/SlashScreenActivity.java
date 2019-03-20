package com.example.da2orderfood.View;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.da2orderfood.R;

//DucAnh 3/16/2019
public class SlashScreenActivity extends AppCompatActivity {
    TextView txtCpyName, txtLoading, txtVesionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_flashscreen);
        txtCpyName = (TextView) findViewById(R.id.txtTenCongTy);
        txtLoading = (TextView) findViewById(R.id.txtTai);
        txtVesionName = (TextView) findViewById(R.id.txtPhienban);
        try {
            // Splash screen lay textname version,tencongty,loading

            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            txtVesionName.setText(getString(R.string.phienban) + " " + packageInfo.versionName);

//             Chuyen man hinh settup time Splash sceen layout
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent iDangnhap = new Intent(SlashScreenActivity.this, DangnhapActivity.class);
                    startActivity(iDangnhap);
                }
            }, 2000);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }


}
