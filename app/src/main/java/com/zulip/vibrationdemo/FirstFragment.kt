package com.zulip.vibrationdemo

import android.os.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        // longArrayOf(0, 50, 50, 50, 50, 150); // too fast; gets muddled, on Pixel 4

        bindVibrate(binding.buttonVibrateA, longArrayOf(0, 100, 50, 250))
        bindVibrate(binding.buttonVibrateB, longArrayOf(0, 125, 75, 250)) // best?
        bindVibrate(binding.buttonVibrateC, longArrayOf(0, 125, 75, 175))
        bindVibrate(binding.buttonVibrateD, longArrayOf(0, 125, 125, 250))
        bindVibrate(binding.buttonVibrateDefault, longArrayOf(0, 250, 250, 250)) // Android notif default
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindVibrate(button: Button, timings: LongArray) {
        button.setOnClickListener {
            vibrate(timings)
        }
    }


    private fun vibrate(timings: LongArray) {
        requireContext().getSystemService(Vibrator::class.java)
            .vibrate(VibrationEffect.createWaveform(timings, -1))
    }
}
