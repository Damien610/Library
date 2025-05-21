package Library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LibraryTest {
    private Library library;
    private Book livreTest;

    @BeforeEach
    public void setUp() {
        library = new Library();
        livreTest = new Book("Titre Test", "Auteur Test", 2020);
    }

    @Test
    public void testAjoutLivre() {
    	library.ajouterLivre(livreTest);
        assertNotNull(library.rechercherLivre("Titre Test"));
    }

    @Test
    public void testSuppressionLivre() {
    	library.ajouterLivre(livreTest);
        assertTrue(library.supprimerLivre("Titre Test"));
        assertNull(library.rechercherLivre("Titre Test"));
        
        library.ajouterLivre(livreTest);
        assertTrue(library.supprimerLivre("titre tEst"));
        assertNull(library.rechercherLivre("Titre Test"));
    }

    @Test
    public void testRechercheLivre() {
    	library.ajouterLivre(livreTest);
        Book result = library.rechercherLivre("titre test");
        assertNotNull(result);
        assertEquals("Auteur Test", result.getAuteur());
    }

    @Test
    public void testListeLivreVidePuisAjout() {
        assertNull(library.rechercherLivre("Inexistant"));
        library.ajouterLivre(livreTest);
        assertNotNull(library.rechercherLivre("Titre Test"));
    }
    
    @Test
    public void testTriLivresParTitre() {
        Book b1 = new Book("Zorro", "Auteur Z", 2000);
        Book b2 = new Book("alice au pays des merveilles", "Auteur A", 1865);
        Book b3 = new Book("Le Seigneur des Anneaux", "Tolkien", 1954);

        library.ajouterLivre(b1);
        library.ajouterLivre(b2);
        library.ajouterLivre(b3);

        library.trierLivresParTitre();
        List<Book> livresTries = library.getLivres(); 

        assertEquals("alice au pays des merveilles", livresTries.get(0).getTitre());
        assertEquals("Le Seigneur des Anneaux", livresTries.get(1).getTitre());
        assertEquals("Zorro", livresTries.get(2).getTitre());
    }
    
    @Test
    public void testRecherchePartielleTitre() {
        Book b1 = new Book("Le Seigneur du Code", "Tolkien.exe", 2001);
        Book b2 = new Book("Code Noir", "Jean C.", 1999);
        Book b3 = new Book("Harry Potar", "J.K. Logging", 2000);

        library.ajouterLivre(b1);
        library.ajouterLivre(b2);
        library.ajouterLivre(b3);

        List<Book> resultats = library.rechercherLivresParMotCle("code");

        assertEquals(2, resultats.size());
        assertTrue(resultats.stream().anyMatch(b -> b.getTitre().equals("Le Seigneur du Code")));
        assertTrue(resultats.stream().anyMatch(b -> b.getTitre().equals("Code Noir")));
    }
    
    @Test
    public void testExportVersFichier() throws Exception {
        Book b1 = new Book("1984", "George Orwell", 1949);
        Book b2 = new Book("Dune", "Frank Herbert", 1965);
        library.ajouterLivre(b1);
        library.ajouterLivre(b2);

        String nomFichier = "test_export.txt";

        library.exporterVersFichier(nomFichier);

        List<String> lignes = Files.readAllLines(Paths.get(nomFichier));

        assertEquals(2, lignes.size());
        assertTrue(lignes.contains("1984 by George Orwell (1949)"));
        assertTrue(lignes.contains("Dune by Frank Herbert (1965)"));

        Files.deleteIfExists(Paths.get(nomFichier));
    }
}
