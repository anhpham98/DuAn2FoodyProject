package com.example.da2orderfood.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;


import com.example.da2orderfood.Adapters.AdapterDanhSachWifi;
import com.example.da2orderfood.Controller.Interfaces.ChiTietQuanAnInterface;
import com.example.da2orderfood.Model.WifiQuanAnModel;
import com.example.da2orderfood.R;

import java.util.ArrayList;
import java.util.List;


public class CapNhatWifiController {
    WifiQuanAnModel wifiQuanAnModel;
    Context context;
    List<WifiQuanAnModel> wifiQuanAnModelList;

    public CapNhatWifiController(Context context){
        wifiQuanAnModel = new WifiQuanAnModel();
        this.context = context;
    }

    public void HienThiDanhSachWifi(String maquanan, final RecyclerView recyclerView){

        wifiQuanAnModelList = new ArrayList<>();
        ChiTietQuanAnInterface chiTietQuanAnInterface = new ChiTietQuanAnInterface() {
            @Override
            public void HienThiDanhSachWifi(WifiQuanAnModel wifiQuanAnModel) {

                wifiQuanAnModelList.add(wifiQuanAnModel);
                AdapterDanhSachWifi adapterDanhSachWifi = new AdapterDanhSachWifi(context, R.layout.layout_wifi_chitietquanan,wifiQuanAnModelList);
                recyclerView.setAdapter(adapterDanhSachWifi);
                adapterDanhSachWifi.notifyDataSetChanged();
            }
        };

        wifiQuanAnModel.LayDanhSachWifiQuanAn(maquanan,chiTietQuanAnInterface);
    }

    public void ThemWifi(Context context, WifiQuanAnModel wifiQuanAnModel, String maquanan){
        wifiQuanAnModel.ThemWifiQuanAn(context,wifiQuanAnModel,maquanan);
    }
}
