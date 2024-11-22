package br.ufpb.dcx.bibliotecapessoal2;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private List<PersonalBook> personalBooks;
    private List<Integer> personalCodes;
    private List<LoanableBook> loanableBooks;
    private List<SellableBook> sellableBooks;
    private BookRecorder recorder;

    public LibrarySystem() {
        this.personalBooks = new ArrayList<>();
        this.personalCodes = new ArrayList<>();
        this.loanableBooks = new ArrayList<>();
        this.sellableBooks = new ArrayList<>();
        this.recorder = new BookRecorder();
    }
    public List<Integer> allPersonalCodes(){
        return this.personalCodes;
    }

    public void registerBook(PersonalBook personalBook) throws BookAlreadyExistsExpection{
        if(this.existPersonalBook(personalBook)){
            throw new BookAlreadyExistsExpection(personalBook.toString());
        }
        this.personalBooks.add(personalBook);
        int code = personalBook.getPersonalCode();
        this.personalCodes.add(code);
        int cont = 0;
        for(Integer i: this.allPersonalCodes()){
            if(i == personalBook.getPersonalCode()){
                cont++;
            }
        }
        if(cont > 1){
            int codeRandom = personalBook.personalBookCode();
            personalBook.setPersonalCode(codeRandom);
        }
    }
    public boolean existPersonalBook(PersonalBook personalBook){
        for(PersonalBook pb: this.personalBooks){
            if(pb.equals(personalBook)){
                return true;
            }
        }
        return false;
    }

    public List<PersonalBook> allPersonalBooks() {
        return this.personalBooks;
    }

    public List<PersonalBook> allPersonalBooksByCategory(String category) {
        List<PersonalBook> booksByCategory = new ArrayList<>();
        for (PersonalBook pb : this.personalBooks) {
            if (pb.getCategory().equalsIgnoreCase(category)) {
                booksByCategory.add(pb);
            }
        }
        return booksByCategory;
    }

    public List<PersonalBook> allPersonalBooksByStatus(String status) {
        List<PersonalBook> booksByStatus = new ArrayList<>();
        for (PersonalBook pb : this.personalBooks) {
            if (pb.getStatus().equals(status)) {
                booksByStatus.add(pb);
            }
        }
        return booksByStatus;
    }

    public List<PersonalBook> allPersonalBooksByAuthor(String author) {
        List<PersonalBook> booksByAythor = new ArrayList<>();
        for (PersonalBook pb : this.personalBooks) {
            if (pb.getAuthor().equalsIgnoreCase(author)) {
                booksByAythor.add(pb);
            }
        }
        return booksByAythor;
    }

    public List<PersonalBook> allPersonalBooksByStatusAndCategory(String status, String category) {
        List<PersonalBook> booksByStatusAndCategory = new ArrayList<>();
        for (PersonalBook pb : this.personalBooks) {
            if (pb.getStatus().equals(status) && pb.getCategory().equalsIgnoreCase(category)) {
                booksByStatusAndCategory.add(pb);
            }
        }
        return booksByStatusAndCategory;
    }

    public PersonalBook personalBookByCode(int code) throws BookDoesNotExistException { //todo: lançar exception
        for (PersonalBook pb : this.personalBooks) {
            if (pb.getPersonalCode() == code) {
                return pb;
            }
        }
        throw new BookDoesNotExistException();
    }

    public void personalToLoanableBook(PersonalBook book) {
        LoanableBook loanableBook = new LoanableBook(book.getTitle(), book.getAuthor(), book.getEdition(), book.getCategory());
        this.loanableBooks.add(loanableBook);
        this.personalBooks.remove(book);
    }

    public void personalBookToSellable(int barcode, double price, PersonalBook book) {
        SellableBook sellableBook = new SellableBook(barcode, price, book.getTitle(), book.getAuthor(), book.getEdition(), book.getCategory());
        this.sellableBooks.add(sellableBook);
        this.personalBooks.remove(book);
    }
    public PersonalBook deletePersonalBook(int personalCode) throws BookDoesNotExistException{
        for (PersonalBook pb : this.personalBooks) {
            if (pb.getPersonalCode() == personalCode) {
                this.personalBooks.remove(pb); //Talvez um break;
                return pb;
            }
        }
        throw new BookDoesNotExistException();
    }
    public void registerBook(LoanableBook loanableBook) throws BookAlreadyExistsExpection {
        if(this.existLoanableBook(loanableBook)){
            throw new BookAlreadyExistsExpection();
        }
        this.loanableBooks.add(loanableBook);
    }
    public boolean existLoanableBook(LoanableBook loanableBook){
        for(LoanableBook lb: this.loanableBooks){
            if(lb.equals(loanableBook)){
                return true;
            }
        }
        return false;
    }
    public List<LoanableBook> allLoanableBooks() {
        return this.loanableBooks;
    }
    public List<LoanableBook> allLoanableByLoanNameStatus(@NotNull String nameOrStatus) {
        List<LoanableBook> allLoanableByLoanNameStatus = new ArrayList<>();
        if(nameOrStatus.equalsIgnoreCase(LoanableBook.LOANED_TO_ANYONE)){
            for(LoanableBook lb: this.loanableBooks){
                if(lb.getLoanableStatus().equalsIgnoreCase(LoanableBook.LOANED_TO_ANYONE)){
                    allLoanableByLoanNameStatus.add(lb);
                }
            }
        } else {
            for(LoanableBook lb: this.loanableBooks){
                if(!(lb.getLoanableStatus().equalsIgnoreCase(LoanableBook.LOANED_TO_ANYONE))){
                    allLoanableByLoanNameStatus.add(lb);
                }
            }
        }
        return allLoanableByLoanNameStatus;
    }
    public List<LoanableBook> allLoanableBooksByCategory(String category){
        List<LoanableBook> loanableBooksByCategory = new ArrayList<>();
        for(LoanableBook lb: this.loanableBooks){
            if(lb.getCategory().equalsIgnoreCase(category)){
                loanableBooksByCategory.add(lb);
            }
        }
        return loanableBooksByCategory;
    }
    public List<LoanableBook> allLoanableBooksByAuthor(String author){
        List<LoanableBook> loanableBooksByAuthor = new ArrayList<>();
        for(LoanableBook lb: this.loanableBooks){
            if(lb.getAuthor().equalsIgnoreCase(author)){
                loanableBooksByAuthor.add(lb);
            }
        }
        return loanableBooksByAuthor;
    }
    public LoanableBook loanableBookByTitleAuthorCategory(String title, String author, int edition)
            throws BookDoesNotExistException{ //TODO: exception
        for(LoanableBook lb: this.loanableBooks){
            if(lb.getTitle().equalsIgnoreCase(title) && lb.getAuthor().equalsIgnoreCase(author) && (lb.getEdition() == edition)){
                return lb;
            }
        }
        throw new BookDoesNotExistException();
    }
    public void loanableBookToSellable(int barCode, double price, LoanableBook loanableBook){
        SellableBook book = new SellableBook(barCode,price,loanableBook.getTitle(),loanableBook.getAuthor(),loanableBook.getEdition(),loanableBook.getCategory());
        this.sellableBooks.add(book);
        this.loanableBooks.remove(loanableBook);
    }
    public void loanableBookToPersonal(LoanableBook loanableBook, String status){
        PersonalBook book = new PersonalBook(loanableBook.getTitle(),loanableBook.getAuthor(),loanableBook.getEdition(),loanableBook.getCategory(),status);
        this.personalBooks.add(book);
        this.loanableBooks.remove(loanableBook);
    }
    public void deleteLoanableBook(LoanableBook loanableBook){
        this.loanableBooks.remove(loanableBook);
    }
    public void registerBook(SellableBook sellableBook) throws BookAlreadyExistsExpection{
        if (this.existSellableBook(sellableBook)) {
            throw new BookAlreadyExistsExpection();
        }
        this.sellableBooks.add(sellableBook);
    }
    public boolean existSellableBook(SellableBook sellableBook){
        for(SellableBook sb: this.sellableBooks){
            if(sb.equals(sellableBook)){
                return true;
            }
        }
        return false;
    }
    public List<SellableBook> allSellableBooks(){
        return this.sellableBooks;
    }
    public List<SellableBook> allsellableBooksByCategory(String category){
        List<SellableBook> sellableBooksByCategory = new ArrayList<>();
        for(SellableBook sb: this.sellableBooks){
            if(sb.getCategory().equalsIgnoreCase(category)){
                sellableBooksByCategory.add(sb);
            }
        }
        return sellableBooksByCategory;
    }
    public List<SellableBook> allLSellableBooksByAuthor(String author){
        List<SellableBook> sellableBooksByAuthor = new ArrayList<>();
        for(SellableBook sb: this.sellableBooks){
            if(sb.getAuthor().equalsIgnoreCase(author)){
                sellableBooksByAuthor.add(sb);
            }
        }
        return sellableBooksByAuthor;
    }
    public SellableBook sellableBookByBarCode(int barCode) throws BookDoesNotExistException {
        for(SellableBook sb: this.sellableBooks){
            if(sb.getBarcode() == barCode){
                return sb;
            }
        }
        throw new BookDoesNotExistException();
    }
    public void sellableBookToPersonal(SellableBook sellableBook,String status){
        PersonalBook book = new PersonalBook(sellableBook.getTitle(),sellableBook.getAuthor(),sellableBook.getEdition(),sellableBook.getCategory(), status);
        this.personalBooks.add(book);
        this.sellableBooks.remove(sellableBook);
    }
    public void sellableBookToLoanable(SellableBook sellableBook){
        LoanableBook book = new LoanableBook(sellableBook.getTitle(),sellableBook.getAuthor(),sellableBook.getEdition(),sellableBook.getCategory());
        this.loanableBooks.add(book);
        this.sellableBooks.remove(sellableBook);
    }
    public void deleteSellableBook (SellableBook sellableBook){
        this.sellableBooks.remove(sellableBook);
    }
    public void saveData() throws IOException {
        this.recorder.recordPBs(this.personalBooks);
        this.recorder.recordLBs(this.loanableBooks);
        this.recorder.recordSBs(this.sellableBooks);
    }
    public void recoverData() throws IOException{
        this.personalBooks = this.recorder.recoverPBs();
        for(PersonalBook pb: this.personalBooks){
            this.personalCodes.add(pb.getPersonalCode());
        }
        this.loanableBooks = this.recorder.recoverLBs();
        this.sellableBooks = this.recorder.recoverSBs();
    }

}

