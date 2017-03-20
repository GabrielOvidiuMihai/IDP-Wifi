package com.example.gabriel.salut;

//package com.am.wifi.amitwifi;

    import java.util.List;

import android.net.wifi.ScanResult;
        import android.net.wifi.WifiManager;
        import android.os.Bundle;
        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.view.Menu;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

public class MainActivity extends Activity {
    WifiManager mainWifiObj;
    WifiScanReceiver wifiReciever;
    ListView list;
    String wifis[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.listView1);
        mainWifiObj = (WifiManager) getApplicationContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiReciever = new WifiScanReceiver();
        mainWifiObj.startScan();

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
                wifis[i] = ((wifiScanList.get(i)).toString());
            }

           // list.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
            //        android.R.layout.simple_list_item_1,wifis));

            list.setAdapter(new ArrayAdapter<String>(
                    getApplicationContext(), R.layout.elemlist, R.id.list_content, wifis));
        }
    }
}
//
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.net.wifi.ScanResult;
//import android.net.wifi.WifiManager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import static android.R.attr.button;
//import static com.example.gabriel.salut.R.id.buttonScan;
//import static com.example.gabriel.salut.R.id.textStatus;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        final TextView textStatus = (TextView) findViewById(R.id.textStatus);
//        ListView lv = (ListView) (ListView)findViewById(R.id.list);
//        Button buttonScan = (Button) findViewById(R.id.buttonScan);
//        buttonScan.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                WifiManager wifi;
//                wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//                if ( !wifi.isWifiEnabled() ) {
//                    Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
//                    wifi.setWifiEnabled(true);
//                }
//
//                List<ScanResult> results = wifi.getScanResults();
//                int size = results.size();
//                Log.d("Salut", "gabriel", results);
//            }
//    });
//
//}
//}
//public class MainActivity extends Activity implements View.OnClickListener
//{
//    WifiManager wifi;
//    ListView lv;
//    TextView textStatus;
//    Button buttonScan;
//    int size = 0;
//    List<ScanResult> results;
//
//    String ITEM_KEY = "key";
//    ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
//    SimpleAdapter adapter;
//
//    /* Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        textStatus = (TextView) findViewById(R.id.textStatus);
//        buttonScan = (Button) findViewById(R.id.buttonScan);
//        buttonScan.setOnClickListener(this);
//        lv = (ListView)findViewById(R.id.list);
//
//        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        if (wifi.isWifiEnabled() == false) {
//            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
//            wifi.setWifiEnabled(true);
//        }
//
////        this.adapter = new SimpleAdapter(MainActivity.this, arraylist, R.layout.row, new String[] { ITEM_KEY }, new int[] { R.id.list_value });
////        lv.setAdapter(this.adapter);
//
//        registerReceiver(new BroadcastReceiver()
//        {
//            @Override
//            public void onReceive(Context c, Intent intent)
//            {
//                results = wifi.getScanResults();
//                size = results.size();
//            }
//        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
//    }
//
//    public void onClick(View view)
//    {
//        arraylist.clear();
//        wifi.startScan();
//
//        Toast.makeText(this, "Scanning...." + size, Toast.LENGTH_SHORT).show();
//        try
//        {
//            size = size - 1;
//            while (size >= 0)
//            {
//                HashMap<String, String> item = new HashMap<String, String>();
//                item.put(ITEM_KEY, results.get(size).SSID + "  " + results.get(size).capabilities);
//
//                arraylist.add(item);
//                size--;
//                //adapter.notifyDataSetChanged();
//            }
//        }
//        catch (Exception e)
//        { }
//    }
//

