package com.example.guldana.myhotel

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guldana.myhotel.Model.HotelRoom
import com.google.firebase.database.*


class RoomsFragment : Fragment() {

    lateinit var ref: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rooms, container, false)

        val roomsList = ArrayList<HotelRoom>()
        ref = FirebaseDatabase.getInstance().getReference("rooms")
        ref.addValueEventListener(object: ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for(n in p0.children){
                        val hotelRoom = n.getValue(HotelRoom::class.java)
                        roomsList.add(hotelRoom!!)
                    }
                }
                val adapter = RoomsListAdapter(roomsList)
                val recyclerView = view.findViewById(R.id.recycler) as RecyclerView
                recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = adapter

            }
        })
        return view
    }

}


/*
https://www.booking.com/hotel/kz/kazakhstan.ru.html?aid=315714;label=kazakhstan-68vvwH87HfqCaf8rlr3s_wS80821646766%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap1t1%3Aneg%3Afi%3Atiaud-261710242742%3Akwd-30784880492%3Alp9063099%3Ali%3Adec%3Adm;sid=567e571798fd49d426781a64e180a4a0;all_sr_blocks=27820202_89743944_0_33_0;checkin=2018-12-18;checkout=2018-12-19;dest_id=-2334069;dest_type=city;dist=0;group_adults=2;hapos=1;highlighted_blocks=27820202_89743944_0_33_0;hpos=1;room1=A%2CA;sb_price_type=total;srepoch=1543073024;srfid=fe27a1368587196055fa30f7e8ed5181b225c4bfX1;srpvid=f6646c3fafe9000c;type=total;ucfs=1&#hotelTmpl
http://www.kazakhstanhotel.kz/rooms
 */