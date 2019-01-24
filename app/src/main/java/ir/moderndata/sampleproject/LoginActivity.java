package ir.moderndata.sampleproject;

import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.aflak.libraries.callback.FingerprintDialogCallback;
import me.aflak.libraries.dialog.FingerprintDialog;

public class LoginActivity extends AppCompatActivity {

    Keyboard keyboard;
    KeyboardView keyboardView;
    TextView text_input;
    Button login_finger;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        text_input = findViewById(R.id.text_input);
        login_finger = findViewById(R.id.login_finger);
        intent   = new Intent(LoginActivity.this,MainActivity.class);
        login_finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FingerprintDialog.initialize(LoginActivity.this)
                    .title("اعتبار سنجی با اثر انگشت")
                    .message("برای اعتبار سنجی شما از اثر انگشت استفاده خواهیم کرد.")
                    .callback(new FingerprintDialogCallback() {
                        @Override
                        public void onAuthenticationSucceeded() {
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }

                    @Override
                    public void onAuthenticationCancel() {
                        Toast.makeText(LoginActivity.this, "اعتبارسنجی ناموفق", Toast.LENGTH_SHORT).show();

                             }
                        })
                .show();
            }
        });




    }
public void ButtonClick(View v){
    Button btn = (Button) v;
    String textViewText = text_input.getText().toString();


        if(btn.getText().equals("حذف")){
            int length = text_input.getText().length();
            if (length > 0) {
                text_input.setText(textViewText.substring(0,text_input.getText().length() - 1));
            }

        }else {
            text_input.append(btn.getText());
            if(text_input.getText().equals("123456")){
                startActivity(intent);
            }
        }


}

}
