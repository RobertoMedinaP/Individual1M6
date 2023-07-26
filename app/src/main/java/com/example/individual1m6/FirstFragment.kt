package com.example.individual1m6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.individual1m6.Model.Player
import com.example.individual1m6.Model.PlayerDataBase
import com.example.individual1m6.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val database= Room.databaseBuilder(
            requireContext().applicationContext,
            PlayerDataBase::class.java,
            "tablaJugadores")
            .build()

        binding.btagregar.setOnClickListener {
            val newPlayer = Player(
                //TODO hacer las validaciones
                apodo = binding.textInputLayoutApodo.editText?.text.toString(),
                nombrecompleto = binding.textInputLayoutNombrecompleto.editText?.text.toString(),
                edad = binding.textInputLayoutEdad.editText?.text.toString().toInt(),

                )

            GlobalScope.launch(Dispatchers.IO) {
                database.getPlayerDao().insertPlayer(newPlayer)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}