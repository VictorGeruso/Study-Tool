package loovsoft.com.br.studytool.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.adapters.TarefasAdapter;
import loovsoft.com.br.studytool.model.Tarefa;


public class TarefasFragment extends Fragment {

    private ArrayList<Tarefa> tarefasLista = new ArrayList<>();
    private ListView listaTarefas;
    private ArrayAdapter adapterListaTarefas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tarefas, container, false);

        adapterListaTarefas = new TarefasAdapter(getContext(),tarefasLista);

        listaTarefas = rootView.findViewById(R.id.fragmenttarefas_listview);

        listaTarefas.setAdapter(adapterListaTarefas);

        adapterListaTarefas.notifyDataSetChanged();

        final FloatingActionButton actionButton = rootView.findViewById(R.id.fragmenttarefas_floatinbutton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == actionButton) {
                    abrirAdicionarTarefa();
                }
            }
        });

        return rootView;
    }

    private void abrirAdicionarTarefa() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_adicionar_tarefa ,null);

       final EditText textoTarefa = view.findViewById(R.id.dialoglayout_tarefa_textoTarefa);
       final Button btnAdicionar = view.findViewById(R.id.dialoglayout_tarefa_adicionar);
       final Button btnCancelar = view.findViewById(R.id.dialoglayout_tarefa_cancelar);

        builder.setView(view);
        builder.setTitle("Adicionar Tarefa");

        final AlertDialog dialog = builder.create();

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               validarTarefa(textoTarefa);
                dialog.dismiss();
                Toast.makeText(getContext(), "Tarefa Adicionada com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void validarTarefa(EditText textoTarefa) {
        String texto = textoTarefa.getText().toString();
        if (texto.isEmpty()){
            textoTarefa.setError("Insira uma tarefa");
        } else {
            adicionarTarefa(texto);
        }
    }

    private void adicionarTarefa(String textoTarefa) {
        Tarefa tarefa = new Tarefa(textoTarefa);
        tarefasLista.add(tarefa);
        adapterListaTarefas.notifyDataSetChanged();

    }

}
