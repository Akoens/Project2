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

    public LicensePlateGenerator(int dutchRatio, int germanRatio, int belgianRatio) {
        this.dutchRatio = dutchRatio;
        this.germanRatio = germanRatio;
        this.belgianRatio = belgianRatio;

        rd = new Random();
        total = dutchRatio + germanRatio + belgianRatio;
    }

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

    private char randomCharacter(){
        return (char) (rd.nextInt(26) + 65);
    }

    private int randomNumber(){
        return rd.nextInt(10);
    }

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
                    for (int i=0; i<x; i++)
                        result.append(randomNumber());
                }
        }
        
        return result.toString();
    }

    public int getDutchRatio() {
        return dutchRatio;
    }

    public int getGermanRatio() {
        return germanRatio;
    }

    public int getBelgianRatio() {
        return belgianRatio;
    }
}