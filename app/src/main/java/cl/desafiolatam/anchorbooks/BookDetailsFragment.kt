package cl.desafiolatam.anchorbooks

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.desafiolatam.anchorbooks.model.room.RoomDetail
import cl.desafiolatam.anchorbooks.viewmodel.MyViewModel
import com.squareup.picasso.Picasso

class BookDetailsFragment(var idProd: Int, context: Context) : Fragment() {

    private lateinit var vModel: MyViewModel
    private lateinit var title: TextView
    private lateinit var author: TextView
    private lateinit var imageLink: ImageView
    private lateinit var price: TextView
    private lateinit var lastPrice: TextView
    private lateinit var country: TextView
    private lateinit var year: TextView
    private lateinit var detail: RoomDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = ViewModelProvider(this).get(MyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.details_layout, container, false)
        title = view.findViewById(R.id.title_book)
        price = view.findViewById(R.id.price_detail)
        imageLink = view.findViewById(R.id.image_book)
        author = view.findViewById(R.id.author_book)
        country = view.findViewById(R.id.country_detail)
        lastPrice = view.findViewById(R.id.lastprice_detail)
        year = view.findViewById(R.id.year_detail)
        vModel.getDetails(idProd)


        vModel.detail.observe(context as SecondActivity, Observer {
            if (it != null && !it.title.isEmpty()) {
                title.text = it.title
                price.text = it.price.toString()
                Picasso.get().load(it.imageLink).into(imageLink)
                title.text = it.title
                lastPrice.text = it.lastPrice.toString()
                price.text = it.price.toString()
                detail = it
            }
        })

        return view
    }

    //Envia Email
    fun sendEmail() {
        var inten: Intent = Intent(Intent.ACTION_SEND).apply {
            setData(Uri.parse("mailto:"))
            setType("text/plain")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("info@plaplix.cl")) // recipients
            putExtra(Intent.EXTRA_SUBJECT, "Consulta ${detail.title} id ${detail.id}")
            putExtra(
                Intent.EXTRA_TEXT, "Hola\n" +
                        "Vi el producto ${detail.title} y me gustaría que me contactaran a este correo o al\n" +
                        "siguiente número _________\n" +
                        "Quedo atento."
            )
        }

        startActivity(inten)
        (context as SecondActivity).finish()
    }
}