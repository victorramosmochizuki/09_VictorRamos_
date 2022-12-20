
package servicios;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.Usuario;


public class Password {
     public static String encrypt (String data){
        return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

     
     
     
    public static void passAleatorio2(Usuario usuario)throws Exception  {
        int length = 10;
        String symbol = "-/.^&*_!@%=+>)";
        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String small_letter = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String finalString = cap_letter + small_letter
                + numbers + symbol;

        Random random = SecureRandom.getInstanceStrong();

        char[] password = new char[length];

        for (int i = 0; i < length; i++) {
            password[i]= finalString.charAt(random.nextInt(finalString.length()));

        }
        String pass=String.valueOf(password);
        usuario.setPWUSU(pass); 
         Logger.getGlobal().log(Level.INFO,pass);
    }
    public static void main(String[] args) throws Exception {
       Usuario usuario = new Usuario();
        passAleatorio2(usuario);

    }

}
