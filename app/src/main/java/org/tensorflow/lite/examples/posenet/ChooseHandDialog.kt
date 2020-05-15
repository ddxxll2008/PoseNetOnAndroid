package org.tensorflow.lite.examples.posenet

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_left_or_right.*
import kotlinx.android.synthetic.main.dialog_left_or_right.view.*

class ChooseHandDialog : DialogFragment() {
    // Use this instance of the interface to deliver action events
    internal lateinit var listener: ChooseHandListener

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    interface ChooseHandListener {
        fun onDialogPositiveClick(dialog: DialogFragment, isLeftHand: Boolean)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            // Verify that the host activity implements the callback interface
            try {
                // Instantiate the NoticeDialogListener so we can send events to the host
                listener = activity as ChooseHandListener
            } catch (e: ClassCastException) {
                // The activity doesn't implement the interface, throw exception
                throw ClassCastException((context.toString() +
                        " must implement ChooseHandListener"))
            }

            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            val view = inflater.inflate(R.layout.dialog_left_or_right, null)

            var isLeftHand = false
            if (view.rb_left!!.isChecked) {
                isLeftHand = true
            }

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.start,
                    DialogInterface.OnClickListener { _, _ ->
                        listener.onDialogPositiveClick(this, isLeftHand)
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { _, _ ->
                        parentFragment!!.activity?.finish()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}