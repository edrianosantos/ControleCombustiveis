package br.com.edrsantos.controlecombustivel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.edrsantos.controlecombustivel.db.DataBase;
import br.com.edrsantos.controlecombustivel.model.Combustivel;

/**
 * Created by slimv on 6/16/2018.
 */

public class CombustivelDAO {

    SQLiteDatabase db;
    DataBase dataBase;

    public CombustivelDAO(Context context) {
        dataBase = new DataBase(context);
    }

    public void save(Combustivel combustivel) {
        ContentValues values = new ContentValues();
        values.put("descricao", combustivel.getDescricao());
        values.put("codigo", combustivel.getCodigo());

        try {
            db = dataBase.getWritableDatabase();
            db.insert("combustivel", null, values);
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao inserir na tabela combustivel: " + e.getMessage());
        }
    }

    public Combustivel findByCodigo(int codigo){
        Combustivel combustivel = null;
        try {
            db = dataBase.getReadableDatabase();
            Cursor cursor = db.query("combustivel",
                    null,
                    "codigo = ?",
                    new String[]{String.valueOf(codigo)},
                    null,
                    null,
                    null);

            while (cursor.moveToNext()) {
                combustivel = new Combustivel();
                combustivel.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                combustivel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                combustivel.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            }
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao listar na tabela abastecimento: " + e.getMessage());
        }

        return combustivel;
    }

    public Combustivel findById(long id) {
        Combustivel combustivel = null;
        try {
            db = dataBase.getReadableDatabase();
            Cursor cursor = db.query("combustivel",
                    null,
                    "_id = ?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null);

            while (cursor.moveToNext()) {
                combustivel = new Combustivel();
                combustivel.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                combustivel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                combustivel.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            }
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao listar na tabela abastecimento: " + e.getMessage());
        }

        return combustivel;
    }

    public List<Combustivel> list() {

        List<Combustivel> combustivels = new ArrayList<>();
        Cursor cursor = null;

        try {
            db = dataBase.getReadableDatabase();
            cursor = db.query("combustivel", null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Combustivel combustivel = new Combustivel();
                combustivel.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                combustivel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                combustivel.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                combustivels.add(combustivel);
            }
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao listar na tabela combustivel: " + e.getMessage());
        }
        return combustivels;
    }

    public List<Combustivel> listDesc() {

        List<Combustivel> combustivels = new ArrayList<>();
        Cursor cursor = null;

        try {
            db = dataBase.getReadableDatabase();
            cursor = db.query("combustivel", null, null, null, null, null, " codigo DESC ");
            while (cursor.moveToNext()) {
                Combustivel combustivel = new Combustivel();
                combustivel.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                combustivel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                combustivel.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                combustivels.add(combustivel);
            }
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao listar na tabela combustivel: " + e.getMessage());
        }
        return combustivels;
    }

    public void update(Combustivel combustivel) {

        String[] whereArgs = {String.valueOf(combustivel.getId())};

        ContentValues values = new ContentValues();
        values.put("descricao", combustivel.getDescricao());
        values.put("codigo", combustivel.getCodigo());

        try {
            db = dataBase.getWritableDatabase();
            db.update("combustivel", values, "_id=?", whereArgs);
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao alterar na tabela combustivel: " + e.getMessage());
        }
    }

    public void delete(long id) {
        String[] whereArgs = {String.valueOf(id)};

        try {
            db = dataBase.getWritableDatabase();
            db.delete("combustivel", "_id=?", whereArgs);
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao deletar na tabela combustivel: " + e.getMessage());
        }
    }

}
