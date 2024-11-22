package br.ufpb.dcx.bibliotecapessoal2;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class BookRecorder {
    public static final String FILE_NAME_PERSONAL_BOOK = "data\\personalBooks.txt";
    public static final String FILE_NAME_LOANABLE_BOOK = "data\\loanableBooks.txt";
    public static final String FILE_NAME_SELLABLE_BOOK = "data\\sellableBooks.txt";

    public void recordPBs(List<PersonalBook> PBToRecord) throws IOException{
        List<String> pbBooksText = new ArrayList<>();
        for(PersonalBook pb: PBToRecord){
            String readedText = pb.getPersonalCode()+"###"+
                    pb.getTitle()+"###"+
                    pb.getAuthor()+"###"+
                    pb.getEdition()+"###"+
                    pb.getCategory()+"###"+
                    pb.getStatus();
            pbBooksText.add(readedText);
        }
        this.recordPBText(pbBooksText);
    }
    public void recordLBs(List<LoanableBook> LBToRecord) throws IOException{
        List<String> lbBooksText = new ArrayList<>();
        for(LoanableBook lb: LBToRecord){
            String readedText = lb.getLoanableStatus()+"###"+
                    lb.getDate()+"###"+
                    lb.getTitle()+"###"+
                    lb.getAuthor()+"###"+
                    lb.getEdition()+"###"+
                    lb.getCategory();
            lbBooksText.add(readedText);
        }
        this.recordLBText(lbBooksText);
    }
    public void recordSBs(List<SellableBook> SBToRecord) throws IOException{
        List<String> sbBooksText = new  ArrayList<>();
        for(SellableBook sb: SBToRecord){
            String readedText = sb.getBarcode()+"###"+
                    sb.getPrice()+"###"+
                    sb.getTitle()+"###"+
                    sb.getAuthor()+"###"+
                    sb.getEdition()+"###"+
                    sb.getCategory();
            sbBooksText.add(readedText);
        }
        this.recordSBText(sbBooksText);
    }

    public List<PersonalBook> recoverPBs() throws IOException{
        List<PersonalBook> recoverdPBs = new ArrayList<>();
        List<String> recoveredTextFromFile = this.recoverPBTextFromFile();
        for(String lineReaded: recoveredTextFromFile){
            PersonalBook pb = new PersonalBook();
            String [] data = lineReaded.split("###");
            pb.setPersonalCode(Integer.parseInt(data[0])); //Personal code
            pb.setTitle(data[1]);
            pb.setAuthor(data[2]);
            pb.setEdition(Integer.parseInt(data[3]));
            pb.setCategory(data[4]);
            pb.setStatus(data[5]);
            recoverdPBs.add(pb);
        }
        return recoverdPBs;
    }
    public List<LoanableBook> recoverLBs() throws IOException{
        List<LoanableBook> recoveredLbs = new ArrayList<>();
        List<String> recoverdTextFromFile = this.recoverLBTextFromFile();
        for(String lineReaded: recoverdTextFromFile){
            LoanableBook lb = new LoanableBook();
            String [] data = lineReaded.split("###");
            lb.setloanNameAndStatus(data[0]);
            lb.setDate(data[1]);
            lb.setTitle(data[2]);
            lb.setAuthor(data[3]);
            lb.setEdition(Integer.parseInt(data[4]));
            lb.setCategory(data[5]);
            recoveredLbs.add(lb);
        }
        return recoveredLbs;
    }
    public List<SellableBook> recoverSBs() throws IOException{
        List<SellableBook> recoverdSBs = new ArrayList<>();
        List<String> recoveredTextFromFile = this.recoverSBTextFromFile();
        for(String lineReaded: recoveredTextFromFile){
            SellableBook sb = new SellableBook();
            String [] data = lineReaded.split("###");
            sb.setBarcode(Integer.parseInt(data[0]));
            sb.setPrice(Double.parseDouble(data[1]));
            sb.setTitle(data[2]);
            sb.setAuthor(data[3]);
            sb.setEdition(Integer.parseInt(data[4]));
            sb.setCategory(data[5]);
        }
        return recoverdSBs;
    }
    public void recordPBText(List<String> textRecived) throws IOException{
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME_PERSONAL_BOOK));
            for(String str: textRecived){
                writer.write(str+"\n");
            }
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
    public void recordLBText(List<String> textRecived) throws IOException{
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME_LOANABLE_BOOK));
            for(String str: textRecived){
                writer.write(str+"\n");
            }
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
    public void recordSBText(List<String> textRecived) throws IOException{
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME_SELLABLE_BOOK));
            for(String str: textRecived){
                writer.write(str+"\n");
            }
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
    public List<String> recoverPBTextFromFile() throws IOException{
        List<String> readedTexts = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME_PERSONAL_BOOK));
            String text = null;
            do{
                text = reader.readLine();
                if(text != null){
                    readedTexts.add(text);
                }
            } while (text != null);
        } finally {
            if(reader != null){
                reader.close();
            }
        }
        return readedTexts;
    }
    public List<String> recoverLBTextFromFile() throws IOException{
        List<String> readedTexts = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME_LOANABLE_BOOK));
            String text = null;
            do{
                text = reader.readLine();
                if(text != null){
                    readedTexts.add(text);
                }
            } while (text != null);
        } finally {
            if(reader != null){
                reader.close();
            }
        }
        return readedTexts;
    }
    public List<String> recoverSBTextFromFile() throws IOException{
        List<String> readedTexts = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME_SELLABLE_BOOK));
            String text = null;
            do{
                text = reader.readLine();
                if(text != null){
                    readedTexts.add(text);
                }
            } while (text != null);
        } finally {
            if(reader != null){
                reader.close();
            }
        }
        return readedTexts;
    }
}
