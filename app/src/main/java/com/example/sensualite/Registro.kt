package com.example.sensualite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.sensualite.databinding.ActivityRegistroBinding
import java.sql.PreparedStatement
import java.sql.SQLException
import android.text.InputFilter
import android.text.Spanned


lateinit var editText1: EditText
lateinit var editText2: EditText
lateinit var editText3: EditText
lateinit var edad:AutoCompleteTextView
lateinit var editText4: EditText
lateinit var editText5: EditText
lateinit var editText6: EditText
lateinit var editText7: EditText
lateinit var btn_registro: Button

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    private var connectSql = ConnectSql()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("18","19","20","21","22","23","24","25","26","27","28"
            ,"29","30","31","32","33","34","35","36","37","38"
            ,"39","40","41","42","43","44","45","46","47","48"
            ,"49","50","51","52","53","54","55","56","57","58"
            ,"59","60","61","62","63","64","65","66","67","68"
            ,"69","70","71","72","73","74","75","76","77","78"
            ,"79","80","81","82","83","84","85","86","87","88"
            ,"89","90","91","92","93","94","95","96","97","98"
            ,"99","100")

        editText1 =findViewById(R.id.nombre)
        editText2 = findViewById(R.id.apaterno)
        editText3 = findViewById(R.id.amaterno)
        edad = findViewById(R.id.edad)
        editText4 = findViewById(R.id.cpostal)
        editText5 = findViewById(R.id.correo)
        editText6 = findViewById(R.id.contraseña)
        editText7 = findViewById(R.id.confirmacion_contraseña)
        btn_registro = findViewById(R.id.registro)

        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        binding.edad.setAdapter(adapter)


        val button = findViewById<Button>(R.id.salir_login)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_registro.setOnClickListener {
            val nombre = editText1.text.toString()
            val apaterno = editText2.text.toString()
            val amaterno = editText3.text.toString()
            val edadSeleccionada = edad.text.toString()
            val codigoPostalString = editText4.text.toString()
            val correo = editText5.text.toString()
            val contraseña = editText6.text.toString()
            val confirmacionContraseña = editText7.text.toString()

            if (nombre.isEmpty() || apaterno.isEmpty() || amaterno.isEmpty() || edadSeleccionada.isEmpty() || codigoPostalString.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || confirmacionContraseña.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else if (contraseña != confirmacionContraseña) {
                Toast.makeText(this, "La confirmación de contraseña no coincide", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val conn = connectSql.dbConn()
                    val query = "SELECT COUNT(*) FROM Usuarios WHERE email = ?"
                    val stmt = conn?.prepareStatement(query)
                    stmt?.setString(1, correo)
                    val resultSet = stmt?.executeQuery()
                    resultSet?.next()
                    val count = resultSet?.getInt(1) ?: 0
                    if (count > 0) {
                        Toast.makeText(this, "El correo electrónico ya está registrado", Toast.LENGTH_SHORT).show()
                    } else {
                        val addUsuario: PreparedStatement =
                            connectSql.dbConn()?.prepareStatement("INSERT INTO Usuarios VALUES (?,?,?,?,?,?,?)")!!
                        addUsuario.setString(1, nombre)
                        addUsuario.setString(2, apaterno)
                        addUsuario.setString(3, amaterno)
                        addUsuario.setString(4, edadSeleccionada)
                        val codigoPostalInt = codigoPostalString.toInt()
                        addUsuario.setInt(5, codigoPostalInt)
                        addUsuario.setString(6, correo)
                        addUsuario.setString(7, contraseña)

                        addUsuario.executeUpdate()

                        Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                    }
                } catch (ex: SQLException) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}