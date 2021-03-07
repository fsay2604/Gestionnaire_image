/**
 * Nom de la classe : PixelPGM
 * Description      : l'objet permet de stocker des pixels noir et blanc
 * @version       : 1.0
 * Date          : 31/01/2021
 * @author      : Samuel Fournier, Olivier Vigneault, François-charles hébert
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class PixelPGM extends Image implements Pixel {

    private int[] _pixels;

    /**
     * Constructeur de Pixel PGM
     * @param scanner le fichier à lire
     */
    public PixelPGM(Scanner scanner){
        _pixels = new int [1];
        read(scanner);
    }

    /**
     * Constructeur par default
     */
    public PixelPGM(){
        _pixels = new int [1];
    }


    /**
     * Fonction pour diminier ou augmenter la valeur du pixel selon le paramètre reçu
     * @param v est pour la variante soit positive, soit négative du pixel
     */
    public void changePixel(int v) {
        for (int i = 0; i < _pixels.length; i++) {
            if (!(_pixels[i] + v > 255)) // Valeur maximale d'un pixel ppm est de 255
                _pixels[i] += v;
            else {
                _pixels[i] = 255;
            }
        }
    }

    /**
     * retourne la valeur des pixel
     *
     * @return un tableau de int pour les ou la valeur du pixel
     */
    public int[] getValue(){
        return _pixels;
    }

    /**
     * Permet d'attribuer une valeur à un pixel
     *
     * @param v Le valeur a set le pixel
     */
    public void setValue(int[] v){
        _pixels[0] = v[0];
    }


    /**
     *Lit une image dans une fichier donnée
     *
     * @param scanner Le tableau de pixel a lire
     */
    public void read(Scanner scanner){
        _pixels[0] = scanner.nextInt();
    }

    /**
     * Permet de faire de l'écriture de l'image dans une fichié donné
     *
     * @param writer Le fichier à lire
     */
    public void write(FileWriter writer) throws IOException{

        writer.write(String.valueOf(_pixels[0])+ " ");
    }
}
