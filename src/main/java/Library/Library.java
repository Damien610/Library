package Library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;

public class Library {
    private ArrayList<Book> livres;

    public Library() {
        this.setLivres(new ArrayList<>());
    }

    public void ajouterLivre(Book livre) {
        getLivres().add(livre);
        System.out.println("Livre ajouté : " + livre.getTitre());
    }

    public boolean supprimerLivre(String titre) {
        Iterator<Book> it = getLivres().iterator();
        while (it.hasNext()) {
            Book livre = it.next();
            if (livre.getTitre().equalsIgnoreCase(titre)) {
                it.remove();
                System.out.println("Livre supprimé : " + titre);
                return true;
            }
        }
        System.out.println("Aucun livre trouvé avec le titre : " + titre);
        return false;
    }

    public Book rechercherLivre(String titre) {
        for (Book livre : getLivres()) {
            if (livre.getTitre().equalsIgnoreCase(titre)) {
                System.out.println("Livre trouvé :");
                livre.afficherInfos();
                return livre;
            }
        }
        System.out.println("Aucun livre trouvé avec le titre : " + titre);
        return null;
    }

    public void afficherBibliotheque() {
        if (getLivres().isEmpty()) {
            System.out.println("La bibliothèque est vide.");
        } else {
            System.out.println("Livres dans la bibliothèque :");
            for (Book livre : getLivres()) {
                livre.afficherInfos();
                System.out.println("---");
            }
        }
    }

	public ArrayList<Book> getLivres() {
		return livres;
	}

	public void setLivres(ArrayList<Book> livres) {
		this.livres = livres;
	}
	
	public void trierLivresParTitre() {
	    Collections.sort(livres, Comparator.comparing(Book::getTitre, String.CASE_INSENSITIVE_ORDER));
	}
	
	public List<Book> rechercherLivresParMotCle(String motCle) {
	    List<Book> resultats = new ArrayList<>();
	    for (Book livre : livres) {
	        if (livre.getTitre().toLowerCase().contains(motCle.toLowerCase())) {
	            resultats.add(livre);
	        }
	    }
	    return resultats;
	}
	
	public void exporterVersFichier(String nomFichier) {
	    try (FileWriter writer = new FileWriter(nomFichier)) {
	        for (Book livre : livres) {
	            writer.write(livre.getTitre() + " by " + livre.getAuteur() + " (" + livre.getAnneePublication() + ")\n");
	        }
	        System.out.println("Export effectué avec succès dans : " + nomFichier);
	    } catch (IOException e) {
	        System.out.println("Erreur lors de l'export : " + e.getMessage());
	    }
	}
}
