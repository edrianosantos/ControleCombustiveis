package br.com.edrsantos.controlecombustivel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by slimv on 6/16/2018.
 */

public class DataBase extends SQLiteOpenHelper {

    private static int VERSION_DB = 1;
    private static String NAME_DB = "ControleCombustivel.db";

    public DataBase(Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableCombustivel = "create table combustivel(" +
                "_id integer primary key autoincrement, " +
                "descricao text, " +
                "codigo integer)";

        String tableAbasteciemnto = "create table abastecimento(" +
                "_id integer primary key autoincrement, " +
                "codigo integer, " +
                "cidade text, " +
                "quantidade integer)";

        try {
            sqLiteDatabase.execSQL(tableCombustivel);
            sqLiteDatabase.execSQL(tableAbasteciemnto);
        }catch (SQLiteException e){
            Log.e(this.getClass().getName(), "Error ao criar a tabela combustivel: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
