package Library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}
