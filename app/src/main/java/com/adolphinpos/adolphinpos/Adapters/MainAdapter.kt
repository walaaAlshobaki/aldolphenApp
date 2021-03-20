package com.adolphinpos.adolphinpos.Adapters

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel

class MainAdapter(
    private val context: Context,
    private val data: List<*>,
    val action: String
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val VIEW_TYPE_ONE = 1
    val VIEW_TYPE_TWO = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ONE) {
            AuthorizedEmployeesViewHolder(
                LayoutInflater.from(context).inflate(R.layout.authorized_employees_item_cell, parent, false)
            )
        } else {
            EmployeesPermissionsViewHolder(
                LayoutInflater.from(context).inflate(R.layout.emp_permissions_item_cell, parent, false)
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (action=="AuthorizedEmployeesViewHolder"){
            (holder as AuthorizedEmployeesViewHolder).bind(position)

        }else if (action== "EmployeesPermissionsViewHolder"){
            (holder as EmployeesPermissionsViewHolder).bind(position)
        }
    }



    override fun getItemCount(): Int {
        return data.size
    }
    override fun getItemViewType(position: Int): Int {

        return if (action == "AuthorizedEmployeesViewHolder") {
            VIEW_TYPE_ONE
        } else  {
            VIEW_TYPE_TWO
        }
    }


    private inner class AuthorizedEmployeesViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var checkBox1: CheckBox = itemView.findViewById(R.id.checkBox1)
        var empName: TextView = itemView.findViewById(R.id.empName)
        var empEmail: TextView = itemView.findViewById(R.id.empEmail)
        var empRole: TextView = itemView.findViewById(R.id.empRole)
        var charName: TextView = itemView.findViewById(R.id.charName)
//        var showLesson: TextView = itemView.findViewById(R.id.showLesson)
//        var num: TextView = itemView.findViewById(R.id.num)
//        var personName: TextView = itemView.findViewById(R.id.personName)
//        var div1: RelativeLayout = itemView.findViewById(R.id.div1)
//        var div2: RelativeLayout = itemView.findViewById(R.id.div2)
//
//        var chat: ImageView = itemView.findViewById(R.id.chat)
//        var book: ImageView = itemView.findViewById(R.id.book)
//        var personImage: ImageView = itemView.findViewById(R.id.personImage)
//        var image_gradient: ImageView = itemView.findViewById(R.id.image_gradient)
//        var background: ImageView = itemView.findViewById(R.id.background)



        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data[position] is UserEmployeeModel.Data -> {
                    val itemCat = data[position] as UserEmployeeModel.Data
                    empName.text=itemCat.firstName+" "+itemCat.lastName
                    charName.text= itemCat.firstName!!.first().toString().toUpperCase()
//                    +itemCat.lastName!!.first().toString().toUpperCase()
                    empEmail.text=itemCat.email
                    empRole.setOnClickListener {
                        onClick!!.onSelectItemCategory(position)
                    }

                }


            }

        }
    }


    private var onClick: OnItemselectedDelegate? = null

    //make interface like this
    interface OnItemselectedDelegate {


        fun onSelectItemCategory(position: Int)



    }

    fun setOnClickItemCategory(onClick: OnItemselectedDelegate) {
        this.onClick = onClick
    }



    private inner class EmployeesPermissionsViewHolder   constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

//        var material: TextView = itemView.findViewById(R.id.material)
//        var lesson: TextView = itemView.findViewById(R.id.lessondet)
//        var homework: TextView = itemView.findViewById(R.id.homework)
//        var exam: TextView = itemView.findViewById(R.id.exam)
//        var showLesson: TextView = itemView.findViewById(R.id.showLesson)
//        var num: TextView = itemView.findViewById(R.id.num)
//        var personName: TextView = itemView.findViewById(R.id.personName)
//        var div1: RelativeLayout = itemView.findViewById(R.id.div1)
//        var div2: RelativeLayout = itemView.findViewById(R.id.div2)
//
//        var chat: ImageView = itemView.findViewById(R.id.chat)
//        var book: ImageView = itemView.findViewById(R.id.book)
//        var personImage: ImageView = itemView.findViewById(R.id.personImage)
//        var image_gradient: ImageView = itemView.findViewById(R.id.image_gradient)
//        var background: ImageView = itemView.findViewById(R.id.background)



        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
//            when {
//                data[position] is SchoolScheduleModel.Data -> {
//                    homework.visibility= View.VISIBLE
//                    exam.visibility= View.VISIBLE
//                    showLesson.visibility= View.VISIBLE
//                    div1.visibility= View.GONE
//                    div2.visibility= View.GONE
//                    val itemCat = data[position] as SchoolScheduleModel.Data
//
//                    val color: Int =  getColors()
////                    color = if (itemCat.colorSubject.isNotEmpty() || itemCat.colorSubject == "" || itemCat.colorSubject == null){
////                        getColors()
////                    }else{
////                        Color.parseColor(itemCat.colorSubject)
////                    }
//                    material.text=itemCat.nameCategory
//                    if (itemCat.lessons.isEmpty()){
//
//                        lesson.text= context.resources.getString(R.string.nolesson)
//                    }else{
//                        val content = SpannableString( itemCat.lessons[0].title)
//                        content.setSpan(UnderlineSpan(), 0, content.length, 0)
//                        lesson.text=content
//                        lesson.setOnClickListener{
////                            onClick!!.onSelectLesson(position)
//                        }
//                    }
//                    if (itemCat.homeworks.isEmpty()){
//                        homework.visibility= View.GONE
//                        div2.visibility= View.GONE
//
//                    }else{
//                        if (itemCat.quiz.isNotEmpty()||itemCat.vc.isNotEmpty()){
//                            div2.visibility= View.VISIBLE
//                        }
//                        homework.setOnClickListener{
////                            onClick!!.onSelectHomework(position)
//                        }
//                    }
//                    if (itemCat.quiz.isEmpty()){
//                        exam.visibility= View.GONE
//                        div1.visibility= View.GONE
//
//                    }else{
//                        if (itemCat.vc.isNotEmpty()){
//                            div1.visibility= View.VISIBLE
//                        }
//                        exam.setOnClickListener{
////                            onClick!!.onSelectQuiz(position)
//                        }
//                    }
//                    if (itemCat.vc.isEmpty()){
//                        showLesson.visibility= View.GONE
//
//                    }else{
//
//
//                        showLesson.setOnClickListener{
////                            onClick!!.onSelectvc(position)
//                        }
//                    }
//
//                    personName.text=itemCat.fullname
//                    image_gradient.setImageDrawable(getGradient(color))
////                    if (itemCat.coverSubject != ""){
//                    Picasso.get().load(itemCat.coverSubject).placeholder(R.drawable.bgcell).into(background)
////                    }
//
//                    Picasso.get()
//                        .load(itemCat.avatar).placeholder(R.drawable.person).transform(
//                            CircleTransform()
//                        )
//                        .into(personImage)
//
//
//                    num.text=(position+1).toString()
//                    ImageViewCompat.setImageTintList(
//                        chat, ColorStateList.valueOf(
//                            manipulateColor(
//                                color,
//                                0.4f
//                            )
//                        )
//                    )
//                    chat.setOnClickListener {
//                        val i = Intent(context, ChatWebViewActivity::class.java)
//                        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
////                        val inboxUserModel= UserChatModel(user_id = itemCat.teacherId,fullname = itemCat.fullname)
////                        i.putExtra("mSenderPerson", inboxUserModel as Serializable)
//                        i.putExtra("user_id",  itemCat.teacherId.toString())
//
//                        context.startActivity(i) }
//                    ImageViewCompat.setImageTintList(
//                        book, ColorStateList.valueOf(
//                            manipulateColor(
//                                color,
//                                0.4f
//                            )
//                        )
//                    )
//                    book.setOnClickListener {
//                        if (itemCat.pathBook==null || itemCat.pathBook==""){
//                            Alert.Instance.showMessageError(context as Activity,context.getString(R.string.nobbook))
//                        }else{
//                            val lessonsModel : LessonModel? = itemCat.pathBook?.let { it1 -> LessonModel(path = it1) }
//                            val i = Intent(context, viewer::class.java)
//                            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                            i.putExtra(common.CODE_EXTRA_VIEWR, lessonsModel)
//                            context.startActivity(i)
//                        }
//
//
//                    }
//
//
//
//
//                    personName.setTextColor(manipulateColor(color, 0.4f))
//
//
//
//
//                }
//
//
//            }

        }
    }
}