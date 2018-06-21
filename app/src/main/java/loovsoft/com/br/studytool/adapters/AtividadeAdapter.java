package loovsoft.com.br.studytool.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.model.Atividade;

public class AtividadeAdapter extends ArrayAdapter<Atividade> {

    private ArrayList<Atividade> listaAtividade;
    private Context context;


    public AtividadeAdapter(@NonNull Context context, @NonNull ArrayList<Atividade> objects) {
        super(context, 0,objects);
        this.context = context;
        this.listaAtividade = objects;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View visao = view;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            visao = inflater.inflate(R.layout.atividade_adapter, viewGroup, false);
        }

        TextView nomeAtivdade = visao.findViewById(R.id.atividade_adapter_nomeAtividade);
        TextView nomeMateria = visao.findViewById(R.id.atividade_adapter_nomeMateria);
        TextView data = visao.findViewById(R.id.atividade_adapter_data);

        Atividade atividade = listaAtividade.get(i);

        nomeAtivdade.setText(atividade.getNome());
        nomeMateria.setText(atividade.getMateria());
        data.setText(atividade.getData());

        return visao;
    }

}
