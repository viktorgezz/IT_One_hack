package com.example.myapplication2.ui.championship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentChampionshipBinding
import com.example.myapplication2.ui.championship.ChampionshipViewModel

class ChampionshipFragment : Fragment() {

    private var _binding: FragmentChampionshipBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val championshipViewModel =
            ViewModelProvider(this).get(ChampionshipViewModel::class.java)

        _binding = FragmentChampionshipBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnBack.setOnClickListener{
            findNavController().navigate(R.id.championshipFragment_to_action_homeFragment)
        }

        championshipViewModel.economyPercent.observe(viewLifecycleOwner) { economyPercent->
            binding.tvDuty.text = "${economyPercent} %"
        }

        championshipViewModel.economy.observe(viewLifecycleOwner) { economy ->
            binding.tvAmount.text = "${economy} руб"
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}