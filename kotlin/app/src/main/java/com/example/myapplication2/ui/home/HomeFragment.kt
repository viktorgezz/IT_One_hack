package com.example.myapplication2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.R
import com.example.myapplication2.data.Expence
import com.example.myapplication2.data.NetworkService
import com.example.myapplication2.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnChampionship.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_championshipFragment)
        }

        homeViewModel.amount.observe(viewLifecycleOwner) { amount ->
            binding.tvAmount.text = amount
        }




        binding.btnAddExpence.setOnClickListener {
            val expenseName = binding.nameExpence.text.toString()
            val expenseAmount = binding.amountExpence.text.toString().toFloat()
            val expenseCategory = binding.categoryExpence.selectedItem.toString()
            // Выводим данные в консоль
            Log.d("HomeFragment", "Название: $expenseName, Сумма: $expenseAmount, Категория: $expenseCategory")

            val accountId = 1

            homeViewModel.addExpense(accountId, expenseName, expenseAmount, expenseCategory).also {
                homeViewModel.refreshAmount()
            }

            binding.nameExpence.text.clear()
            binding.amountExpence.text.clear()


            binding.categoryExpence.setSelection(0)
        }

        binding.btnAddIncome.setOnClickListener{
            val incomeName = binding.nameIncome.text.toString()
            val incomeProfit = binding.amountIncome.text.toString().toFloat()

            val accountId = 1

            homeViewModel.addIncome(accountId, incomeName, incomeProfit)


            binding.nameIncome.text.clear()
            binding.amountIncome.text.clear()
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}