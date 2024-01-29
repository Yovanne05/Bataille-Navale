import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Methode {

    public static final Scanner sc = new Scanner(System.in);
    public static final  Random rd = new Random();
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String CYAN = "\033[0;36m";    // CYAN



    // FONCTIONS OUTILS

    public static char[][] creationPlateau() {
        char[][] plateau = new char[10][10];
        for (char[] cases : plateau) {
            Arrays.fill(cases, '·');
        }
        return plateau;
    }

    /*
     * Retourne la valeur absolue de la différence de deux entiers mis en paramètre
     */
    public static int valAbs(int a, int b) {
        int calcul = a - b;

        if (calcul < 0) {
            calcul = -calcul;
        }
        return calcul;
    }

    /*
     * Effectue plusieurs espaces selon le nombre d'espaces demandés
     */
    public static void espace(int taille) {
        for (int i = 0; i < taille; i++) {
            System.out.print("\n");
        }
    }

    public static void messageCoordonneesTouche(char[][] plateauAttaquant, int ligne, int col){     //Lucas
        int colAffichage;
        char lettreAffichage;

        colAffichage = col + 'A';
        lettreAffichage = (char) colAffichage;

        if (plateauAttaquant[ligne][col] == '❌') {
            System.out.println(RED + "Votre adversaire a touché un de vos bateaux en " + lettreAffichage + "-" + ligne + RESET);
        }
        else{
            System.out.println(BLUE + "Votre adversaire a raté son tir en " + lettreAffichage + "-" + ligne +"\n"+ RESET);
        }
    }

    // FIN FONCTIONS OUTILS


    // FONCTIONS D'AFFICHAGE

    public static void afficherPlateau(char[][] plateau) {
        System.out.print(BLUE + "\tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ" + RESET);
        System.out.println();
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            System.out.print(BLUE + ligne + "\t" + RESET);
            for (int col = 0; col < plateau[ligne].length; col++) {
                System.out.print(plateau[ligne][col] + "\t");
            }
            System.out.println();
        }
    }

    /*
     * Affiche un plateau adapté pour le joueur :
     * Il remplace les symboles par des carrés
     */
    public static void afficherPlateauPourUtilisateur(char[][] plateau) {       //Yovanne
        System.out.print(BLUE + "\tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ" + RESET);
        System.out.println();
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            System.out.print(BLUE + ligne + "\t" + RESET);

            for (int col = 0; col < plateau[ligne].length; col++) {
                if (plateau[ligne][col] == 'P' || plateau[ligne][col] == 'C' || plateau[ligne][col] == 'T' || plateau[ligne][col] == 'S' || plateau[ligne][col] == 'D') {
                    System.out.print('■' + "\t");
                } else {
                    System.out.print(plateau[ligne][col] + "\t");
                }
            }
            System.out.println();
        }
    }

    /*
     * Affiche des messages lorsque le joueur a abattu un bateau
     */
    public static void afficheBateauDetruit(char[][] plateauPlacementBat, char[][] plateauAttaquant) {          //Yovanne
        List<Integer> bateauDetruit;
        bateauDetruit = nbBateauDetruit(plateauPlacementBat, plateauAttaquant);
        while (!bateauDetruit.isEmpty()) {
            int bateau = bateauDetruit.get(0);
            if (bateau == 5) {
                System.out.println("Vous avez eu le Porte-Avion");
            } else if (bateau == 4) {
                System.out.println("Vous avez eu le Cuirassé");
            } else if (bateau == 31) {
                System.out.println("Vous avez eu le Contre-Torpilleur");
            } else if (bateau == 32) {
                System.out.println("Vous avez eu le Sous-Marin");
            }
            else if (bateau == 2) {
                System.out.println("Vous avez eu le Destroyer");
            }
            bateauDetruit.remove(0);
        }

    }

    /*
     * Affiche des messages lorsque le joueur a abattu un bateau
     */
    public static void afficheBateauDetruitIA(char[][] plateauPlacementBat, char[][] plateauAttaquant) {        //Lucas
        List<Integer> bateauDetruit;
        bateauDetruit = nbBateauDetruit(plateauPlacementBat, plateauAttaquant);
        while (!bateauDetruit.isEmpty()) {
            int bateau = bateauDetruit.get(0);
            if (bateau == 5) {
                System.out.println("Votre adversaire a détruit votre Porte-Avion");
            } else if (bateau == 4) {
                System.out.println("Votre adversaire a détruit votre Cuirassé");
            } else if (bateau == 31) {
                System.out.println("Votre adversaire a détruit votre Contre-Torpilleur");
            } else if (bateau == 32) {
                System.out.println("Votre adversaire a détruit votre Sous-Marin");
            } else if (bateau == 2) {
                System.out.println("Votre adversaire a détruit votre Destroyer");
            }
            bateauDetruit.remove(0);
        }

    }

    // FIN DES FONCTIONS D'AFFICHAGES



    // FONCTIONS DES PLACEMENTS DES BATEAUX


    /*
     * Cette méthode demande au joueur de choisir les bateaux qu'il veut placer sur son plateau.
     */
    public static void placementBateau(char[][] plateau, String joueur) {           //Yovanne
        int choix = 0;
        boolean invalide;

        //Bateaux à déployer
        List<Integer> porteAvion = new ArrayList<>();
        porteAvion.add(5);
        List<Integer> cuirasse = new ArrayList<>();
        cuirasse.add(4);
        List<Integer> contreTorpilleur = new ArrayList<>();
        contreTorpilleur.add(31);
        List<Integer> sousMarin = new ArrayList<>();
        sousMarin.add(32);
        List<Integer> destroyer = new ArrayList<>();
        destroyer.add(2);


        afficherPlateauPourUtilisateur(plateau);
        System.out.println("\n" + joueur + CYAN + ", vous disposez des cinqs bateaux ci-dissous:\n"+RESET);

        do {
            if (!porteAvion.isEmpty()) {
                System.out.println("1. Porte-Avion taille: 5");
            } else {
                System.out.println(YELLOW+"Le Porte-avion est déployé !"+RESET);
            }
            if (!cuirasse.isEmpty()) {
                System.out.println("2. Cuirassé taille: 4");
            } else {
                System.out.println(YELLOW+"Le Cuirassé est déployé !"+RESET);
            }
            if (!contreTorpilleur.isEmpty()) {
                System.out.println("3. Contre-Torpilleur taille: 3");
            } else {
                System.out.println(YELLOW+"Le Contre-Torpilleur est déployé !"+RESET);
            }
            if (!sousMarin.isEmpty()) {
                System.out.println("4. Sous-Marin taille: 3");
            } else {
                System.out.println(YELLOW+"Le Sous-marin est déployé !"+RESET);
            }
            if (!destroyer.isEmpty()) {
                System.out.println("5. Destroyer taille: 2");
            } else {
                System.out.println(YELLOW+"Le Destroyer est déployé !"+RESET);
            }
            do {
                invalide=false;
                try {
                    System.out.println(CYAN+"\nChoissisez le bateau que vous voulez placez"+RESET);
                    choix = sc.nextInt();
                }catch (InputMismatchException e){            // Capture une erreur puis affiche un message si la saisie est incorrecte
                    invalide = true;
                    System.out.println(YELLOW+"ERREUR: Veuillez sélectionner un des chiffres suivants: 1,2,3 ou 4\n"+RESET);
                    sc.next();
                }
            }while ((choix < 1 || choix > 5) && invalide);

            if (choix == 1){
                if (porteAvion.isEmpty()){
                    System.out.println(YELLOW+"\nLe porte-avion est déjà déployé\n"+RESET);
                }
                else{
                    renseignementBateau(plateau, porteAvion.get(0));
                    porteAvion.remove(0);
                    afficherPlateauPourUtilisateur(plateau);
                }
            } else if (choix == 2) {
                if (cuirasse.isEmpty()){
                    System.out.println(YELLOW+"\nLe cuirassé est déjà déployé\n"+RESET);
                }else{
                    renseignementBateau(plateau, cuirasse.get(0));
                    cuirasse.remove(0);
                    afficherPlateauPourUtilisateur(plateau);
                }
            }
            else if (choix == 3) {
                if (contreTorpilleur.isEmpty()){
                    System.out.println(YELLOW+"\nLe contre-torpilleur est déjà déployé\n"+RESET);
                }else{
                    renseignementBateau(plateau, contreTorpilleur.get(0));
                    contreTorpilleur.remove(0);
                    afficherPlateauPourUtilisateur(plateau);
                }
            }
            else if (choix == 4) {
                if (sousMarin.isEmpty()){
                    System.out.println(YELLOW+"\nLe sous-marin est déjà déployé\n"+RESET);
                }else{
                    renseignementBateau(plateau, sousMarin.get(0));
                    sousMarin.remove(0);
                    afficherPlateauPourUtilisateur(plateau);
                }
            }
            else if (choix == 5){
                if (destroyer.isEmpty()){
                    System.out.println(YELLOW+"\nLe destroyer est déjà déployé\n"+RESET);
                }else{
                    renseignementBateau(plateau, destroyer.get(0));
                    destroyer.remove(0);
                    afficherPlateauPourUtilisateur(plateau);
                }
            }
            else{
                System.out.println(YELLOW+"ERREUR:  Veuillez sélectionner un des chiffres suivants: 1,2,3,4 ou 5\n"+RESET);
            }
        } while (!porteAvion.isEmpty() || !cuirasse.isEmpty() || !contreTorpilleur.isEmpty() || !sousMarin.isEmpty() || !destroyer.isEmpty()); // S'arrête lorsque tous les bateaux seront déployés
    }

    /*
     * Cette méthode modifie le tableau en parametre en plaçant les bateaux aléatoirement
     */
    public static void placementBatIA(char[][] plateau){        //Lucas
        for (int i = 2 ; i <= 5; i++){
            if (i == 3){
                placementAleatoire(plateau,i);
                modifieSymboleBateau3(plateau);
            }
            placementAleatoire(plateau, i);
        }
    }


    /*
     * Cette méthode collecte les informations nécessaires pour placer les bateaux
     */
    public static void renseignementBateau(char[][] plateau,int tailleBateau) {     //Yovanne
        int ligne = 0, ligne1 = 0, ligne2 = 0;
        int colInt, colInt1, colInt2;

        char col, col1, col2;

        int choix = 0;
        boolean invalide;

        do {
            invalide = false;
            try {
                System.out.println(CYAN+"Voulez-vous le mettre horizontalement ou verticalement ?\nTappez 0 pour horizontal, 1 pour verticale"+RESET);
                choix = sc.nextInt();
            }catch (InputMismatchException e){            // Capture une erreur puis affiche un message si la saisie est incorrecte
                invalide = true;
                System.out.println(YELLOW+"\nERREUR: Veuillez sélectionner un des chiffres suivants: 0 ou 1\n"+RESET);
                sc.next();
            }
        } while (choix < 0 || choix > 1 || invalide);

        char symbole;
        if (tailleBateau == 5) {
            symbole = 'P';
        } else if (tailleBateau == 4) {
            symbole = 'C';
        } else if (tailleBateau == 31) {
            symbole = 'T';
            tailleBateau = 3;
        }
        else if(tailleBateau==32){
            symbole = 'S';
            tailleBateau=3;
        }else {
            symbole = 'D';
        }

        switch (choix) {
            case 0:
                do {
                    invalide = false;
                    try {
                        System.out.println(CYAN+"Indiquer la ligne du bateau"+RESET);
                        System.out.println("Ligne: ");
                        ligne = sc.nextInt();
                    }catch (InputMismatchException e){            // Capture une erreur puis affiche un message si la saisie est incorrecte
                        invalide = true;
                        System.out.println(YELLOW+"\nERREUR: Sélectionner une ligne existante\n"+RESET);
                        sc.next();
                    }
                } while (ligne < 0 || ligne > 9 || invalide);

                do {
                    do {
                        do {
                            System.out.println(CYAN+"Indiquer les extrémités du bateau"+RESET);
                            System.out.println("Colonne n°1:");
                            col1 = sc.next().toUpperCase().charAt(0);
                            if (col1 < 'A' || col1 > 'J'){
                                System.out.println(YELLOW+"\nCette colonne n'existe pas\n"+RESET); // message d'erreur si la lettre saisie est incorrecte
                            }
                        } while (col1 < 'A' || col1 > 'J');
                        colInt1 = col1 - 'A';
                        do {
                            System.out.println("Colonne n°2:");
                            col2 = sc.next().toUpperCase().charAt(0);
                            if (col2 < 'A' || col2 > 'J'){
                                System.out.println(YELLOW+"\nCette colonne n'existe pas\n"+RESET); // message d'erreur si la lettre saisie est incorrecte
                            }
                        } while (col2 < 'A' || col2 > 'J');
                        colInt2 = col2 - 'A';

                        if (valAbs(colInt1, colInt2) != tailleBateau - 1){
                            System.out.println(YELLOW+"\nLes extrémités sont incorrectes\n"+RESET);
                        }

                    } while (valAbs(colInt1, colInt2) != tailleBateau - 1);
                    if (verifPlacementX(plateau, ligne, colInt1, colInt2)) {
                        System.out.println(YELLOW+"\nUn bateau est déjà présent sur le chemin\n"+RESET);
                    }
                }while(verifPlacementX(plateau, ligne, colInt1, colInt2));

                placementBateauX(plateau, ligne, colInt1, colInt2, symbole);

                afficherPlateauPourUtilisateur(plateau);
                do {
                    invalide = false;
                    try {
                        System.out.println(CYAN+"\nÊtes-vous sûr de votre choix ? (vous ne pourrez plus le modifier):");
                        System.out.println("0 pour OUI, 1 pour MODIFIER"+RESET);
                        choix = sc.nextInt();
                    }
                    catch (InputMismatchException e){
                        invalide = true;
                        System.out.println(YELLOW+"\nERREUR: Veuillez sélectionner un des chiffres suivants: 0 ou 1\n"+RESET);
                        sc.next();
                    }
                }while (choix < 0 || choix >1 || invalide);
                if (choix == 1){
                    effacementPlacementBateauX(plateau,ligne,colInt1,colInt2);
                    renseignementBateau(plateau, tailleBateau);
                }
                espace(22);
                break;

            case 1:
                do {
                    System.out.println(CYAN+"Indiquer la colonne du bateau"+RESET);
                    System.out.println("Colonne: ");
                    col = sc.next().toUpperCase().charAt(0);
                    if (col < 'A' || col > 'J'){
                        System.out.println(YELLOW+"\nCette colonne n'existe pas\n"+RESET); // message d'erreur si la lettre saisie est incorrecte
                    }
                } while (col < 'A' || col > 'J');

                colInt = col - 'A';

                do {
                    do {
                        do {
                            invalide = false;
                            try{
                                System.out.println(CYAN+ "Indiquer les extrémités du bateau"+RESET);
                                System.out.println("Ligne n°1:");
                                ligne1 = sc.nextInt();
                            }catch (InputMismatchException e){            // Capture une erreur puis affiche un message si la saisie est incorrecte
                                invalide = true;
                                System.out.println(YELLOW+"\nERREUR: Sélectionner une ligne existante\n"+RESET);
                                sc.next();
                            }
                        }while(ligne1 < 0 || ligne1 > 9 || invalide); // s'effectue tant que la saisie est mauvaise
                        do {
                            invalide = false;
                            try{
                                System.out.println("Ligne n°2:");
                                ligne2 = sc.nextInt();
                            }catch (InputMismatchException e){            // Capture une erreur puis affiche un message si la saisie est incorrecte
                                invalide = true;
                                System.out.println(YELLOW+"\nERREUR: Sélectionner une ligne existante\n"+RESET);
                                sc.next();
                            }
                        }while(ligne2 < 0 || ligne2 > 9 || invalide);// s'effectue tant que la saisie est mauvaise
                        if (valAbs(ligne1, ligne2) != tailleBateau - 1){  //// affiche un message d'erreur si les extrémités sont incorrectes
                            System.out.println(YELLOW+"\nLes extrémités sont incorrectes\n"+RESET);
                        }

                    } while (valAbs(ligne1, ligne2) != tailleBateau - 1); // s'effectue tant que les extrémités ne conviennent pas à la taille du bateau

                    if (verifPlacementY(plateau, colInt, ligne1, ligne2)) {  // affiche un message d'erreur si un bateau est déja présent
                        System.out.println(YELLOW+"\nUn bateau est déjà présent sur le chemin\n"+RESET);
                    }

                }while(verifPlacementY(plateau, colInt, ligne1, ligne2));    // s'effectue tant que le bateau ne peut pas être bien placé

                placementBateauY(plateau, colInt, ligne1, ligne2, symbole);
                afficherPlateauPourUtilisateur(plateau);
                do {
                    invalide = false;
                    try {
                        System.out.println(CYAN+"\nÊtes-vous sûr de votre choix ? (vous ne pourrez plus le modifier):");
                        System.out.println("0 pour OUI, 1 pour MODIFIER"+RESET);
                        choix = sc.nextInt();
                    }
                    catch (InputMismatchException e){
                        invalide = true;
                        System.out.println(YELLOW+"\nERREUR: Veuillez sélectionner un des chiffres suivants: 0 ou 1\n"+RESET);
                        sc.next();
                    }
                }while (choix < 0 || choix >1 || invalide);
                if (choix == 1){
                    effacementPlacementBateauY(plateau,colInt,ligne1,ligne2);
                    renseignementBateau(plateau, tailleBateau);
                }
                espace(22);
                break;
        }
    }


    /*
     * Cette méthode place le bateau aleatoirement
     */
    public static void placementAleatoire(char[][] t, int tailleBateau){        //Lucas

        int colAleatoire;
        int col2Aleatoire;

        int ligneAleatoire;
        int ligne2Aleatoire;

        char symbole;

        if (tailleBateau == 5) {
            symbole = 'P';
        } else if (tailleBateau == 4) {
            symbole = 'C';
        } else if (tailleBateau == 3) {
            symbole = 'T';
        }
        else {
            symbole = 'D';
        }

        int choix = rd.nextInt(2);

        if (choix == 0){  // bateau hozirontal
            do {
                ligneAleatoire = rd.nextInt(10);
                do {
                    colAleatoire = rd.nextInt(10);
                    col2Aleatoire = rd.nextInt(10);
                }while(valAbs(colAleatoire,col2Aleatoire) != tailleBateau - 1);
            }while (verifPlacementX(t,ligneAleatoire,colAleatoire,col2Aleatoire));

            placementBateauX(t,ligneAleatoire,colAleatoire,col2Aleatoire,symbole);
        }
        else{ // bateau vertical

            do {
                colAleatoire = rd.nextInt(10);
                do {
                    ligneAleatoire = rd.nextInt(10);
                    ligne2Aleatoire = rd.nextInt(10);

                }while(valAbs(ligneAleatoire,ligne2Aleatoire) != tailleBateau - 1);
            }while (verifPlacementY(t,colAleatoire,ligneAleatoire,ligne2Aleatoire));

            placementBateauY(t,colAleatoire,ligneAleatoire,ligne2Aleatoire,symbole);
        }

    }



    /*
     * Place une ligne horizontale composée du symbole, donnée en paramètre, dans un tableau double de char, selon les coordonnées mise en paramètre.
     * Elle prend également en paramètre le numéro de ligne et les extrémités de la ligne horizontale
     */
    public static void placementBateauX(char[][] tab, int x, int y1, int y2, char symbole) {        //Yovanne
        if (y1 < y2) {
            for (int i = y1; i < y2 + 1; i++) {
                tab[x][i] = symbole;
            }
        } else {
            for (int i = y2; i < y1 + 1; i++) {
                tab[x][i] = symbole;
            }
        }
    }

    /*
     * Place une ligne verticale composée du symbole, donnée en paramètre, dans un tableau double de char, selon les coordonnées mise en paramètre.
     * Elle prend également en paramètre le numéro de la colonne et les extrémités de la ligne verticale
     */
    public static void placementBateauY(char[][] tab, int y, int x1, int x2, char symbole) {        //Yovanne
        if (x1 < x2) {
            for (int i = x1; i <= x2; i++) {
                tab[i][y] = symbole;
            }
        } else {
            for (int i = x2; i <= x1; i++) {
                tab[i][y] = symbole;
            }
        }
    }

    /*
     * Place une ligne horizontale composée du symbole, donnée en paramètre, dans un tableau double de char, selon les coordonnées mise en paramètre.
     * Elle prend également en paramètre le numéro de ligne et les extrémités de la ligne horizontale
     */
    public static void effacementPlacementBateauX(char[][] plateauPlacementBat, int x, int y1, int y2){     //Yovanne
        if (y1 < y2) {
            for (int i = y1; i < y2 + 1; i++) {
                plateauPlacementBat[x][i] = '·';
            }
        } else {
            for (int i = y2; i < y1 + 1; i++) {
                plateauPlacementBat[x][i] = '·';
            }
        }
    }


    /*
     * Efface une ligne verticale composée du symbole, donnée en paramètre, dans un tableau double de char, selon les coordonnées mise en paramètre.
     * Elle prend également en paramètre le numéro de la colonne et les extrémités de la ligne verticale
     */
    public static void effacementPlacementBateauY(char[][] plateauPlacementBat, int y, int x1, int x2){     //Yovanne
        if (x1 < x2) {
            for (int i = x1; i <= x2; i++) {
                plateauPlacementBat[i][y] = '·';
            }
        } else {
            for (int i = x2; i <= x1; i++) {
                plateauPlacementBat[i][y] = '·';
            }
        }
    }


    /*
     * Vérifie si le placement d'une nouvelle ligne horizontal ne passe pas sur une ligne déja mise dans le tableau.
     */
    public static boolean verifPlacementX(char[][] tab, int x, int y1, int y2) {        //Yovanne
        boolean presence = false;
        if (y1 < y2) {
            for (int i = y1; i < y2 + 1; i++) {
                if (tab[x][i] != '·' ) {
                    presence = true;
                }
            }
        } else {
            for (int i = y2; i < y1 + 1; i++) {
                if (tab[x][i] != '·') {
                    presence = true;
                }
            }
        }
        return presence;
    }

    /*
     * Vérifie si le placement d'une nouvelle ligne verticale ne passe pas sur une ligne déja mise dans le tableau.
     */
    public static boolean verifPlacementY(char[][] tab, int y, int x1, int x2) {        //Yovanne
        boolean presence = false;
        if (x1 < x2) {
            for (int i = x1; i <= x2; i++) {
                if (tab[i][y] != '·')
                    presence = true;
            }
        } else {
            for (int i = x2; i <= x1; i++) {
                if (tab[i][y] != '·')
                    presence = true;
            }
        }
        return presence;
    }


    // FIN FONCTIONS POUR LES PLACEMENT DES BATEAUX



    // FONCTIONS POUR LE DEROULEMENT DE LA PARTIE


    /*
     * Cette procédure modifie le symbole du deuxieme bateau de 3 cases pour pouvoir le distinguer lors du déroulement de la partie
     * Le contre-torpilleur aura pour symbole 'T' et le sous-marin aura 'S'
     */
    public static void modifieSymboleBateau3(char[][] plateauPlacementBat) {     //Marche uniquement si le nombre de O dans le tableau est de 6 sinon bugint cpt = 0 ;      //Yovanne
        for (int i = 0; i < plateauPlacementBat.length; i++) {
            for (int j = 0; j < plateauPlacementBat[i].length; j++) {
                if (plateauPlacementBat[i][j] == 'T') {
                    plateauPlacementBat[i][j] = 'S';
                }
            }
        }
    }


    /*
     * Retourne une liste qui comptabilise le nombre de bateaux détruits
     */
    public static List<Integer> nbBateauDetruit(char[][] plateauPlacementBat, char[][] plateauAttaquant) {      //Yovanne
        List<Integer> bateauxDetruits = new ArrayList<>();
        int cptPorteAv = 0, cptCuirasse = 0, cptContreTor = 0, cptSousMarin = 0, cptDestroyer = 0;

        for (int i = 0; i < plateauPlacementBat.length; i++) {
            for (int j = 0; j < plateauPlacementBat[i].length; j++) {
                if (plateauAttaquant[i][j] == '❌') {
                    if (plateauPlacementBat[i][j] == 'P') {
                        cptPorteAv++;
                    } else if (plateauPlacementBat[i][j] == 'C') {
                        cptCuirasse++;
                    } else if (plateauPlacementBat[i][j] == 'T') {
                        cptContreTor++;
                    }else if (plateauPlacementBat[i][j] == 'S') {
                        cptSousMarin++;
                    }
                    else if (plateauPlacementBat[i][j] == 'D') {
                        cptDestroyer++;
                    }
                }
            }
        }
        if (cptPorteAv == 5) {
            bateauxDetruits.add(5);
        }
        if (cptCuirasse == 4) {
            bateauxDetruits.add(4);
        }
        if (cptContreTor == 3) {
            bateauxDetruits.add(31);
        }
        if (cptSousMarin == 3) {
            bateauxDetruits.add(32);
        }
        if (cptDestroyer == 2) {
            bateauxDetruits.add(2);
        }
        return bateauxDetruits;
    }


    /*
     * Cette fonction modifie les tableaux d'attaques de la partie des joueurs
     * Les tirs touchés modifient les coordonnées du plateau d'attaque en croix rouge et les tirs coulés en des ronds blancs
     */
    public static void tir(char[][] plateauPlacementBat, char[][] plateauAttaquant, int ligne, int col) {       //Yovanne

        if (plateauPlacementBat[ligne][col] != '·')   {
            plateauAttaquant[ligne][col] = '❌';
            afficherPlateau(plateauAttaquant);
            System.out.println(RED + "\nUn bateau a été touché !!\n"+ RESET);
        }
        else {
            plateauAttaquant[ligne][col] = '●';
            afficherPlateau(plateauAttaquant);
            System.out.println(BLUE + "\n\t\t\t  Tir à l'eau...\n" +RESET);
            System.out.print(CYAN+"Cliquer sur 'Entrée' pour continuer"+RESET);
            sc.nextLine(); // le premier scanner sert à récupérer 'l'espace' crée lors de l'initialisation des coordonnées
            sc.nextLine();
        }
        afficheBateauDetruit(plateauPlacementBat, plateauAttaquant);

    }


    public static void tirAleatoire(char[][] plateauPlacementBat, char[][] plateauAttaquant, int ligne, int col){       //Lucas
        int sens;

        int colAffichage;
        char lettreAffichage;

        if (plateauPlacementBat[ligne][col] != '·'){
            sens = rd.nextInt(2);
            if (sens == 0){
                tirAutomatiqueX(plateauPlacementBat,plateauAttaquant,ligne,col);
            }
            else {
                tirAutomatiqueY(plateauPlacementBat,plateauAttaquant,ligne,col);
            }
        }
        else{
            plateauAttaquant[ligne][col] = '●';
            messageCoordonneesTouche(plateauAttaquant,ligne,col);
        }
    }


    /*
     * Cette méthode est censée tirer en continu l'axe x de la coordonnée, affectée en paramètre, tant que celle-ci touche juste.
     * Elle tire par défaut vers la gauche, mais si le point se situe au bord gauche du plateau, il ira vers la droite et inversement.
     * Si celle-ci se retrouve face à une case déjà occupée, elle reprend la chasse en repartant de la coordonnée initiale puis continue vers la direction opposée.
     * Si ce n'est pas possible, cela signifie que l'IA a détruit un bateau, ainsi, il doit retirer aléatoirement.
     */
    public static void tirAutomatiqueX(char[][] plateauPlacementBat, char[][] plateauAttaquant, int ligne, int col){        //Lucas

        if (col >= 1 && plateauAttaquant[ligne][col - 1] == '·'){   // le tir n'est pas au bord du plateau ou a un obstacle sur sa gauche : Peut tirer à gauche
            tirAutomatiqueGauche(plateauPlacementBat,plateauAttaquant,ligne,col);
        }
        else { // tire à droite
            tirAutomatiqueDroite(plateauPlacementBat,plateauAttaquant,ligne,col);
        }
    }

    /*
     * Cette méthode est censée tirer en continu l'axe y de la coordonnée, affectée en paramètre, tant que celle-ci touche juste.
     * Elle tire par défaut vers le haut, mais si le point se situe au bord supérieur du plateau, il ira vers le bas et inversement.
     * Si celle-ci se retrouve face à une case déjà occupée ou au bord du plateau, elle reprend la chasse en repartant de la coordonnée initiale puis continue vers la direction opposée.
     * Si ce n'est pas possible, cela signifie que l'IA a détruit un bateau, ainsi, il doit retirer aléatoirement.
     */
    public static void tirAutomatiqueY(char[][] plateauPlacementBat, char[][] plateauAttaquant, int ligne, int col){        //Lucas
        if (ligne >= 1 && plateauAttaquant[ligne - 1][col] == '·'){   // le tir n'est pas au bord du plateau ou a un obstacle en haut : Peut tirer vers le haut
            tirAutomatiqueHaut(plateauPlacementBat,plateauAttaquant,ligne,col);
        }
        else { // tire vers le bas
            tirAutomatiqueBas(plateauPlacementBat,plateauAttaquant,ligne,col);
        }
    }


    /*
     * Cette méthode est censée tirer en continu l'axe y de la coordonnée, affectée en paramètre, tant que celle-ci touche juste.
     * Elle tire par défaut vers le haut, mais si le point se situe au bord supérieur du plateau, il ira vers le bas et inversement.
     * Si celle-ci se retrouve face à une case déjà occupée ou au bord du plateau, elle reprend la chasse en repartant de la coordonnée initiale puis continue vers la direction opposée.
     * Si ce n'est pas possible, cela signifie que l'IA a détruit un bateau, ainsi, il doit retirer aléatoirement.
     */
    public static void tirAutomatiqueGauche(char[][] plateauPlacementBat, char[][] plateauAttaquant, int ligne, int col){       //Lucas
        int colInitiale;

        int ligne2, col2;

        if (col == 9){
            colInitiale = col;
        }else{
            colInitiale = col+1;
        }

        if (plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] != '❌'){
            do {        //tire tant que le tir est réussi et qu'il n'a pas déja été découvert
                plateauAttaquant[ligne][col] = '❌';  // bateau touché
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
                col--;
                if (col == -1){
                    col++;
                }
            }while (col > 0 && plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] == '·');

            if (plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] == '·'){ // le tir est au bord du plateau, mais vérifie s'il est réussi
                plateauAttaquant[ligne][col] = '❌';  // bateau touché
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
            }

            if ((plateauAttaquant[ligne][col] == '●' && plateauAttaquant[ligne][colInitiale] == '●' )
                    || (plateauAttaquant[ligne][col] == '●' && colInitiale == 9 && plateauAttaquant[ligne][colInitiale] == '❌')
                    || (plateauAttaquant[ligne][col] == '❌' && plateauAttaquant[ligne][colInitiale] == '●')
                    || (plateauAttaquant[ligne][col] == '❌' && col == 0 && plateauAttaquant[ligne][colInitiale] == '●')){  // tire juste, mais sa ligne de tir se retrouve soit entouré bord du plateau et d'un obstacle, soit entouré par deux obstacles.
                do {
                    ligne2 = rd.nextInt(10);
                    col2 = rd.nextInt(10);
                } while (plateauAttaquant[ligne2][col2] != '·');
                tirAleatoire(plateauPlacementBat,plateauAttaquant,ligne2,col2);   //retire aléatoirement
            }

            if ((plateauAttaquant[ligne][col] == '●' && (plateauAttaquant[ligne][colInitiale] == '·' || plateauAttaquant[ligne][colInitiale] == '❌'))    // se retrouve sur un tir raté devant lui, mais la case à droite du point initiale est, soit inconnue, soit un tir réussi
                    || (plateauAttaquant[ligne][col] == '❌' && col == 0 && (plateauAttaquant[ligne][colInitiale] == '·' || plateauAttaquant[ligne][colInitiale] == '❌'))  // se retrouve au bord du plateau, mais la case à droite du point initiale est, soit inconnue, soit un tir réussi
                    || (plateauAttaquant[ligne][col] == '❌' && col == 0)){ // se retrouve au bord du plateau
                tirAutomatiqueDroite(plateauPlacementBat,plateauAttaquant,ligne,colInitiale);   // continue sur la droite
            }

            if (plateauPlacementBat[ligne][col] == '·' && plateauAttaquant[ligne][col] != '●'){    // le tir est raté.
                plateauAttaquant[ligne][col] = '●';
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
            }
        }
        else if (plateauPlacementBat[ligne][col] == '·' && plateauAttaquant[ligne][col] != '●'){   // vérifie si le tir incorrect n'a pas déjà été saisie
            plateauAttaquant[ligne][col] = '●';
            messageCoordonneesTouche(plateauAttaquant,ligne,col);
        }
        else if ((plateauAttaquant[ligne][col] == '●' && plateauAttaquant[ligne][colInitiale] == '●' )
                || (plateauAttaquant[ligne][col] == '●' && colInitiale == 9 && plateauAttaquant[ligne][colInitiale] == '❌')
                || (plateauAttaquant[ligne][col] == '❌' && col == 0 && plateauAttaquant[ligne][colInitiale] == '●')){      // retire au hasard
            do {
                ligne2 = rd.nextInt(10);
                col2 = rd.nextInt(10);
            } while (plateauAttaquant[ligne2][col2] != '·');
            tirAleatoire(plateauPlacementBat,plateauAttaquant,ligne2,col2);
        }

    }

    public static void tirAutomatiqueDroite(char[][] plateauPlacementBat, char[][] plateauAttaquant, int ligne, int col){       //Lucas
        int colInitiale;

        int ligne2, col2;

        if (col == 0){
            colInitiale = col;
        }else{
            colInitiale = col-1;
        }

        if (plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] != '❌'){    // vérifie si le tir est correcte
            do {        //tire tant que le tir est réussi et qu'il n'a pas déja été découvert
                plateauAttaquant[ligne][col] = '❌';  // bateau touché
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
                col++;
                if (col == 10){
                    col--;
                }
            }while (col < 9 && plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] == '·');

            if (plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] == '·'){ // le tir est au bord du plateau, mais vérifie s'il est réussi
                plateauAttaquant[ligne][col] = '❌';  // bateau touché
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
            }

            if ((plateauAttaquant[ligne][col] == '●' && plateauAttaquant[ligne][colInitiale] == '●' )
                    || (plateauAttaquant[ligne][col] == '●' && colInitiale == 0 && plateauAttaquant[ligne][colInitiale] == '❌')
                    || (plateauAttaquant[ligne][col] == '❌' && plateauAttaquant[ligne][colInitiale] == '●')
                    || (plateauAttaquant[ligne][col] == '❌' && col == 9 && plateauAttaquant[ligne][colInitiale] == '●')){ // tire juste, mais sa ligne de tir se retrouve soit entouré bord du plateau et d'un obstacle, soit entouré par deux obstacles.
                do {
                    ligne2 = rd.nextInt(10);
                    col2 = rd.nextInt(10);
                } while (plateauAttaquant[ligne2][col2] != '·');
                tirAleatoire(plateauPlacementBat,plateauAttaquant,ligne2,col2);   //retire aléatoirement
            }

            if ((plateauAttaquant[ligne][col] == '●' && (plateauAttaquant[ligne][colInitiale] == '·' || plateauAttaquant[ligne][colInitiale] == '❌'))    // se retrouve sur un tir raté devant lui, mais la case à gauche du point initiale est, soit inconnue, soit un tir réussi
                    || (plateauAttaquant[ligne][col] == '❌' && col == 9 && (plateauAttaquant[ligne][colInitiale] == '·' || plateauAttaquant[ligne][colInitiale] == '❌'))  // se retrouve au bord du plateau, mais la case à gauche du point initiale est, soit inconnue, soit un tir réussi
                    || (plateauAttaquant[ligne][col] == '❌' && col == 9)){ // se retrouve au bord du plateau
                tirAutomatiqueGauche(plateauPlacementBat,plateauAttaquant,ligne,colInitiale);   // va sur la gauche
            }

            if (plateauPlacementBat[ligne][col] == '·' && plateauAttaquant[ligne][col] != '●'){    // le tir est raté.
                plateauAttaquant[ligne][col] = '●';
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
            }
        }
        else if (plateauPlacementBat[ligne][col] == '·' && plateauAttaquant[ligne][col] != '●'){   // vérifie si le tir incorrect n'a pas déjà été saisie
            plateauAttaquant[ligne][col] = '●';
            messageCoordonneesTouche(plateauAttaquant,ligne,col);
        }
        else if ((plateauAttaquant[ligne][col] == '●' && plateauAttaquant[ligne][colInitiale] == '●' )
                || (plateauAttaquant[ligne][col] == '●' && colInitiale == 0 && plateauAttaquant[ligne][colInitiale] == '❌')
                || (plateauAttaquant[ligne][col] == '❌' && col == 9 && plateauAttaquant[ligne][colInitiale] == '●')){      // retire au hasard
            do {
                ligne2 = rd.nextInt(10);
                col2 = rd.nextInt(10);
            } while (plateauAttaquant[ligne2][col2] != '·');
            tirAleatoire(plateauPlacementBat,plateauAttaquant,ligne2,col2);
        }
    }

    public static void tirAutomatiqueHaut(char[][] plateauPlacementBat, char[][] plateauAttaquant, int ligne, int col){     //Lucas
        int ligneInitiale;

        int ligne2, col2;

        if (ligne == 9){
            ligneInitiale = ligne;
        }
        else {
            ligneInitiale = ligne + 1;
        }

        if (plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] != '❌'){
            do {        //tire tant que le tir est réussi et qu'il n'a pas déja été découvert
                plateauAttaquant[ligne][col] = '❌';  // bateau touché
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
                ligne--;
                if (ligne == -1){
                    ligne++;
                }
            }while (ligne > 0 && plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] == '·');

            if (plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] == '·'){ // le tir est au bord du plateau, mais vérifie s'il est réussi
                plateauAttaquant[ligne][col] = '❌';  // bateau touché
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
            }

            if ((plateauAttaquant[ligne][col] == '●' && plateauAttaquant[ligneInitiale][col] == '●' )
                    || (plateauAttaquant[ligne][col] == '●' && ligneInitiale == 9 && plateauAttaquant[ligneInitiale][col] == '❌')
                    || (plateauAttaquant[ligne][col] == '❌' && plateauAttaquant[ligneInitiale][col] == '●')
                    || (plateauAttaquant[ligne][col] == '❌' && ligne == 0 && plateauAttaquant[ligneInitiale][col] == '●')){  // tire juste, mais sa ligne de tir se retrouve soit entouré bord du plateau et d'un obstacle, soit entouré par deux obstacles.
                do {
                    ligne2 = rd.nextInt(10);
                    col2 = rd.nextInt(10);
                } while (plateauAttaquant[ligne2][col2] != '·');
                tirAleatoire(plateauPlacementBat,plateauAttaquant,ligne2,col2);   //retire aléatoirement
            }

            if ((plateauAttaquant[ligne][col] == '●' && (plateauAttaquant[ligneInitiale][col] == '·' || plateauAttaquant[ligneInitiale][col] == '❌'))    // se retrouve sur un tir raté devant lui, mais la case en dessous du point initiale est, soit inconnue, soit un tir réussi
                    || (plateauAttaquant[ligne][col] == '❌' && ligne == 0 && (plateauAttaquant[ligneInitiale][col] == '·' || plateauAttaquant[ligneInitiale][col] == '❌'))  // se retrouve au bord du plateau, mais la case en dessous du point initiale est, soit inconnue, soit un tir réussi
                    || (plateauAttaquant[ligne][col] == '❌' && ligne == 0)){ // se retrouve au bord du plateau
                tirAutomatiqueBas(plateauPlacementBat,plateauAttaquant,ligneInitiale,col);
            }


            if (plateauPlacementBat[ligne][col] == '·' && plateauAttaquant[ligne][col] != '●'){    // le tir est raté.
                plateauAttaquant[ligne][col] = '●';
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
            }
        }
        else if (plateauPlacementBat[ligne][col] == '·' && plateauAttaquant[ligne][col] != '●'){   // vérifie si le tir incorrect n'a pas déjà été saisie
            plateauAttaquant[ligne][col] = '●';
            messageCoordonneesTouche(plateauAttaquant,ligne,col);
        }
        else if ((plateauAttaquant[ligne][col] == '●' && plateauAttaquant[ligneInitiale][col] == '●' )
                || (plateauAttaquant[ligne][col] == '●' && ligneInitiale == 9 && plateauAttaquant[ligneInitiale][col] == '❌')
                || (plateauAttaquant[ligne][col] == '❌' && ligne == 0 && plateauAttaquant[ligneInitiale][col] == '●')){      // retire au hasard
            do {
                ligne2 = rd.nextInt(10);
                col2 = rd.nextInt(10);
            } while (plateauAttaquant[ligne2][col2] != '·');
            tirAleatoire(plateauPlacementBat,plateauAttaquant,ligne2,col2);
        }
    }


    public static void tirAutomatiqueBas(char[][] plateauPlacementBat, char[][] plateauAttaquant, int ligne, int col){      //Lucas
        int ligneInitiale;

        int ligne2, col2;

        if (ligne == 0){
            ligneInitiale = ligne;
        }
        else{
            ligneInitiale = ligne - 1;
        }

        if (plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] != '❌'){
            do {        //tire tant que le tir est réussi et qu'il n'a pas déja été découvert
                plateauAttaquant[ligne][col] = '❌';  // bateau touché
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
                ligne++;
                if (ligne == 10){
                    ligne--;
                }
            }while (ligne < 9 && plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] == '·');

            if (plateauPlacementBat[ligne][col] != '·' && plateauAttaquant[ligne][col] == '·'){     // le tir est au bord du plateau, mais vérifie s'il est réussi
                plateauAttaquant[ligne][col] = '❌';  // bateau touché
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
            }

            if ((plateauAttaquant[ligne][col] == '●' && plateauAttaquant[ligneInitiale][col] == '●' )
                    || (plateauAttaquant[ligne][col] == '●' && ligneInitiale == 0 && plateauAttaquant[ligneInitiale][col] == '❌')
                    || (plateauAttaquant[ligne][col] == '❌' && plateauAttaquant[ligneInitiale][col] == '●')
                    || (plateauAttaquant[ligne][col] == '❌' && ligne == 9 && plateauAttaquant[ligneInitiale][col] == '●')){  // tire juste, mais sa ligne de tir se retrouve soit entouré bord du plateau et d'un obstacle, soit entouré par deux obstacles.
                do {
                    ligne2 = rd.nextInt(10);
                    col2 = rd.nextInt(10);
                } while (plateauAttaquant[ligne2][col2] != '·');
                tirAleatoire(plateauPlacementBat,plateauAttaquant,ligne2,col2);   //retire aléatoirement
            }

            if ((plateauAttaquant[ligne][col] == '●' && (plateauAttaquant[ligneInitiale][col] == '·' || plateauAttaquant[ligneInitiale][col] == '❌'))    // se retrouve sur un tir raté devant lui, mais la case au-dessus du point initiale est, soit inconnue, soit un tir réussi
                    || (plateauAttaquant[ligne][col] == '❌' && ligne == 9 && (plateauAttaquant[ligneInitiale][col] == '·' || plateauAttaquant[ligneInitiale][col] == '❌'))  // se retrouve au bord du plateau, mais la case au-dessus du point initiale est, soit inconnue, soit un tir réussi
                    || (plateauAttaquant[ligne][col] == '❌' && ligne == 9)){ // se retrouve au bord du plateau
                tirAutomatiqueHaut(plateauPlacementBat,plateauAttaquant,ligneInitiale,col);
            }

            if (plateauPlacementBat[ligne][col] == '·' && plateauAttaquant[ligne][col] != '●'){    // le tir est raté.
                plateauAttaquant[ligne][col] = '●';
                messageCoordonneesTouche(plateauAttaquant,ligne,col);
            }
        }
        else if (plateauPlacementBat[ligne][col] == '·' && plateauAttaquant[ligne][col] != '●'){   // vérifie si le tir incorrect n'a pas déjà été saisie
            plateauAttaquant[ligne][col] = '●';
            messageCoordonneesTouche(plateauAttaquant,ligne,col);
        }
        else if ((plateauAttaquant[ligne][col] == '●' && plateauAttaquant[ligneInitiale][col] == '●' )
                || (plateauAttaquant[ligne][col] == '●' && ligneInitiale == 0 && plateauAttaquant[ligneInitiale][col] == '❌')
                || (plateauAttaquant[ligne][col] == '❌' && ligne == 9 && plateauAttaquant[ligneInitiale][col] == '●')){
            do {    // retire au hasard
                ligne2 = rd.nextInt(10);
                col2 = rd.nextInt(10);
            } while (plateauAttaquant[ligne2][col2] != '·');
            tirAleatoire(plateauPlacementBat,plateauAttaquant,ligne2,col2);
        }
    }

    /*
     * Retourne vrai si les coordonnées correspondent à un tir "touché"
     */
    public static boolean verifTir(char[][] plateauAttaquant, int ligne, int col) {     //Yovanne
        return plateauAttaquant[ligne][col] == '❌';
    }


    /*
     * Verifie si tous les bateaux sont coulées en creant une liste qui stocke le nombre de bateaux détruits
     */
    public static boolean verifSiGagnant(char[][] plateauPlacementBat, char[][] plateauAttaquant) {     //Yovanne
        List<Integer> bateauDetruit;
        bateauDetruit = nbBateauDetruit(plateauPlacementBat, plateauAttaquant);
        return bateauDetruit.size() == 5;
    }

    /*
     * Cette fonction se charge du bon fonctionnement du tour des joueurs
     */
    public static void tourJoueur(char[][] plateauPlacementBat, char[][] plateauAttaquant) {        //Yovanne
        char col;
        int ligne = 0, colInt;
        boolean invalide;

        do {
            System.out.println(CYAN + "Saississez les coordonnées de votre tir" + RESET);
            do {
                do {
                    System.out.println("Colonne: ");
                    col = sc.next().toUpperCase().charAt(0);
                    if (col < 'A' || col > 'J'){                  // affiche un message d'erreur si la saisie est incorrecte
                        System.out.println(YELLOW+"\nLa colonne n'existe pas\n"+RESET);
                    }
                } while (col < 'A' || col > 'J');

                colInt = col - 'A';

                do {
                    invalide = false;
                    try {
                        System.out.println("Ligne: ");
                        ligne = sc.nextInt();
                    }catch (InputMismatchException e){          // Capture une erreur puis affiche un message si la saisie n'est pas un int
                        invalide = true;
                        System.out.println(YELLOW+"\nERREUR: Vous n'avez pas saisie un chiffre\n"+RESET);
                        sc.next();
                    }
                    if (ligne < 0 || ligne > 9){                  // affiche un message d'erreur si la saisie est incorrecte
                        System.out.println(YELLOW+"\nLa ligne n'existe pas\n"+RESET);
                    }
                } while (ligne < 0 || ligne > 9 || invalide);

                if (plateauAttaquant[ligne][colInt] != '·'){
                    System.out.println(YELLOW + "\nVous avez déja tiré ici !!!\n" + RESET);        // affiche un message d'erreur si la saisie est incorrecte
                }
            }while (plateauAttaquant[ligne][colInt] != '·');// s'effectue tant que les coordonnées seront incorrectes
            tir(plateauPlacementBat, plateauAttaquant, ligne, colInt);

            if (verifTir(plateauAttaquant, ligne, colInt) && !verifSiGagnant(plateauPlacementBat,plateauAttaquant)) {
                System.out.println(YELLOW+"\nVous pouvez encore attaquez!"+RESET);
            }

        } while (verifTir(plateauAttaquant, ligne, colInt) && !verifSiGagnant(plateauPlacementBat, plateauAttaquant)); // se déroule tant que le joueur tire juste et qu'il n'a pas gagné
    }


    /*
     * Cette fonction se charge du bon fonctionnement du tour de l'IA
     */

    public static void tourIA(char[][] plateauPlacementBat, char[][] plateauAttaquant){     //Lucas
        int ligne, col;

        do {
            ligne = rd.nextInt(10);
            col = rd.nextInt(10);
        }while (plateauAttaquant[ligne][col] != '·');
        tirAleatoire(plateauPlacementBat, plateauAttaquant, ligne, col);
    }

    /*
     * Cette procédure s'occupe du déroulement du mode de jeu joueur vs joueur.
     * Elle se déroule tant que les joueurs n'ont pas abattu tous les bateaux ennemis
     */
    public static void deroulementPartieJvsJ(char[][] plateauPlacementBatJ1, char[][] plateauPlacementBatJ2, char[][] plateauAttaqueJ1, char[][] plateauAttaqueJ2, String joueur1, String joueur2) {        //Yovanne

        do {
            //tour joueur n°1
            if (!verifSiGagnant(plateauPlacementBatJ1, plateauAttaqueJ2)) {
                afficherPlateau(plateauAttaqueJ1);
                System.out.println("\n\t" + joueur1 + CYAN + ", À vous de d'attaquer !!\n" + RESET);
                tourJoueur(plateauPlacementBatJ2, plateauAttaqueJ1);
                espace(22);
            }

            //tour joueur n°2
            if (!verifSiGagnant(plateauPlacementBatJ2, plateauAttaqueJ1)) {
                afficherPlateau(plateauAttaqueJ2);
                System.out.println("\n\t " + joueur2 + CYAN + ", À vous de d'attaquer !!\n" + RESET);
                tourJoueur(plateauPlacementBatJ1, plateauAttaqueJ2);
                espace(22);
            }

        } while (!verifSiGagnant(plateauPlacementBatJ2, plateauAttaqueJ1) && !verifSiGagnant(plateauPlacementBatJ1, plateauAttaqueJ2));//se deroule tant qu'il n'y a pas de vainqueur

        if (verifSiGagnant(plateauPlacementBatJ2,plateauAttaqueJ1)){
            System.out.println("\n\t"+ joueur1 +CYAN+", a remporté la parti !!!\n");
            System.out.println("Voici où étaient placés tous les bateaux :\n"+RESET);
            afficherPlateauPourUtilisateur(plateauPlacementBatJ1);
            System.out.println();
            System.out.println(CYAN+"Cliquer sur 'Entrée' pour retourner au menu:"+RESET);
            sc.nextLine();
            sc.nextLine();
            espace(14);
        }
        else{
            System.out.println("\n\t"+joueur2+CYAN+", a remporté la parti !!!\n");
            System.out.println("Voici où étaient placés tous les bateaux :\n"+RESET);
            afficherPlateauPourUtilisateur(plateauPlacementBatJ2);
            System.out.println();
            System.out.println(CYAN+"Cliquer sur 'Entrée' pour retourner au menu"+RESET);
            sc.nextLine();
            sc.nextLine();
            espace(14);
        }

    }


    /*
     * Cette procédure s'occupe du déroulement du mode de jeu Joueur vs IA
     * Elle se déroule tant que le joueur et l'IA n'ont pas abattu tous les bateaux ennemis
     */
    public static void deroulementPartieJvsIA(char[][] plateauPlacementBatJoueur, char[][] plateauPlacementBatIA, char[][] plateauAttaqueJoueur, char[][] plateauAttaqueIA, String joueur) {        //Lucas

        do {
            if (!verifSiGagnant(plateauPlacementBatJoueur, plateauAttaqueIA)) {
                afficherPlateau(plateauAttaqueJoueur);
                System.out.println("\n\t" + joueur + CYAN + ", À vous de d'attaquer !!\n" + RESET);
                tourJoueur(plateauPlacementBatIA, plateauAttaqueJoueur);
                if (verifSiGagnant(plateauPlacementBatIA,plateauAttaqueJoueur)){
                    System.out.println(CYAN+"\n\tVous avez remporté la parti !!!\n"+RESET);
                    System.out.println(CYAN+"Cliquer sur 'Entrée' pour retourner au menu"+RESET);
                    sc.nextLine();
                    sc.nextLine();
                    espace(14);
                }
                espace(27);
            }

            if (!verifSiGagnant(plateauPlacementBatIA, plateauAttaqueJoueur)) {
                tourIA(plateauPlacementBatJoueur, plateauAttaqueIA);
                afficherPlateau(plateauAttaqueIA);

                afficheBateauDetruitIA(plateauPlacementBatJoueur, plateauAttaqueIA);
                System.out.println(CYAN+"Cliquer sur 'Entrée' pour continuer"+RESET);
                sc.nextLine();
                espace(32);
            }

        } while (!verifSiGagnant(plateauPlacementBatIA, plateauAttaqueJoueur) && !verifSiGagnant(plateauPlacementBatJoueur, plateauAttaqueIA));//se deroule tant qu'il n'y a pas de vainqueur

        if (verifSiGagnant(plateauPlacementBatJoueur, plateauAttaqueIA)){
            System.out.println(CYAN+"\n\tVotre adversaire a remporté la partie !"+RESET);
            System.out.println("Voici où étaient placés ces bateaux :\n"+RESET);
            afficherPlateauPourUtilisateur(plateauPlacementBatIA);
            System.out.println(CYAN+"\nCliquer sur 'Entrée' pour retourner au menu:"+RESET);
            sc.nextLine();
            sc.nextLine();
            espace(14);
        }
    }

    //  FIN DES FONCTIONS POUR LE DEROULEMENT DES PARTIES

}