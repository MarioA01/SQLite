package app.m.bdd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    DataBaseHelper dbHadler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView name = findViewById(R.id.name),
                price = findViewById(R.id.price);

        Button btnAdd = findViewById(R.id.register),
                btnView = findViewById(R.id.btnView);
        dbHadler = new DataBaseHelper(MainActivity.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String price1 = price.getText().toString();
                if (name.length() != 0 && price.length() != 0) {
                    AddData(name1, price1);
                    name.setText("");
                    price.setText("");
                } else {

                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listado.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String name1, String price1) {
        boolean insertData = dbHadler.insertProduct(name1, price1);
    }
}