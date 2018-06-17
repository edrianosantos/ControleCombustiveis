package br.com.edrsantos.controlecombustivel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.edrsantos.controlecombustivel.R;
import br.com.edrsantos.controlecombustivel.model.Abastecimento;

/**
 * Created by slimv on 6/17/2018.
 */

public class AdapterAbastecimento extends BaseAdapter {

    Context context;
    List<Abastecimento> abastecimentoList;

    public AdapterAbastecimento(Context context, List<Abastecimento> abastecimentoList) {
        this.context = context;
        this.abastecimentoList = abastecimentoList;
    }

    @Override
    public int getCount() {
        return abastecimentoList.size();
    }

    @Override
    public Abastecimento getItem(int i) {
        return abastecimentoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return abastecimentoList.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder holder;

        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_abastecimento_layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        Abastecimento abastecimento = getItem(position);
        holder.txtCodigo.setText(String.valueOf(abastecimento.getCodigo()));
        holder.txtQuantidade.setText(String.valueOf(abastecimento.getQuantidade()));
        holder.txtCidade.setText(abastecimento.getCidade());

        return view;
    }

    class ViewHolder{

        final TextView txtCodigo;
        final TextView txtCidade;
        final TextView txtQuantidade;

        public ViewHolder(View view) {
            txtCodigo = view.findViewById(R.id.txtCodigoAbastecimento);
            txtCidade = view.findViewById(R.id.txtCidade);
            txtQuantidade = view.findViewById(R.id.txtQuantidade);
        }
    }

}
