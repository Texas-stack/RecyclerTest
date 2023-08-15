package com.example.recyclertest


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ItemsAdapter()

        val itemsRecyclerView = findViewById<RecyclerView>(R.id.items)
        itemsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        itemsRecyclerView.adapter = adapter

        val items = (1.. 10).map {id ->
            Item(
                id = "id_$id",
                color = Color.HSVToColor(
                    arrayOf(Random.nextFloat().times(368), 1.0f, 1.0f).toFloatArray()
                )
            )
        }

        adapter.setUpItems(items)
    }
}


data class Item(
    val id: String,
    val color: Int,
)

class ItemsAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private var items: List<Item> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.bind(items[position])

    }

    fun setUpItems(items: List<Item>) {
        this.items = items

        notifyDataSetChanged()
    }

}

class ItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.v_item, parent, false)
) {
        val image: View = itemView.findViewById(R.id.image)

    fun bind(item: Item) {
        image.setBackgroundColor(item.color)
    }


}