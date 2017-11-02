package com.example.alexanderdrumond.validationemail;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.alexanderdrumond.validationemail.models.Email;
import com.example.alexanderdrumond.validationemail.network.GetEmails;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.root);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        editText = (EditText) findViewById(R.id.Et);
        textView = (TextView) findViewById(R.id.tv);
        ImageButton button = (ImageButton) findViewById(R.id.imgbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundValidation().execute(editText.getText().toString());
            }
        });
    }

    private class BackgroundValidation extends GetEmails{


        @Override
        protected void onPostExecute(Email email) {
            if (email.getIsValid() == "true"){
                textView.setText("email valido");
            }else{
                textView.setText("email no valido");}
        }
    }
}
