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
                    Intent iDangnhap = new Intent(SlashScreenActivity.this, DangNhapActivity.class);
                    startActivity(iDangnhap);
                }
            }, 2000);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }


}

//import android.Manifest;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.Handler;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//
//import com.example.da2orderfood.R;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationServices;
//
//
//public class SlashScreenActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
//
//    TextView txtPhienban;
//    GoogleApiClient googleApiClient;
//    public static final int REQUEST_PERMISSION_LOCATION = 1;
//    SharedPreferences sharedPreferences;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_flashscreen);
//
//        txtPhienban = (TextView) findViewById(R.id.txtPhienban);
//
//        sharedPreferences = getSharedPreferences("toado", MODE_PRIVATE);
//
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//
//        int checkPermissionCoarseLocaltion = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
//        int checkPermissionFineLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
//        if (checkPermissionCoarseLocaltion != PackageManager.PERMISSION_GRANTED && checkPermissionCoarseLocaltion != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
//        } else {
//            googleApiClient.connect();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case REQUEST_PERMISSION_LOCATION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    googleApiClient.connect();
//                }
//                break;
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        googleApiClient.disconnect();
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location vitriHienTai = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//        if(vitriHienTai != null){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("latitude", String.valueOf(vitriHienTai.getLatitude()));
//            editor.putString("longitude", String.valueOf(vitriHienTai.getLongitude()));
//            editor.commit();
//        }
//
//        try {
//            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
//            txtPhienban.setText(getString(R.string.phienban) + " " + packageInfo.versionName);
//
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent iDangNhap = new Intent(SlashScreenActivity.this,DangNhapActivity.class);
//                    startActivity(iDangNhap);
//                    finish();
//                }
//            },2000);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//}
