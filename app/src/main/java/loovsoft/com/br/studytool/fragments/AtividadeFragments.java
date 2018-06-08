package loovsoft.com.br.studytool.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import loovsoft.com.br.studytool.BDHelper.MateriasBD;
import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.adapters.AtividadeAdapter;
import loovsoft.com.br.studytool.model.Atividade;
import loovsoft.com.br.studytool.model.Materia;

public class AtividadeFragments extends Fragment {

    private ArrayList<Atividade> atividadeListaDb;
    private ListView listaAtvidades;
    private ArrayAdapter adapterListAtividade;
    //private BDHelper bdHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //bdHelper = new BDHelper(getContext());

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_atividade, container, false);

        //atividadeListaDb = bdHelper.listar();
        //bdHelper.close();

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
        final TextView dataAtividade = view.findViewById(R.id.dialoglayout_atv_DataAtividade);
        Button btnOk = view.findViewById(R.id.dialoglayout_atv_info_btn_ok);
        Button btnEditar = view.findViewById(R.id.dialoglayout_atv_info_btn_editar);
        Button btnRemover = view.findViewById(R.id.dialoglayout_atv_info_btn_remover);

        builder.setView(view);
        builder.setTitle("Informações Atividade");

        final AlertDialog dialog = builder.create();

        Atividade atividadeEscolhida = atividadeListaDb.get(position);

        assuntoAtividade.setText(atividadeEscolhida.getAssunto());
        dataAtividade.setText(atividadeEscolhida.getData());

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerAtividade(position);
                dialog.dismiss();
                Toast.makeText(getContext(), "Atividade removida com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                abrirAlteracaoAtividade(position);
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

    private void abrirAlteracaoAtividade(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_atv_alteracao, null);

        final EditText assuntoAtividade = view.findViewById(R.id.dialoglayout_atv_alterarAssuntoAtividade);
        final EditText dataAtividade = view.findViewById(R.id.dialoglayout_atv_alterarDataAtividade);
        Button btnAlterar = view.findViewById(R.id.dialoglayout_atv_btn_alterar);
        Button btnCancelar = view.findViewById(R.id.dialoglayout_atv_btnAlterar_cancelar);

        builder.setView(view);
        builder.setTitle("Alterar Atividade");

        final AlertDialog dialog = builder.create();

        final Atividade atividadeEscolhida = atividadeListaDb.get(position);

        assuntoAtividade.setText(atividadeEscolhida.getAssunto());
        dataAtividade.setText(atividadeEscolhida.getData());

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = atividadeEscolhida.getId();
                String assunto = assuntoAtividade.getText().toString();
                String data = dataAtividade.getText().toString();

                editarAtividade(id, assunto, data);
                dialog.dismiss();
                Toast.makeText(getContext(), "Atividade alterada com sucesso!", Toast.LENGTH_SHORT).show();
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

    private void editarAtividade(int id, String assunto, String data) {
        for(int i=0; i < atividadeListaDb.size(); i++) {
            if(atividadeListaDb.get(i).getId() == id) {
                Atividade atividade = new Atividade(id, assunto, data);
                atividadeListaDb.get(i).setAssunto(assunto);
                atividadeListaDb.get(i).setData(data);
                //bdHelper.alterarAtividade(atividade);
                //bdHelper.close();
                adapterListAtividade.notifyDataSetChanged();
            }
        }
    }

    private void removerAtividade(int position) {
        Atividade atividade = atividadeListaDb.get(position);
        atividadeListaDb.remove(atividade);
        //bdHelper.deletarAtividade(atividade);
        //bdHelper.close();
        adapterListAtividade.notifyDataSetChanged();
    }

    private void abrirCadastrarAtividade() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_atv_cadastro, null);

        final EditText assuntoAtividade = view.findViewById(R.id.dialoglayout_atv_assuntoAtividade);
        final EditText dataAtividade = view.findViewById(R.id.dialoglayout_atv_DataAtividade);
        Button btnCadastrar = view.findViewById(R.id.dialoglayout_atv_cadastrar);
        Button btnCancelar = view.findViewById(R.id.dialoglayout_atv_cancelar);

        builder.setView(view);
        builder.setTitle("Cadastrar atividade");

        final AlertDialog dialog = builder.create();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCadastro(assuntoAtividade, dataAtividade);
                dialog.dismiss();
                Toast.makeText(getContext(), "Atividade cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
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

    private void validarCadastro(EditText assuntoAtividade, EditText dataAtividade) {
        String assunto = assuntoAtividade.getText().toString();
        String data = dataAtividade.getText().toString();

        if(assunto.isEmpty() || data.isEmpty()) {
            assuntoAtividade.setError("Insira um assunto para a atividade");
            dataAtividade.setError("insira uma data para a atividade");
        } else {
            cadastrarAtividade(assunto, data);
        }
    }

    private void cadastrarAtividade(String assunto, String data) {
        Atividade atividade = new Atividade(assunto, data);
        atividadeListaDb.add(atividade);
        //bdHelper.cadastrarAtividade(atividade);
        //bdHelper.close();
        adapterListAtividade.notifyDataSetChanged();
    }

}
