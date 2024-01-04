package com.developers.lactimuu_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.lactimuu_prueba.R;
import com.github.barteksc.pdfviewer.PDFView;

public class ManualUsuarioRolGerente extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView imageView;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_usuario_rol_gerente);

        progressBar = findViewById(R.id.progressBar_pdf_gerente);
        pdfView = findViewById(R.id.pdf_gerente);
        imageView = findViewById(R.id.imgBackManualGerente);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String urlPdf = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Manual_Usuario_Gerente%2Fpdf_manual_gerente.pdf?alt=media&token=61ea6d1e-8df3-4642-a201-c2d6757b476e";
        new RecibirPDFManualClienteStream(pdfView, progressBar).execute(urlPdf);
    }
}