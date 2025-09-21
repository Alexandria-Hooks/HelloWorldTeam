/comment
public class Cipher {
    public static String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Shifts alphabet by key to the left
    public static String keyingAlphabet(String alph, int key) {
        String keyedAlph = "";
        keyedAlph = alph.substring(key-1, alph.length());
        keyedAlph += alph.substring(0, key-1);
        return keyedAlph;
    }

    public static String CaesarCipher(String msg, int key) {
        String encrypt = "";
        String keyedAlph = keyingAlphabet(alph, key);
        msg = msg.toUpperCase();
        //encrypting the string
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            int index = alph.indexOf(ch);
            if (index == -1) {continue;}
            encrypt += keyedAlph.charAt(index);
        }

        return encrypt;
    }

    //aristocrat
    public static String Aristocrat (String msg, String keyw, int key) {
        String encrypt = "";

        String aristAlph = keyw;    //alphabet with the key starting it
        String[] uniqChars = new String[keyw.length()]; //uniq chars in keyword
        for (int i = 0; i < keyw.length(); i++) {
            String ch = keyw.substring(i, i+1);
            uniqChars[i] = ch;
        }
        String temp = alph;
        for (int i = 0; i < uniqChars.length; i++) {
            if (temp.indexOf(uniqChars[i]) != -1) {
                temp = temp.replaceAll(uniqChars[i], "");
            }
        }

        aristAlph += temp;  //rest of it
        String keyedAlph = keyingAlphabet(aristAlph, key);  //getting the keyed alphabet
        System.out.println(keyedAlph);
        String t = msg.toUpperCase();
        //encrypting the string
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            //System.out.println(ch);
            int index = alph.indexOf(ch);
            if (index == -1) {continue;}
            encrypt += keyedAlph.charAt(index);
        }

        //System.out.println(encrypt);
        return encrypt;
    }


    public static String XenocryptCipher(String msg, String keyw, int key) {
        String alph2 = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String encrypt = "";
        String aristAlph = keyw;    //alphabet with the key starting it
        String[] uniqChars = new String[keyw.length()]; //uniq chars in keyword
        //String temp = alph2.substring(keyw.length()-1, 26);
        String temp = alph2;
        for (int i = 0; i < uniqChars.length; i++) {
            if (temp.indexOf(uniqChars[i]) != -1) {
                temp = temp.replaceAll(uniqChars[i], "");
            }
        }

        aristAlph += temp;  //rest of it
        String keyedAlph = keyingAlphabet(aristAlph, key);  //getting the keyed alphabet

        String t = msg.toUpperCase();
        //encrypting the string
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            int index = alph2.indexOf(ch);
            if (index == -1) {continue;}
            encrypt += keyedAlph.charAt(index);
        }

        //System.out.println(encrypt);
        return encrypt;
    }




    public static String VigenereCipher(String message, String key, boolean encrypt){
        StringBuilder result = new StringBuilder();
        message = message.toUpperCase();
        //to match message length. Extends key tom match lenght of message
        StringBuilder actualKey = new StringBuilder();
        char a;
        for(int i = 0; i < message.length(); i++){
            a = key.charAt(i % key.length());

            actualKey.append(a);
        }
        //process each character message
        char ch;
        int chIndex;
        int actualIndex;
        int result1;
        for (int i = 0; i < message.length(); i++){
            ch = message.charAt(i);
            if(alph.indexOf(ch) != -1){ //encrypt letters
                chIndex = alph.indexOf(ch); //index of message letter
                actualIndex = alph.indexOf(actualKey.charAt(i)); //index of key letter
                result1 = (chIndex + actualIndex) % 26; //encryption
                if (result1 == -1) {continue;}
                result.append(alph.charAt(result1));
            } else{
                result.append(ch); // leave non alphabetical characters unchanged
            }
        }
        return result.toString(); //return final thing

    }

    public static void main(String[] args) {

    }
}
