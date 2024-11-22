package br.ufpb.dcx.bibliotecapessoal2;

public class BookDoesNotExistException extends Exception{
    public BookDoesNotExistException(String msg){
        super(msg);
    }
    public BookDoesNotExistException(){
        super();
    }
}
