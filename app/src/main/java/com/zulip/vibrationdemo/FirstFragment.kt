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

        val timings = listOf(
            // longArrayOf(0, 50, 50, 50, 50, 150); // too fast; gets muddled, on Pixel 4
//        longArrayOf(0, 25, 50, 250), // 25ms gets cut off, feels like a stutter
//        longArrayOf(0, 50, 75, 250), // 50ms still feels pretty light
//        longArrayOf(0, 100, 75, 200), // last proposal
//        longArrayOf(0, 100, 75, 425), // longer finish, the better to notice on leg
//            longArrayOf(0, 5, 100, 250), // 5ms about minimum to notice on leg at all
//            longArrayOf(0, 25, 50, 25, 100, 250), // on leg, can't really distinguish first two buzzes
//            longArrayOf(0, 15, 70, 15, 100, 250), // ditto
            longArrayOf(0, 25, 100, 25, 100, 400), // even here
            longArrayOf(0, 25, 125, 25, 125, 400),
            longArrayOf(0, 25, 150, 25, 150, 400),
            longArrayOf(0, 25, 100, 25, 100, 400),
        )

        bindVibrate(binding.buttonVibrateA, timings[0])
        bindVibrate(binding.buttonVibrateB, timings[1])
        bindVibrate(binding.buttonVibrateC, timings[2])
        bindVibrate(binding.buttonVibrateD, timings[3])
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
