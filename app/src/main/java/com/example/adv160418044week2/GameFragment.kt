package com.example.adv160418044week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random

class GameFragment : Fragment() {
    var firstNumber = 0
    var secondNumber = 0
    var resultNumber = 0

    var playerPoint = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    fun calculation(){
        firstNumber = Random.nextInt(0, 20)
        secondNumber = Random.nextInt(0, 20)
        txtNumber1.text = firstNumber.toString()
        txtNumber2.text = secondNumber.toString()
        resultNumber = firstNumber + secondNumber
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null) {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }
        calculation()
        btnSubmit.setOnClickListener {
            if(txtAnswer.text.toString() != resultNumber.toString()){
                val action = GameFragmentDirections.actionResultFragment(playerPoint)
                Navigation.findNavController(it).navigate(action)
            }
            else{
                Toast.makeText(activity,"Benar!",Toast.LENGTH_SHORT)
                playerPoint +=1
                calculation()
                txtAnswer.setText("")
            }
        }

    }
}