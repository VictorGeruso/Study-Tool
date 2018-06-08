package loovsoft.com.br.studytool.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.model.Materia;
import loovsoft.com.br.studytool.model.Tarefa;

public class TarefasAdapter extends ArrayAdapter<Tarefa> {
    private ArrayList<Tarefa> listaTarefa;
    private Context context;

    public TarefasAdapter(@NonNull Context context, @NonNull ArrayList<Tarefa> objects) {
       super(context,0, objects);
        this.context = context;
        this.listaTarefa = objects;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View visao = view;

        if(view == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            visao = inflater.inflate(R.layout.tarefas_adapter, viewGroup, false);
        }

        TextView textoTarefa = visao.findViewById(R.id.tarefas_adapter_tarefas);
        CheckBox check = visao.findViewById(R.id.tarefas_adapter_check);

        final Tarefa tarefa = listaTarefa.get(i);

        textoTarefa.setText(tarefa.getTarefa());
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tarefa.setCheck(isChecked);

            }
        });

        return visao;
    }
}
