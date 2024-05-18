package com.example.myapplication2.ui.otchet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.databinding.FragmentOtchetBinding

class OtchetFragment : Fragment() {

    private var _binding: FragmentOtchetBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(OtchetViewModel::class.java)

        _binding = FragmentOtchetBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textOtchet
        //notificationsViewModel.text.observe(viewLifecycleOwner) {
        //    textView.text = it
        //}
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}