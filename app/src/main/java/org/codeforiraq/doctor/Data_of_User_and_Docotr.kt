package org.codeforiraq.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_data__user__docotr.*

class Data_of_User_and_Docotr : AppCompatActivity() {

    lateinit var typeuser :Spinner
    lateinit var mohafada :Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data__user__docotr)

        typeuser =findViewById(R.id.typeuser)
        mohafada =findViewById(R.id.mohafada)



        var typearray = arrayOf("اختر نوع الحساب","مواطن","طبيب")
        var mohafadaarray = arrayOf("صلاح الدين")


        var adapter_type  = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,typearray)
        var adapter_mohafada  = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mohafadaarray)


        typeuser.adapter= adapter_type
        mohafada.adapter = adapter_mohafada



        typeuser.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                if (position==1){
                    user()

                    savaDatabtn.setOnClickListener {

                        addUser ()

                    }
                }

                if (position==2){

                    doctor()

                    savaDatabtn.setOnClickListener {
                        addDoctor()
                    }
                }

            }

        }


    }
    fun doctor() {
        name.isEnabled =true
        city.isEnabled =true
        savaDatabtn.isEnabled =true
        department_doctor.isEnabled =true
        addres_doctor.isEnabled =true
        phonenum_doctor.isEnabled =true
        signup_doctor.isEnabled =true
        image_doctor.isEnabled =true
        image_doctor_btn.isEnabled =true
        image_signup.isEnabled =true
        image_signup_doctor_btn.isEnabled =true

    }
    fun user(){

        //true enable
        name.isEnabled =true
        savaDatabtn.isEnabled=true
        image_doctor.isEnabled =true
        image_doctor_btn.isEnabled =true
        city.isEnabled =true

        //false enable
        department_doctor.isEnabled =false
        addres_doctor.isEnabled =false
        phonenum_doctor.isEnabled =false
        signup_doctor.isEnabled =false
        image_signup.isEnabled =false
        image_signup_doctor_btn.isEnabled =false
    }
    fun addDoctor(){


        var name1 =name.text.toString()
        var department_doctor1 =department_doctor.text.toString()
        var addres_doctor1 =addres_doctor.text.toString()
        var phonenum_doctor1 =phonenum_doctor.text.toString()
        var signup_doctor1 =signup_doctor.text.toString()
        var city1 =city.text.toString()


        var doctorDatabase=FirebaseDatabase.getInstance().getReference("Users")
        var doctorid =doctorDatabase.push().key
        var myAuth = FirebaseAuth.getInstance().currentUser!!.uid

        var doctordatainsert = DoctorData(myAuth,name1,department_doctor1,addres_doctor1,phonenum_doctor1,signup_doctor1,city1 ,
            "صلاح الدين",0,0,"طبيب")

        if (name1.isEmpty()
            and department_doctor1.isEmpty()
            and addres_doctor1.isEmpty()
            and phonenum_doctor1.isEmpty()
            and signup_doctor1.isEmpty()
            and city1.isEmpty()) {

            name.error = "ادخل الاسم"
            department_doctor.error = "ادخل الاختصاص"
            addres_doctor.error = "ادخل العنوان"
            phonenum_doctor.error = "ادخل رقم الهاتف"
            signup_doctor.error = "ادخل رقم تسجيل الاجازة"
            city.error = "ادخل رقم تسجيل الاجازة"

            return
        }


        doctorDatabase.child("Doctor").child(myAuth!!).setValue(doctordatainsert).addOnCompleteListener {

            Toast.makeText(this, "تم الحفظ", Toast.LENGTH_LONG).show()

            var intent = Intent(this,Show_for_doctor::class.java)
            startActivity(intent)

        }


    }
    fun addUser (){


        var name1 =name.text.toString()
        var city1 =city.text.toString()


        var userDatabase=FirebaseDatabase.getInstance().getReference("Users")
        var userid =userDatabase.push().key
        var myAuth = FirebaseAuth.getInstance().currentUser!!.uid
        var userdatainsert = UserData(myAuth,name1,0,city1,"صلاح الدين","مستخدم عادي")


        if (name1.isEmpty()&&city1.isEmpty()) {

            name.error = "ادخل الاسم"
            city.error = "ادخل المدينة"

            return
        }


        userDatabase.child("User").child(myAuth!!).setValue(userdatainsert).addOnCompleteListener {

            Toast.makeText(this, "تم الحفظ", Toast.LENGTH_LONG).show()

            var intent = Intent(this,Show_for_user::class.java)
            startActivity(intent)

        }

    }
}

