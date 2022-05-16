package hungmn13.yu_gi_oh_calculator.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hungmn13.yu_gi_oh_calculator.R
import hungmn13.yu_gi_oh_calculator.databinding.FragmentCalculatorBinding
import hungmn13.yu_gi_oh_calculator.enum.Action
import hungmn13.yu_gi_oh_calculator.enum.CalcButton
import hungmn13.yu_gi_oh_calculator.enum.Player

class CalculatorFragment : Fragment() {

	private lateinit var binding: FragmentCalculatorBinding
	private lateinit var vm: CalculatorViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator, container, false)
		vm = ViewModelProvider(this).get(CalculatorViewModel::class.java)

		displayLP()

		binding.inputText.text = vm.inputText.value

		binding.p1LpHalfBtn.setOnClickListener {
			setLP(Player.PLAYER1, null, Action.HALVE)
		}

		binding.swapBtn.setOnClickListener {
			setLP(Player.NONE, null, Action.SWAP)
		}

		binding.p2LpHalfBtn.setOnClickListener {
			setLP(Player.PLAYER2, null, Action.HALVE)
		}

		binding.diceLayout.setOnClickListener {
			rollDice()
		}

		binding.coinLayout.setOnClickListener {
			tossCoin()
		}

		binding.zeroBtn.setOnClickListener {
			setInputText(CalcButton.ZERO)
		}

		binding.doubleZeroBtn.setOnClickListener {
			setInputText(CalcButton.DOUBLE_ZERO)
		}

		binding.oneBtn.setOnClickListener {
			setInputText(CalcButton.ONE)
		}

		binding.twoBtn.setOnClickListener {
			setInputText(CalcButton.TWO)
		}

		binding.threeBtn.setOnClickListener {
			setInputText(CalcButton.THREE)
		}

		binding.fourBtn.setOnClickListener {
			setInputText(CalcButton.FOUR)
		}

		binding.fiveBtn.setOnClickListener {
			setInputText(CalcButton.FIVE)
		}

		binding.sixBtn.setOnClickListener {
			setInputText(CalcButton.SIX)
		}

		binding.sevenBtn.setOnClickListener {
			setInputText(CalcButton.SEVEN)
		}

		binding.eightBtn.setOnClickListener {
			setInputText(CalcButton.EIGHT)
		}

		binding.nineBtn.setOnClickListener {
			setInputText(CalcButton.NINE)
		}

		binding.clearBtn.setOnClickListener {
			setInputText(CalcButton.CLEAR)
		}

		binding.backspaceBtn.setOnClickListener {
			setInputText(CalcButton.BACKSPACE)
		}

		binding.p1MinusBtn.setOnClickListener {
			setLP(Player.PLAYER1, vm.inputText.value!!.toInt(), Action.MINUS)
			resetInputText()
		}

		binding.p2MinusBtn.setOnClickListener {
			setLP(Player.PLAYER2, vm.inputText.value!!.toInt(), Action.MINUS)
			resetInputText()
		}

		binding.p1PlusBtn.setOnClickListener {
			setLP(Player.PLAYER1, vm.inputText.value!!.toInt(), Action.PLUS)
			resetInputText()
		}

		binding.p2PlusBtn.setOnClickListener {
			setLP(Player.PLAYER2, vm.inputText.value!!.toString().toInt(), Action.PLUS)
			resetInputText()
		}

		return binding.root
	}

	private fun setLP(player: Player, lp: Int? = 0, action: Action = Action.SET) {
		when (player) {
			Player.PLAYER1 -> vm.changeP1LP(action, lp)
			Player.PLAYER2 -> vm.changeP2LP(action, lp)
			else -> vm.swapLP()
		}
		checkLPLimit()
		displayLP()
	}

	private fun setInputText(calcButton: CalcButton) {
		vm.changeInputText(calcButton)
		binding.inputText.text = vm.inputText.value
	}

	private fun resetInputText() {
		vm.resetInputText()
		binding.inputText.text = vm.inputText.value
	}

	private fun rollDice() {
		val dice = (1..6).shuffled().first()
		binding.diceTextView.text = dice.toString()

		vm.toggleDiceGlow()
		val tmp =
			if (vm.isDiceGlowing.value!!) resources.getColor(R.color.yellow) else resources.getColor(R.color.green)
		binding.diceTextView.setTextColor(tmp)
	}

	private fun tossCoin() {
		val coin = (0..1).shuffled().first()
		binding.coinTextView.text = if (coin == 1) getString(R.string.heads) else getString(R.string.tails)

		vm.toggleCoinGlow()
		val tmp =
			if (vm.isCoinGlowing.value!!) resources.getColor(R.color.yellow) else resources.getColor(R.color.green)
		binding.coinTextView.setTextColor(tmp)
	}

	private fun displayLP() {
		binding.p1Lp.textSize = when (vm.p1LP.value.toString().length) {
			6 -> 40F
			5 -> 48F
			else -> 56F
		}
		binding.p2Lp.textSize = when (vm.p2LP.value.toString().length) {
			6 -> 40F
			5 -> 48F
			else -> 56F
		}
		binding.p1Lp.text = vm.p1LP.value.toString()
		binding.p2Lp.text = vm.p2LP.value.toString()

		var tmp = if (vm.p1LP.value!! <= 2000) resources.getColor(R.color.red)
		else if (vm.p1LP.value!! >= 4000) resources.getColor(R.color.green)
		else resources.getColor(R.color.black)
		binding.p1Lp.setTextColor(tmp)

		tmp = if (vm.p2LP.value!! <= 2000) resources.getColor(R.color.red)
		else if (vm.p2LP.value!! >= 4000) resources.getColor(R.color.green)
		else resources.getColor(R.color.black)
		binding.p2Lp.setTextColor(tmp)
	}

	private fun checkLPLimit() {
		if (vm.p1LP.value!! < 0) vm.changeP1LP(Action.SET, 0)
		if (vm.p2LP.value!! < 0) vm.changeP2LP(Action.SET, 0)
		if (vm.p1LP.value!! > 999999) vm.changeP1LP(Action.SET, 999999)
		if (vm.p2LP.value!! > 999999) vm.changeP2LP(Action.SET, 999999)
	}
}
