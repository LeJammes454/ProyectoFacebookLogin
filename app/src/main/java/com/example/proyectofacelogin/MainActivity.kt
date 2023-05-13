package com.example.proyectofacelogin
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

class MainActivity : AppCompatActivity() {
    private lateinit var callbackManager: CallbackManager

    private val PERMISSIONS = listOf("pages_read_engagement", "pages_manage_posts")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el CallbackManager
        callbackManager = CallbackManager.Factory.create()

        // Configurar el botón de inicio de sesión de Facebook
        val loginButton: LoginButton = findViewById(R.id.login_button)
        loginButton.setPermissions(PERMISSIONS)
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
          

            override fun onCancel() {
                // El usuario canceló la solicitud de permisos
            }

            override fun onError(error: FacebookException) {
                TODO("Not yet implemented")
            }

            override fun onSuccess(result: LoginResult) {
                publishPostToPage()
            }

        })
    }

    // Sobrescribir el método onActivityResult para llamar a callbackManager.onActivityResult
    // Esto es necesario para manejar las respuestas del inicio de sesión de Facebook
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun publishPostToPage() {
        val parameters = Bundle()
        parameters.putString("message", "Texto de tu publicación")

        val request = GraphRequest(
            AccessToken.getCurrentAccessToken(),
            "/{page-id}/feed",
            parameters,
            HttpMethod.POST,
            object : GraphRequest.Callback {
                override fun onCompleted(response: GraphResponse) {
                    // Aquí puedes manejar la respuesta de la publicación
                    val postId = response.getJSONObject()?.getString("id")
                    if (postId != null) {
                        // La publicación se realizó exitosamente
                        Log.d("Post", "Publicación exitosa. ID: $postId")
                    } else {
                        // La publicación falló
                        Log.d("Post", "La publicación falló")
                    }
                }
            }
        )

        request.executeAsync()
    }


}
