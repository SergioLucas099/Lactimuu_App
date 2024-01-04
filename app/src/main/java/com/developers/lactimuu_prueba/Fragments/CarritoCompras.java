package com.developers.lactimuu_prueba.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developers.lactimuu_prueba.Modelos.MyCartModel;
import com.developers.lactimuu_prueba.PlacedOrderActivity;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.MyCartAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarritoCompras extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView overTotalAmount, t2;
    Spinner mesa;
    RecyclerView recyclerView;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;
    MyCartModel myCartModel;
    Button buyNow;
    String dato;
    int totalBill;

    public CarritoCompras() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.carrito_compras, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = root.findViewById(R.id.rv);
        recyclerView.setVisibility(View.GONE);
        buyNow = root.findViewById(R.id.boton_comprar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        overTotalAmount = root.findViewById(R.id.textView4);
        t2 = root.findViewById(R.id.textView2);
        t2.setVisibility(View.INVISIBLE);

        mesa = root.findViewById(R.id.Mesa);

        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(), cartModelList);
        recyclerView.setAdapter(cartAdapter);

        mesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        t2.setText("Mesa 1");
                        break;
                    case 2:
                        t2.setText("Mesa 2");
                        break;
                    case 3:
                        t2.setText("Mesa 3");
                        break;
                    case 4:
                        t2.setText("Mesa 4");
                        break;
                    case 5:
                        t2.setText("Mesa 5");
                        break;
                    case 6:
                        t2.setText("Mesa 6");
                        break;
                    case 7:
                        t2.setText("Para Llevar");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        db.collection("CarritoCompras").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        String documentId = documentSnapshot.getId();

                        MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);

                        cartModel.setDocumentId(documentId);

                        cartModelList.add(cartModel);
                        cartAdapter.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);
                    }

                    calculateTotalAmount(cartModelList);
                }
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Dato = t2.getText().toString();

                if (Dato.equals("")){
                    Toast.makeText(getActivity(), "Seleccione la mesa donde se le llevara el pedido", Toast.LENGTH_LONG).show();
                } else{
                    Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                    intent.putExtra("itemList", (Serializable) cartModelList);
                    intent.putExtra("dato", t2.getText().toString());
                    startActivity(intent);
                    limpiar();
                }
                }
            });
        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> cartModelList) {

        int totalAmout = 0;
        for (MyCartModel myCartModel : cartModelList) {
            totalAmout += myCartModel.getPrecio_Total();
        }
        overTotalAmount.setText("Valor Total: $" + totalAmout);
    }

    private void limpiar() {

        db.collection("CarritoCompras").document(cartModelList.get(0).getDocumentId())
                .delete();
    }
}


