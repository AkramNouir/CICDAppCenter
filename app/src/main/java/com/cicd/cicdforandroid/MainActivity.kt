package com.cicd.cicdforandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cicd.cicdforandroid.databinding.ActivityMainBinding
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val future = Crashes.hasCrashedInLastSession()
        future.thenAccept {
            if (it) {
                Toast.makeText(this, "Oops ! Sorry about that !!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.calculateButton.setOnClickListener {
            try {
                val interestRate = binding.interestEditText.text.toString().toFloat()
                val currentAge = binding.ageEditText.text.toString().toInt()
                val retirementAge = binding.retirementEditText.text.toString().toInt()

                val monthly = binding.monthlySavingsEditText.text.toString().toFloat()
                val current = binding.currentEditText.text.toString().toFloat()

                val properties: HashMap<String, String> = HashMap<String, String>()
                properties.put("interest_rate", interestRate.toString())
                properties.put("current_age", currentAge.toString())
                properties.put("retirement_age", retirementAge.toString())
                properties.put("monthly_savings", monthly.toString())
                properties.put("current_savings", current.toString())

                if (interestRate <= 0) {
                    Analytics.trackEvent("wrong_interest_rate", properties)
                }
                if (retirementAge <= currentAge) {
                    Analytics.trackEvent("wrong_age", properties)
                }

                Log.e("Event", "event sent !!")

                binding.resultTextView.text = "At the moment rate of $interestRate% saving $monthly"

            } catch (ex: Exception) {
                Analytics.trackEvent(ex.message)
            }
        }
    }

    private fun crashApp() {
        //throw InterruptedException()
        //Crashes.generateTestCrash()
    }
}