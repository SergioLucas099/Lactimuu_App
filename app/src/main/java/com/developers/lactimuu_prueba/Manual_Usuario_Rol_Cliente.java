 package com.developers.lactimuu_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.lactimuu_prueba.R;
import com.github.barteksc.pdfviewer.PDFView;

 public class Manual_Usuario_Rol_Cliente extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView imageView;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_usuario_rol_cliente);

        progressBar = findViewById(R.id.progressBar_mu);
        pdfView = findViewById(R.id.pdf_cliente);
        imageView = findViewById(R.id.imgBackManualCliente);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String urlPdf = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Manual_Usuario_Cliente%2FManual%20del%20Cliente%20(1).pdf?alt=media&token=384ad39b-7fd8-4dd0-b35f-cfdbeea793a2";
        new RecibirPDFManualClienteStream(pdfView, progressBar).execute(urlPdf);
    }
}