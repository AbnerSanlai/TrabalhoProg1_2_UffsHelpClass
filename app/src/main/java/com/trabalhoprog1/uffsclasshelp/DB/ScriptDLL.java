package com.trabalhoprog1.uffsclasshelp.DB;

public class ScriptDLL {

    public static String getCriarTabelaClasse() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE CLASS ( " );
        sql.append("        CLAS_ID         INTEGER      PRIMARY KEY AUTOINCREMENT " );
        sql.append("        UNIQUE" );
        sql.append("        NOT NULL," );
        sql.append("        CLAS_DATA_HORA  DATETIME," );
        sql.append("        CLAS_CONTEUDO   TEXT," );
        sql.append("        CLAS_SALA       VARCHAR (10)," );
        sql.append("        CLAS_BLOCO      VARCHAR (10)," );
        sql.append("        CLAS_CODI_TUTOR INTEGER      NOT NULL," );
        sql.append("        CLAS_CRIADO_POR INTEGER      NOT NULL" );
        sql.append(" );" );

        return sql.toString();

    }
}
