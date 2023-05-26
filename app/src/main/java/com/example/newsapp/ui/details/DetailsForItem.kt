package com.example.newsapp.ui.details

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.databinding.FragmentDetailsBinding
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.models.ArticalUi
import com.example.newsapp.ui.home.HomeFragment
import com.example.newsapp.ui.home.HomeFragmentDirections
import com.sawaf.weatherappex.tools.loadImage
import com.sawaf.weatherappex.tools.toInvisible
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailsForItem : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var image: ImageView
    lateinit var name: TextView
    lateinit var title: TextView
    lateinit var date: TextView
    lateinit var author: TextView
    lateinit var content: TextView

    var clickbtn = false
    val args: DetailsForItemArgs by navArgs()

    companion object {
        fun newInstance() = DetailsForItem()
    }

    private lateinit var viewModel: DetailsViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
//        val root = inflater.inflate(R.layout.fragment_details, container, false)
//        image = root.findViewById(R.id.newsImage)
////        image.setImageResource(args.news.url.toInt())
//        title = root.findViewById(R.id.newsTitle)
//        title.text = "newsTitle:  " + args.news.title
//        date = root.findViewById(R.id.newsPublishedAt)
//        date.text = args.news.publishedAt
//        author = root.findViewById(R.id.newsAuthor)
//        author.text = "newsAuthor:  " + args.news.author
//        content = root.findViewById(R.id.content)
//        content.text = "newsDescrption:  " + args.news.content
//        savebtn = root.findViewById(R.id.save_btn)

       binding.saveBtn.setOnClickListener {
            it.setBackgroundColor(Color.BLUE)
            viewModel.saveArtical(
                ArticalUi(
                    "aaa",
                    title.toString(),
                    image.toString(),
                    date.toString(),
                    content.toString(),
                    true
                )
            )
        }




    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.news.also {
            if (it!!.urlToImage == null) {
                binding.root.toInvisible()
            } else {
                val dateString = it.publishedAt
                val formatter =
                    DateTimeFormatter.ISO_DATE_TIME // Define the formatter for the input string
                val dateTime = LocalDateTime.parse(dateString, formatter) // Parse the input string
                val date1 = dateTime.toLocalDate() // Extract the date component
                val time = dateTime.toLocalTime() // Extract the time component

                binding.newsTitle.text = it.title
                binding.newsAuthor.text = it.author
                binding.newsPublishedAt.text = "$date1  $time"
                binding.content.text = it.content
                binding.newsImage.loadImage(
                    it.urlToImage.toString(),
                    context = requireContext().applicationContext
                )
            }

        }
    }
}