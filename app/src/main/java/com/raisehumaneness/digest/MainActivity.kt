 package com.raisehumaneness.digest

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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

         initContacts()

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

     private fun initContacts(){
         binding.run {
             contactClose.setOnClickListener { contactView.visibility = View.GONE }
             info.setOnClickListener { contactView.visibility = View.VISIBLE }

             contact.contactInstUrl.setOnClickListener { openInst() }
             contact.contactInst.setOnClickListener { openInst() }

             contact.contactMailUrl.setOnClickListener { openEmail() }
             contact.contactMail.setOnClickListener { openEmail() }

             contact.contactPatreonUrl.setOnClickListener { openPatreon() }
             contact.contactPatreon.setOnClickListener { openPatreon() }
         }

     }

     private fun openInst(){
         val uri: Uri = Uri.parse("http://instagram.com/_u/raisehumaneness")
         val likeIng = Intent(Intent.ACTION_VIEW, uri)

         likeIng.setPackage("com.instagram.android")

         try {
             startActivity(likeIng)
         } catch (e: ActivityNotFoundException) {
             startActivity(
                 Intent(
                     Intent.ACTION_VIEW,
                     Uri.parse("http://instagram.com/raisehumaneness")
                 )
             )
         }
     }

     fun openEmail() {
         val intent = Intent(Intent.ACTION_SENDTO,
             Uri.parse("mailto:raisehumaneness@gmail.com"))
         startActivity(Intent.createChooser(intent, "Email"))
     }

     private fun openPatreon(){


         val uri: Uri = Uri.parse("https://www.patreon.com/raisehumaneness")
         val likeIng = Intent(Intent.ACTION_VIEW, uri)

         likeIng.setPackage("com.patreon.android")

         try {
             startActivity(likeIng)
         } catch (e: ActivityNotFoundException) {
             startActivity(
                 Intent(
                     Intent.ACTION_VIEW,
                     Uri.parse("https://www.patreon.com/raisehumaneness")
                 )
             )
         }
     }
 }