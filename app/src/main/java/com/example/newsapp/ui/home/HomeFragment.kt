package com.example.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.core.observeEventOnce
import com.example.newsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import com.sawaf.weatherappex.tools.toGone
import com.sawaf.weatherappex.tools.toVisible
import com.sawaf.weatherappex.tools.toast
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {
    val homeviewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val itemsList = ArrayList<NewsArticle>()
    private lateinit var customAdapter: NewsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = NewsAdapter(
            { article, _ ->
                findNavController().navigate(HomeFragmentDirections.openDetails(article))
            },
            homeviewModel::saveArtical
//            {
//         homeviewModel.saveArtical(it)
//        }
        )
//        val recyclerView: RecyclerView = binding.recyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

//        viewModel.articleList.observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//        }
        homeviewModel.articleList.observe(viewLifecycleOwner) {
            Timber.d("$it")
            adapter.submitList(it)

        }
        observeEventOnce(homeviewModel.isLoading) {
            binding.apply {
                if (it) {
                    loading.toVisible()
                    recyclerView.toGone()
                } else {
                    loading.toGone()
                    recyclerView.toVisible()
                }
            }
        }

        observeEventOnce(homeviewModel.errorMsg) {
            it?.also {
                toast(it)
            }
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


