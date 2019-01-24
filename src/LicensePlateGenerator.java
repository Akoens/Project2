import java.util.Random;

public class LicensePlateGenerator {
    Random rd;
    public LicensePlateGenerator() {
        rd = new Random();
    }

    public String getPlate(){
        //license plate 1 = XXX-00-X
        //license plate 2 = X-00-XXX
        //license plate 3 = 0-XX-000
        //license plate 4 = 000-XX-0
        //license plate 5 = 0-XXX-00
        //license plate 6 = 00-XX-XX
        //license plate 7 = XX-00-00
        String licenseplate = "";
        int decidePlate = rd.nextInt(7) + 1;
        switch (decidePlate) {
            case 1: licenseplate = generateCharacters(3, "char")+"-"+generateCharacters(2,"number")+"-"+generateCharacters(1, "char");
            break;
            case 2: licenseplate = generateCharacters(1,"char")+"-"+generateCharacters(2,"number")+"-"+generateCharacters(3, "char");
            break;
            case 3: licenseplate = generateCharacters(1, "number")+"-"+generateCharacters(2, "char")+"-"+generateCharacters(3, "number");
            break;
            case 4: licenseplate = generateCharacters(3, "number")+"-"+generateCharacters(2, "char")+"-"+generateCharacters(1, "number");
            break;
            case 5: licenseplate = generateCharacters(1,"number")+"-"+generateCharacters(3,"char")+"-"+generateCharacters(2, "number");
            break;
            case 6: licenseplate = generateCharacters(2, "number")+"-"+generateCharacters(2,"char")+"-"+generateCharacters(2, "char");
            break;
            case 7: licenseplate = generateCharacters(2, "char")+"-"+generateCharacters(2, "number")+"-"+generateCharacters(2, "number");
        }
      return licenseplate;
    }

    public int randomValueCharacter(){
        return rd.nextInt(25) + 65;
    }

    public int randomValueNumber(){
        return rd.nextInt(9) + 48;
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
        } else if((x > 0 && x <= 3) && charOrNumber.equals("number")){
            if (x == 1){
                result = Character.toString ((char) randomValueNumber());
            } else if (x == 2){
                result = Character.toString ((char) randomValueNumber())+((char) randomValueNumber());
            } else if (x == 3){
                result = Character.toString ((char) randomValueNumber())+((char) randomValueNumber())+((char) randomValueNumber());
            }
        }
        return result;
    }

}
