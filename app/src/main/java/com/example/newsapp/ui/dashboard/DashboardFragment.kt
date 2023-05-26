package com.example.newsapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.adapter.SourceAdapter
import com.example.newsapp.core.observeEventOnce
import com.example.newsapp.data.model.Source
import com.example.newsapp.databinding.FragmentDashboardBinding
import com.example.newsapp.ui.home.HomeFragmentDirections
import com.sawaf.weatherappex.tools.toGone
import com.sawaf.weatherappex.tools.toVisible
import com.sawaf.weatherappex.tools.toast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    val dashboardViewModel: DashboardViewModel by viewModels()
    private val itemsList = ArrayList<Source>()
    private lateinit var sourceAdapter: SourceAdapter
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = NewsAdapter(
            { article, _ ->
//                val nav=findNavController()
//                val action=DashboardFragmentDirections.
                findNavController().navigate(DashboardFragmentDirections.openSource(article))
            },
            dashboardViewModel::removeArtical
//            {
//         homeviewModel.saveArtical(it)
//        }
        )
//        val recyclerView: RecyclerView = binding.recyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter


        dashboardViewModel.Result.observe(viewLifecycleOwner) {
            Timber.d("$it")
            adapter.submitList(it)


        }

//        val recyclerView: RecyclerView = binding.recyclerView
//        val recyclerView: RecyclerView = binding.recyclerView
//        val layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.layoutManager = layoutManager
//        sourceAdapter = SourceAdapter(itemsList) { source ->
//            findNavController().navigate(DashboardFragmentDirections.openSource(source))
//        }
//        recyclerView.adapter = sourceAdapter
//        observeEventOnce(dashboardViewModel.isLoading) {
//            binding.apply {
//                if (it) {
//                    loading.toVisible()
//                    recyclerView.toGone()
//                } else {
//                    loading.toGone()
//                    recyclerView.toVisible()
//                }
//            }
//        }

        observeEventOnce(dashboardViewModel.errorMsg) {
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