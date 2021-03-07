/**
 * Nom de classe : Image
 * Description   : Object image qui contient la largeur et la longueur, ainsi que le type de ses enfants.
 * @version       : 1.0
 * Date          : 31/01/2021
 * @author      : Samuel Fournier, Olivier Vigneault, François-charles hébert
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;



public class Image {
    private int _width;
    private int _height;
    private String _type;
    int _maxValue;
    Pixel[][] _img;

    /**
     *
     * Copieur
     * @param i1    l'objet a clone
     */
    public Image(Image i1) {
        //Assignation des proriété
        _width = i1.get_width();
        _height = i1.get_heigth();
        _type = i1.get_type();
        _maxValue = i1.get_maxValue();

        _img = new Pixel[_height][_width];  // Resize de l'image selon les dimensions lues

        // Copy de la matrice de pixel
        for (int i = 0; i < _height; i++)
            for (int j = 0; j < _width; j++)
            {
                _img[i][j] = i1.getPixel(i,j);
            }
    }

    /**
     * Ecrit l'entete dans le fichier
     *
     * @param w correspond au writer avec lequel on veut ecrire.
     */
    private void writeHeader(FileWriter w) {
        try {
            // Ecriture de l'entete.
            w.write(_type + "\n");
            w.write(String.valueOf(_width) + " ");
            w.write(String.valueOf(_height) + "\n");
            w.write(String.valueOf(_maxValue) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + " thrown in image.writeHeader()");
        }
    }

    /**
     * Ecrit les pixels dans le fichier
     *
     * @param writer correspond au writer avec lequel on veut ecrire.
     */
    private void writePixel(FileWriter writer) throws IOException
    {
        // Écriture des pixels dans le fichier
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                _img[i][j].write(writer);
            }
            writer.write("\n");
        }
    }

    /**
     * Constructeur par default
     */
    public Image() {
        _width = _height = _maxValue = 0;
        _img = null;
        _type = new String();
    }


    /**
     * Constructeur
     */
    public Image(File f) {
        _width = _height = _maxValue = 0;
        _img = null;
        _type = new String();

        read(f);
    }


    /**
     * Method qui lit un fichier et cree l'image de pixel/
     * @param f Est le fichier que l'on veut lire.
     */
    public void read(File f) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(f);

            // Recuperation de l'entete et assignation des dimensions
            _type = scanner.next();
            _width = scanner.nextInt();
            _height = scanner.nextInt();
            _maxValue = scanner.nextInt();

            _img = new Pixel[_height][_width];  // Resize de l'image selon les dimensions lues

            if (_type.equals("P3"))   // Pixel PPM
            {
                // Construction de l"image pixel par pixel
                for (int i = 0; i < _height; i++) {
                    for (int j = 0; j < _width; j++) {
                        PixelPPM ppm = new PixelPPM(scanner);
                        _img[i][j] = ppm;

                       // System.out.print("(" + _img[i][j].getValue()[0] + "," + _img[i][j].getValue()[1] + ", " + _img[i][j].getValue()[2] + ") "); // Pour voir dans la console.
                    }
                    //System.out.print("\n");
                }
            } else if (_type.equals("P2"))   // Pixel PGM
            {
                // Construction de l"image pixel par pixel
                for (int i = 0; i < _height; i++) {
                    for (int j = 0; j < _width; j++) {
                        PixelPGM pgm = new PixelPGM(scanner);
                        _img[i][j] = pgm;

                       // System.out.print(_img[i][j].getValue()[0] + " ");   // Pour voir dans la console.
                    }
                   // System.out.print("\n");
                }
            }
        }// try end
        catch (FileNotFoundException fe) {
            fe.printStackTrace();
            System.out.println(fe + " thrown in Image.read()");
        }// FileNotFoundException end
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e + " thrown in Image.read()");
        }// Exception end
        finally {
            scanner.close();
        }// finally end
    }

    /**
     * Permet de faire de l'écriture de l'image dans une fichié donné
     *
     * @param fileName Le nom du fichier desirer pour l'ouput
     */
    public void write(String fileName) {
        FileWriter writer = null;
        try {
            // Construction du writer en fonction du type.
            if(_type.equals("P3"))
                writer = new FileWriter((fileName + ".ppm"));
            else if (_type.equals("P2"))
                writer = new FileWriter((fileName + ".pgm"));

            // Ecriture dans le fichier de l'entete et des pixels
            writeHeader(writer);    // Ecriture de l'entete.
            writePixel(writer);     // Ecriture du reste de l'ensemble des pixels d el'image

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e + " thrown in image.write()");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e + " thrown in image.write()");
            }
        }
    }

    /**
     * Accesseur de l'attribut largeur
     *
     * @return la largeur de l'image
     */
    public int get_width() {
        return _width;
    }

    /**
     * Affecte la largeur à l'attribue largeur
     *
     * @param _width la largeur de l'image en question
     */
    public void set_width(int _width) {
        this._width = _width;
    }

    /**
     * Accesseur de l'attribut longueur
     *
     * @return la longueur de l'image
     */
    public int get_heigth() {
        return _height;
    }

    /**
     * Affecte la longueur à l'attribue longueur
     *
     * @param _heigth la longueur de l'image en question
     */
    public void set_heigth(int _heigth) {
        this._height = _heigth;
    }

    /**
     * Accesseur de l'attribut type
     *
     * @return le type de l'image
     */
    public String get_type() {
        return _type;
    }

    /**
     * Affecte la type à l'attribue type
     *
     * @param _type le type d'image
     */
    public void set_type(String _type) {
        this._type = _type;
    }

    /**
     *
     * @return la valeur maximum des pixel
     */
    public int get_maxValue() {
        return _maxValue;
    }

    /**
     *
     * @param maxValue pour la valeur maximum a avoir
     */
    public void set_maxValue(int maxValue) {
        _maxValue = maxValue;
    }

    /**
     * Retourne le arraylist de Pixel
     *
     * @return Retourne le arraylist de Pixel
     */
    public Pixel[][] get_image() {
        return _img;
    }


    /**
     * Retourne un seul pixel du array (selon les index)
     *
     * @param i Premier index de ligne
     * @param j Premier index de colonne
     * @return Le pixel attribué par les index
     */
    public Pixel getPixel(int i, int j) {
        return _img[i][j];
    }


    /**
     * Extrait une partie de l'image et retourne l'image extrait
     *
     * @param rowStart Le point en haut à gauche
     * @param rowEnd Le point en haut à droite
     * @param colStart Le point en bas à gauche
     * @param colEnd Le point en bas à droite
     *
     * @return L'image contenu entre les 4 points reçu en paramètre
     */
    public Image extract(int rowStart,int colStart, int rowEnd, int colEnd){

        Image extractedImg = new Image();

        // Init des valeurs de la nouvelle image.
        int widthE = colEnd - colStart;
        int heightE = rowEnd - rowStart;

        extractedImg.set_width(widthE);
        extractedImg.set_heigth(heightE);
        extractedImg.set_type(_type);
        extractedImg.set_maxValue(_maxValue);
        extractedImg._img = new Pixel[_height][_width];  // Resize de l'image selon les dimensions lues

        PixelPGM pgm;
        PixelPPM ppm;

        // Extraction des pixels dans
        for(int i = rowStart; i < rowEnd; i++){
            for(int j = colStart; j < colEnd; j++){

                if(_type.equals("P2")){
                    pgm = new PixelPGM();
                    pgm.setValue(this.getPixel(i,j).getValue());
                    extractedImg._img[i - rowStart][j - colStart] = pgm;
                }
                else{
                    ppm = new PixelPPM();
                    ppm.setValue(this.getPixel(i,j).getValue());
                    extractedImg._img[i - rowStart][j - colStart] = ppm;
                }

            }
        }

        return extractedImg;
    }

    /**
     * Fait la moyenne des pixels dans une section pour réduire l'image.
     * L'image est séparé en 4 sections comme un graphique.
     *
     * @return L'image réduite après le calcul de moyennes
     */
    public Image shrink(){

        Image shrinkedImage = new Image();
        int[][] values = new int[4][3];
        int[] sum = new int[3];
        int[] average = new int[3];
        PixelPGM pgm;
        PixelPPM ppm;
        shrinkedImage.set_width(_width/2);
        shrinkedImage.set_heigth(_height/2);
        shrinkedImage.set_type(_type);
        shrinkedImage.set_maxValue(_maxValue);
        shrinkedImage._img = new Pixel[_height/2][_width/2];  // Resize de l'image selon les dimensions lues

        for(int i = 0; i < _height - 1; i+=2)
            for (int j = 0; j < _width - 1; j+=2)
            {
                values[0] = (_img[i][j].getValue());    // X O
                                                        // O O

                values[1] = (_img[i][j + 1].getValue());    // O X
                                                            // O O

                values[2] = (_img[i + 1][j].getValue());    // O O
                                                            // X O

                values[3] = (_img[i + 1][j + 1].getValue());    // O O
                                                                // O X

                if(_type.equals("P2")) {
                    for (int[] pixelValue : values) {
                        sum[0] += pixelValue[0]; //on prend values[0,1,2,3][0] et ajoute dans sum
                    }
                    average[0] = sum[0] / 4;
                    pgm = new PixelPGM();
                    pgm.setValue(average);

                    shrinkedImage._img[i / 2][j / 2] = pgm;
                }
                else
                {
                    for (int k = 0; k < 3; k++)
                        for (int m = 0; m < 4; m++)
                        {
                            sum[k] += values[m][k]; //on fait la somme des R - G - B des différents pixels
                        }

                    for (int l = 0; l < 3; l++)
                    {
                        average[l] = sum[l] / 4; //on calcule les moyennes pour R, G et B
                    }
                    ppm = new PixelPPM();
                    ppm.setValue(average);
                    shrinkedImage._img[i / 2][j / 2] = ppm; //on envoie le tableau de moyenne au pixel de la nouvelle image
                }
                for(int a= 0; a < sum.length; a++ )
                    sum[a] = 0;
            }

        return shrinkedImage;
    }

    /**
     * Modifie l'image pour faire une rotation de 90 degree par la droite
     *
     */
    public void rotate() {
        Image rotated = new Image();

        int newHeight = _width;
        int newWidth = _height;
        rotated._img = new Pixel[newHeight][newWidth];

        for (int i=0; i<_height ; i++){

            for (int j=0;j<_width ; j++){
                rotated._img[j][_height-1-i] = _img[i][j];
            }

        }

        _height = newHeight;
        _width = newWidth;
        _img = rotated._img;
    }


}



