package ir.moderndata.sampleproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button send_with_doc;
    String LOG_TAG = "SampleLog";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        send_with_doc = findViewById(R.id.send_with_doc);

        send_with_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SendDocumentActivity.class);
                startActivity(intent);
            }
        });

    }


    public void BtnClick(View v){
        Button btn = (Button) v;
        Log.wtf(LOG_TAG,btn.getTag().toString());
    }

}
