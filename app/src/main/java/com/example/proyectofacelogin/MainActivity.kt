package com.example.proyectofacelogin
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofacelogin.databinding.ActivityMainBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var callbackManager: CallbackManager

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
                // El usuario canceló el inicio de sesión con Facebook
            }

            override fun onError(error: FacebookException) {
                // Ocurrió un error durante el inicio de sesión con Facebook
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
