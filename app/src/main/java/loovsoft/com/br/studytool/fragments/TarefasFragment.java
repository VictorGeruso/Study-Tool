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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import loovsoft.com.br.studytool.BDHelper.BDHelper;
import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.adapters.TarefasAdapter;
import loovsoft.com.br.studytool.model.Materia;
import loovsoft.com.br.studytool.model.Tarefa;


public class TarefasFragment extends Fragment {

    private ArrayList<Tarefa> tarefasListaBD;
    private ListView listaTarefas;
    private ArrayAdapter adapterListaTarefas;
    private BDHelper bdHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bdHelper = new BDHelper(getContext());

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tarefas, container, false);

        tarefasListaBD = bdHelper.listarTarefa();
        bdHelper.close();

        adapterListaTarefas = new TarefasAdapter(getContext(),tarefasListaBD);

        listaTarefas = rootView.findViewById(R.id.fragmenttarefas_listview);

        listaTarefas.setAdapter(adapterListaTarefas);

        adapterListaTarefas.notifyDataSetChanged();

        listaTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                abrirInformacoesTarefa(position);
            }
        });

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

    private void abrirInformacoesTarefa(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_informacoes_tarefa, null);

        final TextView textoTarefa = view.findViewById(R.id.dialoglayout_info_tarefa);
        final Button btnOk = view.findViewById(R.id.dialoglayout_info_btnOk_tarefa);
        final Button btnRemover = view.findViewById(R.id.dialoglayout_info_btnRemover_tarefa);
        final Button btnEditar = view.findViewById(R.id.dialoglayout_info_btnEditar_tarefa);

        builder.setView(view);
        builder.setTitle("Informações Tarefa");

        final AlertDialog dialog = builder.create();

        Tarefa tarefaEscolhida = tarefasListaBD.get(position);

        textoTarefa.setText(tarefaEscolhida.getTarefa());

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerTarefa(position);
                dialog.dismiss();
                Toast.makeText(getContext(), "Tarefa Removida com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                abrirAlteracaoTarefa(position);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void abrirAlteracaoTarefa(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_alteracao_tarefa, null);

        final EditText textoTarefa = view.findViewById(R.id.dialoglayout_alterarTarefa);
        Button btnAlterar = view.findViewById(R.id.dialoglayout_btn_alterar_tarefa);
        Button btnCancelar = view.findViewById(R.id.dialoglayout_btnAlterar_cancelar_tarefa);

        builder.setView(view);
        builder.setTitle("Altera Tarefa");

        final AlertDialog dialog = builder.create();

        final Tarefa tarefaEscolhida = tarefasListaBD.get(position);

        textoTarefa.setText(tarefaEscolhida.getTarefa());

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = tarefaEscolhida.getId();
                String texto = textoTarefa.getText().toString();
                editarMateria(id,texto);
                dialog.dismiss();
                Toast.makeText(getContext(),"Tarefa Alterada com Sucesso!",Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void editarMateria(int id, String texto) {

        for (int i=0; i < tarefasListaBD.size(); i++){
            if (tarefasListaBD.get(i).getId() == id){
                Tarefa tarefa = new Tarefa(id,texto);
                tarefasListaBD.get(i).setTarefa(texto);
                bdHelper.alterarTarefa(tarefa);
                bdHelper.close();
                adapterListaTarefas.notifyDataSetChanged();
            }
        }
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
        tarefasListaBD.add(tarefa);
        bdHelper.cadastrarTarefa(tarefa);
        bdHelper.close();
        adapterListaTarefas.notifyDataSetChanged();

    }

    private void removerTarefa(int position){
        Tarefa tarefa = tarefasListaBD.get(position);
        tarefasListaBD.remove(tarefa);
        bdHelper.deletarTarefa(tarefa);
        bdHelper.close();
        adapterListaTarefas.notifyDataSetChanged();
    }

}
