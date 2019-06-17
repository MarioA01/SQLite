package app.m.bdd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class listado extends AppCompatActivity {
    DataBaseHelper dbHadler;
    ArrayList<String> listado;
    ListView listView;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listView = findViewById(R.id.listview);

        CargarListado();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int id=Integer.parseInt(listado.get(position).split(" ")[0]);
                String name = listado.get(position).split(" ")[1];
                String price = listado.get(position).split(" ")[2];
                Intent intent =new Intent(app.m.bdd.listado.this, Modificar.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("price",price);
                startActivity(intent);
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

    private void CargarListado(){
        listado = ListaProductos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaProductos(){
        ArrayList<String> datos = new ArrayList<String>();
        dbHadler = new DataBaseHelper(this);
        SQLiteDatabase db = dbHadler.getReadableDatabase();
        String sql = "SELECT id, name, price FROM productos";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){

            do{
                String linea = c.getInt(0) + " " + c.getString(1) + " " + c.getString(2);
                datos.add(linea);
            }while(c.moveToNext());
        }
        db.close();
        return datos;
    }
}
