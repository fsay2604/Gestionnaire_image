/**
 * Nom de la classe : GestionImages
 * Description      : Permet de faire la gestion des images
 * @version       : 1.0
 * Date          : 31/01/2021
 * @author      : Samuel Fournier, Olivier Vigneault, François-charles hébert
 */
import java.util.*;
import java.util.Arrays;

public class GestionImage {

    /**
     * Trouve la valeur la plus utiliser dans l'image
     * @param map   la map contenan l'ensemble des pixels et leur occurence.
     * @return      le pixel le plus utilisee
     */
    private String mostUsedColor(Map<String, Integer> map){
        String mostUsed = null;

        // using keySet() for iteration over keys
        for (String k : map.keySet())
        {
            //A la premiere iteration
            if(mostUsed == null)
                mostUsed = k;
            else if(map.get(k).intValue() > map.get(mostUsed).intValue())
                mostUsed = k;
        }

        return mostUsed;
    }

    /**
     * Constructeur par defaut
     */
    public GestionImage() {
    }

    /**
     *Permet de faire la copie de l'image I1 dans l'image I2
     *
     * @param i1 Image source
     */
    public Image copy(Image i1){
        Image i2 = new Image(i1);

        return i2;
    }

    /**
     * Permet de savoir la couleur la plus répandue dans l'image reçu en paramètre
     * @param i1 Image à questionner
     * @return La valeur de la couleur la plus répandue
     */
    public String colorPreponderante(Image i1){
        Map<String, Integer> map = new HashMap<String, Integer>();
        String key = "";

        // Boucle a travers l'image et ajoute le pixel dans une map si le pixel n'existe pas dans la map.
        // Sinon, on incremente le compteur du pixel dans la map.
        for (int i = 0; i < i1.get_heigth(); i++)
        {

            for(int j = 0; j < i1.get_width(); j++)
            {
                key = "";
                // Construit la clef de la map avec les valeur des pixels.
                for(int x = 0; x < i1.getPixel(i,j).getValue().length; x++)
                     key += String.valueOf(i1.getPixel(i,j).getValue()[x]) + " ";



                if(isInMap(map,key)){
                    map.replace(key, map.get(key) + 1); // Si existe on incremente le counter de 1
                }
                else
                    map.put(key, 1);                    // Sinon on le cree.

                // Verifie si la clef existe
                if(map.containsKey(key))
                    map.replace(key, map.get(key) + 1); // Si existe on incremente le counter de 1
                else
                    map.put(key, 1);                    // Sinon on le cree.


            }
        }

        // Recuperation de la couleur la plus utilise
        String mostUsedPixel = mostUsedColor(map);

        return mostUsedPixel;
    }



    private boolean isInMap(Map<String, Integer> map,String valeur){

        for (String key : map.keySet()){
            if(key.equals(valeur))
                return true;
        }

        return false;
    }

    /**
     * Permet de sois noircir l'image avec un paramète v négatif ou de l'éclaircir avec un positif
     *
     * @param i1 l'image en question
     * @param v La valeur qui affecte les pixels, sois positif ou négatif
     */
    public void changeColor(Image i1, int v){

        for (int i = 0; i < i1.get_heigth(); i++){
            for(int j = 0; j < i1.get_width(); j++){
                i1.get_image()[i][j].changePixel(v);
            }
        }
    }

    /**
     * Permet de savoir si des images sont identiques (pixel par pixel)
     *
     * @param i1 Première image à comparer
     * @param i2 Deuxième image à comparer
     *
     * @return Vrai s'ils sont identique ou sinon faux
     */
    public boolean isIdentical(Image i1, Image i2){
        // Si les images n'ont pas les meme dimensions
        if (i1.get_heigth() != i2.get_heigth() || i1.get_width() != i2.get_width())
            return false;

        for (int i = 0; i < i1.get_heigth(); i++){
            for(int j = 0; j < i1.get_width(); j++){

                if(!(Arrays.equals(i1.getPixel(i,j).getValue(), i2.getPixel(i,j).getValue())))
                    return false;
            }
        }

        return true;
    }


}
