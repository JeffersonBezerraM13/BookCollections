package br.ufpb.dcx.bibliotecapessoal2;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class LibraryProgram {
    public static String statusConstant(String options) {
        return switch (options) {
            case "2" -> (PersonalBook.STATUS_READING);
            case "3" -> (PersonalBook.STATUS_READ);
            default -> (PersonalBook.STATUS_TO_READ);
        };
    }

    public static String statusOptionUI() {
        ImageIcon nullIcon = new ImageIcon((String) null);
        return (String) JOptionPane.showInputDialog(null,
                """
                        Digite a opção de status:
                        (Opção padrão: PARA LER)\
                        
                        1- PARA LER\
                        
                        2- LENDO\
                        
                        3- LIDO""",
                "Opção de status:", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
    }
    public static void nullInformationDataMsg(){
        ImageIcon informationIcon = new ImageIcon("icons\\informationIcon50x50.png");
        JOptionPane.showMessageDialog(null,
                "Insira um dado válido",
                "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE, informationIcon);
    }
    public static void integerNumberFormatExceptionMsg(){
        ImageIcon errorIcon = new ImageIcon("icons\\errorIcon50x50.png");
        JOptionPane.showMessageDialog(null,
                "Insira um número inteiro válido",
                "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
    }
    public static void doubleNumberFormatExceptionMsg(){
        ImageIcon errorIcon = new ImageIcon("icons\\errorIcon50x50.png");
        JOptionPane.showMessageDialog(null,
                "Insira um número decimal válido",
                "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
    }
    public static void main(String[] args) {
        ImageIcon nullIcon = new ImageIcon((String)null);
        ImageIcon informationIcon = new ImageIcon("icons\\informationIcon50x50.png");
        ImageIcon errorIcon  = new ImageIcon("icons\\errorIcon50x50.png");
        LibrarySystem system = new LibrarySystem();
        try {
            system.recoverData();
            JOptionPane.showMessageDialog(null,
                    "Seus dados foram recuperados",
                    "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE, informationIcon);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Atenção! Seus dados não foram recuperados",
                    "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
            e.printStackTrace();
        }
        while (true) { //while3
            String [] options = {"Acervo Particular", "Acervo Compartilhável", "Acervo Disponível para Compra","Informações"};
            JComboBox<String> comboBoxMain = new JComboBox<>(options);
            int result = JOptionPane.showConfirmDialog(null,
                    comboBoxMain,
                    "Gerenciamento dos acervos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("icons\\arquivesKirby180.png"));
            if (result == JOptionPane.OK_OPTION) {
                String switch1 = (String) comboBoxMain.getSelectedItem();
                switch (switch1) {
                    case "Acervo Particular":
                        while (true) { //while2
                            String personalLibraryOption = (String) JOptionPane.showInputDialog(null,
                                    "Escolha uma opção para gerenciar seu acervo pessoal:\n" +
                                            "\n1- Cadastrar Livro" +
                                            "\n2- Exibição" +
                                            "\n3- Colocar livro para emprestimo" +
                                            "\n4- Colocar livro para venda" +
                                            "\n5- Alterar dados de livro" +
                                            "\n6- Atualizar status de livro" +
                                            "\n7- Apagar livro" +
                                            "\n8- Salvar dados",
                                    "Gerenciamento do acervo pessoal", JOptionPane.QUESTION_MESSAGE,
                                    new ImageIcon("icons\\PersonalIcon.png"), null, null);
                            String allPersonalBooksMain = "";
                            for (PersonalBook pb : system.allPersonalBooks()) {
                                allPersonalBooksMain += pb.toString() + "\n";
                            }
                            if(personalLibraryOption == null){ //Para sair da aba de biblioteca pessoal é só apertar "cancel"
                                break;
                            }
                            switch (personalLibraryOption) {
                                case "1": //Cadastrar livro pessoal
                                    String titleP1 = (String) JOptionPane.showInputDialog(null,
                                            "Digite o título do livro:",
                                            "Cadastrar livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(titleP1 == null){
                                        break;
                                    }
                                    if(titleP1.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    String authorP1 = (String) JOptionPane.showInputDialog(null,
                                            "O(a) autor(a) do livro:",
                                            "Cadastrar livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(authorP1 == null){
                                        break;
                                    }
                                    if(authorP1.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    try {
                                        int editioP1 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                "Insira a edição:",
                                                "Cadastrar livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        String categoryP1 = (String) JOptionPane.showInputDialog(null,
                                                "Insira a categoria:",
                                                "Cadastrar livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                        if(categoryP1 == null){
                                            break;
                                        }
                                        if(categoryP1.isEmpty()){
                                            nullInformationDataMsg();
                                            continue;
                                        }
                                        String statusP1 = statusConstant(statusOptionUI());
                                        try {
                                            system.registerBook(new PersonalBook(titleP1, authorP1, editioP1, categoryP1, statusP1));
                                            JOptionPane.showMessageDialog(null,
                                                    "Livro cadastrado com sucesso!",
                                                    "Cadastrar livro pessoal", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                            continue;

                                        } catch (BookAlreadyExistsExpection e){
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro "+e.getMessage()+ "já está cadastrado no sistema",
                                                    "Mensagem do sistema",JOptionPane.ERROR_MESSAGE,errorIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "2": //Exibição
                                    while (true) { //while3
                                        String exibitionOption = (String) JOptionPane.showInputDialog(null, """
                                                Escolha uma opção de exibição\
                                                
                                                1- Mostrar todos os livros\
                                                
                                                2- Mostrar todos os livros por categoria\
                                                
                                                3- Mostrar livros por status\
                                                
                                                4- Mostrar livros por autor\
                                                
                                                5- Mostrar todos os livros por status e por categoria""",
                                                "Menu de exibições", JOptionPane.QUESTION_MESSAGE, new ImageIcon("icons\\personalIcon.png"), null, null);
                                        if(exibitionOption == null){ //Para sair do meno de exibição é só apertar "cancel"
                                            break;
                                        }
                                        switch (exibitionOption) {
                                            case "1": //Mostrar todos os livros
                                                if (allPersonalBooksMain.isBlank() || allPersonalBooksMain.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros cadastrados",
                                                            "Todos os livros", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        allPersonalBooksMain,
                                                        "Seus livros", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            case "2": //Mostrar todos os livros por categoria
                                                String categoryP2C2 = (String) JOptionPane.showInputDialog(null,
                                                        """
                                                                Por favor, insira a categoria de livros que você gostaria de ver.\
                                                                
                                                                
                                                                Exemplos: Ficção, Tecnologia, História, Autoajuda.\
                                                                
                                                                
                                                                Digite a categoria:""",
                                                        "Todos os livros por categoria", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                if(categoryP2C2 == null){
                                                    break;
                                                }
                                                if(categoryP2C2.isEmpty()){
                                                    nullInformationDataMsg();
                                                    continue;
                                                }
                                                List<PersonalBook> personalBooksByCategory = system.allPersonalBooksByCategory(categoryP2C2);
                                                if (personalBooksByCategory == null || personalBooksByCategory.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros da categoria " + categoryP2C2 + " cadastrados!",
                                                            "Todos os livros por categoria", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                String booksByCategoryStr = "";
                                                for (PersonalBook pb : personalBooksByCategory) {
                                                    booksByCategoryStr += pb.toString() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        booksByCategoryStr,
                                                        "Seus livros da categoria " + categoryP2C2, JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            case "3": //Mostrar livros por status
                                                String statusP2C3 = statusConstant(statusOptionUI());
                                                List<PersonalBook> personalBooksByStatus = system.allPersonalBooksByStatus(statusP2C3);
                                                if (personalBooksByStatus == null || personalBooksByStatus.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros do status " + statusP2C3 + " cadastrados",
                                                            "Todos os livros por status", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                String booksByStatusStr = "";
                                                for (PersonalBook pb : personalBooksByStatus) {
                                                    booksByStatusStr += pb.toString() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        booksByStatusStr,
                                                        "Seus livros com status " + statusP2C3, JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            case "4": //Mostrar livros por autor
                                                String authorP2C4 = (String) JOptionPane.showInputDialog(null,
                                                        "Insira o nome do autor:",
                                                        "Todos os livros por autor(a)", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                if(authorP2C4 == null){
                                                    break;
                                                }
                                                if(authorP2C4.isEmpty()){
                                                    nullInformationDataMsg();
                                                    continue;
                                                }
                                                List<PersonalBook> personalBooksByAuthor = system.allPersonalBooksByAuthor(authorP2C4);
                                                if (personalBooksByAuthor == null || personalBooksByAuthor.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não livros cadastrados do(a) autor(a) " + authorP2C4,
                                                            "Todos os livros por autor(a):", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                String booksByAuthorStr = "";
                                                for (PersonalBook pb : personalBooksByAuthor) {
                                                    booksByAuthorStr += pb.toString() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        booksByAuthorStr,
                                                        "Seus livros do(a) autor(a) " + authorP2C4, JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            case "5": //Mostrar todos os livros por status e por categoria
                                                String statusC2P5 = statusConstant(statusOptionUI());
                                                String categoryC2P5 = (String) JOptionPane.showInputDialog(null,
                                                        "Insira a categoria:",
                                                        "Mostrar todos os livros por status e categoria", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                if(categoryC2P5 == null){
                                                    break;
                                                }
                                                if(categoryC2P5.isEmpty()){
                                                    nullInformationDataMsg();
                                                    continue;
                                                }
                                                List<PersonalBook> personalBooksByStatusAndCategory = system.allPersonalBooksByStatusAndCategory(statusC2P5, categoryC2P5);
                                                if (personalBooksByStatusAndCategory == null || personalBooksByStatusAndCategory.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros cadastrados com o status " + statusC2P5 + " ou a categoria " + categoryC2P5,
                                                            "Todos os livros por status e categoria", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                String booksByStatusAndCategoryStr = "";
                                                for (PersonalBook pb : personalBooksByStatusAndCategory) {
                                                    booksByStatusAndCategoryStr += pb.toString() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        booksByStatusAndCategoryStr,
                                                        "Seus livros do status "+statusC2P5 +" e da categoria "+categoryC2P5, JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            default:
                                                JOptionPane.showMessageDialog(null,
                                                        "Insira uma opção válida",
                                                        "Mensagem do sistema",JOptionPane.ERROR_MESSAGE,nullIcon);
                                        }
                                    }
                                    continue;
                                case "3": //Transformar de pessoal para emprestavel
                                    if (allPersonalBooksMain.isBlank() || allPersonalBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Transformar livro pessoal em livro emprestavel", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    try{
                                        int personalCodeP3 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                "Insira o código do livro que você deseja transformar:\n\n" + allPersonalBooksMain,
                                                "Transforme um livro pessoal em um livro emprestável", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            PersonalBook personalBookToLoanable = system.personalBookByCode(personalCodeP3);
                                            if (personalBookToLoanable.getStatus().equals(PersonalBook.STATUS_TO_READ) || personalBookToLoanable.getStatus().equals(PersonalBook.STATUS_READING)) {
                                                String statusWarning = (String) JOptionPane.showInputDialog(null,
                                                        "O livro "+personalBookToLoanable+" está com o status "+personalBookToLoanable.getStatus()+"\nDeseja continuar?(Sim-Não)",
                                                        "Transforme livro pessoal em um livro emprestável",JOptionPane.QUESTION_MESSAGE, informationIcon, null,null);
                                                if(!(statusWarning.equalsIgnoreCase("sim"))){
                                                    break;
                                                }
                                            }
                                            system.personalToLoanableBook(personalBookToLoanable);
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro \"" +personalBookToLoanable+ "\" foi convertido em livro emprestável",
                                                    "Transformar um livro peossal em um livro emprestável", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        } catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,
                                                    "Não há livros cadastrados com esse código",
                                                    "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "4": //Livro pessoal para livro vendivel
                                    if (allPersonalBooksMain.isBlank() || allPersonalBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Transformar livro pessoal em livro vendível", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    try {
                                        int barCodeP4 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                "Insira o código de barra do livro que irá ser vendido:",
                                                "Transformar um livro pessoal em um livro para venda", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            double priceP4 = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                    "Insira o preço que o livro deve ter:",
                                                    "Transformar um livro pessoal em um livro para venda", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                            try{
                                                int personalCodeP4 = Integer.parseInt(JOptionPane.showInputDialog(null,allPersonalBooksMain+
                                                        "\n\nInsira o código pessoal do livro que deseja transformar em um livro vendivel: ",
                                                        "Transformar um livro pessoal em um livro para venda", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                                try {
                                                    PersonalBook personalBookToSellable = system.personalBookByCode(personalCodeP4);
                                                    if (personalBookToSellable.getStatus().equals(PersonalBook.STATUS_TO_READ) || personalBookToSellable.getStatus().equals(PersonalBook.STATUS_READING)) {
                                                        String statusWarning = (String) JOptionPane.showInputDialog(null,
                                                                "O livro "+personalBookToSellable+" está com o status "+personalBookToSellable.getStatus()+"\nDeseja continuar?(Sim-Não)",
                                                                "Transforme livro pessoal em um livro para venda",JOptionPane.QUESTION_MESSAGE, informationIcon, null,null);
                                                        if(!(statusWarning.equalsIgnoreCase("sim"))){
                                                            break;
                                                        }
                                                    }
                                                    system.personalBookToSellable(barCodeP4, priceP4, personalBookToSellable);
                                                    JOptionPane.showMessageDialog(null,
                                                            "O livro \""+personalBookToSellable+"\" foi transformado com sucesso!",
                                                            "Transformar um livro pessoal em um livro para venda", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                } catch (BookDoesNotExistException e){
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros pessoais cadastrados com esse código",
                                                            "Mensagem do sistema",JOptionPane.ERROR_MESSAGE, errorIcon);
                                                }
                                            } catch (NullPointerException | NumberFormatException e){
                                                integerNumberFormatExceptionMsg();
                                            }
                                        } catch (NullPointerException | NumberFormatException e){
                                            doubleNumberFormatExceptionMsg();
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "5": //Alterar dados de livro
                                    if (allPersonalBooksMain.isBlank() || allPersonalBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Alterar dados de livro", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    try {
                                        int personalCodeP6 = Integer.parseInt(JOptionPane.showInputDialog(null, allPersonalBooksMain+
                                                " \n\nInsira o código pessoal do livro que deseja alterar os dados:",
                                                "Alterar dados de livro:", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            PersonalBook pbP6 = system.personalBookByCode(personalCodeP6);
                                            String changeData = (String) JOptionPane.showInputDialog(null,"Escolha qual dados quer alterar do livro:"+pbP6+"\n\n"+
                                                            """
                                                                    1- Título\
                                                                    
                                                                    2- Autor(a)\
                                                                    
                                                                    3- Edição\
                                                                    
                                                                    4- Categoria""",
                                                    "", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                            if(changeData == null){
                                                break;
                                            }
                                            switch (changeData) {
                                                case "1":
                                                    String titleP6 = (String) JOptionPane.showInputDialog(null,
                                                            "Insira o título para que seja alterado",
                                                            "Altera o título de um livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                    if(titleP6 == null){
                                                        break;
                                                    }
                                                    if(titleP6.isEmpty()){
                                                        nullInformationDataMsg();
                                                        continue;
                                                    }
                                                    pbP6.setTitle(titleP6);
                                                    JOptionPane.showMessageDialog(null,
                                                            "Título alterado com sucesso!\n"+pbP6,
                                                            "Altera o titulo de um livro", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    break;
                                                case "2":
                                                    String authorP6 = (String) JOptionPane.showInputDialog(null,
                                                            "Insira o autor do livro para que seja alterado",
                                                            "Altera o nome do autor de um livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                    if(authorP6 == null){
                                                        break;
                                                    }
                                                    if(authorP6.isEmpty()){
                                                        nullInformationDataMsg();
                                                        continue;
                                                    }
                                                    pbP6.setAuthor(authorP6);
                                                    JOptionPane.showMessageDialog(null,
                                                            "Autor(a) alterado com sucesso!\n"+pbP6,
                                                            "Altera o(a) autor(a) de um livro", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    break;
                                                case "3":
                                                    try{
                                                        int editionP6 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                "Insira o título para que seja alterado",
                                                                "Altera o título de um livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                                        pbP6.setEdition(editionP6);
                                                        JOptionPane.showMessageDialog(null,
                                                                "Edição alterada com sucesso!\n"+pbP6,
                                                                "Altera a edição de um livro", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                        break;
                                                    } catch (NullPointerException | NumberFormatException e){
                                                        integerNumberFormatExceptionMsg();
                                                    }
                                                    break;
                                                case "4":
                                                    String categoryP6 = (String) JOptionPane.showInputDialog(null,
                                                            "Insira a cateogoria do livro para que seja alterado",
                                                            "Altera a cateogoria de um livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                    if(categoryP6 == null){
                                                        break;
                                                    }
                                                    if(categoryP6.isEmpty()){
                                                        nullInformationDataMsg();
                                                        continue;
                                                    }
                                                    pbP6.setCategory(categoryP6);
                                                    JOptionPane.showMessageDialog(null,
                                                            "Categoria alterada com sucesso!\n"+pbP6,
                                                            "Altera a categoria de um livro", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    break;
                                                default:
                                                    JOptionPane.showMessageDialog(null,
                                                            "Insira uma opção válida",
                                                            "Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,informationIcon);
                                            }
                                        } catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,
                                                    "Não há livros cadastrados com esse código",
                                                    "Mensagem do sistema", JOptionPane.ERROR_MESSAGE,errorIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "6": //Atualizar status de livro
                                    if (allPersonalBooksMain.isBlank() || allPersonalBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Atualizar status de livro", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    try {
                                        int personalCodeP7 = Integer.parseInt(JOptionPane.showInputDialog(null, allPersonalBooksMain+
                                                " \n\nInsira o código pessoal do livro que deseja transformar em um livro vendivel:",
                                                "Transformar um livro pessoal em um livro para venda:", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            PersonalBook book = system.personalBookByCode(personalCodeP7);
                                            String statusP7 = statusConstant(statusOptionUI());
                                            book.setStatus(statusP7);
                                            JOptionPane.showMessageDialog(null,
                                                    "Status alterado com sucesso!\n\n"+book,
                                                    "Alterar status de um livro:", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        } catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,
                                                    "Não há livros cadastrados com esse código",
                                                    "Mensagem do sistema",JOptionPane.ERROR_MESSAGE,errorIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "7": //Apagar livro
                                    if (allPersonalBooksMain.isBlank() || allPersonalBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Apagar livro", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    try {
                                        int personalCodeP8 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                "Insira o código pessoal do livro que deseja transformar em um livro vendivel: \n\n" + allPersonalBooksMain,
                                                "Apagar um livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try{
                                            PersonalBook bookDeleted = system.deletePersonalBook(personalCodeP8);
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro \""+bookDeleted+"\" foi apagado com sucesso!",
                                                    "Apagar um livro:", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        } catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro não foi encontrado",
                                                    "Mensamgem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "8": //Salvar dados
                                    try {
                                        system.saveData();
                                        JOptionPane.showMessageDialog(null,
                                                "Dados salvos com sucesso!",
                                                "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                    } catch (IOException e) {
                                        JOptionPane.showMessageDialog(null,
                                                "Atenção! Seus dados não foram salvos",
                                                "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        e.printStackTrace();
                                    }
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,
                                            "Insira uma opção válida",
                                            "Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,informationIcon);
                            }
                        }
                        continue;
                    case "Acervo Compartilhável": //Biblioteca de livros emprestaveis
                        while (true) {
                            String allLoanableBooksMain = "";
                            for (LoanableBook lb : system.allLoanableBooks()) {
                                allLoanableBooksMain += lb.toString() + "\n";
                            }
                            String loanableLibraryOptions = (String) JOptionPane.showInputDialog(null,
                                    "Escolha uma opção para gerenciar seu acervo compartilhável:\n" +
                                            "\n1- Cadastrar livro emprestável" +
                                            "\n2- Exibição" +
                                            "\n3- Colocar livro para pessoal" +
                                            "\n4- Colocar livro para venda" +
                                            "\n5- Alterar dados de livro" +
                                            "\n6- Apagar livro" +
                                            "\n7- Salvar dados",
                                    "Gerenciamento do acervo compartilhável", JOptionPane.QUESTION_MESSAGE,
                                    new ImageIcon("icons\\LoanableIcon.png"), null, null);
                            if(loanableLibraryOptions == null){
                                break;
                            }
                            switch (loanableLibraryOptions) {
                                case "1": //Cadastrar Livro emprestavel
                                    String titleL1 = (String) JOptionPane.showInputDialog(null,
                                            "Insira o título do livro:",
                                            "Cadastrar livro emprestável", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(titleL1 == null){
                                        break;
                                    }
                                    if(titleL1.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    String authorL1 = (String) JOptionPane.showInputDialog(null,
                                            "O(a) autor(a) do livro:",
                                            "Cadastrar livro emprestável", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(authorL1 == null){
                                        break;
                                    }
                                    if(authorL1.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    try{
                                        int editioL1 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                "Insira a edição:",
                                                "Cadastrar livro emprestável", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        String categoryL1 = (String) JOptionPane.showInputDialog(null,
                                                "Insira a categoria:",
                                                "Cadastrar livro emprestável", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                        if(categoryL1 == null){
                                            break;
                                        }
                                        if(categoryL1.isEmpty()){
                                            nullInformationDataMsg();
                                            continue;
                                        }
                                        LoanableBook bookRegister = new LoanableBook(titleL1, authorL1, editioL1, categoryL1);
                                        String nameInRegisterOption = (String) JOptionPane.showInputDialog(null,
                                                "Resposta padrão: Aperte enter para \"Não\" \n\n" + "Deseja colocar o nome da pessoa a qual você emprestou o livro já no cadastro?",
                                                "Cadastrar livro emprestável", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                        if (nameInRegisterOption.equalsIgnoreCase("sim")) {
                                            String nameLoan = (String) JOptionPane.showInputDialog(null,
                                                    "Qual o nome da pessoa eu você vai emprestar o livro?",
                                                    "Cadastrar livro emprestável", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                            bookRegister.setloanNameAndStatus(nameLoan);
                                        }
                                        try {
                                            system.registerBook(bookRegister);
                                            JOptionPane.showMessageDialog(null,
                                                    "Livro cadastrado com sucesso!",
                                                    "Cadastrar livro emprestável", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        } catch (BookAlreadyExistsExpection e){
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro que você quer cadastrar já existe no sistema",
                                                    "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "2": //Exibição
                                    while (true) { //while3
                                        String exibitionOption = (String) JOptionPane.showInputDialog(null, """
                                                Escolha uma opção de exibição:\
                                                
                                                1- Mostrar todos os livros\
                                                
                                                2- Mostrar todos os livros emprestados\
                                                
                                                3- Mostrar todos os livros disponiveis\
                                                
                                                4- Mostrar todos os livros por categoria\
                                                
                                                5- Mostrar livros por autor""",
                                                "Menu de exibições", JOptionPane.QUESTION_MESSAGE,
                                                new ImageIcon("icons\\loanableIcon.png"), null, null);
                                        if(exibitionOption == null){
                                            break;
                                        }
                                        switch (exibitionOption) {
                                            case "1"://Mostrar todos os livros
                                                if (allLoanableBooksMain.isEmpty()) { //Talvez eu use esse ou talvez eu trate no metodo
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros cadastrados",
                                                            "Todos os livros", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        allLoanableBooksMain,
                                                        "Seus livros", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            case "2"://Mostrar todos os livros emprestados
                                                List<LoanableBook> allLoanableByLoanNameStatusC2 = system.allLoanableByLoanNameStatus("any");
                                                if (allLoanableByLoanNameStatusC2 == null || allLoanableByLoanNameStatusC2.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros cadastrados",
                                                            "Mostrar todos os livros emprestados", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                String allLoanableByLoanNameStatusC2Str = "";
                                                for (LoanableBook lb : allLoanableByLoanNameStatusC2) {
                                                    allLoanableByLoanNameStatusC2Str += lb.toString() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        allLoanableByLoanNameStatusC2Str,
                                                        "Seus livros emprestados", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            case "3"://Mostrar todos os livros disponiveis
                                                List<LoanableBook> allLoanableByLoanNameStatusC3 = system.allLoanableByLoanNameStatus(LoanableBook.LOANED_TO_ANYONE);
                                                if (allLoanableByLoanNameStatusC3 == null || allLoanableByLoanNameStatusC3.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros cadastrados",
                                                            "Mostrar todos os livros disponiveis", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                String allLoanableByLoanNameStatusC3Str = "";
                                                for (LoanableBook lb : allLoanableByLoanNameStatusC3){
                                                    allLoanableByLoanNameStatusC3Str += lb.toString() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        allLoanableByLoanNameStatusC3Str,
                                                        "Seus livros disponiveis", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            case "4"://Mostrar todos os livros por categoria
                                                String categoryL2C4 = (String) JOptionPane.showInputDialog(null,
                                                        """
                                                                Por favor, insira a categoria de livros que você gostaria de ver.\
                                                                
                                                                
                                                                Exemplos: Ficção, Tecnologia, História, Autoajuda.\
                                                                
                                                                
                                                                Digite a categoria:""",
                                                        "Todos os livros por categoria", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                if(categoryL2C4 == null){
                                                    break;
                                                }
                                                if(categoryL2C4.isEmpty()){
                                                    nullInformationDataMsg();
                                                    continue;
                                                }
                                                List<LoanableBook> loanableBooksByCategory = system.allLoanableBooksByCategory(categoryL2C4);
                                                if (loanableBooksByCategory == null || loanableBooksByCategory.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros da categoria " + categoryL2C4 + " cadastrados!",
                                                            "Todos os livros por categoria", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                String booksByCategoryStr = "";
                                                for (LoanableBook lb : loanableBooksByCategory) {
                                                    booksByCategoryStr += lb.toString() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        booksByCategoryStr,
                                                        "Seus livros da categoria " + categoryL2C4, JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            case "5"://Mostrar livros por autor
                                                String authorL2C5 = (String) JOptionPane.showInputDialog(null,
                                                        "Insira o nome do autor(a):",
                                                        "Todos os livros por autor(a)", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                if(authorL2C5 == null){
                                                    break;
                                                }
                                                if(authorL2C5.isEmpty()){
                                                    nullInformationDataMsg();
                                                    continue;
                                                }
                                                List<LoanableBook> loanableBooksByAuthor = system.allLoanableBooksByAuthor(authorL2C5);
                                                if (loanableBooksByAuthor == null || loanableBooksByAuthor.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros cadastrados do(a) autor(a) " + authorL2C5,
                                                            "Todos os livros por autor(a)", JOptionPane.QUESTION_MESSAGE, nullIcon);
                                                    break;
                                                }
                                                String booksByAuthorStr = "";
                                                for (LoanableBook lb : loanableBooksByAuthor) {
                                                    booksByAuthorStr += lb.toString() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        booksByAuthorStr,
                                                        "Seus livros do(a) autor(a) " +authorL2C5, JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            default:
                                                JOptionPane.showMessageDialog(null,
                                                        "Insira uma opção válida",
                                                        "Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,informationIcon);
                                        }
                                    }
                                    break;
                                case "3": //Colocar livro para pessoal
                                    if (allLoanableBooksMain.isBlank() || allLoanableBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Transformar livro emprestável em livro pessoal", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    JOptionPane.showMessageDialog(null,
                                            "Atenção! Primeiro será localizado o livro emprestável\n" +
                                                    "para isso, insira a seguir as informações do livro\n" +
                                                    " que você quer transformar",
                                            "Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,informationIcon);
                                    String titleL3 = (String) JOptionPane.showInputDialog(null, allLoanableBooksMain+
                                            "\n\nInsira o título do livro:",
                                            "Transformar livro emprestável em livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(titleL3 == null){
                                        break;
                                    }
                                    if(titleL3.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    String authorL3 = (String) JOptionPane.showInputDialog(null, allLoanableBooksMain+
                                            "\n\nO(a) autor(a) do livro:",
                                            "Transformar livro emprestável em livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(authorL3 == null){
                                        break;
                                    }
                                    if(authorL3.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    try {
                                        int editionL3 = Integer.parseInt(JOptionPane.showInputDialog(null,allLoanableBooksMain+
                                                "\n\nInsira a ediçã:o",
                                                "Transformar livro emprestavel em livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        String statusL3 = statusConstant(statusOptionUI());
                                        try {
                                            LoanableBook loanableBookToPersonal = system.loanableBookByTitleAuthorCategory(titleL3, authorL3, editionL3);
                                            if(!(loanableBookToPersonal.getLoanableStatus().equalsIgnoreCase(LoanableBook.LOANED_TO_ANYONE))){
                                                JOptionPane.showMessageDialog(null,
                                                        "Você não pode tranformar um livro que está emprestado",
                                                        "Transformar livro emprestável em livro pessoal", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                            }
                                            system.loanableBookToPersonal(loanableBookToPersonal, statusL3);
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro \"" + loanableBookToPersonal + "\" foi convertido em livro emprestável",
                                                    "Transformar um livro emprestável em um livro pessoal", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        } catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,
                                                    "Esses dados não correspondem a um livro cadastrado",
                                                    "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "4": //Colocar livro emprestavel para venda
                                    if (allLoanableBooksMain.isBlank() || allLoanableBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Transformar livro emprestável em livro vendível", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    JOptionPane.showMessageDialog(null,
                                            "Atenção! Primeiro será localizado o livro emprestável\n" +
                                                    "para isso, insira a seguir as informações do livro\n" +
                                                    " que você quer transformar",
                                            "Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,informationIcon);
                                    String titleL4 = (String) JOptionPane.showInputDialog(null, allLoanableBooksMain+
                                            "\n\nInsira o título do livro:",
                                            "Transformar livro emprestável em livro vendível", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(titleL4 == null){
                                        break;
                                    }
                                    if(titleL4.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    String authorL4 = (String) JOptionPane.showInputDialog(null, allLoanableBooksMain+
                                            "\n\nO(a) autor(a) do livro:",
                                            "Transformar livro emprestável em livro vendível", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(authorL4 == null){
                                        break;
                                    }
                                    if(authorL4.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    try {
                                        int editionL4 = Integer.parseInt(JOptionPane.showInputDialog(null, allLoanableBooksMain+
                                                "\n\nInsira a edição:                                             ",
                                                "Transformar livro emprestável em livro vendível", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            int barCodeL4 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                    "Insira o código de barra do livro para que seja possível transformar:",
                                                    "Transformar um livro emprestável em um livro para venda:", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                            try {
                                                double priceL4 = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                        "Insira o preço que o livro deve ter:                           ",
                                                        "Transformar um livro emprestável em um livro para venda:", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                                try {
                                                    LoanableBook loanableBookToSellable = system.loanableBookByTitleAuthorCategory(titleL4, authorL4, editionL4);
                                                    if(!(loanableBookToSellable.getLoanableStatus().equalsIgnoreCase(LoanableBook.LOANED_TO_ANYONE))){
                                                        JOptionPane.showMessageDialog(null,
                                                                "Você não pode tranformar um livro que está emprestado",
                                                                "Transformar livro emprestável em livro pessoal", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    }
                                                    system.loanableBookToSellable(barCodeL4, priceL4, loanableBookToSellable);
                                                    JOptionPane.showMessageDialog(null,
                                                            "O livro \""+loanableBookToSellable+"\" foi transformado com sucesso!",
                                                            "Transformar um livro emprestável em um livro para venda", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                } catch  (BookDoesNotExistException e){
                                                    JOptionPane.showMessageDialog(null,
                                                            "Esses dados não correspondem a um livro cadastrado",
                                                            "Mensagem do sistema",JOptionPane.ERROR_MESSAGE, errorIcon);
                                                }
                                            } catch (NullPointerException | NumberFormatException e) {
                                                doubleNumberFormatExceptionMsg();
                                            }
                                        } catch (NullPointerException | NumberFormatException e) {
                                            integerNumberFormatExceptionMsg();
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "5": //Alterar dados de livro
                                    if (allLoanableBooksMain.isBlank() || allLoanableBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Alterar dados de livro", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    JOptionPane.showMessageDialog(null,
                                            "Importante! Será preciso encontrar o livro para só depois modificar os seus dados",
                                            "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                    String titleL6 = (String) JOptionPane.showInputDialog(null,
                                            allLoanableBooksMain + "\n\n" +
                                                    "Insira o título do livro:",
                                            "Alterar dados de livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(titleL6 == null){
                                        break;
                                    }
                                    if(titleL6.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    String authorL6 = (String) JOptionPane.showInputDialog(null,
                                            allLoanableBooksMain + "\n\n" +
                                                    "O(a) autor(a) do livro:",
                                            "Alterar dados de livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(authorL6 == null){
                                        break;
                                    }
                                    if(authorL6.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    try {
                                        int editionL6 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                allLoanableBooksMain + "\n\n" +
                                                        "Insira a edição:",
                                                "Alterar dados de livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            LoanableBook lb = system.loanableBookByTitleAuthorCategory(titleL6, authorL6, editionL6);
                                            String changeData = (String) JOptionPane.showInputDialog(null,
                                                    """
                                                            Seus livro foi encontrado!\
                                                             Agora escolha a dado que quer trocar:\
                                                            1- Título\
                                                            
                                                            2- Autor(a)\
                                                            
                                                            3- Edição\
                                                            
                                                            4- Categoria""",
                                                    "Escolha qual dados quer alterar do livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                            if(changeData == null){
                                                break;
                                            }
                                            switch (changeData) {
                                                case "1": //Titulo
                                                    String titleL6C1 = (String) JOptionPane.showInputDialog(null,
                                                            "Insira o título do livro:",
                                                            "Alterar o título de um livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                    if(titleL6C1 == null){
                                                        break;
                                                    }
                                                    if(titleL6C1.isEmpty()){
                                                        nullInformationDataMsg();
                                                        continue;
                                                    }
                                                    lb.setTitle(titleL6C1);
                                                    JOptionPane.showMessageDialog(null,
                                                            "Título alterado com sucesso\n"+lb,
                                                            "Alterar dados de um livro", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    break;
                                                case "2": //Autor
                                                    String authorL6C2 = (String) JOptionPane.showInputDialog(null,
                                                            "O(a) autor(a) do livro:",
                                                            "Alterar o nome do(a) autor(a) de um livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                    if(authorL6C2 == null){
                                                        break;
                                                    }
                                                    if(authorL6C2.isEmpty()){
                                                        nullInformationDataMsg();
                                                        continue;
                                                    }
                                                    lb.setAuthor(authorL6C2);
                                                    JOptionPane.showMessageDialog(null,
                                                            "nome do(a) autor(a) alterado com sucesso\n"+lb,
                                                            "Alterar dados de um livro", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    break;
                                                case "3": //Edição
                                                    try {
                                                        int editionL6C3 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                "Insira a edição:",
                                                                "Transformar livro emprestavel em livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                                        lb.setEdition(editionL6C3);
                                                        JOptionPane.showMessageDialog(null,
                                                                "Edição alterada com sucesso\n"+lb,
                                                                "Alterar dados de um livro", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    } catch (NullPointerException | NumberFormatException e){
                                                        integerNumberFormatExceptionMsg();
                                                    }
                                                    break;
                                                case "4": //Categoria
                                                    String categoryL6C4 = (String) JOptionPane.showInputDialog(null,
                                                            "A categoria do livro:",
                                                            "Transformar livro emprestavel em livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                    if(categoryL6C4 == null){
                                                        break;
                                                    }
                                                    if(categoryL6C4.isEmpty()){
                                                        nullInformationDataMsg();
                                                        continue;
                                                    }
                                                    lb.setCategory(categoryL6C4);
                                                    JOptionPane.showMessageDialog(null,
                                                            "Categoria alterada com sucesso\n"+lb,
                                                            "Alterar dados de um livro", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    break;
                                            }
                                        } catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,
                                                    "Livro não encontrado",
                                                    "Mensagem do sistema", JOptionPane.ERROR_MESSAGE,errorIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "6": //Apagar livro
                                    if (allLoanableBooksMain.isBlank() || allLoanableBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Mensagem do sistema",JOptionPane.ERROR_MESSAGE,errorIcon);
                                        break;
                                    }
                                    String titleL7 = (String) JOptionPane.showInputDialog(null,
                                            allLoanableBooksMain + "\n\n" +
                                                    "Insira o título do livro:",
                                            "Apagar livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(titleL7 == null){
                                        break;
                                    }
                                    if(titleL7.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    String authorL7 = (String) JOptionPane.showInputDialog(null,
                                            allLoanableBooksMain + "\n\n" +
                                                    "O(a) autor(a) do livro:",
                                            "Apagar livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                    if(authorL7 == null){
                                        break;
                                    }
                                    if(authorL7.isEmpty()){
                                        nullInformationDataMsg();
                                        continue;
                                    }
                                    try{
                                        int editionL7 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                allLoanableBooksMain + "\n\n" +
                                                        "Insira a edição:",
                                                "Apagar livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            LoanableBook deleteBook = system.loanableBookByTitleAuthorCategory(titleL7,authorL7,editionL7);
                                            system.deleteLoanableBook(deleteBook);
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro \"" +deleteBook+ "\" foi deletado com sucesso",
                                                    "Apagar livro", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        }  catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,e.getMessage());
                                        }
                                    }catch (NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "7": //Salvar dados
                                    try {
                                        system.saveData();
                                        JOptionPane.showMessageDialog(null,
                                                "Seus dados foram salvos com sucesso!",
                                                "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                    } catch (IOException e) {
                                        JOptionPane.showMessageDialog(null,
                                                "Atenção! Seus dados não foram salvos",
                                                "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        e.printStackTrace();
                                    }
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,
                                            "Insira uma opção válida",
                                            "Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,informationIcon);
                            }
                        }
                        break;
                    case "Acervo Disponível para Compra": //Biblioteca de livros vendiveis
                        while (true) {
                            String allSellableBooksMain = "";
                            for (SellableBook lb : system.allSellableBooks()) {
                                allSellableBooksMain += lb.toString() + "\n";
                            }
                            String sellableLibraryOptions = (String) JOptionPane.showInputDialog(null,
                                    "Escolha uma opção para gerenciar seu acervo disponível para compra:\n" +
                                            "\n1- Cadastrar livro vendível" +
                                            "\n2- Exibição" +
                                            "\n3- Transferir livro para Acervo Particular" +
                                            "\n4- Transferir livro para Acervo Compartilhado" +
                                            "\n5- Alterar dados de livro" +
                                            "\n6- Apagar livro" +
                                            "\n7- Salvar dados",
                                    "Gerencioamento de acervo disponível para compra", JOptionPane.QUESTION_MESSAGE,
                                    new ImageIcon("icons\\SellableIcon.png"), null, null);
                            if (sellableLibraryOptions == null){
                                break;
                            }
                            switch (sellableLibraryOptions) {
                                case "1"://Cadastrar Livro vendivel
                                    try {
                                        int barCodeS1 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                "Insira o codigo de barra do livro:",
                                                "Cadastrar livro vendível", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            int priceS1 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                    "Insira o preço de barra do livro:",
                                                    "Cadastrar livro vendível", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                            String titleS1 = (String) JOptionPane.showInputDialog(null,
                                                    "Insira o título do livro:",
                                                    "Cadastrar livro vendível", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                            if(titleS1 == null){
                                                break;
                                            }
                                            if(titleS1.isEmpty()){
                                                nullInformationDataMsg();
                                                continue;
                                            }
                                            String authorS1 = (String) JOptionPane.showInputDialog(null,
                                                    "O(a) autor(a) do livro:",
                                                    "Cadastrar livro vendível", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                            if(authorS1 == null){
                                                break;
                                            }
                                            if(authorS1.isEmpty()){
                                                nullInformationDataMsg();
                                                continue;
                                            }
                                            try {
                                                int editioS1 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                        "Insira a edição:",
                                                        "Cadastrar livro vendível", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                                String categoryS1 = (String) JOptionPane.showInputDialog(null,
                                                        "Insira a categoria:",
                                                        "Cadastrar livro vendível", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                if(categoryS1 == null){
                                                    break;
                                                }
                                                if(categoryS1.isEmpty()){
                                                    nullInformationDataMsg();
                                                    continue;
                                                }
                                                try {
                                                    system.registerBook(new SellableBook(barCodeS1, priceS1, titleS1, authorS1, editioS1, categoryS1));
                                                    JOptionPane.showMessageDialog(null,
                                                            "Livro cadastrado com sucesso!",
                                                            "Cadastrar livro vendível", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                } catch (BookAlreadyExistsExpection e){
                                                    JOptionPane.showMessageDialog(null,
                                                            "O livro que você está tentando cadastrar já existe no sistema",
                                                            "Mesangem do sistema", JOptionPane.ERROR_MESSAGE,errorIcon);
                                                }
                                            } catch (NullPointerException | NumberFormatException e){
                                                integerNumberFormatExceptionMsg();
                                            }
                                        } catch (NullPointerException | NumberFormatException e){
                                            doubleNumberFormatExceptionMsg();
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "2": //Exibição
                                    while (true) { //while3
                                        String exibitionOption = (String) JOptionPane.showInputDialog(null, """
                                                Escolha uma opção de exibição\
                                                
                                                1- Mostrar todos os livros\
                                                
                                                2- Mostrar todos os livros por categoria\
                                                
                                                3-  Mostrar livros por autor""",
                                                "Menu de exibições", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                        if(exibitionOption == null){
                                            break;
                                        }
                                        switch (exibitionOption) {
                                            case "1"://Mostrar todos os livros
                                                if (allSellableBooksMain.isBlank() || allSellableBooksMain.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros cadastrados",
                                                            "Mostrar todos os livros", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        allSellableBooksMain,
                                                        "Seus livros para venda", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            case "2"://Mostrar todos os livros por categoria
                                                String categoryS2C2 = (String) JOptionPane.showInputDialog(null,
                                                        """
                                                                Por favor, insira a categoria de livros que você gostaria de ver.\
                                                                
                                                                
                                                                Exemplos: Ficção, Tecnologia, História, Autoajuda.\
                                                                
                                                                
                                                                Digite a categoria:""",
                                                        "Todos os livros por categoria", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                if(categoryS2C2 == null){
                                                    break;
                                                }
                                                if(categoryS2C2.isEmpty()){
                                                    nullInformationDataMsg();
                                                    continue;
                                                }
                                                List<SellableBook> sellableBooksByCategory = system.allsellableBooksByCategory(categoryS2C2);
                                                if (sellableBooksByCategory == null || sellableBooksByCategory.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não há livros da categoria " + categoryS2C2 + " cadastrados!",
                                                            "Todos os livros por categoria", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                String booksByCategoryStr = "";
                                                for (SellableBook sb : sellableBooksByCategory) {
                                                    booksByCategoryStr += sb.toString() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        booksByCategoryStr,
                                                        "Seus livros da categoria " + categoryS2C2, JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            case "3"://Mostrar livros por autor
                                                String authorS2C3 = (String) JOptionPane.showInputDialog(null,
                                                        "Insira o nome do autor:",
                                                        "Todos os livros por autor:", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                if(authorS2C3 == null){
                                                    break;
                                                }
                                                if(authorS2C3.isEmpty()){
                                                    nullInformationDataMsg();
                                                    continue;
                                                }
                                                List<SellableBook> sellableBooksByAuthor = system.allLSellableBooksByAuthor(authorS2C3);
                                                if (sellableBooksByAuthor == null || sellableBooksByAuthor.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Não livros cadastrados do(a) autor(a) " + authorS2C3,
                                                            "Todos os livros por autor(a)", JOptionPane.ERROR_MESSAGE, errorIcon);
                                                    break;
                                                }
                                                String booksByAuthorStr = "";
                                                for (SellableBook sb : sellableBooksByAuthor) {
                                                    booksByAuthorStr += sb.toString() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null,
                                                        booksByAuthorStr,
                                                        "Seus livros do(a) autor(a) " + authorS2C3, JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                break;
                                            default:
                                                JOptionPane.showMessageDialog(null,
                                                        "Insira um opção válida",
                                                        "Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,informationIcon);
                                        }
                                    }
                                    break;
                                case "3"://Colocar livro para pessoal
                                    if (allSellableBooksMain.isBlank() || allSellableBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    try {
                                        int barCodeS3 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                "Insira o codigo de barras do livro que deseja transformar em livro pessoal \n\n" + allSellableBooksMain,
                                                "Transformar livro vendível em livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            SellableBook bookToPersonal = system.sellableBookByBarCode(barCodeS3);
                                            String statusS3 = statusConstant(statusOptionUI());
                                            system.sellableBookToPersonal(bookToPersonal, statusS3);
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro \"" +bookToPersonal+ "\" foi transformado em livro pessoal",
                                                    "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE,informationIcon);
                                        } catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro não foi encontrado",
                                                    "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "4"://Colocar livro para emprestavel
                                    if (allSellableBooksMain.isBlank() || allSellableBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    try {
                                        int barCodeS4 = Integer.parseInt(JOptionPane.showInputDialog(null,allSellableBooksMain+
                                                " \n\nInsira o codigo de barras do livro que deseja transformar em livro emprestavel",
                                                "Transformar livro vendivel em livro emprestavel", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            SellableBook bookToLoanable = system.sellableBookByBarCode(barCodeS4);
                                            system.sellableBookToLoanable(bookToLoanable);
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro " + bookToLoanable+ " foi transformado em livro emprestável",
                                                    "Transformar livro vendivel em livro emprestavel", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        } catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,
                                                    "Livro não encontrado",
                                                    "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        }

                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "5"://Alterar dados de livro
                                    if (allSellableBooksMain.isBlank() || allSellableBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    try {
                                        int barCodeS6 = Integer.parseInt(JOptionPane.showInputDialog(null,allSellableBooksMain+
                                                " \n\nInsira o codigo de barras do livro que deseja alterar os dados",
                                                "Transformar livro vendivel em livro pessoal", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            SellableBook sb = system.sellableBookByBarCode(barCodeS6);
                                            String changeData = (String) JOptionPane.showInputDialog(null,
                                                    """
                                                            Seus livro foi encontrado!\
                                                             Agora escolha a dado que quer trocar:\
                                                            1- Codigo de barra\
                                                            
                                                            2 - Preço\
                                                            
                                                            3- Título\
                                                            
                                                            4- Autor(a)\
                                                            
                                                            5- Edição\
                                                            
                                                            6- Categoria""",
                                                    "Escolha qual dados quer alterar do livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                            if(changeData == null){
                                                break;
                                            }
                                            switch (changeData) {
                                                case "1":
                                                    try{
                                                        int barCodeS6C1 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                "Insira o codigo de barras do livro que deseja alterar os dados \n\n" + allSellableBooksMain,
                                                                "Alterar código de barra", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                                        sb.setBarcode(barCodeS6C1);
                                                        JOptionPane.showMessageDialog(null,
                                                                "Código de barra alterado com sucerro!\n"+sb,
                                                                "Alterar óodigo de barra", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    } catch (NullPointerException | NumberFormatException e){
                                                        integerNumberFormatExceptionMsg();
                                                    }
                                                    break;
                                                case "2":
                                                    try {
                                                        double priceS6C2 = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                                "Insira o preço do livro",
                                                                "Alterar preço", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                                        sb.setPrice(priceS6C2);
                                                        JOptionPane.showMessageDialog(null,
                                                                "Preço alterado com sucerro!\n"+sb,
                                                                "Alterar preço", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    } catch (NullPointerException | NumberFormatException e){
                                                        doubleNumberFormatExceptionMsg();
                                                    }
                                                    break;
                                                case "3":
                                                    String titleS6C3 = (String) JOptionPane.showInputDialog(null,
                                                            "Insira o título do livro:",
                                                            "Alterar título", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                    if(titleS6C3 == null){
                                                        break;
                                                    }
                                                    if(titleS6C3.isEmpty()){
                                                        nullInformationDataMsg();
                                                        continue;
                                                    }
                                                    sb.setTitle(titleS6C3);
                                                    JOptionPane.showMessageDialog(null,
                                                            "Título alterado com sucerro\n"+sb,
                                                            "Alterar título", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    break;
                                                case "4":
                                                    String authorS6C4 = (String) JOptionPane.showInputDialog(null,
                                                            "Insira o nome do(a) autor(a) do livro:",
                                                            "Alterar o(a) autor(a)", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                    if(authorS6C4 == null){
                                                        break;
                                                    }
                                                    if(authorS6C4.isEmpty()){
                                                        nullInformationDataMsg();
                                                        continue;
                                                    }
                                                    sb.setAuthor(authorS6C4);
                                                    JOptionPane.showMessageDialog(null,
                                                            "O(a) autor(a) foi alterado(a) com sucesso\n"+sb,
                                                            "Alterar o(a) autor(a)", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    break;
                                                case "5":
                                                    try {
                                                        int editioS6C5 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                "Insira a edição:",
                                                                "Alterar a edição", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                                        sb.setEdition(editioS6C5);
                                                        JOptionPane.showMessageDialog(null,
                                                                "A edição do foi alterada com sucesso\n"+sb,
                                                                "Alterar a edição", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    } catch (NullPointerException | NumberFormatException e){
                                                        integerNumberFormatExceptionMsg();
                                                    }
                                                    break;
                                                case "6":
                                                    String categoryS6C6 = (String) JOptionPane.showInputDialog(null,
                                                            "Insira a categoria:",
                                                            "Alterar a categoria", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null);
                                                    if(categoryS6C6 == null){
                                                        break;
                                                    }
                                                    if(categoryS6C6.isEmpty()){
                                                        nullInformationDataMsg();
                                                        continue;
                                                    }
                                                    sb.setCategory(categoryS6C6);
                                                    JOptionPane.showMessageDialog(null,
                                                            "A categoria foi alterada com sucesso\n"+sb,
                                                            "Alterar a categoria", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                                    break;
                                                case "7":
                                                    break;
                                            }
                                        } catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,
                                                    "Livro não encontrado",
                                                    "Mensagem do sistema", JOptionPane.ERROR_MESSAGE,errorIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "6"://Apagar livro
                                    if (allSellableBooksMain.isBlank() || allSellableBooksMain.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                "Não há livros cadastrados",
                                                "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        break;
                                    }
                                    try {
                                        int barCodeS7 = Integer.parseInt(JOptionPane.showInputDialog(null,allSellableBooksMain+
                                                " \n\nInsira o codigo de barras do livro que deseja alterar os dados",
                                                "Apagar livro", JOptionPane.QUESTION_MESSAGE, nullIcon, null, null).toString());
                                        try {
                                            SellableBook sbDelete = system.sellableBookByBarCode(barCodeS7);
                                            system.deleteSellableBook(sbDelete);
                                            JOptionPane.showMessageDialog(null,
                                                    "O livro \""+sbDelete+"\" foi apagado com sucesso",
                                                    "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        } catch (BookDoesNotExistException e){
                                            JOptionPane.showMessageDialog(null,
                                                    "Livro não foi encontrado",
                                                    "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, nullIcon);
                                        }
                                    } catch (NullPointerException | NumberFormatException e){
                                        integerNumberFormatExceptionMsg();
                                    }
                                    break;
                                case "7"://Salvar dados
                                    try {
                                        system.saveData();
                                        JOptionPane.showMessageDialog(null,
                                                "Seus dados forma salvos!",
                                                "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                    } catch (IOException e) {
                                        JOptionPane.showMessageDialog(null,
                                                "Atenção! Seus dados não foram salvos",
                                                "Mensagem do sistema", JOptionPane.ERROR_MESSAGE, errorIcon);
                                        e.printStackTrace();
                                    }
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,
                                            "Insira um opção válida",
                                            "Mensagem do sistema", JOptionPane.ERROR_MESSAGE,nullIcon);
                            }
                        }
                        break;
                    case "Informações":
                        while(true){
                            String [] optionsInformation = {"Info - Acervo particular","Info - Acervo Compartilhado","Info - Acervo Disponível para Compra","Créditos"};
                            JComboBox<String> comboBoxInformation = new JComboBox<>(optionsInformation);
                            int resultInformation = JOptionPane.showConfirmDialog(null,
                                    comboBoxInformation,
                                    "Selecione qual acervo você quer obter informações", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                    informationIcon);
                            if (resultInformation == JOptionPane.OK_OPTION) {
                                String switchInfo = (String) comboBoxInformation.getSelectedItem();
                                switch (switchInfo) {
                                    case "Info - Acervo particular":
                                        JOptionPane.showMessageDialog(null,
                                                "O acervo particular é responsável por gerenciar os livros pessoais, que \n" +
                                                        "armazenam diversas informações. Cada livro possui um código pessoal \n" +
                                                        "gerado a partir da data (ano e mês, no formato yyyyMM) concatenada com um \n" +
                                                        "número aleatório entre 10 e 60; caso esse número já exista em outro cadastro,\n" +
                                                        " o código é gerado novamente. Além disso, são registrados o título do livro, \n" +
                                                        "o(a) autor(a), a edição, a categoria e o status, que pode ser \"para ler\", \n" +
                                                        "\"lendo\" ou \"lido\". No acervo particular, é possível realizar diversas \n" +
                                                        "ações: cadastrar um novo livro, exibir os livros conforme critérios definidos, \n" +
                                                        "transformar um livro pessoal em outro tipo (com um aviso caso o livro ainda não \n" +
                                                        "tenha sido lido, permitindo que você confirme ou cancele a ação), editar informações\n" +
                                                        " de um livro, atualizar seu status, excluir um livro e salvar os dados do acervo.",
                                                "Informações do Acervo Particular",JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        break;
                                    case "Info - Acervo Compartilhado":
                                        JOptionPane.showMessageDialog(null,
                                                "O acervo compartilhado é responsável por gerenciar os livros emprestáveis, \n" +
                                                        "que armazenam informações como a data de empréstimo, registrada automaticamente \n" +
                                                        "no momento do cadastramento com base no tempo real do sistema. Cada livro também\n" +
                                                        " possui um status/nome, que é solicitado no cadastro e pode ser preenchido com o \n" +
                                                        "nome da pessoa para quem será emprestado; caso não seja especificado, o status/nome\n" +
                                                        " é definido como \"Não emprestado\" por padrão. Além disso, são coletados o título, \n" +
                                                        "o(a) autor(a), a edição e a categoria do livro. O sistema permite realizar outras ações,\n" +
                                                        " como exibir os livros cadastrados, transformar um livro \"Não emprestado\" (ou seja, disponível) \n" +
                                                        "em um livro pessoal ou para venda, editar as informações de um livro, excluir registros e salvar \n" +
                                                        "os dados do acervo.",
                                                "Informações do Acervo Compartilhado", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        break;
                                    case "Info - Acervo Disponível para Compra":
                                        JOptionPane.showMessageDialog(null,
                                                "O acervo compartilhado é responsável por gerenciar os livros emprestáveis,\n" +
                                                        " que armazenam informações como a data de empréstimo, registrada automaticamente \n" +
                                                        "no momento do cadastramento com base no tempo real do sistema. Cada livro também \n" +
                                                        "possui um status/nome, que é solicitado no cadastro e pode ser preenchido com o \n" +
                                                        "nome da pessoa para quem será emprestado; caso não seja especificado, \n" +
                                                        "o status/nome é definido como \"Não emprestado\" por padrão. Além disso, \n" +
                                                        "são coletados o título, o(a) autor(a), a edição e a categoria do livro. \n" +
                                                        "O sistema permite realizar outras ações, como exibir os livros cadastrados, \n" +
                                                        "transformar um livro \"Não emprestado\" (ou seja, disponível) em um livro pessoal \n" +
                                                        "ou para venda, editar as informações de um livro, excluir registros e salvar os \n" +
                                                        "dados do acervo.",
                                                "Informações do Acervo Disponível para Compra", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        break;
                                    case "Créditos":
                                        JOptionPane.showMessageDialog(null,
                                                "Meu nome é Jefferson Bezerra, sou estudante de Ciência da Computação na UFPB \n" +
                                                        "e estou atualmente no terceiro período.\n\n Para feedbacks:\nContato - jefferson.bezerra@dcx.ufpb.br",
                                                "Informações do criador do projeto", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                                        break;
                                }
                            } else {
                                break;
                            }
                        }
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Você cancelou a seleção, até mais tarde!",
                        "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE, informationIcon);
                break;
            }
        }
    }
}
