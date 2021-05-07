package com.hugorafaelcosta.netflixclone

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.hugorafaelcosta.netflixclone.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        binding.btCadastrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagem_erro = binding.mensagemErro

            if (email.isEmpty() || senha.isEmpty()) {
                mensagem_erro.setText("Preencha todos os campos!")
            } else {
                CadastrarUsuario()
            }
        }


    }

    private fun CadastrarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagem_erro = binding.mensagemErro
        
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener{
            if(it.isSuccessful){
                Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                binding.run {
                    editEmail.run { setText("") }
                    editSenha.run { setText("") }
                    mensagem_erro.text = ""
                }
            }
        }.addOnFailureListener{
            mensagem_erro.text = "Erro ao cadastrar usuário"
        }

    }

    private fun Toolbar() {
        val toolbar = binding.toolbarCadastro.also {
            it.setBackgroundColor(getColor(R.color.white))
        }
        toolbar.navigationIcon = getDrawable(R.drawable.ic_netflix_official_logo)
    }
}