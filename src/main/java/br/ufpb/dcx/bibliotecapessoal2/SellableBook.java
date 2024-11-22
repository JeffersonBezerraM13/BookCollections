package br.ufpb.dcx.bibliotecapessoal2;

import java.util.Objects;

public class SellableBook extends Book {
    private int barcode;
    private double price;

    public SellableBook(int barcode, double price, String title, String author, int edition, String category){
        super(title, author, edition, category);
        this.barcode = barcode;
        this.price = price;
    }

    public SellableBook(){
        super("","",0,"");
        this.barcode = 0;
        this.price = 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SellableBook that = (SellableBook) o;
        return barcode == that.barcode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), barcode);
    }

    public void setBarcode(int barcode){
        this.barcode = barcode;
    }
    public int getBarcode(){
        return this.barcode;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return this.price;
    }
    public String toString(){
        return ("Código de barras: "+this.barcode+
                ", Preço: R$"+this.price+
                ", "+super.toString());

    }
}
