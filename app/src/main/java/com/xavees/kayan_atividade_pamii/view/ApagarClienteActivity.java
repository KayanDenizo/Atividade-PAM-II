package com.xavees.kayan_atividade_pamii.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.xavees.kayan_atividade_pamii.R;
import com.xavees.kayan_atividade_pamii.controller.ClienteController;

public class ApagarClienteActivity extends AppCompatActivity {

    private EditText editId;
    private Button btnApagar;
    private ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_cliente);

        controller = new ClienteController(this);

        editId = findViewById(R.id.edit_id_apagar);
        btnApagar = findViewById(R.id.btn_apagar);

        btnApagar.setOnClickListener(v -> {
            String idStr = editId.getText().toString();

            if (idStr.isEmpty()) {
                Toast.makeText(this, "Informe o ID", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idStr);

            if (controller.deletar(id)) {
                Toast.makeText(this, "Cliente removido com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao remover ou ID inexistente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
