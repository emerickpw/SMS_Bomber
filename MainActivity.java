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

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

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
        textQtte = (EditText) findViewById(R.id.editTextQtte);

        //definir le filtre des quantités
        textQtte.setFilters(new InputFilter[]{new InputFilterMinMax("1", "100")}); //Definit la limite

        //Connecter le bouton spam, et recuperer les data
        final Button myButtonSend = (Button) findViewById(R.id.buttonEnvoi);
        myButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNo = textPhoneNo.getText().toString();
                String sms = textSMS.getText().toString();
                if (textQtte.getText().toString().trim().isEmpty()){
                    textQtte.setText("1");
                }
                int Qtte = Integer.parseInt(textQtte.getText().toString());


                try {

                    for (int i = 1; i <= Qtte; i++) {
                        Log.d("test", "try");
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo, null, sms + "// SMS " + i + " have Fun", null, null);
                        Toast.makeText(getApplicationContext(), i + " /" + Qtte + "envoyés !",
                                Toast.LENGTH_LONG).show();
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Verifier les autorisations SMS dans les paramètres de l'application",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

        });

    }
}



