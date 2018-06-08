package loovsoft.com.br.studytool.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.model.Atividade;

public class AtividadeAdapter extends ArrayAdapter<Atividade> {

    private ArrayList<Atividade> listaAtividades;
    private Context context;

    public AtividadeAdapter(@NonNull Context context, @NonNull ArrayList<Atividade> objects) {
        super(context, 0, objects);
        this.context = context;
        this.listaAtividades = objects;
    }

    @NonNull
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View visao = view;
        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            visao = inflater.inflate(R.layout.lista_adapter, viewGroup, false);
        }

        TextView assuntoAtividade = visao.findViewById(R.id.lista_atividade_adapter_assuntoAtividade);
        TextView nomeMateria = visao.findViewById(R.id.lista_atividade_adapter_nomeMateria);
        TextView dataAtividade = visao.findViewById(R.id.lista_atividade_adapter_dataAtividade);

        Atividade atividade = listaAtividades.get(i);

        assuntoAtividade.setText(atividade.getAssunto());
        nomeMateria.setText(atividade.getMateria().getNome());
        dataAtividade.setText(atividade.getData().toString());

        return visao;
    }
}
