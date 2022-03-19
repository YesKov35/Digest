 package com.raisehumaneness.digest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.raisehumaneness.digest.data.models.PhoneBookModel
import com.raisehumaneness.digest.data.repository.CountryRepository
import com.raisehumaneness.digest.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding
     private lateinit var navController: NavController

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)

         val navHostFragment =
             supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController

         CountryRepository.instance.countryList = getData()
     }

     override fun onSupportNavigateUp(): Boolean {
         return super.onSupportNavigateUp() || navController.navigateUp()
     }

     private fun getData(): List<PhoneBookModel>{
         val dataResult = emptyList<PhoneBookModel>().toMutableList()
         val countryNames = resources.getStringArray(R.array.country_names)
         val phoneBook = resources.getStringArray(R.array.country_phones)

         for(i in countryNames.indices)
             dataResult += PhoneBookModel(countryNames[i], phoneBook[i], i)

         return dataResult
     }
 }