import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

public class Skyscraper {

    public static void main(String[] args) {
        int[] skyscrapers = generateSkyscraperHeights();
        int[] indexOfLeftPerson =  leftPersonView(skyscrapers);
        int[] indexOfRightPerson = rightPersonView(skyscrapers);
        displaySkyscraperViews(skyscrapers,indexOfLeftPerson, indexOfRightPerson);
    }

    private static int getUserInputForSkyscraperCount() {
        int amountOfSkyscraper = 0;
        boolean isValidInput = true;
    
        while (isValidInput) {
            System.out.println("Es sind nur Eingaben von 1 bis 10 erlaubt");
            System.out.print("Eingabe: ");
            Scanner sc = new Scanner(System.in);
            try {
                amountOfSkyscraper = sc.nextInt();
                if (amountOfSkyscraper < 1 || amountOfSkyscraper > 10) {
                    System.out.println("Falsche Eingabe Bitte versuche es erneut!");
                } else {
                    System.out.println("Es werden " + amountOfSkyscraper + " Hochhäuser erstellt.");
                    isValidInput = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe Bitte geben Sie eine Zahl ein.");
                sc.next();
            }
        }
        return amountOfSkyscraper;
    }

    private static void displaySkyscraperViews( int[] skyscraperheight, int[] indexOfLeftPerson, int[] indexOfRightPerson ){

        System.out.println("--------------------------------------");
        System.out.println("Hochhäuser: ");
        for (int height : skyscraperheight ){
            System.out.print(height + " ");
        }
        System.out.println("\n--------------------------------------");
        System.out.print("Linke Person sieht ingesamt " + indexOfLeftPerson.length + " Hochhäuser: ");
        System.out.print("Linke Person sieht folgende Hochhäuser: ");
        for (int index : indexOfLeftPerson ){
            System.out.print(index + " ");
        }
        System.out.println("\n--------------------------------------");
        System.out.print("rechte Person sieht ingesamt "+ indexOfRightPerson.length + " Hochhäuser: ");
        System.out.print("rechte Person sieht folgende Hochhäuser: ");
        for (int index : indexOfRightPerson ){
            System.out.print(index + " ");
        }
    }

    private static int[] generateSkyscraperHeights() {
        int amountOfSkyscraper = getUserInputForSkyscraperCount();
        System.out.println(amountOfSkyscraper);
        int [] skyscrapershape =  new int[amountOfSkyscraper];

        for (int i = 0 ; i < amountOfSkyscraper; i++){
            skyscrapershape[i] = (int) Math.max(0, Math.round(generateNormalDistribution()));
        }
        return skyscrapershape;
    }

    private static int[] leftPersonView(int[] skyscraper) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int highestSkyscraperIndex = 0;
        int highestSkyscraper = skyscraper[highestSkyscraperIndex];
        indexes.add(highestSkyscraperIndex + 1);

        for (int i = 1; i < skyscraper.length; i++) {
            if (skyscraper[i] > highestSkyscraper) {
                highestSkyscraper = skyscraper[i];
                highestSkyscraperIndex = i;
                indexes.add(highestSkyscraperIndex + 1);
            }
        }

        return convertToArray(indexes);
    }

    private static int[] rightPersonView(int[] skyscraper) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int highestSkyscraperIndex = skyscraper.length - 1;
        int highestSkyscraper = skyscraper[highestSkyscraperIndex];
        indexes.add(highestSkyscraperIndex + 1);

        for (int i = skyscraper.length - 2; i >= 0; i--) {
            if (skyscraper[i] > highestSkyscraper) {
                highestSkyscraper = skyscraper[i];
                highestSkyscraperIndex = i;
                indexes.add(highestSkyscraperIndex + 1);
            }
        }

        return convertToArray(indexes);
    }

    private static int[] convertToArray(ArrayList<Integer> list) {
        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    
    public static double generateNormalDistribution() {
        Random random = new Random();
        int meanHeight = 7;
        int variance = 3;

        double u1 = 1.0 - random.nextDouble();
        double u2 = 1.0 - random.nextDouble();
        double z = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);

        return meanHeight + Math.sqrt(variance) * z;
    }
}
