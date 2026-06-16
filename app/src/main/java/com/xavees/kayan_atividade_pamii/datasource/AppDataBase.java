package com.xavees.kayan_atividade_pamii.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.xavees.kayan_atividade_pamii.api.AppUtil;
import com.xavees.kayan_atividade_pamii.datamodel.ClienteDataModel;
import com.xavees.kayan_atividade_pamii.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class AppDataBase extends SQLiteOpenHelper {
    public static final String NAME = "etim.sqlite";
    public static int version = 1;

    public AppDataBase(@Nullable Context context) {
        super(context, NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(AppUtil.TAG, "Criando a tabela " + ClienteDataModel.TABELA);
        db.execSQL(ClienteDataModel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ClienteDataModel.TABELA);
        onCreate(db);
    }

    public boolean inserir(Cliente obj) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put(ClienteDataModel.NOME, obj.getNome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());

        return db.insert(ClienteDataModel.TABELA, null, dados) > 0;
    }

    public boolean alterar(Cliente obj) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put(ClienteDataModel.NOME, obj.getNome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());

        return db.update(ClienteDataModel.TABELA, dados, ClienteDataModel.ID + "=?", new String[]{String.valueOf(obj.getId())}) > 0;
    }

    public boolean deletar(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(ClienteDataModel.TABELA, ClienteDataModel.ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ClienteDataModel.TABELA, null);

        if (cursor.moveToFirst()) {
            do {
                Cliente cliente = new Cliente();
                cliente.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ClienteDataModel.ID)));
                cliente.setNome(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.NOME)));
                cliente.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.EMAIL)));
                lista.add(cliente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}
