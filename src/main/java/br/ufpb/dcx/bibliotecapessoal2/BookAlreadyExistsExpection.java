package br.ufpb.dcx.bibliotecapessoal2;

public class BookAlreadyExistsExpection extends Exception{
    public BookAlreadyExistsExpection(String msg){
        super(msg);
    }
    public BookAlreadyExistsExpection(){
        super();
    }
}
