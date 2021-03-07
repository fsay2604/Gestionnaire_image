/**
 * Nom de la classe : Main
 * Description      : Site de test de l'ensemble de nos methodes.
 * @version       : 1.0
 * Date          : 31/01/2021
 * @author      : Samuel Fournier, Olivier Vigneault, François-charles hébert
 */

import java.io.File;

public class main {

    public static void main(String[] args) {
        Image i = new Image();
        Image i2 = new Image();
        Image i3 = new Image();
        Image i4 = new Image(); //extract
        GestionImage gi = new GestionImage();

        // Constructeur d<une image PPM en lisant un fichier.
        File f = new File(".\\src\\res\\Sherbrooke_Frontenac_nuit.ppm");
        i.read(f);

        // Copier d<image
        i2 = gi.copy(i);

        //Vérifie si il sont identique
        if(gi.isIdentical(i,i2))
            System.out.println("L'image i est identique a i2");
        else
            System.out.println("Pas identique");

        // Reduction d'une image
        i3 =  i.shrink();

        // Extraction d'une partie de l'image
        i4 = i.extract(0,0,98,128);

        //Rotation d'une image
        i.rotate();

        //Obtenir la couleur preponderante dans l'image
        String s = gi.colorPreponderante(i);
        System.out.print("La ou les couleur plus redondante est :");
        System.out.print(s);

        // Ecriture dans les fichiers les manipulations des images.
        i.write("ImageRotatier");
        i2.write("ImageRotatierCopier");
        i3.write("ImageShrink");
        i4.write("ImageExtract");
    } //fin main
} // Fin classe


     /* TO DO / QUESTION

        Classe Pixels:
        Question : changePixel()->Bloquer la valeur a 0 si la changement <0?

      */