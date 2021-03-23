package cl.desafiolatam.anchorbooks

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.anchorbooks.viewmodel.MyViewModel

class FirstFragment : Fragment(), FragmentListAdapter.OnBookSelectListener {

    private lateinit var vModel: MyViewModel
    private lateinit var recyView: RecyclerView
    private lateinit var adapter: FragmentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = ViewModelProvider(this).get(MyViewModel::class.java)
        adapter = FragmentListAdapter(mutableListOf(), this)
        vModel.productList.observe(this, { adapter.update(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.first_fragment, container, false)
        recyView = view.findViewById(R.id.recycler)
        recyView.layoutManager = LinearLayoutManager(context)
        recyView.adapter = adapter

        return view
    }

    override fun onBookSelected(id: Int) {
        val inten = Intent(context, SecondActivity::class.java)
        inten.putExtra("ID", id)
        startActivity(inten)
        (context as MainActivity).finish()
    }
}