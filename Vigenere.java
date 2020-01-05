public class Vigenere
{
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ
    private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    
    private static final int alphabetLength = 26;
    private String[][] brett = new String[alphabetLength][alphabetLength]; // Mir kein besserer Name als Brett eingefallen :D
    
    private String klartext = "NachtsIstEsKaelterAlsDraussen".toLowerCase().trim();
    private String key = "Stift".toLowerCase().trim();
    private String verschluesselterText = "FTKMMKBAYXKDIJELXZFEKWZFNKLMS".toLowerCase();
    
    private String filledKey = getFilledKey(key);
    
    Vigenere() {
        fillBrett();
    }
    
    /**
     * Befüllt das zweidimensionales Array mit dem Alphabet. Nach jeder Spalte wird
     * das Alphabet um eine Stelle verschoben.
     */
    private void fillBrett() {
        for(int i = 0; i < brett.length; i++) {
            for(int j = 0; j < brett[i].length; j++) {
                brett[i][j] = alphabet[shift(j, i, true)];
            }
        }
    }
    
    public void verschluesseln() {
        final StringBuilder result = new StringBuilder();
        for(int i = 0; i < klartext.length(); i++) {
            final String klartextLetter = Character.toString(klartext.charAt(i));
            final String keyLetter = Character.toString(filledKey.charAt(i)); 
            result.append(brett[getPositionInAlphabet(klartextLetter)][getPositionInAlphabet(keyLetter)]);
        }
        System.out.println("Verschlüsselter Text: " + result);
    }
    
    public void entschluesseln() {
        final StringBuilder result = new StringBuilder();
        for(int i = 0; i < klartext.length(); i++) {
            final String verschluesselterTextLetter = Character.toString(verschluesselterText.charAt(i));
            final String keyLetter = Character.toString(filledKey.charAt(i));
            
            int positionOfKlartextLetter = -1;
            for(int j = 0; j < klartext.length(); j++) {
                String triedChar = brett[j][getPositionInAlphabet(keyLetter)];
                if(triedChar.equalsIgnoreCase(verschluesselterTextLetter)) {
                    positionOfKlartextLetter = j;
                    break;
                }
            }
            
            result.append(alphabet[positionOfKlartextLetter]);
        }
        System.out.println("Entschlüsselter Text: " + result);
    }
    
    /** Durchläuft das Alphabet, um die Position eines Buchstaben im Alphabet zu bekommen. */
    private int getPositionInAlphabet(String letter) {
        for(int i = 0; i < alphabet.length; i++) {
            if(alphabet[i].equalsIgnoreCase(letter)) return i;
        }
        return -1;
    }
    
    /** Bringt den Key auf die gleiche Länge des Textes (Rekursiv)*/
    private String getFilledKey(String key) {
        if(key.length() < klartext.length()) return getFilledKey(new StringBuilder(key).append(key).toString());
        return key.substring(0, klartext.length());
    }
    
    /** Gibt die Stelle im Alphabet vom verschobenen Buchstaben an. */
    private int shift(int letter, int shift, boolean encrypting) {
        return encrypting ? (letter + shift) % 26 : (letter - (shift - 26)) % 26;
    }
}
