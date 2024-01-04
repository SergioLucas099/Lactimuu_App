package com.developers.lactimuu_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.developers.lactimuu_prueba.Modelos.MyCartModel;
import com.example.lactimuu_prueba.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    TextView t, pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        t = findViewById(R.id.tv7);
        pedido = findViewById(R.id.textviewPedido);
        t.setVisibility(View.INVISIBLE);

        String dato = getIntent().getStringExtra("dato");
        t.setText(dato);

        if (dato.equals("Mesa 1")){

            pedido.setText("Su orden será llevada a la Mesa 1");

            List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");
            if (list != null && list.size() > 0){
                for (MyCartModel model : list){
                    final HashMap<String,Object> cartMap = new HashMap<>();

                    cartMap.put("Nombre_Producto",model.getNombre_Producto());
                    cartMap.put("Precio",model.getPrecio());
                    cartMap.put("Fecha_Actual",model.getFecha_Actual());
                    cartMap.put("Hora_Pedido",model.getHora_Pedido());
                    cartMap.put("Cantidad",model.getCantidad());
                    cartMap.put("Precio_Total",model.getPrecio_Total());
                    cartMap.put("Pedido",t.getText().toString());

                    firestore.collection("Pedidos_Mesa_1").add(cartMap);

                }
            }

        } else if (dato.equals("Mesa 2")){

            pedido.setText("Su orden será llevada a la Mesa 2");

            List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");
            if (list != null && list.size() > 0){
                for (MyCartModel model : list){
                    final HashMap<String,Object> cartMap = new HashMap<>();

                    cartMap.put("Nombre_Producto",model.getNombre_Producto());
                    cartMap.put("Precio",model.getPrecio());
                    cartMap.put("Fecha_Actual",model.getFecha_Actual());
                    cartMap.put("Hora_Pedido",model.getHora_Pedido());
                    cartMap.put("Cantidad",model.getCantidad());
                    cartMap.put("Precio_Total",model.getPrecio_Total());
                    cartMap.put("Pedido",t.getText().toString());

                    firestore.collection("Pedidos_Mesa_2").add(cartMap);

                }
            }

        } else if (dato.equals("Mesa 3")){

            pedido.setText("Su orden será llevada a la Mesa 3");

            List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");
            if (list != null && list.size() > 0){
                for (MyCartModel model : list){
                    final HashMap<String,Object> cartMap = new HashMap<>();

                    cartMap.put("Nombre_Producto",model.getNombre_Producto());
                    cartMap.put("Precio",model.getPrecio());
                    cartMap.put("Fecha_Actual",model.getFecha_Actual());
                    cartMap.put("Hora_Pedido",model.getHora_Pedido());
                    cartMap.put("Cantidad",model.getCantidad());
                    cartMap.put("Precio_Total",model.getPrecio_Total());
                    cartMap.put("Pedido",t.getText().toString());

                    firestore.collection("Pedidos_Mesa_3").add(cartMap);

                }
            }

        } else if (dato.equals("Mesa 4")){

            pedido.setText("Su orden será llevada a la Mesa 4");

            List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");
            if (list != null && list.size() > 0){
                for (MyCartModel model : list){
                    final HashMap<String,Object> cartMap = new HashMap<>();

                    cartMap.put("Nombre_Producto",model.getNombre_Producto());
                    cartMap.put("Precio",model.getPrecio());
                    cartMap.put("Fecha_Actual",model.getFecha_Actual());
                    cartMap.put("Hora_Pedido",model.getHora_Pedido());
                    cartMap.put("Cantidad",model.getCantidad());
                    cartMap.put("Precio_Total",model.getPrecio_Total());
                    cartMap.put("Pedido",t.getText().toString());

                    firestore.collection("Pedidos_Mesa_4").add(cartMap);

                }
            }

        } else if (dato.equals("Mesa 5")){

            pedido.setText("Su orden será llevada a la Mesa 5");

            List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");
            if (list != null && list.size() > 0){
                for (MyCartModel model : list){
                    final HashMap<String,Object> cartMap = new HashMap<>();

                    cartMap.put("Nombre_Producto",model.getNombre_Producto());
                    cartMap.put("Precio",model.getPrecio());
                    cartMap.put("Fecha_Actual",model.getFecha_Actual());
                    cartMap.put("Hora_Pedido",model.getHora_Pedido());
                    cartMap.put("Cantidad",model.getCantidad());
                    cartMap.put("Precio_Total",model.getPrecio_Total());
                    cartMap.put("Pedido",t.getText().toString());

                    firestore.collection("Pedidos_Mesa_5").add(cartMap);

                }
            }

        } else if (dato.equals("Mesa 6")){

            pedido.setText("Su orden será llevada a la Mesa 6");

            List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");
            if (list != null && list.size() > 0){
                for (MyCartModel model : list){
                    final HashMap<String,Object> cartMap = new HashMap<>();

                    cartMap.put("Nombre_Producto",model.getNombre_Producto());
                    cartMap.put("Precio",model.getPrecio());
                    cartMap.put("Fecha_Actual",model.getFecha_Actual());
                    cartMap.put("Hora_Pedido",model.getHora_Pedido());
                    cartMap.put("Cantidad",model.getCantidad());
                    cartMap.put("Precio_Total",model.getPrecio_Total());
                    cartMap.put("Pedido",t.getText().toString());

                    firestore.collection("Pedidos_Mesa_6").add(cartMap);

                }
            }

        } else if (dato.equals("Para Llevar")){

            pedido.setText("Su orden será para llevar");

            List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");
            if (list != null && list.size() > 0){
                for (MyCartModel model : list){
                    final HashMap<String,Object> cartMap = new HashMap<>();

                    cartMap.put("Nombre_Producto",model.getNombre_Producto());
                    cartMap.put("Precio",model.getPrecio());
                    cartMap.put("Fecha_Actual",model.getFecha_Actual());
                    cartMap.put("Hora_Pedido",model.getHora_Pedido());
                    cartMap.put("Cantidad",model.getCantidad());
                    cartMap.put("Precio_Total",model.getPrecio_Total());
                    cartMap.put("Pedido",t.getText().toString());

                    firestore.collection("Pedidos_Para_Llevar").add(cartMap);

                }
            }
        }

        Toast.makeText(PlacedOrderActivity.this, "Su orden a sido puesta", Toast.LENGTH_SHORT).show();

    }


    public void MenuPrincipal (View view){
        Intent siguiente = new Intent(this, InicioActivity.class);
        startActivity(siguiente);
    }

    public void Juegos (View view){
        Intent siguiente2 = new Intent(this, Menu_Juegos.class);
        startActivity(siguiente2);
    }

    public void Calificarnos (View view){
        Intent siguiente3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/ggNBCDTdLuadY2rj8"));
        startActivity(siguiente3);
    }
}