
public class Cipher {
    public static String keyingAlphabet (String alph, int key) {
        String keyedAlph = "";
        
        keyedAlph = alph.substring(key-1, alph.length());
        keyedAlph += alph.substring(0, key-1);
        
        return keyedAlph;
    }
    
    public static String CaesarCipher (String msg, int key) {
        String encrypt = "";
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String keyedAlph = keyingAlphabet(alph, key);
        
        //encrypting the string
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            int index = alph.indexOf(ch);
            encrypt += keyedAlph.charAt(index);
        }
        
        //System.out.println(encrypt);
        return encrypt;
    }
    
    public static String Aristocrat (String msg, String keyw, int key) {
        String encrypt = "";
        
        return encrypt;
    }
    
    public static void main(String[] args) {
        
    }
}
