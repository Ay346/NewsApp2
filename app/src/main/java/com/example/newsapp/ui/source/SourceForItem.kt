package com.example.newsapp.ui.source

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import dagger.hilt.android.AndroidEntryPoint


class SourceForItem : Fragment() {
    lateinit var id: TextView
//    val args: SourceForItemArgs by navArgs()
    companion object {
        fun newInstance() = SourceForItem()
    }

    private lateinit var viewModel: SourceViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_source, container, false)
        id= root.findViewById(R.id.sourceId)
//        id.text="id= "+args.source.id
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SourceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}