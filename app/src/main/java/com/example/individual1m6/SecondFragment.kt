package com.example.individual1m6

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.individual1m6.Model.Player
import com.example.individual1m6.Model.PlayerDataBase
import com.example.individual1m6.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        //se pasa el adapter al rv
        val recyclerView: RecyclerView = binding.rv1
        val adapter = PlayerAdapter(emptyList()){
            player ->  eliminarJugador(player)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


        //se vuelve a crear la base de datos

        val database= Room.databaseBuilder(
            requireContext().applicationContext,
            PlayerDataBase::class.java,
            "jugadoresDb")
            .build()


        //despertamos la corrutina

        GlobalScope.launch(Dispatchers.IO) {
            val playerlist = database.getPlayerDao().showallplayers()
            Log.i("database", ">> " + playerlist.count())
            //se actualiza la vista del recycler con los datos obtenidos de la bd
            withContext(Dispatchers.Main) {
                adapter.updateData(playerlist)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun eliminarJugador(player: Player) {
        GlobalScope.launch(Dispatchers.IO) {
            val database = Room.databaseBuilder(
                requireContext().applicationContext,
                PlayerDataBase::class.java,
                "jugadoresDb"
            ).build()

            database.getPlayerDao().deletePlayer(player)
        }

        Snackbar.make(requireView(), "Jugador eliminado", Snackbar.LENGTH_SHORT).show()
    }
}