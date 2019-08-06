package org.codeforiraq.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_show_for_doctor.*

class Show_for_doctor : AppCompatActivity() {


    lateinit var doctor_listview: ListView
    lateinit var doctorList :MutableList<DoctorData>
    var myAuth = FirebaseAuth.getInstance()

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_for_doctor)



        doctor_listview =findViewById(R.id.doctor_listview)
        doctorList = mutableListOf()

        ref = FirebaseDatabase.getInstance().getReference("Users").child("Doctor")


        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                doctorList.clear()
                if (p0.exists()){

                    for (e in p0.children) {

                        var data = e.getValue(DoctorData::class.java)

                        doctorList.add(data!!)

                        }
                    }

                var adapter = Doctor_adapter(this@Show_for_doctor,doctorList)
                doctor_listview.adapter =adapter
            }


        })



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.signup -> {
                myAuth.signOut()
                Toast.makeText(this, "جار تسجيل الخروج ...", Toast.LENGTH_LONG).show()

                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
                return true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
