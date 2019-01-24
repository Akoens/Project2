import java.math.BigDecimal;
import java.util.Random;

public class LicensePlateGenerator {
    Random rd;
    int nlRatio;
    int dRatio;
    int bRatio;
    int total;

    public LicensePlateGenerator(int nlRatio, int dRatio, int bRatio) {
        rd = new Random();
        this.nlRatio = nlRatio;
        this.dRatio = dRatio;
        this.bRatio = bRatio;
        total = nlRatio + dRatio + bRatio;
    }

    public String generatePlate() {
        int decideCountry = rd.nextInt(total);
        String licenseplate;
        if (decideCountry < nlRatio) {
            int decidePlate = rd.nextInt(7) + 1;
            licenseplate = "NL: ";
            switch (decidePlate) {
                case 1:
                    licenseplate += generateCharacters(3, "char") + "-" + generateCharacters(2, "number") + "-" + generateCharacters(1, "char");
                    break;
                case 2:
                    licenseplate += generateCharacters(1, "char") + "-" + generateCharacters(2, "number") + "-" + generateCharacters(3, "char");
                    break;
                case 3:
                    licenseplate += generateCharacters(1, "number") + "-" + generateCharacters(2, "char") + "-" + generateCharacters(3, "number");
                    break;
                case 4:
                    licenseplate += generateCharacters(3, "number") + "-" + generateCharacters(2, "char") + "-" + generateCharacters(1, "number");
                    break;
                case 5:
                    licenseplate += generateCharacters(1, "number") + "-" + generateCharacters(3, "char") + "-" + generateCharacters(2, "number");
                    break;
                case 6:
                    licenseplate += generateCharacters(2, "number") + "-" + generateCharacters(2, "char") + "-" + generateCharacters(2, "char");
                    break;
                case 7:
                    licenseplate += generateCharacters(2, "char") + "-" + generateCharacters(2, "number") + "-" + generateCharacters(2, "number");
            }
            return licenseplate;
        } else if (decideCountry < dRatio + nlRatio) {
            int decidePlate = rd.nextInt(4) + 1;
            licenseplate = "D: ";
            switch (decidePlate) {
                case 1:
                    licenseplate += generateCharacters(3, "char") + "-" + generateCharacters(2, "char") + "-" + generateCharacters(3, "number");
                    break;
                case 2:
                    licenseplate += generateCharacters(1, "char") + "-" + generateCharacters(2, "char") + "-" + generateCharacters(4, "number");
                    break;
                case 3:
                    licenseplate += generateCharacters(2, "char") + "-" + generateCharacters(1, "char") + "-" + generateCharacters(4, "number");
                    break;
                case 4:
                    licenseplate += generateCharacters(2, "char") + "-" + generateCharacters(1, "char") + "-" + generateCharacters(3, "number");
                    break;
            }
            return licenseplate;
        } else {
            int decidePlate = rd.nextInt(3) + 1;
            licenseplate = "B: ";
            switch (decidePlate) {
                case 1:
                    licenseplate += generateCharacters(3, "number") + "-" + generateCharacters(3, "char");
                    break;
                case 2:
                    licenseplate += generateCharacters(3, "char") + "-" + generateCharacters(3, "number");
                    break;
                case 3:
                    licenseplate += generateCharacters(1, "number") + "-" + generateCharacters(3, "char") + "-" + generateCharacters(3, "number");
            }
        }
        return licenseplate;
    }

    public int randomValueCharacter(){
        return rd.nextInt(26) + 65;
    }

    public int randomValueNumber(){
        return rd.nextInt(10) + 48;
    }

    public String generateCharacters(int x, String charOrNumber){
        String result = "";
        if ((x > 0 && x <= 3) && charOrNumber.equals("char")){
            if (x == 1){
                result = Character.toString ((char) randomValueCharacter());
            } else if (x == 2){
                result = Character.toString ((char) randomValueCharacter())+((char) randomValueCharacter());
            } else if (x == 3){
                result = Character.toString ((char) randomValueCharacter())+((char) randomValueCharacter())+((char) randomValueCharacter());
            }
        } else if ((x > 0 && x <= 4) && charOrNumber.equals("number")) {
            if (x == 1){
                result = Character.toString ((char) randomValueNumber());
            } else if (x == 2){
                result = Character.toString ((char) randomValueNumber())+((char) randomValueNumber());
            } else if (x == 3){
                result = Character.toString ((char) randomValueNumber())+((char) randomValueNumber())+((char) randomValueNumber());
            } else if (x == 4) {
                result = Character.toString((char) randomValueNumber()) + ((char) randomValueNumber()) + ((char) randomValueNumber());
            }
        }
        return result;
    }

    public int getNlRatio() {
        return nlRatio;
    }

    public void setNlRatio(int nlRatio) {
        this.nlRatio = nlRatio;
    }

    public int getdRatio() {
        return dRatio;
    }

    public void setdRatio(int dRatio) {
        this.dRatio = dRatio;
    }

    public int getbRatio() {
        return bRatio;
    }

    public void setbRatio(int bRatio) {
        this.bRatio = bRatio;
    }

    public int getTotal() {
        return total;
    }
}