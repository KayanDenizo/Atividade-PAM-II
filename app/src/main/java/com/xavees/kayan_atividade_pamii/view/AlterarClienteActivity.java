package com.xavees.kayan_atividade_pamii.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.xavees.kayan_atividade_pamii.R;
import com.xavees.kayan_atividade_pamii.controller.ClienteController;
import com.xavees.kayan_atividade_pamii.model.Cliente;

public class AlterarClienteActivity extends AppCompatActivity {

    private EditText editId, editNome, editEmail;
    private Button btnAlterar;
    private ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_cliente);

        controller = new ClienteController(this);

        editId = findViewById(R.id.edit_id_alterar);
        editNome = findViewById(R.id.edit_nome_alterar);
        editEmail = findViewById(R.id.edit_email_alterar);
        btnAlterar = findViewById(R.id.btn_alterar);

        btnAlterar.setOnClickListener(v -> {
            String idStr = editId.getText().toString();
            String nome = editNome.getText().toString();
            String email = editEmail.getText().toString();

            if (idStr.isEmpty() || nome.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Cliente cliente = new Cliente();
            cliente.setId(Integer.parseInt(idStr));
            cliente.setNome(nome);
            cliente.setEmail(email);

            if (controller.alterar(cliente)) {
                Toast.makeText(this, "Cliente alterado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao alterar cliente ou ID não encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
