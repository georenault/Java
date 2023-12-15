package fr.eni.hello;

import java.util.Scanner;

public class Salaire {

	protected static final double TXCRDS = 0.0349;
	
	private static String var = "fsdg";
	protected static final double TXCSG = 0.0423;
	private static final double TXASSMALADIE = 0.0095;
	private static final double TXASSVIELLESSE = 0.0324;
	private static final double TXCHOMAGE = 0.0305;
	private static final double TXRETCOMPL = 0.0381;
	private static final double TXCOTISAGFF = 0.0102;

//Taux horaires spécifiques
	private static final double TXHORAIRECADRE = 25.0;
	private static final double TXHORAIREAGENTMAITRISE = 15.0;
	private static final double TXHORAIREEMPLOYEBUREAU = 10.0;

//Calcul total des cotisations
	protected static final double TOTALCOTISATIONS = TXCRDS + TXCSG + TXASSMALADIE + TXASSVIELLESSE + TXCHOMAGE
			+ TXRETCOMPL + TXCOTISAGFF;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println(var);

//Saisie des infos connues sur l'employé
		System.out.println("Nom de l'employé");
		String nom = scan.nextLine();
		System.out.println("Prénom de l'employé");
		String prenom = scan.nextLine();
		System.out.println("Statut de l'employé (cadre, agent de maîtrise, employé de bureau");
		String statut = scan.nextLine();
		System.out.println("Nombre d'heures travaillées");
		int heuresTravaillees = scan.nextInt();
		System.out.println("Nombre d'enfants");
		int nombreEnfants = scan.nextInt();
//System.out.println("Salaire de base");

//Calcul du taux horaire
		double tauxHoraire;
		
		if (statut.equalsIgnoreCase("cadre")) {
			tauxHoraire = TXHORAIRECADRE;
		} else if (statut.equalsIgnoreCase("agent de maîtrise")) {
			tauxHoraire = TXHORAIREAGENTMAITRISE;
		} else {
			tauxHoraire = TXHORAIREEMPLOYEBUREAU;
		}

		System.out.println("Modif sur master");
		
		//Calcul du salaire de base par rapport aux heures travaillées et du taux horaire + majorations.
		double salaireBase = heuresTravaillees * tauxHoraire;
		double salaireTotal;
		
		if (heuresTravaillees <= 169) {
			salaireTotal = salaireBase;
		} else if (heuresTravaillees <= 180) {
			salaireTotal = 169 * tauxHoraire + (heuresTravaillees - 169) * tauxHoraire * 1.5;
		} else {
			salaireTotal = 169 * tauxHoraire + 11 * 1.5 * tauxHoraire + (heuresTravaillees - 180) * tauxHoraire * 1.6;
		}

		// Calcul prime en fonction du nbre d'enfants

		double prime;

		if (nombreEnfants == 0) {
			prime = 0;
		} else if (nombreEnfants == 1) {
			prime = 20;
		} else if (nombreEnfants == 2) {
			prime = 50;
		} else {
			prime = 70 + (nombreEnfants - 2) * 20;
		}

		// Calcul total cotisations salariales
		double cotisationsSalariales = salaireTotal * TOTALCOTISATIONS;
		
		// Calcul du montant net à payer
		double netAPayer = salaireTotal + prime - cotisationsSalariales;

		// Affichage du bulletin de paye

		System.out.println("Bulletin de paye de " + prenom + " " + nom);
		System.out.println("Statut : " + statut);
		System.out.printf("Salaire de base : %.2f € \n", salaireBase);
		System.out.printf("Cotisations salariales : %.2f € \n", cotisationsSalariales);
		System.out.printf("Prime : %.2f € \n", prime);
		System.out.printf("Net à payer : %.2f Euros \n", netAPayer);

		// Fermeture du scanner
		scan.close();
	}
}
