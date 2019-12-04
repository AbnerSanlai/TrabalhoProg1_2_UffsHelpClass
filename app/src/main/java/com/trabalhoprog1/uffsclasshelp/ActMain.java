package com.trabalhoprog1.uffsclasshelp;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trabalhoprog1.uffsclasshelp.DB.DBHELPCLASS;

public class ActMain extends AppCompatActivity {


    private FloatingActionButton fab;
    private SQLiteDatabase conexao;
    private DBHELPCLASS dbhelpclass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);

        criarConexao();
    }

    private void criarConexao() {
        try {
            dbhelpclass = new DBHELPCLASS(this);
            conexao = dbhelpclass.getWritableDatabase();
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro" );
            dlg.setMessage("Deu Certo");
            dlg.setNeutralButton("Ok", null);
            dlg.show();
            //Snackbar.make(layoutContatMain, "Conexao Criada com Sucesso", Snackbar.LENGTH_SHORT).setAction("OK", null).show();

        } catch (SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro" );
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();

        }
    }

    public void CadastrarClasse(View view) {
        Intent it = new Intent(ActMain.this, ActCadastroTurma.class);
        startActivity(it);
    }
}
