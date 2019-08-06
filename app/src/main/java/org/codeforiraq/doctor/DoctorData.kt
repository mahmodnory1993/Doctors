package org.codeforiraq.doctor

class DoctorData(var doctor_id:String,var doctor_name:String,var doctor_department:String,var doctor_addres:String,var doctor_phonenum:String ,
                 var doctor_signup_num:String,var city:String ,var mohafada:String,var image_signup_doctor:Int,var image_doctor:Int,var typeofuser:String){

    constructor() : this("","","","","","","",""
        ,0,0,""){

    }
}