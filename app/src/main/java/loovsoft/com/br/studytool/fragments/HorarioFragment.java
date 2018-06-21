package loovsoft.com.br.studytool.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import loovsoft.com.br.studytool.BDHelper.BDHelper;
import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.adapters.HorarioAdapter;
import loovsoft.com.br.studytool.model.Materia;

public class HorarioFragment extends Fragment {

    private ArrayList<Materia> materiaListaBd;
    private ListView listaMaterias;
    private ArrayAdapter adapterListaMaterias;
    private BDHelper BDHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_horario, container, false);

        BDHelper = new BDHelper(getContext());

        materiaListaBd = BDHelper.listarMateria();
        BDHelper.close();

        adapterListaMaterias = new HorarioAdapter(getContext(), materiaListaBd);

        listaMaterias = rootView.findViewById(R.id.fragmenthorario_listview);

        listaMaterias.setAdapter(adapterListaMaterias);

        adapterListaMaterias.notifyDataSetChanged();

        return rootView;
    }

}
