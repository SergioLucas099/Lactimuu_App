package com.developers.lactimuu_prueba.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.developers.lactimuu_prueba.Clases_Clima.Example;
import com.developers.lactimuu_prueba.Clases_Clima.Main;
import com.developers.lactimuu_prueba.Clases_Clima.weatherapi;
import com.developers.lactimuu_prueba.Inicio_Administrador;
import com.developers.lactimuu_prueba.Modelos.Categorias;
import com.developers.lactimuu_prueba.Modelos.Productos_Lactimuu_Model;
import com.developers.lactimuu_prueba.Modelos.Productos_Rec_Clima_Model;
import com.developers.lactimuu_prueba.Modelos.ViewAllModel;
import com.developers.lactimuu_prueba.Modo_Usuario_Empleado;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Home_Categorias_Adapter;
import com.developers.lactimuu_prueba.adapters.ViewAllAdapter;
import com.developers.lactimuu_prueba.adapters.Productos_Rec_Clima_Adapter;
import com.developers.lactimuu_prueba.adapters.Productos_Lactimuu_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment{

    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView recomendadosRec, categoriaRec, recomeRec;
    FirebaseFirestore db;
    ImageSlider imageSlider;
    ImageView imageView;
    TextView texto_sin_wifi;
    SharedPreferences preferences;

    //API Clima
    TextView tv, et, d, caliente, templado, frio;
    String url = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}";
    String apikey = "fcd2c236893559071f53147e2c72132f";
    LocationManager manager;
    LocationListener locationListener;

    //Productos Recomendados Clima
    List<Productos_Rec_Clima_Model> productosRecomendadosList;
    Productos_Rec_Clima_Adapter productos_recomendados_adapter;

    //////////// Search View
    EditText search_box;
    private List<ViewAllModel> viewAllModelList;
    private  RecyclerView recyclerViewSearch;
    private ViewAllAdapter viewAllAdapter;

    //Categorias
    List<Categorias> categoriasList;
    Home_Categorias_Adapter home_categorias_adapter;

    //Productos Lactimuu
    List<Productos_Lactimuu_Model> productosLactimuuModelList;
    Productos_Lactimuu_Adapter Productos_Lactimuu_Adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment,container,false);
        db = FirebaseFirestore.getInstance();

        recomendadosRec = root.findViewById(R.id.pop_rec);
        categoriaRec = root.findViewById(R.id.explorar_rec);
        recomeRec = root.findViewById(R.id.recommended_rec);
        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.barra_progreso);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        imageSlider = root.findViewById(R.id.imageSlider);

        imageView = root.findViewById(R.id.logo_sin_wifi);
        texto_sin_wifi = root.findViewById(R.id.aviso_sin_wifi);

        caliente = root.findViewById(R.id.txt_caliente);
        templado = root.findViewById(R.id.txt_templado);
        frio = root.findViewById(R.id.txt_frio);

        caliente.setVisibility(View.INVISIBLE);
        templado.setVisibility(View.INVISIBLE);
        frio.setVisibility(View.INVISIBLE);

        et = root.findViewById(R.id.et);
        et.setText("Chiquinquira");
        tv = root.findViewById(R.id.tv);
        d = root.findViewById(R.id.dato);
        d.setVisibility(View.INVISIBLE);

        imageView.setVisibility(View.INVISIBLE);
        texto_sin_wifi.setVisibility(View.INVISIBLE);

        preferences = getActivity().getSharedPreferences("typeUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        ObtenerClima();

        ///////////////////Imagenes Promocionales/////////////////////////

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        db.collection("ImagenesPromocionales").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                        slideModels.add(new SlideModel(queryDocumentSnapshot.getString("url"), ScaleTypes.FIT));
                        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
                    }
                }
                else{
                    Toast.makeText(getActivity(), "No se pueden cargar las imagenes", Toast.LENGTH_LONG).show();;
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "No se pueden cargar las imagenes", Toast.LENGTH_LONG).show();;
            }
        });

        ///////////////////Categoria/////////////////////////

        categoriaRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
        categoriasList = new ArrayList<>();
        home_categorias_adapter = new Home_Categorias_Adapter(getActivity(),categoriasList);
        categoriaRec.setAdapter(home_categorias_adapter);

        db.collection("Categoria")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Categorias categorias = document.toObject(Categorias.class);
                                categoriasList.add(categorias);
                                home_categorias_adapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        ///////////////////Productos Lactimuu/////////////////////////

        recomeRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
        productosLactimuuModelList = new ArrayList<>();
        Productos_Lactimuu_Adapter = new Productos_Lactimuu_Adapter(getActivity(), productosLactimuuModelList);
        recomeRec.setAdapter(Productos_Lactimuu_Adapter);

        db.collection("TodosLosProductos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Productos_Lactimuu_Model Productos_Lactimuu_Model = document.toObject(Productos_Lactimuu_Model.class);
                                productosLactimuuModelList.add(Productos_Lactimuu_Model);
                                Productos_Lactimuu_Adapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        //////////// Search View
        recyclerViewSearch = root.findViewById(R.id.searchR);
        search_box = root.findViewById(R.id.search_box);
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(getContext(),viewAllModelList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.setAdapter(viewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.toString().isEmpty()){
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }else {
                    searchProduct(editable.toString());
                }

            }
        });

        return root;
    }

    private void searchProduct(String Nombre) {

        if (!Nombre.isEmpty()){

            db.collection("TodosLosProductos").whereEqualTo("Nombre",Nombre).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful() && task.getResult() != null){

                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel = doc.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                    });
        }
    }

    public void ObtenerClima(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi myapi=retrofit.create(weatherapi.class);
        Call<Example> examplecall=myapi.getweather(et.getText().toString().trim(),apikey);
        examplecall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example mydata = response.body();
                Main main = mydata.getMain();
                Double temp = main.getTemp();
                Integer temperature = (int) (temp - 273.15);
                tv.setText(String.valueOf(temperature) + "°C");
                d.setText(String.valueOf(temperature));

                ///////////////////Productos Recomendados/////////////////////////

                recomendadosRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                productosRecomendadosList = new ArrayList<>();
                productos_recomendados_adapter = new Productos_Rec_Clima_Adapter(getActivity(), productosRecomendadosList);
                recomendadosRec.setAdapter(productos_recomendados_adapter);

                int dato = Integer.parseInt(d.getText().toString());

                if (dato > 17) {

                    caliente.setVisibility(View.VISIBLE);

                    db.collection("TodosLosProductos").whereEqualTo("Estado_Clima", "Caliente")
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful() && task.getResult() != null) {

                                productosRecomendadosList.clear();
                                productos_recomendados_adapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    Productos_Rec_Clima_Model Productos_Rec_Clima_Model = doc.toObject(Productos_Rec_Clima_Model.class);
                                    productosRecomendadosList.add(Productos_Rec_Clima_Model);
                                    productos_recomendados_adapter.notifyDataSetChanged();

                                    progressBar.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                }
                            } else {
                                Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else if (dato <= 17 && dato >=14){

                    templado.setVisibility(View.VISIBLE);

                    db.collection("TodosLosProductos").whereEqualTo("Estado_Clima", "Templado")
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful() && task.getResult() != null) {

                                productosRecomendadosList.clear();
                                productos_recomendados_adapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    Productos_Rec_Clima_Model Productos_Rec_Clima_Model = doc.toObject(Productos_Rec_Clima_Model.class);
                                    productosRecomendadosList.add(Productos_Rec_Clima_Model);
                                    productos_recomendados_adapter.notifyDataSetChanged();

                                    progressBar.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                }
                            } else {
                                Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else if (dato < 14){

                    frio.setVisibility(View.VISIBLE);

                    db.collection("TodosLosProductos").whereEqualTo("Estado_Clima", "Frio")
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful() && task.getResult() != null) {

                                productosRecomendadosList.clear();
                                productos_recomendados_adapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    Productos_Rec_Clima_Model Productos_Rec_Clima_Model = doc.toObject(Productos_Rec_Clima_Model.class);
                                    productosRecomendadosList.add(Productos_Rec_Clima_Model);
                                    productos_recomendados_adapter.notifyDataSetChanged();

                                    progressBar.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                }
                            } else {
                                Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getActivity(),"Sin Conexión a Internet",Toast.LENGTH_LONG).show();
                imageView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                texto_sin_wifi.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            String user = preferences.getString("user", "");
            if (user.equals("Gerente")){
                Intent intent = new Intent(getContext(), Inicio_Administrador.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            else if (user.equals("Empleado")){
                Intent intent = new Intent(getContext(), Modo_Usuario_Empleado.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }
}