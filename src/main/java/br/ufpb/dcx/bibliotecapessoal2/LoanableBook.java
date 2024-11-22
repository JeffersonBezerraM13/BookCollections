package br.ufpb.dcx.bibliotecapessoal2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LoanableBook extends Book{
    private String loanNameAndStatus;
    private String date;
    public static final String LOANED_TO_ANYONE = "Emprestado a ninguém";
    public LoanableBook(String title, String author, int edition, String cateogory){
        super(title, author, edition, cateogory);
        this.loanNameAndStatus = LOANED_TO_ANYONE;
        LocalDate dataAtual = LocalDate.now(); //Data atual
        DateTimeFormatter dia = DateTimeFormatter.ofPattern("dd"); //Formatado para ano e mes
        DateTimeFormatter mes = DateTimeFormatter.ofPattern("MM"); //Formatado para ano e mes
        DateTimeFormatter ano = DateTimeFormatter.ofPattern("yyyy"); //Formatado para ano e mes
        String diaStr = dataAtual.format(dia);
        String mesStr = dataAtual.format(mes);
        String anoStr = dataAtual.format(ano);
        this.date = diaStr+"/"+mesStr+"/"+anoStr;
    }
    public LoanableBook(){
        super("","",0,"");
        this.loanNameAndStatus = LOANED_TO_ANYONE;
        this.date = "";
    }
    public void setloanNameAndStatus(String loanNameAndStatus){
        this.loanNameAndStatus = loanNameAndStatus;
    }
    public String getLoanableStatus(){
        return this.loanNameAndStatus;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public String toString(){
        return ("Status emprestismo: "+this.loanNameAndStatus+
                ", Data do emprestimo: " +this.date+
                " ,"+super.toString());
    }
}
