package com.example.healthapp.ui.dashboard

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthapp.MainActivity
import com.example.healthapp.R
import com.example.healthapp.databinding.FragmentDashboardBinding
import com.example.healthapp.databinding.FragmentHomeBinding
import com.example.healthapp.ui.home.HomeViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        var activity: MainActivity = MainActivity();
        val yes = activity.TODAY



        val eatProgress: Float = ((yes.getCalsEaten().toFloat() / yes.getCalsGoal().toFloat())*100.toFloat())
        kotlin.math.round(eatProgress);
        System.out.println(eatProgress);
        var inteatProg = eatProgress .toInt();
        var pview : ProgressBar=binding.progressBar2
        pview.setProgress(inteatProg)
        //val burned = yes.getMealCals(0,"Waffles")
        var tv : TextView= binding.pbarText
        tv.text= inteatProg.toString()+"%"
        var breakCals : TextView = binding.homeBreakfastCals
        breakCals.text= yes.getCalsBreakfast().toString();
        var lunCals : TextView = binding.homeLunchCals
        lunCals.text= yes.getCalsLunch().toString();
        var dinCals : TextView = binding.homeDinnerCals
        dinCals.text= yes.getCalsDinner().toString();
        var snackCals : TextView = binding.homeSnackCals
        snackCals.text= yes.getCalsSnack().toString();
        var tWeight = binding.todayWeight
        tWeight.text = yes.getWeight().toString()





        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
