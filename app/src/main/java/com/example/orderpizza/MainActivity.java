package com.example.orderpizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    public static final String message="com.example.orderpizza";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox margheritaPizza_box = (CheckBox) findViewById(R.id.pizza_margherita);
        CheckBox quickTomatoPizza_box = (CheckBox) findViewById(R.id.pizza_tomato);
        CheckBox cheesePizza_box = (CheckBox) findViewById(R.id.pizza_cheese);
        CheckBox vegetablePizza_box = (CheckBox) findViewById(R.id.pizza_vegetable);
        margheritaPizza_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    selectQuantity("margherita");
                else
                    removeVisibility("margherita");
            }
        });
        quickTomatoPizza_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    selectQuantity("quickTomato");
                else
                    removeVisibility("quickTomato");
            }
        });
        cheesePizza_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    selectQuantity("cheese");
                else
                    removeVisibility("cheese");
            }
        });
        vegetablePizza_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    selectQuantity("vegetable");
                else
                    removeVisibility("vegetable");
            }
        });
    }
    public void selectQuantity(String pizza) {
        findViewById(R.id.proceed_button).setActivated(true);
        TextView quantitySelectHeading = (TextView) findViewById(R.id.quantity_heading);
        quantitySelectHeading.setText("Select quantity: ");
        quantitySelectHeading.setTextSize(20);
        quantitySelectHeading.setVisibility(View.VISIBLE);
        quantitySelectHeading.setPadding(40, 20, 20, 20);
        switch(pizza){
            case "margherita":selectMargheritaQuantity();
                break;
            case "quickTomato": selectQuickTomatoQuantity();
                break;
            case "cheese": selectCheeseQuantity();
                break;
            case "vegetable": selectVegetableQuantity();
                break;
        }
    }
    public void selectMargheritaQuantity(){
        TextView textView =(TextView) findViewById(R.id.quantity_margherita);
        textView.setText("Pizza Margherita");
        textView.setTextSize(17);
        textView.setPadding(80, 40, 60, 20);
        findViewById(R.id.margherita).setVisibility(View.VISIBLE);

        EditText edittext = (EditText) findViewById(R.id.quantity_margherita_edit_text);
        edittext.setText("1");
        edittext.setTextSize(18);
    }
    public void selectQuickTomatoQuantity(){
        TextView textView =(TextView) findViewById(R.id.quantity_quick_tomato);
        textView.setText("Pizza Quick Tomato");
        textView.setTextSize(17);
        textView.setPadding(80, 40, 60, 20);
        findViewById(R.id.tomato).setVisibility(View.VISIBLE);

        EditText edittext = (EditText) findViewById(R.id.quantity_quick_tomato_edit_text);
        edittext.setText("1");
        edittext.setTextSize(18);
    }
    public void selectCheeseQuantity(){
        TextView textView =(TextView) findViewById(R.id.quantity_four_cheese);
        textView.setText("Pizza Four Cheese");
        textView.setTextSize(17);
        textView.setPadding(80, 40, 60, 20);
        findViewById(R.id.cheese).setVisibility(View.VISIBLE);

        EditText edittext = (EditText) findViewById(R.id.quantity_four_cheese_edit_text);
        edittext.setText("1");
        edittext.setTextSize(18);
    }
    public void selectVegetableQuantity(){
        TextView textView =(TextView) findViewById(R.id.quantity_cheesy_vegetable);
        textView.setText("Pizza Cheesy Vegetable");
        textView.setTextSize(17);
        textView.setPadding(80, 40, 10, 20);
        findViewById(R.id.vegetable).setVisibility(View.VISIBLE);

        EditText edittext = (EditText) findViewById(R.id.quantity_cheesy_vegetable_edit_text);
        edittext.setText("1");
        edittext.setTextSize(18);
    }
    public void removeVisibility(String layout){
        switch(layout){
            case "margherita":findViewById(R.id.margherita).setVisibility(View.GONE);
                break;
            case "quickTomato": findViewById(R.id.tomato).setVisibility(View.GONE);
                break;
            case "cheese": findViewById(R.id.cheese).setVisibility(View.GONE);
                break;
            case "vegetable": findViewById(R.id.vegetable).setVisibility(View.GONE);
                break;
        }
        if(findViewById(R.id.margherita).getVisibility()==View.GONE && findViewById(R.id.tomato).getVisibility()==View.GONE && findViewById(R.id.cheese).getVisibility()==View.GONE && findViewById(R.id.vegetable).getVisibility()==View.GONE){
            findViewById(R.id.quantity_heading).setVisibility(View.GONE);
            findViewById(R.id.proceed_button).setActivated(false);
        }
    }
    public void proceed(View view){
        if(findViewById(R.id.proceed_button).isActivated()) {
            String order="Order Summary: \n\n";
            double amount=0;
            Intent intent = new Intent(this, MainActivity2.class);
            if(((CheckBox)findViewById(R.id.pizza_margherita)).isChecked()){
                EditText editText = (EditText) findViewById(R.id.quantity_margherita_edit_text);
                order += "Pizza Margherita: " + editText.getText().toString() + "\n";
                amount += Integer.parseInt(editText.getText().toString()) *20;
            }
            if(((CheckBox)findViewById(R.id.pizza_tomato)).isChecked()){
                EditText editText = (EditText) findViewById(R.id.quantity_quick_tomato_edit_text);
                order += "Pizza Quick Tomato: " + editText.getText().toString() + "\n";
                amount += Integer.parseInt(editText.getText().toString()) *20;
            }
            if(((CheckBox)findViewById(R.id.pizza_cheese)).isChecked()){
                EditText editText = (EditText) findViewById(R.id.quantity_four_cheese_edit_text);
                order += "Pizza Four Cheese: " + editText.getText().toString() + "\n";
                amount += Integer.parseInt(editText.getText().toString()) *20;
            }
            if(((CheckBox)findViewById(R.id.pizza_vegetable)).isChecked()){
                EditText editText = (EditText) findViewById(R.id.quantity_cheesy_vegetable_edit_text);
                order += "Pizza Cheese Vegetable: " + editText.getText().toString() + "\n";
                amount += Integer.parseInt(editText.getText().toString()) *20;
            }
            DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("en", "IN"));
            order += "\n\n Total Amount: " + formatter.format(amount) +"\n";
            intent.putExtra(message, order);
            startActivity(intent);
        }
        else Toast.makeText(this, "No item is selected", Toast.LENGTH_SHORT).show();
    }
}
