package hungmn13.yu_gi_oh_calculator.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hungmn13.yu_gi_oh_calculator.enum.Action
import hungmn13.yu_gi_oh_calculator.enum.CalcButton
import hungmn13.yu_gi_oh_calculator.enum.Player

class CalculatorViewModel : ViewModel() {
	private val _p1LP = MutableLiveData<Int>()
	val p1LP: LiveData<Int>
		get() = _p1LP

	private val _p2LP = MutableLiveData<Int>()
	val p2LP: LiveData<Int>
		get() = _p2LP

	private val _inputText = MutableLiveData<String>()
	val inputText: LiveData<String>
		get() = _inputText

	private val _isDiceGlowing = MutableLiveData<Boolean>()
	val isDiceGlowing: LiveData<Boolean>
		get() = _isDiceGlowing

	private val _isCoinGlowing = MutableLiveData<Boolean>()
	val isCoinGlowing: LiveData<Boolean>
		get() = _isCoinGlowing

	init {
		changeP1LP(Action.SET, 8000)
		changeP2LP(Action.SET, 8000)
		resetInputText()
		_isDiceGlowing.value = false
		_isCoinGlowing.value = false
	}

	fun changeP1LP(action: Action, lp: Int?) {
		_p1LP.value = when (action) {
			Action.SET -> lp!!
			Action.PLUS -> p1LP.value!!.plus(lp!!)
			Action.MINUS -> p1LP.value!!.minus(lp!!)
			Action.HALVE -> p1LP.value!!.div(2)
			else -> p1LP.value!!
		}
		checkLPLimit(Player.PLAYER1)
	}

	fun changeP2LP(action: Action, lp: Int?) {
		_p2LP.value = when (action) {
			Action.SET -> lp!!
			Action.PLUS -> p2LP.value!!.plus(lp!!)
			Action.MINUS -> p2LP.value!!.minus(lp!!)
			Action.HALVE -> p2LP.value!!.div(2)
			else -> p2LP.value!!
		}
		checkLPLimit(Player.PLAYER2)
	}

	fun swapLP() {
		val tmp = p1LP.value!!
		changeP1LP(Action.SET, p2LP.value!!)
		changeP2LP(Action.SET, tmp)
	}

	fun changeInputText(calcButton: CalcButton) {
		if (inputText.value.equals("0")) _inputText.value = ""
		_inputText.value = when (calcButton) {
			CalcButton.ZERO -> if (inputText.value.equals("")) "0" else inputText.value.plus("0")
			CalcButton.DOUBLE_ZERO -> if (inputText.value.equals("")) "0" else inputText.value.plus("00")
			CalcButton.ONE -> inputText.value.plus("1")
			CalcButton.TWO -> inputText.value.plus("2")
			CalcButton.THREE -> inputText.value.plus("3")
			CalcButton.FOUR -> inputText.value.plus("4")
			CalcButton.FIVE -> inputText.value.plus("5")
			CalcButton.SIX -> inputText.value.plus("6")
			CalcButton.SEVEN -> inputText.value.plus("7")
			CalcButton.EIGHT -> inputText.value.plus("8")
			CalcButton.NINE -> inputText.value.plus("9")
			CalcButton.CLEAR -> "0"
			CalcButton.BACKSPACE -> if (inputText.value!!.length <= 1) "0" else inputText.value!!.dropLast(1)
		}
		if (inputText.value!!.length > 6) _inputText.value = inputText.value!!.dropLast(inputText.value!!.length - 6)
	}

	fun resetInputText() {
		_inputText.value = "0"
	}

	fun toggleDiceGlow() {
		_isDiceGlowing.value = !(isDiceGlowing.value!!)
	}

	fun toggleCoinGlow() {
		_isCoinGlowing.value = !(isCoinGlowing.value!!)
	}

	private fun checkLPLimit(player: Player) {
		if (player == Player.PLAYER1) {
			if (p1LP.value!! < 0) changeP1LP(Action.SET, 0)
			if (p1LP.value!! > 999999) changeP1LP(Action.SET, 999999)
		} else {
			if (p2LP.value!! < 0) changeP2LP(Action.SET, 0)
			if (p2LP.value!! > 999999) changeP2LP(Action.SET, 999999)
		}
	}
}
