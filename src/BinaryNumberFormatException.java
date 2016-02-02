/**
 * Created by trevorforrey on 1/30/16.
 */
public class BinaryNumberFormatException extends Exception{


    public char invalidCharacter;

    public BinaryNumberFormatException() {}


    // Single arg constructor
    public BinaryNumberFormatException(char illegalCharacter) {
        invalidCharacter = illegalCharacter;
    }

    // Returns the invalid character of an instance of a BinaryNumberFormatException object
    public char getInvalidCharacter() {
        return invalidCharacter;
    }

}
