package fr.eni.hello;

import java.util.Scanner;

public class Salaire2 {

	public static void main(String[] args) {
		 
		String nom, prenom, affStatut;
		float salaireFinal, salaireMoinsCotis;
		int heuresSuppUn = 0;
		int heuresSuppDeux = 0;
		int statut = -1;
		int heuresTrav = -1;
		int nbEnfants = -1;
		float salaireNet = -1.0f;
		final int TAUX_HORAIRE = 15;
		
		// Détail statut
		final int CADRE = -1;
		final String AFF_CADRE = "Cadre";
		final int AGENT_MAITRISE = 2;
		final String AFF_AGENT = "Agent de maîtrise";
		final int EMPLOYE = 3;
		final String AFF_EMPLOYE = "Employé de bureau";
 
		// Détail taux cotisations
		final float TX_CRDS = 0.0349f;
		final float TX_CSG = 0.0615f;
		final float TX_ASS_MALADIE = 0.0095f;
		final float TX_ASS_VIEILLESSE = 0.0844f;
		final float TX_ASS_CHOMAGE = 0.0305f;
		final float TX_RET_COMPLEMENTAIRE = 0.0381f;
		final float TX_COTISATION_AGFF = 0.0102f;
//		final float TOT _COTISATIONS = TX_CRDS + TX_CSG + TX_ASS_MALADIE + TX_ASS_VIEILLESSE + TX_ASS_CHOMAGE + TX_RET_COMPLEMENTAIRE + TX_COTISATION_AGFF;
		float totalCotisations = TX_CRDS+TX_CSG+TX_ASS_MALADIE+TX_ASS_VIEILLESSE+TX_ASS_CHOMAGE+TX_RET_COMPLEMENTAIRE+TX_COTISATION_AGFF;
		
		// Détail majorations
		final float MajorCentSoixanteNeuf = 1.5f;
		final float MajorCentQuatreVingts = 1.6f;
		final int PrimeEnfantZero = 0;
		final int PrimeEnfantUn = 20;
		final int PrimeEnfantDeux = 50;
		final int PrimeEnfantTrois = 70;
		final int PrimeEnfantSupp = 20;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Saisir le nom du salarié");
		nom = scan.nextLine();
		System.out.println("Saisir le prénom du salarié");
		prenom = scan.nextLine();
		while (statut == -1) {
			System.out.println("Saisir le statut du salarié (1 si cadre, 2 si agent de maîtrise ou 3 si employé de bureau)");
			affStatut = scan.nextLine();
			statut = verifieValeurNumerique(affStatut);
			if (statut != 1 && statut !=2 && statut !=3) {
				statut = -1;
			}
		}
		
		while (heuresTrav<=0) {
			System.out.println("Saisir le nombre d'heures travaillées par le salarié (doit être supérieur à 0)");
			heuresTrav = scan.nextInt();
		}
		while (nbEnfants<0) {
			System.out.println("Saisir le nombre d'enfants du salarié (doit être supérieur ou égal à 0)");
			nbEnfants = scan.nextInt();
		}		
		
		if (heuresTrav > 180) {
			salaireFinal = (heuresTrav - 180) * TAUX_HORAIRE * MajorCentQuatreVingts + 11 * TAUX_HORAIRE * MajorCentSoixanteNeuf + 169 * TAUX_HORAIRE;
		} else if (heuresTrav > 169) {
			salaireFinal = (heuresTrav - 169) * TAUX_HORAIRE * MajorCentSoixanteNeuf + 169 * TAUX_HORAIRE;
		} else {
			salaireFinal = heuresTrav * TAUX_HORAIRE;
		}
		
		salaireMoinsCotis = salaireFinal * (1 - totalCotisations);
 
		switch(nbEnfants) {
			case 0 :
				salaireNet = salaireMoinsCotis;
				break;
			case 1 :
				salaireNet = salaireMoinsCotis + PrimeEnfantUn;
				break;
			case 2 :
				salaireNet = salaireMoinsCotis + PrimeEnfantDeux;
				break;
			default :
				salaireNet = salaireMoinsCotis + PrimeEnfantTrois + PrimeEnfantSupp * (nbEnfants-2);
				break;
		}
		
		/*
		if (nbEnfants == 0) {
			salaireNet = salaireMoinsCotis;
		} else if (nbEnfants == 1) {
			salaireNet = salaireMoinsCotis + PrimeEnfantUn;
		} else if (nbEnfants == 2){	 
			salaireNet = salaireMoinsCotis + PrimeEnfantDeux;
				} else {
					if (nbEnfants == 3) {
						salaireNet = salaireMoinsCotis + PrimeEnfantTrois;
					} else {
						if (nbEnfants>3) {
							salaireNet = salaireMoinsCotis + PrimeEnfantTrois + (nbEnfants-3)*PrimeEnfantSupp;
						}
					}
				}
			}
		}
		*/
		
		switch(statut) {
			case 1 :
				affStatut = AFF_CADRE;
				break;
			case 2 :
				affStatut = AFF_AGENT;
				break;
			default :
				affStatut = AFF_EMPLOYE;
				break;
		}
 
		/* if (statut ==1) {
			affStatut = AFF_CADRE;
		} else {
			if (statut == 2) {
				affStatut = AFF_AGENT;
			} else {
				affStatut = AFF_EMPLOYE;
			}
		} */
		
		System.out.println("Nom et prénom de salarié : " + nom + " " + prenom);
		System.out.println("Statut du salarié : " + affStatut);
		System.out.println("Nombre d'heures travaillées : " + heuresTrav);
		System.out.println("Salaire de base : " + salaireFinal + " euros.");
		System.out.println("Cotisations salariales : " + salaireFinal*totalCotisations + " euros.");
		System.out.println("Salaire net : " + salaireNet + " euros.");
		scan.close();
		
	}
 
	public static int verifieValeurNumerique(String texteSaisi) {
 
		// Cette fonction vérifie que la chaîne de caractères saisie est bien un nombre
		// Renvoie le nombre si c'est un nombre, renvoie -999 sinon
 
		int conversion = -999;
 
		try {
			conversion = (Integer.parseInt(texteSaisi));
		} catch(NumberFormatException exception) {
			conversion = -999;
		}
		return conversion;
	}

}
