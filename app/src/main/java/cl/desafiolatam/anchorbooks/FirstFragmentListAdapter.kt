package cl.desafiolatam.anchorbooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.anchorbooks.model.room.RoomBook
import com.squareup.picasso.Picasso

class FirstFragmentListAdapter (var list: MutableList<RoomBook>, var onProdSelListener:OnBookSelectListener):
    RecyclerView.Adapter<FirstFragmentListAdapter.Holder>(),
    View.OnClickListener {

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var title: TextView
        lateinit var author: TextView
        lateinit var image: ImageView
    }

    fun update(list: MutableList<RoomBook>){
        this.list.clear()
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.book_list, parent, false)
        var holder:Holder = Holder(view)
        holder.title = view.findViewById(R.id.title_book)
        holder.author = view.findViewById(R.id.author_book)
        holder.image = view.findViewById(R.id.image_book)
        view.setOnClickListener(this)

        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.tag = list[position].id
        holder.title.text = list[position].title
        holder.author.text = list[position].author
        Picasso.get().load(list[position].imageLink).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onClick(v: View?) {
        onProdSelListener.onBooktSelected(v!!.tag as Int)
    }

    interface OnBookSelectListener{
        fun onBooktSelected(id:Int)
    }
}