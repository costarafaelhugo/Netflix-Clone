package com.hugorafaelcosta.netflixclone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.hugorafaelcosta.netflixclone.Adapter.FilmesAdapter
import com.hugorafaelcosta.netflixclone.Model.addFilmes
import com.hugorafaelcosta.netflixclone.databinding.ActivityDetalhesFilmeBinding
import com.squareup.picasso.Picasso

class DetalhesFilme : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesFilmeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        val recycler_outros_filmes = binding.recyclerOutrosFilmes
        recycler_outros_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_outros_filmes.layoutManager = GridLayoutManager(applicationContext, 3)

        val capaTheWitcher = "https://firebasestorage.googleapis.com/v0/b/netflix-clone-76531.appspot.com/o/video.jpg?alt=media&token=45680ce9-ad2b-4cc8-a9a0-85212b122be0"
        Picasso.get().load(capaTheWitcher).fit().into(binding.capa)

        binding.playVideo.setOnClickListener {
            val intent = Intent(this, Video::class.java)
            startActivity(intent)
        }
    }

    private fun Toolbar() {
        val toolbarDetalhes = binding.toolbarDetalhes
        toolbarDetalhes.setNavigationIcon(getDrawable(R.drawable.ic_voltar))
        toolbarDetalhes.setNavigationOnClickListener {
            val intent = Intent(this, ListaFilmes::class.java)
            startActivity(intent)
            finish()
        }
    }
}