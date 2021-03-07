/**
 * Nom de l'interface : Pixel
 * Description   : Interface pixel qui permet d'avoir accès à la valeur
 * @version       : 1.0
 * Date          : 31/01/2021
 * @author      : Samuel Fournier, Olivier Vigneault, François-charles hébert
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public interface Pixel {

    /**
     * Permet de changer la couleur d'un pixel soit négatif pour noircir ou positif pour éclaircir
     *
     * @param v la variante de couleur a changer
     */
    public void changePixel(int v);

    /**
     * retourne la valeur des pixel
     *
     * @return un tableau de int pour les ou la valeur du pixel
     */
    public int[] getValue();

    /**
     *Lit une image dans une fichier donnée
     *
     * @param scanner Le tableau de pixel a lire
     */
    public void read(Scanner scanner);

    /**
     * Permet de faire de l'écriture de l'image dans une fichié donné
     *
     * @param writer Le fichier à lire
     */
    public void write(FileWriter writer) throws IOException;

    /**
     * Permet d'attribuer une valeur à un pixel
     *
     * @param v Le valeur a set le pixel
     */
    public void setValue(int[] v);

}
