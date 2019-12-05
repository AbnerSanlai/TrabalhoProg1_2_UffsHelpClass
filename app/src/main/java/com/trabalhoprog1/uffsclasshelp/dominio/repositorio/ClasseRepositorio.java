package com.trabalhoprog1.uffsclasshelp.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trabalhoprog1.uffsclasshelp.dominio.entidade.Turma;

import java.util.ArrayList;
import java.util.List;

public class ClasseRepositorio {

    private SQLiteDatabase conexao;

    public ClasseRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Turma turma) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("CLAS_DATA_HORA", turma.CLAS_DATA_HORA);
        contentValues.put("CLAS_BLOCO", turma.CLAS_BLOCO);
        contentValues.put("CLAS_CODI_TUTOR", turma.CLAS_CODI_TUTOR);
        contentValues.put("CLAS_CONTEUDO", turma.CLAS_CONTEUDO);
        contentValues.put("CLAS_CRIADO_POR", turma.CLAS_CRIADO_POR);
        contentValues.put("CLAS_SALA", turma.CLAS_SALA);

        conexao.insertOrThrow("CLASS", null, contentValues);

    }

    public void excluir(int codigo) {


        String[] parametrosex = new String[1];
        parametrosex[0] = String.valueOf(codigo);

        conexao.delete("CLASS", "CLAS_ID = ?", parametrosex);


    }

    public void alterar(Turma turma) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("CLAS_DATA_HORA", turma.CLAS_DATA_HORA);
        contentValues.put("CLAS_BLOCO", turma.CLAS_BLOCO);
        contentValues.put("CLAS_CODI_TUTOR", turma.CLAS_CODI_TUTOR);
        contentValues.put("CLAS_CONTEUDO", turma.CLAS_CONTEUDO);
        contentValues.put("CLAS_CRIADO_POR", turma.CLAS_CRIADO_POR);
        contentValues.put("CLAS_SALA", turma.CLAS_SALA);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(turma.CLAS_ID);

        conexao.update("CLASS", contentValues, "CLAS_ID = ?", parametros);
    }

    public List<Turma> buscarTodos() {

        List<Turma> turma = new ArrayList<Turma>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM CLASS WHERE CLAS_ID = ?" );

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0) {
            resultado.moveToFirst();
            do {
                Turma cla = new Turma();
                cla.CLAS_BLOCO = resultado.getString(resultado.getColumnIndexOrThrow("CLAS_BLOCO" ));
                cla.CLAS_CODI_TUTOR = resultado.getInt(resultado.getColumnIndexOrThrow("CLAS_CODI_TUTOR" ));
                cla.CLAS_CONTEUDO = resultado.getString(resultado.getColumnIndexOrThrow("CLAS_CONTEUDO" ));
                cla.CLAS_CRIADO_POR = resultado.getInt(resultado.getColumnIndexOrThrow("CLAS_CRIADO_POR" ));
                cla.CLAS_DATA_HORA = resultado.getString(resultado.getColumnIndexOrThrow("CLAS_DATA_HORA" ));
                cla.CLAS_SALA = resultado.getString(resultado.getColumnIndexOrThrow("CLAS_SALA" ));
                cla.CLAS_ID = resultado.getInt(resultado.getColumnIndexOrThrow("CLAS_ID" ));
            } while (resultado.moveToNext());

        }


        return turma;
    }

    public Turma buscarCliente(int codigo) {
        Turma classe = new Turma();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM CLASS WHERE CLAS_ID = ?" );

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            classe.CLAS_BLOCO = resultado.getString(resultado.getColumnIndexOrThrow("CLAS_BLOCO" ));
            classe.CLAS_CODI_TUTOR = resultado.getInt(resultado.getColumnIndexOrThrow("CLAS_CODI_TUTOR" ));
            classe.CLAS_CONTEUDO = resultado.getString(resultado.getColumnIndexOrThrow("CLAS_CONTEUDO" ));
            classe.CLAS_CRIADO_POR = resultado.getInt(resultado.getColumnIndexOrThrow("CLAS_CRIADO_POR" ));
            classe.CLAS_DATA_HORA = resultado.getString(resultado.getColumnIndexOrThrow("CLAS_DATA_HORA" ));
            classe.CLAS_SALA = resultado.getString(resultado.getColumnIndexOrThrow("CLAS_SALA" ));
            classe.CLAS_ID = resultado.getInt(resultado.getColumnIndexOrThrow("CLAS_ID" ));

            return  classe;
        }


        return null;

    }
}
