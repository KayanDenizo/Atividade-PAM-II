package com.xavees.kayan_atividade_pamii.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.xavees.kayan_atividade_pamii.R;
import com.xavees.kayan_atividade_pamii.api.AppUtil;
import com.xavees.kayan_atividade_pamii.controller.ClienteController;
import com.xavees.kayan_atividade_pamii.model.Cliente;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnTelaInserir, btnTelaAlterar, btnTelaApagar, btnListar;
    private ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        controller = new ClienteController(this);

        btnTelaInserir = findViewById(R.id.btn_tela_inserir);
        btnTelaAlterar = findViewById(R.id.btn_tela_alterar);
        btnTelaApagar = findViewById(R.id.btn_tela_apagar);
        btnListar = findViewById(R.id.btn_listar);

        btnTelaInserir.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InserirClienteActivity.class);
            startActivity(intent);
        });

        btnTelaAlterar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlterarClienteActivity.class);
            startActivity(intent);
        });

        btnTelaApagar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ApagarClienteActivity.class);
            startActivity(intent);
        });

        btnListar.setOnClickListener(v -> {
            List<Cliente> clientes = controller.listar();
            for (Cliente c : clientes) {
                Log.i(AppUtil.TAG, "ID: " + c.getId() + " | Nome: " + c.getNome() + " | Email: " + c.getEmail());
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
