package com.xavees.kayan_atividade_pamii.controller;

import android.content.Context;
import com.xavees.kayan_atividade_pamii.datasource.AppDataBase;
import com.xavees.kayan_atividade_pamii.model.Cliente;
import java.util.List;

public class ClienteController {
    private AppDataBase db;

    public ClienteController(Context context) {
        db = new AppDataBase(context);
    }

    public boolean inserir(Cliente obj) {
        return db.inserir(obj);
    }

    public boolean alterar(Cliente obj) {
        return db.alterar(obj);
    }

    public boolean deletar(int id) {
        return db.deletar(id);
    }

    public List<Cliente> listar() {
        return db.listar();
    }
}
