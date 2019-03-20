package com.example.da2orderfood.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.da2orderfood.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import java.security.AuthProvider;

public class DangnhapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener,
        FirebaseAuth.AuthStateListener {
    Button btnDangnhapGooogle;
    LoginButton btnDanhNhapFacebook;
    GoogleApiClient googleApiClient;
    public static int CODE_DANGNHAP_GOOGLE = 99;
    public static int KIEMTRA_PROVIDER_DANGNHAP = 0;
    FirebaseAuth firebaseAuth;
    CallbackManager mCallbackFacebook;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.layout_dangnhap);
        mCallbackFacebook = CallbackManager.Factory.create();

        firebaseAuth = FirebaseAuth.getInstance();
        LoginManager.getInstance().logOut();
        btnDangnhapGooogle = (Button) findViewById(R.id.btnDangNhapGoogle);
        btnDanhNhapFacebook=(LoginButton)findViewById(R.id.btnDangNhapFacebook);
        btnDanhNhapFacebook.setReadPermissions("email","public_profile");
        btnDanhNhapFacebook.registerCallback(mCallbackFacebook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                KIEMTRA_PROVIDER_DANGNHAP=2;
                String tokenID = loginResult.getAccessToken().getToken();
                ChungthucDangNhapFisebase(tokenID);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        btnDangnhapGooogle.setOnClickListener(this);
        Taoclientdangnhapgoogle();

    }

    //Khoi tao client cho dang nhap google
    private void Taoclientdangnhapgoogle() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder().requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

    }

    //end khoi tao client cho dang nhap google
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
}

    //Mo form dang nhap = google
    private void DangnhapGoogle(GoogleApiClient googleApiClient) {
        CODE_DANGNHAP_GOOGLE = 1;
        Intent idnGoogle = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(idnGoogle, CODE_DANGNHAP_GOOGLE);
    }
    //end mo form=google

    //Lay tokenId da dang nhap google de dang nhap tren firebase
    private void ChungthucDangNhapFisebase(String tokenID) {
        if (KIEMTRA_PROVIDER_DANGNHAP == 1) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID, null);
            firebaseAuth.signInWithCredential(authCredential);
        }else if(KIEMTRA_PROVIDER_DANGNHAP == 2) {
            AuthCredential authCredential= FacebookAuthProvider.getCredential(tokenID);
            firebaseAuth.signInWithCredential(authCredential);
        }

    }
    //end lay tokenId da dang nhap google de dang nhap tren firebase


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_DANGNHAP_GOOGLE) {
            if (resultCode == RESULT_OK) {
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account = signInResult.getSignInAccount();
                String tokenID = account.getIdToken();
                ChungthucDangNhapFisebase(tokenID);
            }
        }else {
            mCallbackFacebook.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //Lang nghe su kien click user click vao button dang nhap google,facebook va email account
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDangNhapGoogle:
                DangnhapGoogle(googleApiClient);
                break;
        }
    }
    //end
    //su kien kiem tra xem nguoi dung da dang nhap thanh cong hay dang xuat
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            Intent iTrangchu = new Intent(this, TrangChuActivity.class);
            startActivity(iTrangchu);
        } else {

        }
        //end
    }

}
