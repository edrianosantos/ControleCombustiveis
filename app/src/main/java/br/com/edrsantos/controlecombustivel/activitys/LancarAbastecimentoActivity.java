package br.com.edrsantos.controlecombustivel.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.edrsantos.controlecombustivel.R;
import br.com.edrsantos.controlecombustivel.dao.AbastecimentoDAO;
import br.com.edrsantos.controlecombustivel.model.Abastecimento;
import br.com.edrsantos.controlecombustivel.util.Util;

public class LancarAbastecimentoActivity extends AppCompatActivity {

    EditText edtCodigo;
    EditText edtCidade;
    EditText edtQuantidade;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar);

        edtCodigo = findViewById(R.id.edtCodigoPesquisa);
        edtCidade = findViewById(R.id.edtCidade);
        edtQuantidade = findViewById(R.id.edtQtdLitros);

        Button btnPesquisa = findViewById(R.id.btnPesquisa);
        Button btnIncluir = findViewById(R.id.btnIncluir);

        bundle = getIntent().getExtras();
        if(bundle != null){
            Abastecimento abastecimento = (Abastecimento) bundle.get("abastecimento");
            edtCodigo.setText(String.valueOf(abastecimento.getCodigo()));
            edtCidade.setText(abastecimento.getCidade());
            edtQuantidade.setText(String.valueOf(abastecimento.getQuantidade()));
        }

        btnPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(LancarAbastecimentoActivity.this, CombustiveisActivity.class), 1234);
            }
        });

        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incluir();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1234:
                if(data != null){
                    edtCodigo.setText(data.getStringExtra("codigo"));
                    edtCodigo.setError(null);
                }
                break;
        }

    }

    private void incluir() {
        if(edtCodigo.getText().toString().isEmpty()){
            edtCodigo.setError("Esse campo não pode ser vazio");
            return;
        }
        if(edtCidade.getText().toString().isEmpty()){
            edtCidade.setError("Esse campo não pode ser vazio");
            return;
        }
        if(edtQuantidade.getText().toString().isEmpty()){
            edtQuantidade.setError("Esse campo não pode ser vazio");
            return;
        }

        Abastecimento abastecimento = new Abastecimento();
        abastecimento.setCodigo(Integer.parseInt(edtCodigo.getText().toString()));
        abastecimento.setCidade(edtCidade.getText().toString());
        abastecimento.setQuantidade(Integer.parseInt(edtQuantidade.getText().toString()));

        try {
            AbastecimentoDAO dao = new AbastecimentoDAO(this);
//            if(dao.findByCodigo(abastecimento.getCodigo()) != null){
//                Util.showToast(this, "Esse código já está cadastrado");
//                return;
//            }
            if(bundle != null && bundle.get("abastecimento") != null){
                abastecimento.setId(((Abastecimento)bundle.get("abastecimento")).getId());
                dao.update(abastecimento);
            }else
                dao.save(abastecimento);
            Util.showToast(this, "Inclusão realizada com sucesso!");
            finish();
        }catch (Exception e){
            Util.showToast(this, e.getMessage());
        }
    }
}
