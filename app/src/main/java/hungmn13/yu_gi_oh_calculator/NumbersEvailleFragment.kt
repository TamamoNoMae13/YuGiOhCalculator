package hungmn13.yu_gi_oh_calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import hungmn13.yu_gi_oh_calculator.databinding.FragmentNumbersEvailleBinding

class NumbersEvailleFragment : Fragment() {

	private lateinit var binding: FragmentNumbersEvailleBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_numbers_evaille, container, false)

		return binding.root
	}
}
