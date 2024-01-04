package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developers.lactimuu_prueba.Modelos.ViewAllModel;
import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetallesActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;

    ImageView detailedImg, ImgBack;
    TextView price,description,pa, name;
    Button addToCart;
    ImageView addItem,removeItem;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ViewAllModel viewAllModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }

        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        ImgBack = findViewById(R.id.imgBackDetail);

        price = findViewById(R.id.detailed_price);
        description = findViewById(R.id.detailed_dec);
        name = findViewById(R.id.detailed_name);
        pa = findViewById(R.id.texto_producto_agotado);

        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);

            if (viewAllModel.getEstado().equals("Disponible")){
                pa.setVisibility(View.INVISIBLE);

            }else if (viewAllModel.getEstado().equals("Agotado")){
                addItem.setVisibility(View.INVISIBLE);
                removeItem.setVisibility(View.INVISIBLE);
                quantity.setVisibility(View.INVISIBLE);
                pa.setVisibility(View.VISIBLE);
            }

            description.setText(viewAllModel.getDescripcion());
            name.setText(viewAllModel.getNombre());
            price.setText("$"+viewAllModel.getPrecio());

            totalPrice = viewAllModel.getPrecio() * totalQuantity;

            if (viewAllModel.getTipo().equals("Licores")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
            if (viewAllModel.getTipo().equals("Bebidas Calientes")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
            if (viewAllModel.getTipo().equals("Bebidas Frias")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
            if (viewAllModel.getTipo().equals("Paquetes")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
            if (viewAllModel.getTipo().equals("Lacteos")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
            if (viewAllModel.getTipo().equals("Golosinas")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
            if (viewAllModel.getTipo().equals("Carnes")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
            if (viewAllModel.getTipo().equals("Cigarrillos")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
            if (viewAllModel.getTipo().equals("Postres")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
            if (viewAllModel.getTipo().equals("Helados")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
            if (viewAllModel.getTipo().equals("Tradicional Regional")){
                price.setText("$"+viewAllModel.getPrecio());
                totalPrice = viewAllModel.getPrecio() * totalQuantity;
            }
        }

        addToCart = findViewById(R.id.add_to_cart);
        if(viewAllModel.getEstado().equals("Agotado")){
            addToCart.setVisibility(View.INVISIBLE);
        }else if(viewAllModel.getEstado().equals("Disponible")){

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addedToCart();
                }
            });
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addedToCart();
                }
            });
            addItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (totalQuantity < 99){
                        totalQuantity++;
                        quantity.setText(String.valueOf(totalQuantity));
                        totalPrice = viewAllModel.getPrecio() * totalQuantity;
                    }

                }
            });
            removeItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (totalQuantity > 1){
                        totalQuantity--;
                        quantity.setText(String.valueOf(totalQuantity));
                        totalPrice = viewAllModel.getPrecio() * totalQuantity;
                    }
                }
            });
        }
    }
    private void addedToCart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate =  Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("Nombre_Producto",viewAllModel.getNombre());
        cartMap.put("Precio",price.getText().toString());
        cartMap.put("Fecha_Actual",saveCurrentDate);
        cartMap.put("Hora_Pedido",saveCurrentTime);
        cartMap.put("Cantidad",quantity.getText().toString());
        cartMap.put("Precio_Total",totalPrice);

        firestore.collection("CarritoCompras").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetallesActivity.this, "Agregado al Carrito de Compras", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}