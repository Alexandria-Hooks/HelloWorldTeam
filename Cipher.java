//comment
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

    //untested hashtag i do not want to do this
    public static String Aristocrat (String msg, String keyw, int key) {
        String encrypt = "";
        
        String aristAlph = keyw;    //alphabet with the key starting it
        String[] uniqChars = new String[keyw.length()]; //uniq chars in keyword
        String temp = alph.substring(keyw.length()-1, 26);
        for (int i = 0; i < uniqChars.length; i++) {
            if (temp.indexOf(uniqChars[i]) != -1) {
                temp = temp.replaceAll(uniqChars[i], "");
            }
        }
        
        aristAlph += temp;  //rest of it
        String keyedAlph = keyingAlphabet(aristAlph, key);  //getting the keyed alphabet
        
        //encrypting the string
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            int index = alph.indexOf(ch);
            encrypt += keyedAlph.charAt(index);
        }
        
        //System.out.println(encrypt);
        return encrypt;
    }

     public static String VigenereCipher(String message, String key, boolean encrypt){
        String Alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder answer = new StringBuilder();

        //to match message lenght 
        StringBuilder ActulKeyBruh = new StringBuilder();
        for(int i = 0; i < message.length(); i++){
            char a = key.charAt (i % key.length());
            ActaualKeyBruh.append(a);
        }
        //process message (hopefully)
        for (int i = 0; i < message.length(); i++){
             char yey = message.ChartAt(i);
            if(alph.indexOf(yey) != -1){ //encrypt letters
                int lol = alph.indexOf(yey);
                int what = alph.indexOf(ActaualKeyBruh.charAt(i));
                int result1;

                if(encrypt){
                    result1 = (lol + what) % 26; //encryption 
                }else{
                    result1 = (lol - waht + 26) % 26; //decryption 
            }
                result.append(alph.CharAt(result1));
            }else{
                result.append(a);
            } 
        }
        return result.toString();
        
    }




    
    
    public static void main(String[] args) {
        
    }
}
