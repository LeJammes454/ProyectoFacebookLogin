package com.example.proyectofacelogin

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    private lateinit var callbackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el CallbackManager
        callbackManager = CallbackManager.Factory.create()

        // Llamada a la función para generar el hash clave
        //generateKeyHash()
    }

    // Sobrescribir el método onActivityResult para llamar a callbackManager.onActivityResult
    // Esto es necesario para manejar las respuestas del inicio de sesión de Facebook
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    // Crear una función para manejar el inicio de sesión de Facebook
    private fun loginWithFacebook() {
        // Definir los permisos que se solicitarán al usuario durante el inicio de sesión
        val permissions = listOf("email", "public_profile")

        // Iniciar sesión con los permisos definidos y el CallbackManager
        LoginManager.getInstance().logInWithReadPermissions(this, permissions)

        // Registrar el callback para manejar el resultado del inicio de sesión
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    // El inicio de sesión fue exitoso, puedes acceder a los datos del usuario usando loginResult.accessToken.
                }

                override fun onCancel() {
                    // El usuario canceló el inicio de sesión.
                }

                override fun onError(exception: FacebookException) {
                    // Ocurrió un error durante el inicio de sesión.
                }
            })
    }
    private fun generateKeyHash() {
        try {
            val packageName = packageName
            val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            val signatures = packageInfo.signatures

            val md = MessageDigest.getInstance("SHA")
            for (signature in signatures) {
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), Base64.DEFAULT))
                Log.d("KeyHash", hashKey)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}