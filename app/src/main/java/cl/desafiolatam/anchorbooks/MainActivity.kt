package cl.desafiolatam.anchorbooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/*
[x] Modelo (data class)
[] Consumo API (retrofit)
[] Repositorio
[] ViewModel
[] ViewBinding
[] Fragmento de listado (listing)
[] RecyclerView + Adapter + ViewHolder
[] Fragmento de detalle (detail)
[] Testing unitario para mappers
[] Intent implícito para compartir
[] Persistencia de datos locales (ROOM)
[] Testing para la base de datos
*/




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(FirstFragment())


    }

    fun changeFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame, frag).commit()
    }
}