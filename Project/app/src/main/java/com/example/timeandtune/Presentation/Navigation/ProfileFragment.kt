package com.example.timeandtune.Presentation.Navigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.timeandtune.Presentation.AddTask
import com.example.timeandtune.Presentation.ChangePassword
import com.example.timeandtune.R
import com.example.timeandtune.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {
    private var _binding: ProfileFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.changePasswordButton.setOnClickListener {
            val intent = Intent(requireContext(), ChangePassword::class.java)
            startActivity(intent)
        }

        return view
    }
}