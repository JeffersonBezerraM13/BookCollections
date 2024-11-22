package br.ufpb.dcx.bibliotecapessoal2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

public class PersonalBook extends Book{
    private int personalCode;
    private String status;
    public static final String STATUS_TO_READ = "PARA LER";
    public static final String STATUS_READING = "LENDO";
    public static final String STATUS_READ = "LIDO";

    public PersonalBook(String title, String author, int edition, String category, String status){
        super(title, author, edition, category);
        this.personalCode = this.personalBookCode();
        this.status = status;
    }
    public PersonalBook(){
        this ("","",0,"","");
        this.status = "";
    }
    public int personalBookCode(){
        LocalDate dataAtual = LocalDate.now(); //Data atual
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyyMM"); //Formatado para ano e mes
        String anoMes = dataAtual.format(dataFormatada);

        Random random = new Random();
        int randomTeste = random.nextInt();
        int random50 = random.nextInt(10,60); //valores randomicos de 10 a 60 (50 variedades)
        String random50STR = String.valueOf(random50); //esse mesmo valor em Str
        return Integer.parseInt(anoMes + random50STR); //retorna o str (ex: 20241140) convertido para numero inteiro
    }
    public void setPersonalCode(int personalCode){
        this.personalCode = personalCode;
    }
    public int getPersonalCode() {
        return this.personalCode;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public String toString(){
        return (this.personalCode +", "
                +super.toString()+
                ", Status: "+this.status);
    }
}