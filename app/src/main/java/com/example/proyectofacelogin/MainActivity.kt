package com.example.proyectofacelogin
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.example.proyectofacelogin.databinding.ActivityMainBinding
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.facebook.share.Sharer
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import org.json.JSONObject
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var callbackManager: CallbackManager
    private var bandera = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        callbackManager = CallbackManager.Factory.create()
        binding.loginButton.setPermissions("email")


        binding.loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val accessToken = loginResult.accessToken


                // Obtener datos del usuario
                val request = GraphRequest.newMeRequest(accessToken) { jsonObject, response ->
                    val name = jsonObject?.optString("name")
                    val email = jsonObject?.optString("email")

                    // Mostrar datos en la interfaz de usuario
                    binding.txtName.text = name
                    binding.txtEmail.text = email

                }

                val parameters = Bundle()
                parameters.putString("fields", "name,email")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException) {
                // Ocurrió un error durante el inicio de sesión con Facebook
            }
        })

        binding.btnShare.setOnClickListener {
            shareContent()
        }

    }

    private fun shareContent() {
        val shareContent = ShareLinkContent.Builder()
            .setContentUrl(Uri.parse("https://www.example.com")) // URL del contenido que deseas compartir
            .setQuote("¡Mira este contenido compartido desde mi aplicación!") // Texto adicional que deseas incluir
            .build()

        val shareDialog = ShareDialog(this)
        shareDialog.registerCallback(callbackManager, object : FacebookCallback<Sharer.Result> {


            override fun onCancel() {
                Toast.makeText(applicationContext, "Compartir cancelado", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(applicationContext, "Error al compartir: ${error?.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(result: Sharer.Result) {
                Toast.makeText(applicationContext, "Contenido compartido exitosamente", Toast.LENGTH_SHORT).show()
            }

        })

        shareDialog.show(shareContent, ShareDialog.Mode.AUTOMATIC)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }



    // Método para generar la Key Hash
    fun generateKeyHash(context: Context, packageName: String) {
        try {
            val info = context.packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT)
                Log.d("KeyHash", "Key Hash: $keyHash")
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

    }

}


