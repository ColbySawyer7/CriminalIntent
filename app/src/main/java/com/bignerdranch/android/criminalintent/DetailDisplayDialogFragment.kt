package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import java.io.File
import java.util.*

private const val ARG_PHOTO_FILE = "photoFile"
class DetailDisplayDialogFragment : DialogFragment() {

    private  lateinit var detailView: ImageView
    private lateinit var photoFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoFile = arguments?.getSerializable(ARG_PHOTO_FILE) as File
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_display, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailView = view.findViewById(R.id.detail_view) as ImageView

        detailView.setOnClickListener{
            dismiss()
        }
        updatePhotoView()
    }

    private fun updatePhotoView() {
        if (photoFile.exists()) {
            val bitmap = getScaledBitmap(photoFile.path, requireActivity())
            detailView.setImageBitmap(bitmap)
        } else {
            detailView.setImageDrawable(null)
        }
    }

    companion object {

        fun newInstance(photoFile: File): DetailDisplayDialogFragment {
            val args = Bundle().apply {
                putSerializable(ARG_PHOTO_FILE, photoFile)
            }
            return DetailDisplayDialogFragment().apply {
                arguments = args
            }
        }
    }

}