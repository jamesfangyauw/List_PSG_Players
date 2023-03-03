package com.james.psgplayers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.james.psgplayers.databinding.ActivityMainBinding
import java.util.Objects

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private val listItem = ArrayList<Player>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.rvItem.setHasFixedSize(true)
        listItem.addAll(getListPlayer())
        showRecyclerList()
    }

    private fun getListPlayer(): ArrayList<Player> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPosition = resources.getStringArray(R.array.data_position)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDescription = resources.getStringArray(R.array.data_description)

        val listPlayer = ArrayList<Player>()
        for (i in dataName.indices) {
            val player = Player(
                dataName[i],
                dataPosition[i],
                dataPhoto.getResourceId(i, -1),
                dataDescription[i]
            )
            listPlayer.add(player)
        }
        return listPlayer
    }

    private fun showRecyclerList() {
        activityMainBinding.rvItem.layoutManager = LinearLayoutManager(this)
        val listPlayerAdapter = ListPlayerAdapter(listItem)
        activityMainBinding.rvItem.adapter = listPlayerAdapter
        listPlayerAdapter.setOnItemClickCallback(object : ListPlayerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Player) {
                val intentToDetail = Intent(this@MainActivity, DetailPlayerActivity::class.java)
                intentToDetail.putExtra(DetailPlayerActivity.EXTRA_PLAYER, data)
                startActivity(intentToDetail)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}