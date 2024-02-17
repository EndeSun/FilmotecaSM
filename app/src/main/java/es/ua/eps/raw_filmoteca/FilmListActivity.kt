package es.ua.eps.raw_filmoteca

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import es.ua.eps.raw_filmoteca.data.FilmDataSource
import es.ua.eps.raw_filmoteca.data.FilmsArrayAdapter
import es.ua.eps.raw_filmoteca.databinding.ActivityFilmListBinding

//-------------------------------------
class FilmListActivity : BaseActivity()
    , AdapterView.OnItemClickListener {

    private lateinit var bindings : ActivityFilmListBinding
    private lateinit var filmAdapter: FilmsArrayAdapter

    //---------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        config()
        checkPermission(Manifest.permission.INTERNET, {
            filmAdapter.notifyDataSetChanged()
        })
    }

    //---------------------------------
    override fun onRestart() {
        super.onRestart()
        filmAdapter.notifyDataSetChanged()
    }

    //---------------------------------
    private fun initUI() {
        bindings = ActivityFilmListBinding.inflate(layoutInflater)
        with(bindings) {
            setContentView(root)
            filmAdapter = FilmsArrayAdapter(this@FilmListActivity, android.R.layout.simple_list_item_1, FilmDataSource.films)
            list.onItemClickListener = this@FilmListActivity
            list.adapter = filmAdapter
        }
    }

    //---------------------------------
    // AdapterView.OnItemClickListener (ListView)
    //---------------------------------
    override fun onItemClick(adapterView: AdapterView<*>?, view: View?, index: Int, l: Long) {
        val intent = Intent(this, FilmDataActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        intent.putExtra(FilmDataActivity.EXTRA_FILM_ID, index)
        startActivity(intent)
    }

    private fun config(){
        //CONDIGURACIÓN
        actionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "Filmoteca"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#590DE3")))
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#3204AC")
    }

    //CREACIÓN DEL MENÚ
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    //OPCIONES DE LOS MENÚ
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //R.id.addFilm -> addFilm() //Estas son funciones que hay que crear aparte.
            //R.id.closeSession -> closeSession()
            //R.id.disconnectAccount -> disconnectAccount()
            //R.id.about -> about()
        }
        return super.onOptionsItemSelected(item)
    }

}
