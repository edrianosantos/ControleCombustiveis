package br.com.edrsantos.controlecombustivel.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.edrsantos.controlecombustivel.R;
import br.com.edrsantos.controlecombustivel.adapter.AdapterCombustivel;
import br.com.edrsantos.controlecombustivel.dao.CombustivelDAO;
import br.com.edrsantos.controlecombustivel.model.Combustivel;

public class CombustiveisActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private CombustivelDAO dao;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        listView = findViewById(R.id.lisViewPesquisa);
    }

    @Override
    protected void onStart() {
        super.onStart();
        iniciaLista(true);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    private void iniciaLista(boolean orderbyAsc){
        dao = new CombustivelDAO(this);
        List<Combustivel> list;
        if(orderbyAsc)
            list = dao.list();
        else
            list = dao.listDesc();
        AdapterCombustivel adapterCombustivel = new AdapterCombustivel(this, list);
        listView.setAdapter(adapterCombustivel);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
        Combustivel combustivel = dao.findById(id);

        Bundle bundle = new Bundle();
        bundle.putString("codigo", String.valueOf(combustivel.getCodigo()));
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
        showPopupMenu(view,id);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_combutivel, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuOrdemAsc:
                iniciaLista(true);
                break;

            case R.id.menuOrdemDesc:
                iniciaLista(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showPopupMenu(View view, final long id){
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.menu_popup, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menuEditar:
                        Combustivel combustivel = dao.findById(id);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("combustivel", combustivel);

                        Intent intent = new Intent(CombustiveisActivity.this, CadastroCombustivelActivity.class);
                        intent.putExtras(bundle);

                        startActivity(intent);
                        break;

                    case R.id.menuDeletar:
                        deletar(id);
                        break;
                }

                return true;
            }
        });
        popup.show();
    }

    private void deletar(final long id){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletar");
        builder.setMessage("Tem certeza que deseja deletar este item?");
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(id);
                iniciaLista(true);
            }
        });

        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

}
