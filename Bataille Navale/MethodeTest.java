import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class MethodeTest {

    @Test
    void testCreationPlateau(){
        char[][] nouveauPlateau;

        char[][] plateauAttendu = {{'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};

        nouveauPlateau = Methode.creationPlateau();

        assertArrayEquals(plateauAttendu,nouveauPlateau);

        Methode.placementBatIA(nouveauPlateau);
        Methode.afficherPlateau(nouveauPlateau);
    }

    @Test
    void testValAbs(){
        int nb1 = 4, nb2 = 2, nb3 = 2, nb4 = 4;
        int taille = 3;
        int resultat1, resultat2;

        resultat1 = Methode.valAbs(nb1,nb2);
        resultat2 = Methode.valAbs(nb3,nb4);

        assertEquals(taille - 1,resultat1,"valeur absolu avec 2");
        assertEquals(taille - 1,resultat2,"valeur absolu avec -2");
    }

    @Test
    void testPlacementBateau_PorteAvionHorizontal() {
        char[][] plateau = Methode.creationPlateau();
        String joueur = "Joueur 1";

        // Appeler directement la méthode de placement avec des paramètres prédéfinis
        Methode.placementBateauX(plateau, 0,0,4,'P');

        // Vérifier le résultat après le placement du porte-avion
        assertEquals('P', plateau[0][0]);
        assertEquals('P', plateau[0][1]);
        assertEquals('P', plateau[0][2]);
        assertEquals('P', plateau[0][3]);
        assertEquals('P', plateau[0][4]);
    }

    @Test
    void testPlacementBateau_CuirasseVertical() {
        char[][] plateau = Methode.creationPlateau();
        String joueur = "Joueur 1";

        // Appeler directement la méthode de placement avec des paramètres prédéfinis
        Methode.placementBateauY(plateau, 3,1,4,'C');

        // Vérifier le résultat après le placement du cuirassé
        assertEquals('C', plateau[1][3]);
        assertEquals('C', plateau[2][3]);
        assertEquals('C', plateau[3][3]);
        assertEquals('C', plateau[4][3]);
    }

    @Test
    void testPlacementBateau_ContreTorpilleurHorizontal() {
        char[][] plateau = Methode.creationPlateau();
        String joueur = "Joueur 1";

        // Appeler directement la méthode de placement avec des paramètres prédéfinis
        Methode.placementBateauX(plateau, 0,5,7,'T');

        // Vérifier le résultat après le placement du contre-torpilleur
        assertEquals('T', plateau[0][5]);
        assertEquals('T', plateau[0][6]);
        assertEquals('T', plateau[0][7]);
    }

    @Test
    void testPlacementBateau_SousMarinVertical() {
        char[][] plateau = Methode.creationPlateau();
        String joueur = "Joueur 1";

        // Appeler directement la méthode de placement avec des paramètres prédéfinis
        Methode.placementBateauY(plateau, 5,3,5,'S');

        // Vérifier le résultat après le placement du sous-marin
        assertEquals('S', plateau[3][5]);
        assertEquals('S', plateau[4][5]);
        assertEquals('S', plateau[5][5]);
    }

    @Test
    void testPlacementBateau_DestroyerHorizontal() {
        char[][] plateau = Methode.creationPlateau();
        String joueur = "Joueur 1";

        // Appeler directement la méthode de placement avec des paramètres prédéfinis
        Methode.placementBateauX(plateau, 9,8,9,'D');

        // Vérifier le résultat après le placement du destroyer
        assertEquals('D', plateau[9][8]);
        assertEquals('D', plateau[9][9]);
    }

    @Test
    void testEffacementPlacementBateauX(){
        char[][]plateau = Methode.creationPlateau();
        char[][]plateauAttendu = Methode.creationPlateau();

        Methode.placementBateauX(plateau,0,1,4,'P');
        Methode.effacementPlacementBateauX(plateau,0,1,4);

        assertArrayEquals(plateauAttendu,plateau,"placement effacé");
    }

    @Test
    void testEffacementPlacementBateauY(){
        char[][]plateau = Methode.creationPlateau();
        char[][]plateauAttendu = Methode.creationPlateau();

        Methode.placementBateauY(plateau,0,1,4,'P');
        Methode.effacementPlacementBateauY(plateau,0,1,4);

        assertArrayEquals(plateauAttendu,plateau,"placement effacé");
    }

    @Test
    void test_Tir(){
        char[][] plateau = Methode.creationPlateau();
        char[][] plateauAttaque = Methode.creationPlateau();
        Methode.placementBateauX(plateau, 0,0,4,'P');
        Methode.placementBateauY(plateau, 3,1,4,'C');
        Methode.placementBateauX(plateau, 0,5,7,'T');
        Methode.placementBateauY(plateau, 5,3,5,'S');
        Methode.placementBateauX(plateau, 9,8,9,'D');

        Methode.tir(plateau, plateauAttaque, 0,0);
        Methode.tir(plateau, plateauAttaque, 0,1);
        Methode.tir(plateau, plateauAttaque, 0,2);
        Methode.tir(plateau, plateauAttaque, 0,3);
        Methode.tir(plateau, plateauAttaque, 0,4);

        List<Integer> bateauxDetruits = Methode.nbBateauDetruit(plateau, plateauAttaque);

        assertEquals(5,bateauxDetruits.get(0));

    }

    @Test
    void test_Gagnant(){
        char[][] plateau = new char[10][10];
        char[][] plateauAttaque = new char[10][10];
        Methode.placementBateauX(plateau, 0,0,4,'P');
        Methode.placementBateauY(plateau, 3,1,4,'C');
        Methode.placementBateauX(plateau, 0,5,7,'T');
        Methode.placementBateauY(plateau, 5,3,5,'S');
        Methode.placementBateauX(plateau, 9,8,9,'D');

        Methode.tir(plateau, plateauAttaque, 0,0);
        Methode.tir(plateau, plateauAttaque, 0,1);
        Methode.tir(plateau, plateauAttaque, 0,2);
        Methode.tir(plateau, plateauAttaque, 0,3);
        Methode.tir(plateau, plateauAttaque, 0,4);

        Methode.tir(plateau, plateauAttaque, 1,3);
        Methode.tir(plateau, plateauAttaque, 2,3);
        Methode.tir(plateau, plateauAttaque, 3,3);
        Methode.tir(plateau, plateauAttaque, 4,3);

        Methode.tir(plateau, plateauAttaque, 0,5);
        Methode.tir(plateau, plateauAttaque, 0,6);
        Methode.tir(plateau, plateauAttaque, 0,7);

        Methode.tir(plateau, plateauAttaque, 3,5);
        Methode.tir(plateau, plateauAttaque, 4,5);
        Methode.tir(plateau, plateauAttaque, 5,5);

        Methode.tir(plateau, plateauAttaque, 9,8);
        Methode.tir(plateau, plateauAttaque, 9,9);

        assertTrue(Methode.verifSiGagnant(plateau,plateauAttaque));

    }

    @Test
    void test_TirAleatoireEtAutomatique(){
        char[][] plateau = Methode.creationPlateau();

        char[][] plateau2 = Methode.creationPlateau();
        char[][] plateau2Bis = Methode.creationPlateau();

        char[][] plateau3 = Methode.creationPlateau();
        char[][] plateau3Bis = Methode.creationPlateau();

        char[][] plateau4 = Methode.creationPlateau();
        char[][] plateau4Bis = Methode.creationPlateau();

        char[][] plateau5 = Methode.creationPlateau();
        char[][] plateau5Bis = Methode.creationPlateau();

        Methode.placementBateauX(plateau, 0,0,7,'P');

        Methode.placementBateauX(plateau2, 0,2,9,'P');
        Methode.placementBateauX(plateau2Bis, 0,2,8,'P');

        Methode.placementBateauX(plateau3, 0,0,7,'P');
        Methode.placementBateauX(plateau3Bis, 0,1,7,'P');

        Methode.placementBateauY(plateau4, 0,2,9,'P');
        Methode.placementBateauY(plateau4Bis, 0,2,8,'P');

        Methode.placementBateauY(plateau5, 0,0,7,'P');
        Methode.placementBateauY(plateau5Bis, 0,1,7,'P');

        char[][] plateauAttaque = Methode.creationPlateau();

        char[][] plateauAttaque2 = {{'·','●','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};

        char[][] plateauAttaque3 = {{'·','·','·','·','·','·','·','·','●','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};

        char[][] plateauAttaque4 = {{'·','·','·','·','·','·','·','·','·','·'},
                {'●','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};

        char[][] plateauAttaque5 = {{'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'●','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};


        char[][] plateauAttaqueAttendu1 = {{'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','●','·','·'}};


        char[][] plateauAttaqueAttendu2 = {{'·','●','❌','❌','❌','❌','❌','❌','❌','❌'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};

        char[][] plateauAttaqueAttendu2Bis =   {{'·','●','❌','❌','❌','❌','❌','❌','❌','●'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};

        char[][] plateauAttaqueAttendu3 = {{'❌','❌','❌','❌','❌','❌','❌','❌','●','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};

        char[][] plateauAttaqueAttendu3Bis = {{'●','❌','❌','❌','❌','❌','❌','❌','●','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};

        char[][] plateauAttaqueAttendu4 = {{'·','·','·','·','·','·','·','·','·','·'},
                {'●','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'}};

        char[][] plateauAttaqueAttendu4Bis =   {{'·','·','·','·','·','·','·','·','·','·'},
                {'●','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'●','·','·','·','·','·','·','·','·','·'}};


        char[][] plateauAttaqueAttendu5 = {{'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'●','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};

        char[][] plateauAttaqueAttendu5Bis = {{'●','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'❌','·','·','·','·','·','·','·','·','·'},
                {'●','·','·','·','·','·','·','·','·','·'},
                {'·','·','·','·','·','·','·','·','·','·'}};


        // - tirs ratés
        Methode.tirAleatoire(plateau,plateauAttaque, 9,7 );
        assertArrayEquals(plateauAttaqueAttendu1,plateauAttaque,"cas tir raté");

        //  - tirs vers la gauche
        plateauAttaque = Methode.creationPlateau();
        Methode.tirAutomatiqueGauche(plateau2,plateauAttaque,0,9);
        assertArrayEquals(plateauAttaqueAttendu2,plateauAttaque,"tir réussi puis tire sur la gauche tant que c'est réussi");

        plateauAttaque = Methode.creationPlateau();
        Methode.tirAutomatiqueGauche(plateau3,plateauAttaque,0,4);
        assertArrayEquals(plateauAttaqueAttendu3,plateauAttaque,"tir réussi, continue sur la gauche puis se retrouve au bord du plateau");


        Methode.tirAutomatiqueGauche(plateau2Bis,plateauAttaque2,0,3);
        assertArrayEquals(plateauAttaqueAttendu2Bis,plateauAttaque2,"tir réussi, continue sur la gauche puis rencontre un tir raté déjà placé");
//

        //  - tirs vers la droite
        plateauAttaque = Methode.creationPlateau();
        Methode.tirAutomatiqueDroite(plateau3,plateauAttaque,0,0);
        assertArrayEquals(plateauAttaqueAttendu3,plateauAttaque,"tir réussi puis tire sur la droite tant que c'est réussi");

        plateauAttaque = Methode.creationPlateau();
        Methode.tirAutomatiqueDroite(plateau2,plateauAttaque,0,6);
        assertArrayEquals(plateauAttaqueAttendu2,plateauAttaque,"tir réussi, continue sur la droite puis se retrouve au bord du plateau");

        Methode.tirAutomatiqueDroite(plateau3Bis,plateauAttaque3,0,4);
        assertArrayEquals(plateauAttaqueAttendu3Bis,plateauAttaque3,"cas tir réussi et la chasse continue sur la droite du tir raté déjà placé");


        //  - tirs vers le haut
        plateauAttaque = Methode.creationPlateau();
        Methode.tirAutomatiqueHaut(plateau4,plateauAttaque,9,0);
        assertArrayEquals(plateauAttaqueAttendu4,plateauAttaque,"tir réussi puis tire vers le haut tant que c'est réussi");

        plateauAttaque = Methode.creationPlateau();
        Methode.tirAutomatiqueHaut(plateau5,plateauAttaque,4,0);
        assertArrayEquals(plateauAttaqueAttendu5,plateauAttaque,"tir réussi, continue vers la haut puis se retrouve au bord du plateau");

        plateauAttaque = Methode.creationPlateau();
        Methode.tirAutomatiqueHaut(plateau4Bis,plateauAttaque4,3,0);
        assertArrayEquals(plateauAttaqueAttendu4Bis,plateauAttaque4,"tir réussi, continue vers le haut puis rencontre un tir raté déjà placé");


        //  - tirs vers le bas
        plateauAttaque = Methode.creationPlateau();
        Methode.tirAutomatiqueBas(plateau5,plateauAttaque,0,0);
        assertArrayEquals(plateauAttaqueAttendu5,plateauAttaque,"tir réussi puis tire vers le bas tant que c'est réussi");

        plateauAttaque = Methode.creationPlateau();
        Methode.tirAutomatiqueBas(plateau4,plateauAttaque,4,0);
        assertArrayEquals(plateauAttaqueAttendu4,plateauAttaque,"tir réussi, continue vers la bas puis se retrouve au bord du plateau");

        plateauAttaque = Methode.creationPlateau();
        Methode.tirAutomatiqueBas(plateau5Bis,plateauAttaque5,7,0);
        assertArrayEquals(plateauAttaqueAttendu5Bis,plateauAttaque5,"tir réussi, continue vers le bas puis rencontre un tir raté déjà placé");


    }
}
