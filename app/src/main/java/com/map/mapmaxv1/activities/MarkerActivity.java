package com.map.mapmaxv1.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.map.mapmaxv1.R;

public class MarkerActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextText;
    private EditText editTextPrice;
    private EditText editTextType;
    private TextView textViewAddress;
    private Button button;
    private Switch switchCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker);
        setTitle("Создание маркера");
        String addressDTO;
        addressDTO = getIntent().getStringExtra("Address");
        textViewAddress = (TextView)findViewById(R.id.textViewAddress);
        textViewAddress.setText("Адрес: " + addressDTO);
        editTextTitle = (EditText)findViewById(R.id.editTextTitle);
        editTextText = (EditText)findViewById(R.id.editTextText);
        editTextPrice = (EditText)findViewById(R.id.editTextPrice);
        editTextType = (EditText)findViewById(R.id.editTextType);
        button = (Button)findViewById(R.id.button2);
        switchCompat = (Switch) findViewById(R.id.switch1);

    }

    public void justDoIt(){

    }

    public void onClick(View view) {
        boolean check = true;
        if (editTextText.getText().length() == 0) check = false;
        if (editTextTitle.getText().length() == 0) check = false;
        if (editTextPrice.getText().length() == 0) check = false;
        if (check){
            Intent intent = new Intent();
            intent.putExtra("Title", editTextTitle.getText().toString());
            intent.putExtra("Text", editTextText.getText().toString());
            intent.putExtra("Price", editTextPrice.getText().toString());
            intent.putExtra("Type", editTextType.getText().toString());
            intent.putExtra("CheckData", String.valueOf(switchCompat.isChecked()));
            setResult(RESULT_OK, intent);
            finish();
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
