package org.codeforiraq.doctor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.listview_de_doctor.view.*

class Doctor_adapter (context:Context , var playlist:MutableList<DoctorData>)
    :ArrayAdapter<DoctorData> (context,0,playlist){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view =LayoutInflater.from(context).inflate(R.layout.listview_de_doctor,parent,false)
        var database : DoctorData? = playlist[position]

        var doctor_name = database!!.doctor_name
        var doctor_addres = database.doctor_addres
        var doctor_phone = database.doctor_phonenum

        view.doctor_name_listview.text = "الاسم: $doctor_name"
        view.doctor_addres_listview.text= "العنوان: $doctor_addres"
        view.doctor_phone_listview.text = "رقم الهاتف: $doctor_phone"


        return view
    }
}