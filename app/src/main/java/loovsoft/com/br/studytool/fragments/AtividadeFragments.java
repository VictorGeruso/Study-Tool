package loovsoft.com.br.studytool.fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import loovsoft.com.br.studytool.BDHelper.MateriasBD;
import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.adapters.AtividadeAdapter;
import loovsoft.com.br.studytool.model.Atividade;

public class AtividadeFragments extends Fragment {

    private ArrayList<Atividade> atividadeListaDb;
    private ListView listaAtvidades;
    private ArrayAdapter adapterListAtividade;
    //private MateriasDB atividadesDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //atividadesDB = new MateriasBD(getContext());

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_atividade, container, false);

        //atividadeListaDb = atividadesDB.listar();
        //atividadesDB.close();

        adapterListAtividade = new AtividadeAdapter(getContext(), atividadeListaDb);

        listaAtvidades.setAdapter(adapterListAtividade);

        adapterListAtividade.notifyDataSetChanged();

        listaAtvidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                abrirInformacoesAtividade(position);
            }
        });

        final FloatingActionButton actionButton = rootView.findViewById(R.id.fragmentatv_floatinbutton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == actionButton) {
                    abrirCadastrarAtividade();
                }
            }
        });

        return rootView;
    }

    private void abrirInformacoesAtividade(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_informacoes, null);

        final TextView assuntoAtividade = view.findViewById(R.id.dialoglayout_atv_AssuntoAtividade);
        final TextView nomeMateria = view.findViewById(R.id.dialoglayout_atv_nomeMateria);
        final TextView dataAtividade = view.findViewById(R.id.dialoglayout_atv_DataAtividade);
    }

    private void abrirCadastrarAtividade() {

    }

}
