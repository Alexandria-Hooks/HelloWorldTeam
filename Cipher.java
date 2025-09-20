public class Cipher {
    public static String CaesarCipher (String msg, int key) {
        String encrypt = "";
        String alph = "abcdefghijklmnopqrstuvwxyz";
        String keyedAlph = "";
        
        //creates the keyed alphabet
        keyedAlph = alph.substring(key-1, alph.length());
        keyedAlph += alph.substring(0, key-1);
        
        /*System.out.println(alph);
        System.out.println(keyedAlph);
        System.out.println(keyedAlph.length());*/
        
        //encrypting the string
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            int index = alph.indexOf(ch);
            encrypt += keyedAlph.charAt(index);
        }
        
        //System.out.println(encrypt);
        return encrypt;
    }
    
    public static void main(String[] args) {
        
    }
}
