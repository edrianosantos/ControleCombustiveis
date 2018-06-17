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
import br.com.edrsantos.controlecombustivel.model.Abastecimento;

/**
 * Created by slimv on 6/16/2018.
 */

public class AbastecimentoDAO {

    SQLiteDatabase db;
    DataBase dataBase;

    public AbastecimentoDAO(Context context) {
        dataBase = new DataBase(context);
    }

    public void save(Abastecimento abastecimento) {
        ContentValues values = new ContentValues();
        values.put("codigo", abastecimento.getCodigo());
        values.put("cidade", abastecimento.getCidade());
        values.put("quantidade", abastecimento.getQuantidade());

        try {
            db = dataBase.getWritableDatabase();
            db.insert("abastecimento", null, values);
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao inserir na tabela abastecimento: " + e.getMessage());
        }
    }

    public Abastecimento findByCodigo(int codigo){
        Abastecimento abastecimento = null;
        try {
            db = dataBase.getReadableDatabase();
            Cursor cursor = db.query("abastecimento",
                    null,
                    "codigo = ?",
                    new String[]{String.valueOf(codigo)},
                    null,
                    null,
                    null);

            while (cursor.moveToNext()) {
                abastecimento = new Abastecimento();
                abastecimento.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                abastecimento.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                abastecimento.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                abastecimento.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            }
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao listar na tabela abastecimento: " + e.getMessage());
        }

        return abastecimento;
    }

    public Abastecimento findById(long id) {
        Abastecimento abastecimento = null;
        try {
            db = dataBase.getReadableDatabase();
            Cursor cursor = db.query("abastecimento",
                    null,
                    "_id = ?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null);

            while (cursor.moveToNext()) {
                abastecimento = new Abastecimento();
                abastecimento.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                abastecimento.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                abastecimento.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                abastecimento.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            }
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao listar na tabela abastecimento: " + e.getMessage());
        }

        return abastecimento;
    }

    public List<Abastecimento> list() {

        List<Abastecimento> abastecimentos = new ArrayList<>();
        Cursor cursor = null;

        try {
            db = dataBase.getReadableDatabase();
            cursor = db.query("abastecimento", null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Abastecimento abastecimento = new Abastecimento();
                abastecimento.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                abastecimento.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                abastecimento.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                abastecimento.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
                abastecimentos.add(abastecimento);
            }
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao listar na tabela abastecimento: " + e.getMessage());
        }
        return abastecimentos;
    }

    public List<Abastecimento> listDesc() {

        List<Abastecimento> abastecimentos = new ArrayList<>();
        Cursor cursor = null;

        try {
            db = dataBase.getReadableDatabase();
            cursor = db.query("abastecimento", null, null, null, null, null, " quantidade DESC ");
            while (cursor.moveToNext()) {
                Abastecimento abastecimento = new Abastecimento();
                abastecimento.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                abastecimento.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                abastecimento.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                abastecimento.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
                abastecimentos.add(abastecimento);
            }
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao listar na tabela abastecimento: " + e.getMessage());
        }
        return abastecimentos;
    }

    public void update(Abastecimento abastecimento) {

        String[] whereArgs = {String.valueOf(abastecimento.getId())};

        ContentValues values = new ContentValues();
        values.put("codigo", abastecimento.getCodigo());
        values.put("cidade", abastecimento.getCidade());
        values.put("quantidade", abastecimento.getQuantidade());

        try {
            db = dataBase.getWritableDatabase();
            db.update("abastecimento", values, "_id=?", whereArgs);
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao alterar na tabela abastecimento: " + e.getMessage());
        }
    }

    public void delete(long id) {
        String[] whereArgs = {String.valueOf(id)};

        try {
            db = dataBase.getWritableDatabase();
            db.delete("abastecimento", "_id=?", whereArgs);
            db.close();
        } catch (SQLiteException e) {
            Log.e(this.getClass().getName(), "Erro ao deletar na tabela abastecimento: " + e.getMessage());
        }
    }

}
