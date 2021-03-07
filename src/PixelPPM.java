/**
 * Nom de la classe : PixelPPM
 * Description      : l'objet permet de stocker des pixels de couleur
 * @version       : 1.0
 * Date          : 31/01/2021
 * @author      : Samuel Fournier, Olivier Vigneault, François-charles hébert
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PixelPPM extends Image implements Pixel {

    private int[] _pixels;
    /**
     * Constructeur de Pixel PPM
     * @param scanner est le scanner qui scan un fichier et appelel la methode read()
     */
    public PixelPPM(Scanner scanner)
    {
        _pixels = new int[3];
        read(scanner);

    }


    /**
     * Constructeur par default
     */
    public PixelPPM(){
        _pixels = new int[3];
    }


    /**
     * Fonction pour diminier ou augmenter la valeur du pixel selon le paramètre reçu
     * @param v est pour la variante soit positive, soit négative du pixel
     */
    public void changePixel(int v){

        for(int i = 0; i < _pixels.length; i++){

            if(!(_pixels[i] + v > 255)) // Valeur maximale d'un pixel ppm est de 255
                _pixels[i] += v;
            else
                _pixels[i] = 255;
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
        for(int i = 0; i < v.length; i++)
            _pixels[i] = v[i];
    }


    /**
     *Lit une image dans une fichier donnée
     *
     * @param scanner Le tableau de pixel a lire
     */
    public void read(Scanner scanner){

        _pixels[0] = scanner.nextInt();
        _pixels[1] = scanner.nextInt();
        _pixels[2] = scanner.nextInt();
    }

    /**
     * Permet de faire de l'écriture de l'image dans une fichié donné
     *
     * @param writer Le fichier à lire
     */
    public void write(FileWriter writer) throws IOException{

        writer.write(String.valueOf(_pixels[0]) + " " + String.valueOf(_pixels[1]) + " " + String.valueOf(_pixels[2]) + " ");

    }
}
