package com.onuroztop.superherobook

import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_row.view.*

class RecyclerViewAdapter(var heroList:ArrayList<String>,var heroImages:ArrayList<Bitmap>) : RecyclerView.Adapter<RecyclerViewAdapter.HeroViewHolder>() {

    //inner class
    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }

    //interface implementation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {

        //Inflater,LayoutInflater,XMLInflater

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row,parent,false)

        return HeroViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {

        holder.itemView.recyclerViewRowText.text = heroList[position]

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context,HeroInfoActivity::class.java)

            intent.putExtra("SelectedHeroName",heroList[position])

            val selectedHeroImage = heroImages[position]
            SelectedHeroSingleton.selectedHeroImage = selectedHeroImage

            holder.itemView.context.startActivity(intent)

        }



    }

    override fun getItemCount(): Int {

        return heroList.size

    }
}