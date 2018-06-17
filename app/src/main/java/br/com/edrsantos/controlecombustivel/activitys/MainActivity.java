package br.com.edrsantos.controlecombustivel.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.edrsantos.controlecombustivel.R;
import br.com.edrsantos.controlecombustivel.dao.AbastecimentoDAO;
import br.com.edrsantos.controlecombustivel.model.Abastecimento;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastro = findViewById(R.id.btnCadastrarCombustivel);
        Button btnLancar = findViewById(R.id.btnLancar);
        Button btnMedia = findViewById(R.id.btnMedia);
        Button btnAbastecimentos = findViewById(R.id.btnAbastecimentos);
        Button btnCombustiveis = findViewById(R.id.btnCombustiveis);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CadastroCombustivelActivity.class));
            }
        });

        btnLancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LancarAbastecimentoActivity.class));
            }
        });

        btnMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculaMedia();
            }
        });

        btnAbastecimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AbastecimentosActivity.class));
            }
        });

        btnCombustiveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CombustiveisActivity.class));
            }
        });

    }

    private void calculaMedia() {
        AbastecimentoDAO dao = new AbastecimentoDAO(this);
        List<Abastecimento> list = dao.list();
        double media = 0;

        for(Abastecimento a : list){
            media += Double.parseDouble(String.valueOf(a.getQuantidade()));
        }
        media = media / list.size();
        mostrarMedia(media);
    }

    private void mostrarMedia(double media){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Media de Abastecimentos");
        builder.setMessage("Média: "+ media + " litros.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
