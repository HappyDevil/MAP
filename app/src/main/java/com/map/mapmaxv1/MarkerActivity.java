package com.map.mapmaxv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MarkerActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextText;
    private EditText editTextPrice;
    private TextView textViewAddress;
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
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
