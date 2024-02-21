package com.mortatval.seminar_4
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.mortatval.seminar_4.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActivityState()
    }

    private fun checkConditions(): Boolean {
        val conditionNameField = binding.username.length() in 1..40
        val conditionPhoneField = binding.phone.text.toString().isNotBlank() &&
                binding.phone.toString().isNotEmpty()
        val conditionSexIsChecked = binding.male.isChecked ||
                binding.female.isChecked

        val conditionNotificationSwitch = (binding.notifications.isChecked &&
                binding.authorization.isChecked || binding.offers.isChecked)
                || !binding.notifications.isChecked

        return conditionSexIsChecked && conditionNameField
                && conditionPhoneField && conditionNotificationSwitch
    }

    private fun changeSaveButtonState() {
        if (checkConditions()) {
            binding.saveButton.isEnabled = true
        } else {
            binding.saveButton.isEnabled = false
        }
    }


    private fun initActivityState() {
        val numberOfPoints = Random.nextInt(101)

        binding.progressBar.secondaryProgress = numberOfPoints

        binding.PointsOfNumber.text =
            String.format(getString(R.string.points_of_number, numberOfPoints))

        binding.offers.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                changeSaveButtonState()
            } else {
                changeSaveButtonState()
            }
        }

        binding.authorization.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                changeSaveButtonState()
            } else {
                changeSaveButtonState()
            }
        }

        binding.username.doAfterTextChanged {
            changeSaveButtonState()
        }

        binding.phone.doAfterTextChanged {
            changeSaveButtonState()
        }


        binding.notifications.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.authorization.isEnabled = true
                binding.offers.isEnabled = true
                changeSaveButtonState()
            } else {
                binding.authorization.isEnabled = !binding.authorization.isEnabled
                binding.offers.isEnabled = !binding.offers.isEnabled
                binding.authorization.isChecked = false
                binding.offers.isChecked = false
                changeSaveButtonState()
            }
        }


        binding.gender.setOnCheckedChangeListener { _, _ ->
            changeSaveButtonState()
        }

        binding.saveButton.setOnClickListener {
            Toast.makeText(this, R.string.changes_saved, Toast.LENGTH_SHORT).show()
        }
    }

}


