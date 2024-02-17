package es.ua.eps.raw_filmoteca

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import es.ua.eps.raw_filmoteca.databinding.ActivityFilmListBinding
import es.ua.eps.raw_filmoteca.databinding.ActivityFilmSignBinding

class FilmSignActivity : AppCompatActivity() {
    private lateinit var bindings : ActivityFilmSignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        config()
    }


    //---------------------------------
    private fun initUI(){
        bindings = ActivityFilmSignBinding.inflate(layoutInflater)
        with(bindings){
            setContentView(root)
        }
    }

    //---------------------------------
    private fun config(){
        //CONDIGURACIÓN
        actionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "Filmoteca"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#590DE3")))
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#3204AC")
    }
}