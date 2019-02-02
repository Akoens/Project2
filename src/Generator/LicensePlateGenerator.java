package Generator;

import java.util.Random;

public class LicensePlateGenerator {

    private enum CharType {
        CHARACTER,
        NUMBER
    }

    private Random rd;
    private int dutchRatio;
    private int germanRatio;
    private int belgianRatio;
    private int total;

    /**
     * Constructor for the LicensePlateGenerator object, which takes three integer parameters.
     * @param dutchRatio   the dutch nationality ratio of appearing cars.
     * @param germanRatio  the german nationality ratio of appearing cars.
     * @param belgianRatio the belgian nationality ratio of appearing cars.
     */
    public LicensePlateGenerator(int dutchRatio, int germanRatio, int belgianRatio) {
        this.dutchRatio = dutchRatio;
        this.germanRatio = germanRatio;
        this.belgianRatio = belgianRatio;

        rd = new Random();
        total = dutchRatio + germanRatio + belgianRatio;
    }

    /**
     * Method to generate a a random license plate string using the given parameters from the constructor.
     * @return a string object containing a Dutch, a German or a Belgium license plate.
     */
    public String generatePlate() {
        int decideCountry = rd.nextInt(total);
        String licensePlate;
        if (decideCountry < dutchRatio) {
            int decidePlate = rd.nextInt(7) + 1;
            licensePlate = "NL: ";
            switch (decidePlate) {
                case 1:
                    licensePlate += generateCharacters(3, CharType.CHARACTER) + "-" + generateCharacters(2, CharType.NUMBER) + "-" + generateCharacters(1, CharType.CHARACTER);
                    break;
                case 2:
                    licensePlate += generateCharacters(1, CharType.CHARACTER) + "-" + generateCharacters(2, CharType.NUMBER) + "-" + generateCharacters(3, CharType.CHARACTER);
                    break;
                case 3:
                    licensePlate += generateCharacters(1, CharType.NUMBER) + "-" + generateCharacters(2, CharType.CHARACTER) + "-" + generateCharacters(3, CharType.NUMBER);
                    break;
                case 4:
                    licensePlate += generateCharacters(3, CharType.NUMBER) + "-" + generateCharacters(2, CharType.CHARACTER) + "-" + generateCharacters(1, CharType.NUMBER);
                    break;
                case 5:
                    licensePlate += generateCharacters(1, CharType.NUMBER) + "-" + generateCharacters(3, CharType.CHARACTER) + "-" + generateCharacters(2, CharType.NUMBER);
                    break;
                case 6:
                    licensePlate += generateCharacters(2, CharType.NUMBER) + "-" + generateCharacters(2, CharType.CHARACTER) + "-" + generateCharacters(2, CharType.CHARACTER);
                    break;
                case 7:
                    licensePlate += generateCharacters(2, CharType.CHARACTER) + "-" + generateCharacters(2, CharType.NUMBER) + "-" + generateCharacters(2, CharType.NUMBER);
                    break;
            }
            return licensePlate;
        } else if (decideCountry < germanRatio + dutchRatio) {
            int decidePlate = rd.nextInt(4) + 1;
            licensePlate = "D: ";
            switch (decidePlate) {
                case 1:
                    licensePlate += generateCharacters(3, CharType.CHARACTER) + "-" + generateCharacters(2, CharType.CHARACTER) + "-" + generateCharacters(3, CharType.NUMBER);
                    break;
                case 2:
                    licensePlate += generateCharacters(1, CharType.CHARACTER) + "-" + generateCharacters(2, CharType.CHARACTER) + "-" + generateCharacters(4, CharType.NUMBER);
                    break;
                case 3:
                    licensePlate += generateCharacters(2, CharType.CHARACTER) + "-" + generateCharacters(1, CharType.CHARACTER) + "-" + generateCharacters(4, CharType.NUMBER);
                    break;
                case 4:
                    licensePlate += generateCharacters(2, CharType.CHARACTER) + "-" + generateCharacters(1, CharType.CHARACTER) + "-" + generateCharacters(3, CharType.NUMBER);
                    break;
            }
            return licensePlate;
        } else {
            int decidePlate = rd.nextInt(3) + 1;
            licensePlate = "B: ";
            switch (decidePlate) {
                case 1:
                    licensePlate += generateCharacters(3, CharType.NUMBER) + "-" + generateCharacters(3, CharType.CHARACTER);
                    break;
                case 2:
                    licensePlate += generateCharacters(3, CharType.CHARACTER) + "-" + generateCharacters(3, CharType.NUMBER);
                    break;
                case 3:
                    licensePlate += generateCharacters(1, CharType.NUMBER) + "-" + generateCharacters(3, CharType.CHARACTER) + "-" + generateCharacters(3, CharType.NUMBER);
                    break;
            }
        }
        return licensePlate;
    }

    /**
     * Method to decide a random character.
     * @return a randomly decided character between A-Z in Uppercase.
     */
    private char randomCharacter(){
        return (char) (rd.nextInt(26) + 65);
    }

    /**
     * Method to decide a random number.
     * @return a randomly decided number between 0-9.
     */
    private int randomNumber(){
        return rd.nextInt(10);
    }

    /**
     * Method to generate an x amount of letters or numbers concatenated into a string.
     * @param x the amount of letters or numbers as an integer.
     * @param type which type of character is needed, valid input will be: CHARACTER or NUMBER.
     * @return the chosen amount and which type of characters concatenated into a string
     */
    private String generateCharacters(int x, CharType type){
        StringBuilder result = new StringBuilder();

        if (x < 1) {
            return result.toString();
        }

        switch (type) {
            case CHARACTER:
                if (x <= 3) {
                    for (int i = 0; i<x; i++)
                        result.append(randomCharacter());
                    break;
                }
            case NUMBER:
                if (x <= 4) {
                    for (int i = 0; i<x; i++)
                        result.append(randomNumber());
                }
        }

        return result.toString();
    }

    /**
     * Method to retrieve the given Dutch ratio.
     * @return the Dutch ratio as an integer.
     */
    public int getDutchRatio() {
        return dutchRatio;
    }

    /**
     * Method to retrieve the given German ratio.
     * @return the German ratio as an integer.
     */
    public int getGermanRatio() {
        return germanRatio;
    }

    /**
     * Method to retrieve the given Belgian ratio.
     * @return the Belgian ratio as an integer.
     */
    public int getBelgianRatio() {
        return belgianRatio;
    }
}