package com.adolphinpos.adolphinpos.userOption

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.adolphinpos.adolphinpos.Adapters.ChildBottomSheetAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.helper.Alert
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.home.ChildModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tapadoo.alerter.Alerter

import kotlinx.android.synthetic.main.alert.view.*
import kotlinx.android.synthetic.main.fragment_user_option_dialog.*

// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    UserOptionDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class UserOptionDialogFragment : BottomSheetDialogFragment(), ChildBottomSheetAdapter.OnItemClickedDelegate {

    private var mListener: Listener? = null
    private var mAdpater: ChildBottomSheetAdapter? = null
    private var mListData: ArrayList<ChildModel> = ArrayList()
    private var mChildDelegateItemTapListener: ChildInterface? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mListData.clear()

        mListData.add(ChildModel(
            id = 1, fullname ="profile", avatar =R.drawable.ic_user,
            type ="item"))
        mListData.add(ChildModel(id = 2, fullname ="Logout", avatar =R.drawable.ic_logout, type ="item"))



        mAdpater = ChildBottomSheetAdapter(mListData, context!!)

        mAdpater!!.setOnClickChild(this)



        list.layoutManager = LinearLayoutManager(context)

        list.adapter = mAdpater

        list.isNestedScrollingEnabled = true

        mAdpater!!.notifyDataSetChanged()
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        val parent = parentFragment
//        if (parent != null) {
//            mListener = parent as Listener
//            mChildDelegateItemTapListener = parent as ChildInterface
//
//        } else {
//            mListener = context as Listener
//            mChildDelegateItemTapListener = context as ChildInterface
//
//        }
//    }
//
//
//    override fun onDetach() {
//        mListener = null
//        mChildDelegateItemTapListener = null
//        super.onDetach()
//    }


    companion object {

        // TODO: Customize parameters
        fun newInstance(itemCount: Int): UserOptionDialogFragment =
                UserOptionDialogFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ITEM_COUNT, itemCount)
                    }
                }

    }

    override fun onChildSelect(position: Int) {
        if (mListData[position].id==2){
            RxBus.publish(MessageEvent(7, mListData[position]))
            dismiss()

        }
        else if (mListData[position].id==1){
            RxBus.publish(MessageEvent(8, mListData[position]))
            dismiss()
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)



            dialog.setOnShowListener(DialogInterface.OnShowListener { dialogInterface ->
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                setupFullHeight(bottomSheetDialog)

            })
            return dialog

    }


    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from<FrameLayout?>(bottomSheet!!)
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight-450
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
    override fun onGraphSelect(index: Int) {
        TODO("Not yet implemented")
    }
    interface ChildInterface {
        fun didSelectChild(categoryModelSelected: ChildModel)
    }

    interface Listener {
        fun onItemChildClicked(position: Int)
    }

}