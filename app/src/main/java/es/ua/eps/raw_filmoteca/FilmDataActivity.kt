package es.ua.eps.raw_filmoteca

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import es.ua.eps.raw_filmoteca.data.FilmDataSource
import es.ua.eps.raw_filmoteca.databinding.ActivityFilmDataBinding
import es.ua.eps.raw_filmoteca.tools.OnTaskCompleted
import es.ua.eps.raw_filmoteca.tools.TaskRunner

class FilmDataActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FILM_ID = "EXTRA_FILM_ID"
    }

    private lateinit var bindings: ActivityFilmDataBinding
    private var index = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        config()
        index = intent.getIntExtra(EXTRA_FILM_ID, -1)
        loadMovieData(index)
    }

    private fun initUI() {
        bindings = ActivityFilmDataBinding.inflate(layoutInflater)
        with(bindings) {
            setContentView(root)
        }
    }

    private fun loadMovieData(index: Int) {
        if (index != -1) {
            val film = FilmDataSource.films[index]

            when {
                film.image != null -> {
                    bindings.imgCover.setImageBitmap(film.image)
                }

                film.imageResId != 0 -> {
                    bindings.imgCover.setImageResource(film.imageResId)
                }

                film.imageUrl != null -> {
                    TaskRunner.doInBackground(
                        bindings.imgCover,
                        film.imageUrl,
                        object : OnTaskCompleted {
                            override fun onTaskCompleted(result: Bitmap?) {
                                film.image = result
                            }
                        })
                }
            }

            bindings.movieTitle.text = film.title
            bindings.tvDirector.text = film.director
            bindings.tvYear.text = film.year.toString()
            //bindings.tvFormat.text   = "${resources.getStringArray(R.array.formats)[film.format]}, ${resources.getStringArray(R.array.genres)[film.genre]}"
            bindings.tvComments.text = film.comments
        }
    }

    private fun config() {
        //CONDIGURACIÓN
        actionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Filmoteca"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#590DE3")))
        //Para cambiar el color de la barra de estado
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#3204AC")
    }
}