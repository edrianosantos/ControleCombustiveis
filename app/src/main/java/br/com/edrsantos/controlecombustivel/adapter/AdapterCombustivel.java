package br.com.edrsantos.controlecombustivel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.edrsantos.controlecombustivel.R;
import br.com.edrsantos.controlecombustivel.model.Combustivel;

/**
 * Created by slimv on 6/17/2018.
 */

public class AdapterCombustivel extends BaseAdapter {

    Context context;
    List<Combustivel> combustivelList;

    public AdapterCombustivel(Context context, List<Combustivel> combustivelList) {
        this.context = context;
        this.combustivelList = combustivelList;
    }

    @Override
    public int getCount() {
        return combustivelList.size();
    }

    @Override
    public Combustivel getItem(int i) {
        return combustivelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return combustivelList.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder holder;

        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_combustivel_layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        Combustivel combustivel = getItem(position);
        holder.txtCodigo.setText(String.valueOf(combustivel.getCodigo()));
        holder.txtDescricao.setText(combustivel.getDescricao());

        return view;
    }

    class ViewHolder{

        final TextView txtCodigo;
        final TextView txtDescricao;

        public ViewHolder(View view) {
            txtCodigo = view.findViewById(R.id.txtCodigoCombustivel);
            txtDescricao = view.findViewById(R.id.txtDescricao);
        }
    }

}
