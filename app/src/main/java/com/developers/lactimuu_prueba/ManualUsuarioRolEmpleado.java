package com.developers.lactimuu_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.lactimuu_prueba.R;
import com.github.barteksc.pdfviewer.PDFView;

public class ManualUsuarioRolEmpleado extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView imageView;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_usuario_rol_empleado);

        progressBar = findViewById(R.id.progressBar_pdf_empleado);
        pdfView = findViewById(R.id.pdf_emplado);
        imageView = findViewById(R.id.imgBackManualEmpleado);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String urlPdf = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Manual_Usuario_Empleado%2Fpdf_manual_empleado.pdf?alt=media&token=3e9c33b0-b38f-4df2-a717-76181db426ff";
        new RecibirPDFManualClienteStream(pdfView, progressBar).execute(urlPdf);

    }
}