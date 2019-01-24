package ir.moderndata.sampleproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class SendDocumentActivity extends AppCompatActivity {

    ImageView final_image;
    ImageView select_image;
    private static final int CAMERA_REQUEST = 44;
    private static final int CAMERA_PERMISSION_CODE = 10;
    Button send_btn;
    Button back_btn;
    EditText payer_edt;
    EditText from_edt;
    String LOG_TAG = "SampleLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_document);
        final_image = findViewById(R.id.final_image);
        select_image = findViewById(R.id.select_image);
        send_btn = findViewById(R.id.send_btn);
        back_btn = findViewById(R.id.back_btn);
        payer_edt = findViewById(R.id.payer_edt);
        from_edt = findViewById(R.id.from_edt);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(from_edt.getText().toString().trim().length() > 0 && payer_edt.getText().toString().trim().length() > 0){
                    BitmapDrawable drawable = (BitmapDrawable) final_image.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    ServerInterface serverInterface = new ServerInterface(SendDocumentActivity.this);
                    HashMap<String,String> params = new HashMap<>();
                    serverInterface.PostImage("/withDocument", params, bitmap, new ServerInterface.responseListeneer() {
                        @Override
                        public void OnResponse(String content) {
                            Log.wtf(LOG_TAG,content);
                        }
                    });
                }else{
                    Toast.makeText(SendDocumentActivity.this, "لطفا تمامی فیلد ها را پر کنید. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCamera();
            }
        });
        final_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCamera();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                OpenCamera();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            try{
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                final_image.setImageBitmap(bitmap);
            }catch (NullPointerException nux){
                Toast.makeText(this, "Null Exception", Toast.LENGTH_SHORT).show();
            }

        }
    }




    private void OpenCamera(){
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
        }else{
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);;
            startActivityForResult(cameraIntent,CAMERA_REQUEST);
        }
    }
}
