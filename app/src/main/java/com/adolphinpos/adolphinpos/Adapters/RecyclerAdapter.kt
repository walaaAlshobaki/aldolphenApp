package com.adolphinpos.adolphinpos.Adapters
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.registeration.country.CountryModel

import java.util.*
import kotlin.math.roundToInt


class RecyclerAdapter(
        private val context: Context,
        private val data: List<*>,

) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val VIEW_TYPE_ONE = 1
    val VIEW_TYPE_TWO = 2
    val VIEW_TYPE_THREE = 3
    val VIEW_TYPE_FOUR = 4
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return if (viewType == VIEW_TYPE_ONE) {
            return ScheduleViewHolder(
                     LayoutInflater.from(context).inflate(R.layout.cell_country_item, parent, false))
//            )
//        }else  if (viewType == VIEW_TYPE_TWO) {
//            AssignmentsViewHolder(
//                    LayoutInflater.from(context).inflate(R.layout.assignments_item, parent, false)
//            )
//        }else  if (viewType == VIEW_TYPE_THREE) {
//            BottomViewHolder(
//                    LayoutInflater.from(context).inflate(R.layout.bottom_sheet_item, parent, false)
//            )
//        }else{
//            TeacherHomeworkViewHolder(
//                    LayoutInflater.from(context).inflate(R.layout.cell_teacher_homework, parent, false)
//            )
//        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            (holder as ScheduleViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
//    override fun getItemViewType(position: Int): Int {
//
//        return if (action=="SchoolScheduleFragment"){
//            VIEW_TYPE_ONE
//        }else if (action=="AssignmentsFragment"){
//            VIEW_TYPE_TWO
//        }else if (action== "quiz"||action=="lesson"||action=="homework" ){
//            VIEW_TYPE_THREE
//        }else{
//            VIEW_TYPE_FOUR
//        }
//
//
//    }




    @RequiresApi(Build.VERSION_CODES.M)
    fun getGradient(color: Int): GradientDrawable {
        var color: Int =color
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= 0.8f // value component
        color = Color.HSVToColor(hsv)
        hsv[2] *= 0.1f
        val color2= Color.HSVToColor(hsv)
        val alpha = 220
        val alpha2 = 200
        val res = color and 0x00ffffff or (alpha2 shl 24)
        val res2 = color2 and 0x00ffffff or (alpha shl 24)
        val gd = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(res2, res)
        )
        gd.cornerRadius = 0f

        return gd
    }
    private inner class ScheduleViewHolder   constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.titlec)




        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(position: Int) {
            when {
                data[position] is CountryModel.Data -> {

                    val itemCat = data[position] as CountryModel.Data

                }


            }

        }
    }

    fun manipulateColor(color: Int, factor: Float): Int {
        val a = Color.alpha(color)
        val r = (Color.red(color) * factor).roundToInt()
        val g = (Color.green(color) * factor).roundToInt()
        val b = (Color.blue(color) * factor).roundToInt()
        return Color.argb(
                a,
                r.coerceAtMost(255),
                g.coerceAtMost(255),
                b.coerceAtMost(255)
        )
    }


//    private inner class BottomViewHolder   constructor(itemView: View) :
//            RecyclerView.ViewHolder(itemView) {
//
//
//        var show: ImageView = itemView.findViewById(R.id.show)
//        var cover: ImageView = itemView.findViewById(R.id.cover)
//        var title: TextView = itemView.findViewById(R.id.title)
//        var avg: TextView = itemView.findViewById(R.id.avg)
//
//
//
//        @SuppressLint("SetTextI18n")
//        @RequiresApi(Build.VERSION_CODES.M)
//        fun bind(position: Int) {
//            when {
//                data[position] is SchoolScheduleModel.Data.Homework -> {
//                    val itemCat = data[position] as SchoolScheduleModel.Data.Homework
//                    title.text=itemCat.title
//                    avg.text=itemCat.avg.toString()+" %"
//
//
//                    Picasso.get().load(itemCat.thumbnail).placeholder(R.drawable.bgcell).into(cover)
//
//
//
//                    show.setOnClickListener {
//                        val lessonsModel : LessonModel? = itemCat.path?.let { it1 -> LessonModel(path = it1) }
//                        val i = Intent(context, viewer::class.java)
//                        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                        i.putExtra(common.CODE_EXTRA_VIEWR, lessonsModel)
//                        context.startActivity(i)
//                    }
//
//
//                }
//
//                data[position] is SchoolScheduleModel.Data.Lesson -> {
//                    val itemCat = data[position] as SchoolScheduleModel.Data.Lesson
//                    title.text=itemCat.title
//                    avg.text=itemCat.avg.toString()+" %"
//
//
//                    Picasso.get().load(itemCat.thumbnail).placeholder(R.drawable.bgcell).into(cover)
//
//
//
//                    show.setOnClickListener {
//                        val lessonsModel : LessonModel? = LessonModel(path = itemCat.path)
//                        val i = Intent(context, viewer::class.java)
//                        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                        i.putExtra(common.CODE_EXTRA_VIEWR, lessonsModel)
//                        context.startActivity(i)
//                    }
//
//
//                }
//                data[position] is SchoolScheduleModel.Data.Quiz -> {
//                    val itemCat = data[position] as SchoolScheduleModel.Data.Quiz
//                    title.text=itemCat.title
//                    avg.text=itemCat.avg.toString()+" %"
//
//
//                    Picasso.get().load(itemCat.thumbnail).placeholder(R.drawable.bgcell).into(cover)
//
//
//
//                    show.setOnClickListener {
//                        val lessonsModel : LessonModel? = itemCat.path?.let { it1 -> LessonModel(path = it1) }
//                        val i = Intent(context, viewer::class.java)
//                        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                        i.putExtra(common.CODE_EXTRA_VIEWR, lessonsModel)
//                        context.startActivity(i)
//                    }
//
//
//                }
//
//
//
//            }
//
//        }
//    }
//
//
//    private inner class AssignmentsViewHolder   constructor(itemView: View) :
//            RecyclerView.ViewHolder(itemView) {
//
//        var material: TextView = itemView.findViewById(R.id.material)
//        var homework: TextView = itemView.findViewById(R.id.homework)
//        var Quiz: TextView = itemView.findViewById(R.id.Quiz)
//        var homeworkText: TextView = itemView.findViewById(R.id.homeworkText)
//        var QuizText: TextView = itemView.findViewById(R.id.QuizText)
//        var image_gradient: RelativeLayout = itemView.findViewById(R.id.image_gradient)
//        var avatar_img: ImageView = itemView.findViewById(R.id.avatar_img)
//        var container: ConstraintLayout = itemView.findViewById(R.id.container)
//
//        var vc: TextView = itemView.findViewById(R.id.vc)
//        var vcText: TextView = itemView.findViewById(R.id.vcText)
//
//
//        @SuppressLint("SetTextI18n")
//        @RequiresApi(Build.VERSION_CODES.M)
//        fun bind(position: Int) {
//            when {
//                data[position] is AssignmentsModel.Data -> {
//                    val itemCat = data[position] as AssignmentsModel.Data
//                    val color: Int =getColors()
//                    material.text=itemCat.nameCategory
//                    image_gradient.setBackgroundColor(getColors())
//
//                    if (itemCat.vcWithoutDateCount>0){
//                        vc.text= itemCat.vcWithoutDateCount.toString()
//                    }else{
//                        vc.text= context.getString(R.string.not_found)
//                    }
//                    vcText.setTextColor(color)
//                    vc.setTextColor(color)
//                    Picasso.get().load(itemCat.avatar)       .error(R.drawable.profile)
//                            .transform(Utilites.instance.CircleTransform())
//                            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(avatar_img)
//                    Quiz.setTextColor(color)
//                    if (itemCat.quizWithoutDateCount>0){
//                        Quiz.text= itemCat.quizWithoutDateCount.toString()
//                    }else{
//                        Quiz.text= context.getString(R.string.not_found)
//                    }
//
//
//                    homework.setTextColor(color)
//                    QuizText.setTextColor(color)
//                    homeworkText.setTextColor(color)
//
//
//
//                    if (itemCat.homeworksWithoutDateCount>0){
//                        homework.text= itemCat.homeworksWithoutDateCount.toString()
//                    }else{
//                        homework.text= context.getString(R.string.not_found)
//                    }
//                    container.setOnClickListener {
//                        onClick!!.onSelectItemCategory(position)
////                        val intent = Intent(
////                            context,
////                            ShowImages::class.java
////                        )
////                        intent.putExtra("image", image)
////                        context.startActivity(intent)
//                    }
//                }
//
//
//            }
//
//        }
//    }
//    private inner class TeacherHomeworkViewHolder   constructor(itemView: View) :
//            RecyclerView.ViewHolder(itemView) {
//        //        var edit: LinearLayout = itemView.findViewById(R.id.edit)
////        var delete: LinearLayout = itemView.findViewById(R.id.delete)
////        var btnAssign: LinearLayout = itemView.findViewById(R.id.btnAssign)
////        var BrowseAttachments: LinearLayout = itemView.findViewById(R.id.BrowseAttachments)
////        var BrowseAssigns: LinearLayout = itemView.findViewById(R.id.BrowseAssigns)
////        var media: LinearLayout = itemView.findViewById(R.id.media)
//        var material: TextView = itemView.findViewById(R.id.material)
//        var homeworkDate: TextView = itemView.findViewById(R.id.homeworkDate)
//        var homeworkCatagory: TextView = itemView.findViewById(R.id.homeworkCatagory)
//        //        var editText: TextView = itemView.findViewById(R.id.editText)
////        var editImage: ImageView = itemView.findViewById(R.id.editImage)
////        var deleteText: TextView = itemView.findViewById(R.id.deleteText)
////        var deleteImage: ImageView = itemView.findViewById(R.id.deleteImage)
////        var assignText: TextView = itemView.findViewById(R.id.assignText)
////        var assignImage: ImageView = itemView.findViewById(R.id.assignImage)
////        var BrowseAttachmentsImage: ImageView = itemView.findViewById(R.id.BrowseAttachmentsImage)
////        var BrowseAttachmentsText: TextView = itemView.findViewById(R.id.BrowseAttachmentsText)
////        var BrowseAssignsImage: ImageView = itemView.findViewById(R.id.BrowseAssignsImage)
////        var BrowseAssignsText: TextView = itemView.findViewById(R.id.BrowseAssignsText)
////        var mediaImage: ImageView = itemView.findViewById(R.id.mediaImage)
//        var showOption: ImageView = itemView.findViewById(R.id.showOption)
////        var mediaText: TextView = itemView.findViewById(R.id.mediaText)
//
//        var avatar_img: ImageView = itemView.findViewById(R.id.cover)
//
//
//
//
//        @SuppressLint("SetTextI18n")
//        @RequiresApi(Build.VERSION_CODES.M)
//        fun bind(position: Int) {
//            when {
//                data[position] is TeacherResModel.Data -> {
//                    val itemCat = data[position] as TeacherResModel.Data
//                    val color: Int =getColors()
//                    if (common.langUI == "ar") {
//
//                        homeworkCatagory.text=itemCat.ctitleAr
//
//                    } else {
//
//                        homeworkCatagory.text=itemCat.ctitleEn
//
//                    }
//
//                    homeworkDate.text=itemCat.createdAt
//                    material.text=itemCat.title
//                    Picasso.get().load(itemCat.thumbnail)
//                            .error(R.drawable.arabic)
//
//                            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
//                            .into(avatar_img)
//
//
//                    showOption.setOnClickListener {
//                        onClick!!.onSelectshowOption(position) }
//                }
//
//
//            }
//
//        }
//    }
    private var onClick: OnItemselectedDelegate? = null

    //make interface like this
    interface OnItemselectedDelegate {


        fun onSelectItemCategory(position: Int)
        fun onSelectLesson(position: Int)
        fun onSelectQuiz(position: Int)
        fun onSelectHomework(position: Int)
        fun onSelectvc(position: Int)
        //        fun onSelectEdit(position: Int)
//        fun onSelectDelete(position: Int)
//        fun onSelectAssign(position: Int)
//        fun onSelectBrowseAttachments(position: Int)
//        fun onSelectBrowseAssigns(position: Int)
//        fun onSelectmedia(position: Int)
        fun onSelectshowOption(position: Int)


    }

    fun setOnClickItemCategory(onClick: OnItemselectedDelegate) {
        this.onClick = onClick
    }


    fun getColors(): Int {
        val rnd = Random()
        var color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= 0.8f // value component
        color = Color.HSVToColor(hsv)
        return color
    }


}