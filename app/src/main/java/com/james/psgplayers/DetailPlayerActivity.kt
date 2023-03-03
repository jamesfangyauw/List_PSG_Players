package com.james.psgplayers

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class DetailPlayerActivity : AppCompatActivity() {
    private lateinit var tvName : TextView
    private lateinit var tvPosition : TextView
    private lateinit var ivPhoto : ImageView
    private lateinit var tvDescription : TextView

    companion object{
        const val EXTRA_PLAYER = "extra_player"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)



        tvName = findViewById(R.id.tv_name)
        tvPosition = findViewById(R.id.tv_position)
        ivPhoto = findViewById(R.id.iv_photo)
        tvDescription = findViewById(R.id.tv_description)

        val playerDetail = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PLAYER, Player::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Player>(EXTRA_PLAYER)
        }

        val actionBar = supportActionBar
        actionBar!!.title= "${playerDetail?.name}"
        actionBar.setDisplayHomeAsUpEnabled(true)

        if(playerDetail!= null){
            tvName.text= playerDetail.name
            tvPosition.text = playerDetail.position
            ivPhoto.setImageResource(playerDetail.photo)
            tvDescription.text=playerDetail.description
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share_content -> {
                val intent= Intent()
                intent.action=Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, tvDescription.text)
                intent.type="text/plain"
                startActivity(Intent.createChooser(intent,"Share To:"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}