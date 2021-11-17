package com.zulip.vibrationdemo

import android.os.*
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

        binding.buttonVibrateA.setOnClickListener {
            // longArrayOf(0, 50, 50, 50, 50, 150); // too fast; gets muddled, on Pixel 4
            vibrate(longArrayOf(0, 100, 50, 250)) // pretty good imitation of Zulip drumroll sound?
        }

        binding.buttonVibrateB.setOnClickListener {
            vibrate(longArrayOf(0, 125, 75, 250))
        }

        /*
        binding.buttonVibrateLater.setOnClickListener {
            view.handler.postDelayed({ vibrate(0) }, 3000)

            // TODO get this to work after locking screen (so as to put in pocket).
            // This didn't help:
//            Handler(Looper.getMainLooper()).postDelayed({ vibrate() }, 3000)

            // Nor this :-/
//            Thread {
//                Looper.prepare()
//                Handler(Looper.myLooper()!!).postDelayed({ vibrate() }, 3000)
//                Looper.loop()
//            }

            // Nor this:
//            vibrate(3000)

            // And indeed, this gets cut off when locking the screen:
//            requireContext().getSystemService(Vibrator::class.java)
//                .vibrate(VibrationEffect.createWaveform(longArrayOf(0, 100, 50, 250, 400, 0), 0))
        }
        */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun vibrate(timings: LongArray) {
        requireContext().getSystemService(Vibrator::class.java)
            .vibrate(VibrationEffect.createWaveform(timings, -1))
    }
}
