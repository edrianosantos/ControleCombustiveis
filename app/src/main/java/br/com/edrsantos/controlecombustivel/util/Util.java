package br.com.edrsantos.controlecombustivel.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by slimv on 6/16/2018.
 */

public class Util {

    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
