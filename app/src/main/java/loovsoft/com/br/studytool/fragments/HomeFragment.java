package loovsoft.com.br.studytool.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import loovsoft.com.br.studytool.R;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        final FloatingActionButton actionButton = rootView.findViewById(R.id.fragmenthome_floatinbutton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == actionButton) {

                    abrirCadastroMateria();

                }

            }
        });

        return rootView;
    }

    private void abrirCadastroMateria() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_layout_cadastro ,null);

        Button btnCadastrar = view.findViewById(R.id.dialoglayout_cadastrar);
        Button btnCancelar = view.findViewById(R.id.dialoglayout_cancelar);
        builder.setView(view);



        builder.setTitle("Cadastrar matéria");

        final AlertDialog dialog = builder.create();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //CHAMA METODO CADASTRAR

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
}
