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
import loovsoft.com.br.studytool.model.Materia;

public class HorarioAdapter  extends ArrayAdapter<Materia> {
    private ArrayList<Materia> listaMaterias;
    private Context context;

    public HorarioAdapter(@NonNull Context context, @NonNull ArrayList<Materia> objects) {
        super(context, 0, objects);
        this.context = context;
        this.listaMaterias = objects;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View visao = view;

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            visao = inflater.inflate(R.layout.lista_adapter, viewGroup, false);
        }



        return visao;
    }
}
