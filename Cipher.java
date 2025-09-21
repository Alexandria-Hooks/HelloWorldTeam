/* Cipher Class
*
*  used to encrypt phrases
*  via different ciphers
*/
public class Cipher {
    public static String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Shifts alphabet by key to the left
    public static String keyingAlphabet(String alph, int key) {
        return alph.substring(key) + alph.substring(0, key);
    }
    
    public static String CaesarCipher(String msg, int key) {
        StringBuilder encrypt = new StringBuilder();
        String keyedAlph = keyingAlphabet(alph, key);
        
        //encrypting the string
        char ch;    // current character
        int index;  // index of current character
        for (int i = 0; i < msg.length(); i++) {
            ch = msg.charAt(i);
            if (alph.contains(Character.toString(ch))) {
                index = alph.indexOf(ch);
                encrypt.append(keyedAlph.charAt(index));
            } else { // in case of spaces and punctuation
                encrypt.append(ch);
            }
        }

        return encrypt.toString();
    }

    public static String Aristocrat(String msg, String keyw, int key) {
        StringBuilder encrypt = new StringBuilder();

        String aristAlph = keyw;    // alphabet with the key starting it
        String[] uniqChars = new String[keyw.length()]; //uniq chars in keyword
        String ch;
        for (int i = 0; i < keyw.length(); i++) {
            ch = keyw.substring(i, i + 1);
            uniqChars[i] = ch;
        }
        String temp = alph;
        for (String uniqChar : uniqChars) {
            if (temp.contains(uniqChar)) {
                temp = temp.replaceAll(uniqChar, "");
            }
        }

        aristAlph += temp;  // rest of it
        String keyedAlph = keyingAlphabet(aristAlph, key);  // get keyed alphabet

        //encrypting the string
        char c;
        int index;
        for (int i = 0; i < msg.length(); i++) {
            c = msg.charAt(i);
            if (alph.contains(Character.toString(c))) {
                index = alph.indexOf(c);
                encrypt.append(keyedAlph.charAt(index));
            } else { // for spaces and punctuation
                encrypt.append(c);
            }
        }

        return encrypt.toString();
    }

    public static String XenocryptCipher(String msg, String keyw, int key) { // TODO: FIX
        StringBuilder encrypt = new StringBuilder();
        String spanishAlph = "ABCDEFGHIJKLMÑOPQRSTUVWXYZ";
        String aristAlph = keyw;    //alphabet with the key starting it
        String[] uniqChars = new String[keyw.length()]; //uniq chars in keyword
        String temp = spanishAlph.substring(keyw.length()-1, 27);
        for (String uniqChar : uniqChars) {
            if (temp.contains(uniqChar)) {
                temp = temp.replaceAll(uniqChar, "");
            }
        }
        
        aristAlph += temp;  // rest of it
        String keyedAlph = keyingAlphabet(aristAlph, key);  // get keyed alphabet
        
        // encrypting the string
        char c;
        int index;
        for (int i = 0; i < msg.length(); i++) {
            c = msg.charAt(i);
            if (spanishAlph.contains(Character.toString(c))) {
                index = spanishAlph.indexOf(c);
                encrypt.append(keyedAlph.charAt(index));
            } else {
                encrypt.append(c);
            }
        }

        return encrypt.toString();
    }

    public static String VigenereCipher(String msg, String keyw){
        StringBuilder result = new StringBuilder();

        // to match message length
        StringBuilder actualKey = new StringBuilder();
        char a;
        for(int i = 0; i < msg.length(); i++){
            a = keyw.charAt(i % keyw.length());
            actualKey.append(a);
        }

        // process each character message
        char ch;
        int chIndex;
        int actualIndex;
        int result1;
        for (int i = 0; i < msg.length(); i++){
            ch = msg.charAt(i);
            if(alph.indexOf(ch) != -1){ //encrypt letters
                chIndex = alph.indexOf(ch);
                actualIndex = alph.indexOf(actualKey.charAt(i));
                result1 = (chIndex + actualIndex) % 26;
                result.append(alph.charAt(result1));
            } else { // leave non-alphabetical characters unchanged
                result.append(ch);
            } 
        }
        return result.toString();
    }
    
    public static void main(String[] args) { // for debugging cipher
        
    }
}
