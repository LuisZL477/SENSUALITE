package com.example.sensualite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.sensualite.databinding.ActivityMainBinding
import java.sql.SQLException
import kotlin.concurrent.thread
lateinit var edittextemail: EditText
lateinit var edittextcontraseña: EditText
lateinit var btn_inicio_sesion: Button

// MainActivity.kt
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var connectSql = ConnectSql()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        edittextemail= findViewById(R.id.email)
        edittextcontraseña = findViewById(R.id.contra)
        btn_inicio_sesion = findViewById(R.id.iniciar_sesión)

        val buttonRegistro = findViewById<Button>(R.id.registro)

        buttonRegistro.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        btn_inicio_sesion.setOnClickListener {
            val email = edittextemail.text.toString()
            val contraseña = edittextcontraseña.text.toString()

            if(email.isEmpty() || contraseña.isEmpty()){
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                try{
                    val conn = connectSql.dbConn()
                    val query = "SELECT * FROM Usuarios WHERE email = ? AND contraseña = ?"
                    val stmt = conn?.prepareStatement(query)
                    stmt?.setString(1, email)
                    stmt?.setString(2, contraseña)
                    val resultSet = stmt?.executeQuery()

                    if(resultSet?.next() == true){
                        Toast.makeText(this,"Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Inicio::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Correo eléctrónico o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }catch (ex: SQLException){
                    Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
                }


            }



        }

        Thread.sleep(1000)
    }
}
