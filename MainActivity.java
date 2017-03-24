package com.example.emeri.smsbomber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.InputFilter;


public class MainActivity extends AppCompatActivity {

    Button buttonSend;
    EditText textPhoneNo;
    EditText textSMS;
    EditText textQtte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connecter les editbox
        textPhoneNo = (EditText) findViewById(R.id.editTextNum);
        textSMS = (EditText) findViewById(R.id.editTextMsg);
        textQtte =(EditText) findViewById(R.id.editTextQtte);
        textQtte.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "10")});

        //Connect The Save button for Saving, and then redirecting to the activity Hall Of Fame activity
        final Button myButtonSend = (Button) findViewById(R.id.buttonEnvoi);
        myButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNo = textPhoneNo.getText().toString();
                String sms = textSMS.getText().toString();
                int Qtte = Integer.parseInt(textQtte.getText().toString());
                if (textQtte.toString().trim().length() < 1){
                    Qtte = 1;
                    Toast.makeText(getApplicationContext(), "Pas de quantité donnée, 1 SMS envoyé",
                            Toast.LENGTH_LONG).show();
                }


                try {
                    for (int i = 1; i <= Qtte; i++) {
                        Log.d("test", "try");
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo, null, sms + "// SMS " + i +" have Fun" , null, null);
                        Toast.makeText(getApplicationContext(), i+"/" + Qtte + "envoyés !",
                                Toast.LENGTH_LONG).show();
                        Thread.sleep(2*1000);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

        });

    }
}



