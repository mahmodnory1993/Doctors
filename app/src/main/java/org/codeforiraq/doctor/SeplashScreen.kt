package org.codeforiraq.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_seplash_screen.*

class SeplashScreen : AppCompatActivity() {

    var ref = FirebaseDatabase.getInstance().getReference("Users").child("Doctor")

    var myAuth =FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seplash_screen)




        var thread = Thread{


            Thread.sleep(4000)


            if (myAuth.currentUser != null) {
                ref.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }


                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0.exists()) {

                            for (e in p0.children) {

                                var data = e.getValue(DoctorData::class.java)

                                if (data!!.doctor_id == myAuth.currentUser!!.uid) {

                                    var intent = Intent(this@SeplashScreen, Show_for_doctor::class.java)
                                    startActivity(intent)
                                } else {
                                    var intent = Intent(this@SeplashScreen, Show_for_user::class.java)
                                    startActivity(intent)
                                }


                            }
                        }
                    }

                })
            }

            if (myAuth.currentUser == null){
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }


            finish()


        }
        thread.start()




    }

}
