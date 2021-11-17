package com.zulip.vibrationdemo

import android.media.AudioAttributes
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

//        bindVibrate(binding.buttonVibrateB, longArrayOf(0, 25, 50, 250)) // 25ms gets cut off, feels like a stutter
//        bindVibrate(binding.buttonVibrateC, longArrayOf(0, 50, 75, 250)) // 50ms still feels pretty light
        bindVibrate(binding.buttonVibrateA, longArrayOf(0, 125, 75, 250)) // best?
        bindVibrate(binding.buttonVibrateB, longArrayOf(0, 100, 75, 200)) // or better? bit faster
        bindVibrate(binding.buttonVibrateC, longArrayOf(0, 75, 75, 200))
        bindVibrate(binding.buttonVibrateD, longArrayOf(0, 100, 75, 250))
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
        // This (and starting with a rest) didn't make the effect survive locking the screen either.
//                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build())
    }
}
