package com.example.rajat_pc.barcodescanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button scanBtn;
    private TextView contentTxt,formatTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = (Button)findViewById(R.id.scan_btn);
        contentTxt = (TextView)findViewById(R.id.scan_content_tv);
        formatTxt = (TextView)findViewById(R.id.scan_format_tv);
        scanBtn.setOnClickListener(this);
    }

    public void onClick(View view){
        if(view.getId() == R.id.scan_btn){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scanningResult != null){
            String content_string = scanningResult.getContents();
            String format_string = scanningResult.getFormatName();
            if(content_string != null&&format_string != null)
            {
                contentTxt.setText("Content : "+content_string);
                formatTxt.setText("Format : "+format_string);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No scan data received",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No scan data received",Toast.LENGTH_SHORT).show();
        }
    }
}
