package br.com.edrsantos.controlecombustivel.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.edrsantos.controlecombustivel.R;
import br.com.edrsantos.controlecombustivel.dao.CombustivelDAO;
import br.com.edrsantos.controlecombustivel.model.Combustivel;
import br.com.edrsantos.controlecombustivel.util.Util;

public class CadastroCombustivelActivity extends AppCompatActivity {

    EditText edtCodigo;
    EditText edtDescricao;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtCodigo = findViewById(R.id.edtCodigo);
        edtDescricao = findViewById(R.id.edtDescricao);

        bundle = getIntent().getExtras();
        if(bundle != null){
            Combustivel combustivel = (Combustivel) bundle.get("combustivel");
            edtCodigo.setText(String.valueOf(combustivel.getCodigo()));
            edtDescricao.setText(combustivel.getDescricao());
        }

        Button btnCadastro = findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void salvar() {
        if(edtCodigo.getText().toString().isEmpty()){
            edtCodigo.setError("Esse campo não pode ser vazio");
            return;
        }

        if(edtDescricao.getText().toString().isEmpty()){
            edtDescricao.setError("Esse campo não pode ser vazio");
            return;
        }

        Combustivel combustivel = new Combustivel();
        combustivel.setCodigo(Integer.parseInt(edtCodigo.getText().toString()));
        combustivel.setDescricao(edtDescricao.getText().toString());

        try {
            CombustivelDAO dao = new CombustivelDAO(this);
            if(bundle != null && bundle.get("combustivel") != null){
                combustivel.setId(((Combustivel)bundle.get("combustivel")).getId());
                dao.update(combustivel);
            }else{
                if(dao.findByCodigo(combustivel.getCodigo()) != null){
                    Util.showToast(this, "Esse código já está cadastrado");
                    return;
                }
                dao.save(combustivel);
            }

            Util.showToast(this, "Salvo com sucesso!");
            finish();
        }catch (Exception e){
            Util.showToast(this, e.getMessage());
        }
    }
}
