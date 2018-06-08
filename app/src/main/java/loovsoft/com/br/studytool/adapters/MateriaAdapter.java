package loovsoft.com.br.studytool.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.model.Materia;

public class MateriaAdapter extends ArrayAdapter<Materia> {

    private ArrayList<Materia> listaMaterias;
    private Context context;

    public MateriaAdapter(@NonNull Context context, @NonNull ArrayList<Materia> objects) {
        super(context, 0, objects);
        this.context = context;
        this.listaMaterias = objects;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View visao = view;

        if(view == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            visao = inflater.inflate(R.layout.lista_adapter, viewGroup, false);
        }

        TextView nomeMateria = visao.findViewById(R.id.lista_adapter_nomeMateria);
        TextView nomeProfessor = visao.findViewById(R.id.lista_adapter_nomeProfessor);

        Materia materia = listaMaterias.get(i);

        nomeMateria.setText(materia.getNome());
        nomeProfessor.setText(materia.getProfessor());

        return visao;
    }
}
