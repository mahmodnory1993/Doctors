package org.codeforiraq.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {

    lateinit var email_doctor: EditText
    lateinit var password_doctor: EditText
    lateinit var sava_data_doctor: Button

    var myAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        email_doctor=findViewById(R.id.email_doctor)
        password_doctor=findViewById(R.id.password_doctor)
        sava_data_doctor=findViewById(R.id.save_data_doctor)


        sava_data_doctor.setOnClickListener {

            var email   =email_doctor.text.toString().trim()
            var password =password_doctor.text.toString()

            myAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {task ->


                if (task.isSuccessful){

                    Toast.makeText(this,"Done", Toast.LENGTH_LONG).show()


                    var intent = Intent(this,Data_of_User_and_Docotr::class.java)


                    startActivity(intent)
                }
            }
        }


    }
}

