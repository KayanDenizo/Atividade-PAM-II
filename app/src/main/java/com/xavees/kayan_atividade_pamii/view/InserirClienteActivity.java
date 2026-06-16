package com.xavees.kayan_atividade_pamii.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.xavees.kayan_atividade_pamii.R;
import com.xavees.kayan_atividade_pamii.controller.ClienteController;
import com.xavees.kayan_atividade_pamii.model.Cliente;

public class InserirClienteActivity extends AppCompatActivity {

    private EditText editNome, editEmail;
    private Button btnSalvar;
    private ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_cliente);

        controller = new ClienteController(this);

        editNome = findViewById(R.id.edit_nome);
        editEmail = findViewById(R.id.edit_email);
        btnSalvar = findViewById(R.id.btn_salvar);

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString();
            String email = editEmail.getText().toString();

            if (nome.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Cliente novoCliente = new Cliente();
            novoCliente.setNome(nome);
            novoCliente.setEmail(email);

            if (controller.inserir(novoCliente)) {
                Toast.makeText(this, "Cliente inserido com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao inserir cliente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
