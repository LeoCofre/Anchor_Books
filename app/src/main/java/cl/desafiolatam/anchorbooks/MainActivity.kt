package cl.desafiolatam.anchorbooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/* 1.-_Crear Retrofit Client
*
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(FirstFragment())


    }

    private fun changeFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame, frag).commit()
    }
}