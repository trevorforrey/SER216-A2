/**
 * Created by trevorforrey on 1/30/16.
 */
public class HexNumberFormatException extends Exception {


    public char invalidCharacter;

    public HexNumberFormatException() {}

    // Single arg constructor
    public HexNumberFormatException(char illegalCharacter) {
        invalidCharacter = illegalCharacter;
    }

    // Returns the invalid character of an instance of a HexNumberFormatException object
    public char getInvalidCharacter() {
        return invalidCharacter;
    }

}
