package com.example.guldana.myhotel

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guldana.myhotel.Model.News
import com.google.firebase.database.*


class NewsFragment : Fragment() {

    lateinit var ref: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news, container, false)


        val newsList = ArrayList<News>()
        ref = FirebaseDatabase.getInstance().getReference("newses")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for(n in p0.children){
                        val news = n.getValue(News::class.java)
                        newsList.add(news!!)
                    }
                }
                val adapter = ListAdapter(newsList)
                val recyclerView = view.findViewById(R.id.recycler) as RecyclerView
                recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = adapter
            }
        })
        return view
    }
}
