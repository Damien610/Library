package Library;

public class LibraryFunctionalTest {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("1984", "George Orwell", 1949);
        library.ajouterLivre(book1);

        Book book2 = new Book("Dune", "Frank Herbert", 1965);
        library.ajouterLivre(book2);

        library.supprimerLivre("1984");

        System.out.println("\nLivres restants dans la bibliothèque :");
        for (Book livre : library.getLivres()) {
            System.out.println(livre.getTitre() + " by " + livre.getAuteur() + " (" + livre.getAnneePublication() + ")");
        }
    }
}

