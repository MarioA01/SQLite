package app.m.bdd;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {
    DataBaseHelper dbHadler;
    EditText name, price;
    Button btnUpdate, btnDelete;
    int id;
    String name1,price1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b= getIntent().getExtras();
        if (b!=null){
            id = b.getInt("id");
            name1 = b.getString("name");
            price1 = b.getString("price");

        }

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        name.setText(name1);
        price.setText(price1);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modificar(id, name.getText().toString(), price.getText().toString());
                onBackPressed();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar(id);
                onBackPressed();
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return  super.onOptionsItemSelected(item);
    }

    private void Modificar(int id, String name, String price){
        dbHadler = new DataBaseHelper(this);
        SQLiteDatabase db = dbHadler.getReadableDatabase();

        String sql = "UPDATE productos set name='" + name + "',price='" + price +"' where id="+id;
        db.execSQL(sql);
        db.close();
    }

    private void Eliminar(int id){
        dbHadler = new DataBaseHelper(this);
        SQLiteDatabase db = dbHadler.getReadableDatabase();

        String sql = "DELETE FROM productos where id="+id;
        db.execSQL(sql);
        db.close();
    }
}
