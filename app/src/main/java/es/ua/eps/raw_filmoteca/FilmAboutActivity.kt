package es.ua.eps.raw_filmoteca

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import es.ua.eps.raw_filmoteca.databinding.ActivityFilmAboutBinding


class FilmAboutActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityFilmAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        config()

        bindings.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    //---------------------------------
    private fun initUI() {
        bindings = ActivityFilmAboutBinding.inflate(layoutInflater)
        with(bindings) {
            setContentView(root)
        }
    }

    //---------------------------------
    private fun config() {
        //CONDIGURACIÓN
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "Filmoteca"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#590DE3")))
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#3204AC")
    }

}