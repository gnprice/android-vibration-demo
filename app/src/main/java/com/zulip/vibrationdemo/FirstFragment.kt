package com.zulip.vibrationdemo

import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zulip.vibrationdemo.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
//            val timings = longArrayOf(0, 50, 50, 50, 50, 150); // too fast; gets muddled, on Pixel 4
            val timings = longArrayOf(0, 100, 50, 250); // pretty good imitation of Zulip drumroll sound?
            requireContext().getSystemService(Vibrator::class.java)
                .vibrate(VibrationEffect.createWaveform(timings, -1))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}