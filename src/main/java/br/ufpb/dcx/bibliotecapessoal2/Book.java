package br.ufpb.dcx.bibliotecapessoal2;

import java.util.Objects;

public class Book{

    private String title;
    private String author;
    private int edition;
    private String category;

    public Book(String title, String author, int edition, String category){
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.category = category;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return edition == book.edition && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, edition);
    }

    public Book(){
        this ("","",0,"");
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setEdition(int edition) {
        this.edition = edition;
    }
    public int getEdition() {
        return this.edition;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return this.category;
    }
    public String toString(){
        return ("Título: "+this.title+
                ", Autor(a): "+this.author+
                ", "+this.edition+"ª Edição"+
                ", Categoria: "+this.category);
    }
}
