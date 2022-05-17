package hungmn13.yu_gi_oh_calculator.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import hungmn13.yu_gi_oh_calculator.R
import hungmn13.yu_gi_oh_calculator.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

	private lateinit var binding: FragmentWelcomeBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.calculatorBtn.setOnClickListener {
			it.findNavController().navigate(R.id.action_welcomeFragment_to_calculatorFragment)
		}

		binding.numbersEvailleBtn.setOnClickListener {
			it.findNavController().navigate(R.id.action_welcomeFragment_to_numbersEvailleFragment)
		}
	}
}
