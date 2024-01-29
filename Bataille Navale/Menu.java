import java.util.InputMismatchException;

public class Menu {
    public static void BatailleNavale(){
        int choix = 0;
        boolean invalide;

        do {
            dessinBateau();
            Methode.espace(2);
            System.out.println("\n\t\t\t\t\t\t  MENU\n");

            do {
                invalide = false;
                try {
                    System.out.println("1. JOUEUR VS JOUEUR");
                    System.out.println("2. JOUEUR VS IA");
                    System.out.println("3. RÈGLES");
                    System.out.println("4. QUITTER LE JEU");
                    choix = Methode.sc.nextInt();
                    Methode.sc.nextLine();
                }catch (InputMismatchException e){
                    invalide = true;
                    System.out.println(Methode.YELLOW+"\nERREUR: Saisie différente d'un chiffre\n"+Methode.RESET);
                    Methode.sc.next();
                }
            }while (invalide);
            Methode.espace(3);

            switch (choix){
                case 1:
                    Methode.espace(10);
                    JoueurVsJoueur();
                    break;
                case 2:
                    Methode.espace(10);
                    JeuPlayerVsIA();
                    break;
                case 3:
                    Methode.espace(40);
                    regle();
                    break;
                case 4:
                    System.out.println("Merci d'avoir joué. Au revoir !");
                    break;
                default:
                    Methode.espace(15);
                    System.out.println(Methode.YELLOW+"ERREUR: saisie incorrecte"+Methode.RESET);
                    break;
            }
        }while(choix != 4);


    }


    /*
     * Cette méthode s'occupe du déroulement de la partie entière du mode de jeu Joueur vs Joueur.
     * Elle stocke les plateaux principaux de la partie ainsi que les pseudos des deux joueurs
     */
    public static void JoueurVsJoueur() {
        String joueur1;
        String joueur2;
        char[][] PlateauJ1, PlateauJ2, PlateauAttaqueJ1, PlateauAttaqueJ2;


        System.out.println(Methode.CYAN+"JOUEUR N°1, Entrez votre pseudo:"+Methode.RESET);
        joueur1 = Methode.sc.nextLine();
        System.out.println(Methode.CYAN+"JOUEUR N°2, Entrez votre pseudo:"+Methode.RESET);
        joueur2 = Methode.sc.nextLine();
        Methode.espace(20);

        PlateauJ1 = Methode.creationPlateau();
        PlateauJ2 = Methode.creationPlateau();

        Methode.placementBateau(PlateauJ1, joueur1);
        Methode.espace(30);
        Methode.placementBateau(PlateauJ2, joueur2);
        Methode.espace(30);

        System.out.println(Methode.CYAN+"\t  Tous les bateaux sont déployés ");
        System.out.println("\t\tLa partie peut commencer !!"+Methode.RESET);

        PlateauAttaqueJ1 = Methode.creationPlateau();
        PlateauAttaqueJ2 = Methode.creationPlateau();

        Methode.deroulementPartieJvsJ(PlateauJ1, PlateauJ2, PlateauAttaqueJ1, PlateauAttaqueJ2, joueur1, joueur2);

    }

    public static void JeuPlayerVsIA(){
        String joueur;
        char[][] plateauPlacementBatJoueur, plateauPlacementBatIA, plateauAttaqueJoueur, plateauAttaqueIA;

        // Initialisation des informations du joueur
        System.out.println(Methode.CYAN+"Entrez votre pseudo:"+Methode.RESET);
        joueur = Methode.sc.nextLine();
        Methode.espace(20);

        //PLACEMENTS BATEAUX JOUEUR
        plateauPlacementBatJoueur = Methode.creationPlateau();
        Methode.placementBateau(plateauPlacementBatJoueur, joueur);

        // PLACEMENTS BATEAUX IA
        plateauPlacementBatIA = Methode.creationPlateau();
        Methode.placementBatIA(plateauPlacementBatIA);

        Methode.espace(30);
        System.out.println(Methode.CYAN+"\t  Tous les bateaux sont déployés ");
        System.out.println("\t\tLa partie peut commencer !!"+Methode.RESET);

        plateauAttaqueJoueur = Methode.creationPlateau();
        plateauAttaqueIA = Methode.creationPlateau();

        Methode.deroulementPartieJvsIA(plateauPlacementBatJoueur,plateauPlacementBatIA,plateauAttaqueJoueur,plateauAttaqueIA, joueur);
    }

    public static void dessinBateau(){
        System.out.println("\n░░░░██████╗░███████╗████████╗████████╗██╗░░░░███████╗░░░");
        System.out.println("░░░░██╔══██╗██╔══██║░░░██╔══╝░░░██╔══╝██║░░░░██╔════╝░░░");
        System.out.println("░░░░██████╔╝███████║░░░██║░░░░░░██║░░░██║░░░░█████╗░░░░░");
        System.out.println("░░░░██╔══██╗██╔══██║░░░██║░░░░░░██║░░░██║░░░░██╔══╝░░░░░");
        System.out.println("░░░░██████╔╝██║░░██║░░░██║░░░░░░██║░░░██████╗███████╗░░░");
        System.out.println("░░░░╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░╚═════╝╚══════╝░░░");
        System.out.println(Methode.BLUE+"░░░░░░░░░░░███████╗██╗░░██╗███╗██████╗░███████╗░░░░░░░░░");
        System.out.println("░░░░░░░░░░░██╔════╝██║░░██║███║██╔══██╗██╔════╝░░░░░░░░░");
        System.out.println("░░░░░░░░░░░███████╗███████║███║██████╔╝███████╗░░░░░░░░░");
        System.out.println("░░░░░░░░░░░╚════██║██╔══██║███║██╔═══╝ ╚════██║░░░░░░░░░");
        System.out.println("░░░░░░░░░░░███████║██║░░██║███║██║░░░░░███████║░░░░░░░░░");
        System.out.println("░░░░░░░░░░░╚══════╝╚═╝░░╚═╝╚══╝╚═╝░░░░░╚══════╝░░░░░░░░░"+Methode.RESET);
    }

    public static void regle(){

        System.out.println("                                                    RÈGLES\n");
        System.out.println(Methode.BLUE+"==========================================================================================================="+Methode.RESET);
        System.out.println("                            1. LE JEU SE JOUE SUR UN PLATEAU DE DIMENSION 10x10.\n");
        System.out.println("         2. CHAQUE JOUEUR DISPOSE DE CINQ BATEAUX DIFFÉRENTS QU'IL DOIT PLACER SUR SON PLATEAU:\n");
        System.out.println("                                        - UN PORTE-AVION (5 cases)\n");
        System.out.println("                                         - UN CUIRASSÉ (4 cases)\n");
        System.out.println("                                     - UN CONTRE-TORPILLEUR (3 cases)\n");
        System.out.println("                                        - UN SOUS-MARIN (3 cases)\n");
        System.out.println("                                         - UN DESTROYER (2 cases)\n");
        System.out.println("                               3. LES BATEAUX NE DOIVENT PAS SE CHEVAUCHER.\n");
        System.out.println("        4. LES TIRS SONT EFFECTUÉS EN SPÉCIFIANT LES COORDONNÉES (colonne, ligne). EXEMPLE: A-4\n");
        System.out.println("             5. UN TIR RÉUSSI EST MARQUÉ PAR UN '❌', UN TIR RATÉ EST MARQUÉ PAR UN '●'.\n");
        System.out.println("  6. LE JOUEUR QUI RÉUSSI À FAIRE COULER TOUS LES BATEAUX DE L'ADVERSAIRE EN PREMIER REMPORTE LA PARTIE.\n");
        System.out.println(" 7. LA PLUPART DES CHOIX MIS À DISPOSITION SONT A SÉLECTIONNER PAR RAPPORT AU CHIFFRE SITUÉ SUR LEUR GAUCHE.\n");
        System.out.println(Methode.BLUE+"===========================================================================================================\n"+Methode.RESET);
        System.out.println("                 Si vous avez fini de lire, cliquer sur 'Entrée' pour retourner au menu");
        Methode.sc.nextLine();
        Methode.espace(30);
    }



}

