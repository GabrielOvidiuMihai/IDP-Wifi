package com.example.gabriel.salut;

import java.util.List;

import android.app.ProgressDialog;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    WifiManager mainWifiObj;
    WifiScanReceiver wifiReciever;
    ListView list;
    String wifis[];
    Button buttonOne;
    Toolbar myToolbar;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
       // list = (ListView)findViewById(R.id.listView1);
        mainWifiObj = (WifiManager) getApplicationContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiReciever = new WifiScanReceiver();
        pbar = (ProgressBar) findViewById(R.id.ctrlActivityIndicator);

        buttonOne = (Button) findViewById(R.id.button5);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbar.setVisibility(View.VISIBLE);
                list = (ListView)findViewById(R.id.listView1);
                mainWifiObj.startScan();
            }
        });

    }
    protected void onPause() {
        unregisterReceiver(wifiReciever);
        super.onPause();
    }


    protected void onResume() {
        registerReceiver(wifiReciever, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }
    class WifiScanReceiver extends BroadcastReceiver {
        @SuppressLint("UseValueOf")
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
            wifis = new String[wifiScanList.size()];
            for(int i = 0; i < wifiScanList.size(); i++){
                wifis[i] = (("SSID: " + (wifiScanList.get(i)).SSID + "\n" +
                        "BSSID: " + (wifiScanList.get(i)).BSSID + "\n" +
                        "Capabilities: " + (wifiScanList.get(i)).capabilities + "\n" +
                        "Frequency:" + (wifiScanList.get(i)).frequency) );
            }

            list.setAdapter(new ArrayAdapter<String>(
                    getApplicationContext(), R.layout.elemlist, R.id.list_content, wifis));

            pbar.setVisibility(View.INVISIBLE);
        }
    }
}

