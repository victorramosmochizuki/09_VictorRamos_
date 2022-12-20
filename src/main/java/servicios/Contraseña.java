package servicios;

import java.util.UUID;

public class Contraseña {
    
//    public static void contraseña() {
//        String contra = "";
//        String contra2 = "";
//        contra = UUID.randomUUID().toString().toUpperCase().substring(8,15);
//        contra2 = UUID.randomUUID().toString().toLowerCase().substring(8,15);
//        System.out.println(contra + contra2);
//        
//    }

    public static String NUMEROS = "0123456789";

    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    public static String ESPECIALES = "ñÑ";

    //
    public static String getPinNumber() {
        return getPassword(NUMEROS, 4);
    }

    public static String getPassword() {
        return getPassword(8);
    }

    public static String getPassword(int length) {
        return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
    }

    public static String getPassword(String key, int length) {
        String pswd = "";

        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }

        return pswd;
    }
}
