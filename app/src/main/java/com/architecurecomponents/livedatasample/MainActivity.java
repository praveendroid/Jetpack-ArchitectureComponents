package com.architecurecomponents.livedatasample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvRandomNum = findViewById(R.id.random_num);

        Button btFetchData = findViewById(R.id.button);

        MainActivityViewModel model = new ViewModelProvider(this).get(MainActivityViewModel.class);

        LiveData<String> randomNumber = model.getNumber();

        randomNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvRandomNum.setText(s);// Data changes on every fetch data from server in view model
                Log.i(TAG, "Data updated in UI");
            }
        });

        btFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.createNumber(); // its equal to fetching the data from server
            }
        });
        Log.i(TAG, "Random number set");

    }
}