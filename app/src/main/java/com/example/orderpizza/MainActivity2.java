package com.example.orderpizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Spinner payMethod = (Spinner) findViewById(R.id.spinner);
        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.message);
    }
    public void placeOrder(View view){
        EditText nameEditText = (EditText) findViewById(R.id.name_edittext_view);
        String name = nameEditText.getText().toString();

        EditText addressline1 = (EditText) findViewById(R.id.address1);
        String address1 = addressline1.getText().toString();
        EditText addressline2 = (EditText) findViewById(R.id.address2);
        String address2 = addressline2.getText().toString();
        message += "\n\n Delivery Details: \n\n" + "Receiver's name:   " + name + "\n\n\n Receiver's Address: \n\n" + address1 + ", " + address2;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Pizza Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        try {
            startActivity(intent);
        }catch(ActivityNotFoundException e){
            Toast.makeText( this, "No App Found", Toast.LENGTH_SHORT).show();
        }
    }
}