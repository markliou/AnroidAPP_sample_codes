package com.example.test_example;

import com.example.fan.cla_other_pac;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calInvest = this.findViewById(R.id.calcInvest);

        calInvest.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                int a;
                int b;
                int c;
                EditText principal = MainActivity.this.findViewById(R.id.principle);
                EditText profitRate = MainActivity.this.findViewById(R.id.profit_rate);
                a = Integer.parseInt(principal.getText().toString()) ;
                b = Integer.parseInt(profitRate.getText().toString()) ;
                c = a + b ;
                TextView investTotal = MainActivity.this.findViewById(R.id.invest_total);
                investTotal.setText(":" + c);
            }
                  }

        );

    }
}
