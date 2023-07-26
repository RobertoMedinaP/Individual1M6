package com.example.individual1m6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.individual1m6.Model.Player
import com.example.individual1m6.databinding.PlayerListBinding

class PlayerAdapter(private val dataset: List<Player>): RecyclerView.Adapter<PlayerAdapter.AdapterViewHolder>() {

    class AdapterViewHolder(binding: PlayerListBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val binding= PlayerListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        TODO("Not yet implemented")
        //tomar elementos con una posicion 
    }
}