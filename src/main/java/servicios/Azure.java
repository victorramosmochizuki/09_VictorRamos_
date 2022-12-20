package servicios;

import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import modelo.Datos;
import modelo.Proveedor;

public class Azure {

   
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Datos dato = new Datos();
        Proveedor proveedor = new Proveedor();
        recognizeFromMic(proveedor);
        System.out.println("data ="+ proveedor.getTELPROV());
   
    }

    public static void recognizeFromMic(Proveedor proveedor) throws InterruptedException, ExecutionException {
        
                
        SpeechConfig speechConfig = SpeechConfig.fromSubscription("657ba4f4c7b64d7dbfc9ac6d54356744", "eastus");
        speechConfig.setSpeechRecognitionLanguage("es-PE");
        AudioConfig audioConfig = AudioConfig.fromDefaultMicrophoneInput();
        SpeechRecognizer recognizer = new SpeechRecognizer(speechConfig, audioConfig);

        System.out.println("Speak into your microphone.");
        Future<SpeechRecognitionResult> task = recognizer.recognizeOnceAsync();
        SpeechRecognitionResult result = task.get();
        proveedor.setTELPROV(result.getText());
        System.out.println("RECOGNIZED: Text=" + result.getText());
    }

}
