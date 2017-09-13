package fiap.com.br.stt

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun falar(v : View) {
            val it = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            it.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
            it.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale algo:");
            it.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");
            it.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);

            startActivityForResult(it, 0);
        }
         fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
            super.onActivityResult(requestCode, resultCode, data)

            if (resultCode == Activity.RESULT_OK) {
                val palavras = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                val palavra = palavras[0]

                    if(palavra == "ligar"){
                        doAsync {
                             URL("http://10.0.2.2:3000").readText()
                        }
                    }else if (palavra == "desligar"){
                        doAsync {
                            URL("http://10.0.2.2:3000").readText()
                        }
                    }else{
                        toast("Comando nao reconhecido")
                    }
            }
        }
    }
}
