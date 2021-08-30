package com.example.testdeliciously

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //fonction permettant la création du pop-up contenant la fiche du restaurant
    fun drawPopup(idRestaurant: Int){

        //faire des requêtes sql pour récupérer les infos du restaurant selectionné depuis une BDD
        //
        //TO DO
        //

        //initialisation d'une nouvelle vue pour le pop-up
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_layout, null)

        //récupération de l'id des donnée à modifier dans la vue template
        val photoRestaurant = popupView.findViewById<ImageView>(R.id.photo_restaurant)
        val nomRestaurant = popupView.findViewById<TextView>(R.id.nom_restaurant)
        val typeRestaurant = popupView.findViewById<TextView>(R.id.type_restaurant)
        val adresseRestaurant = popupView.findViewById<TextView>(R.id.adresse_restaurant)
        val viewTextCarac1 = popupView.findViewById<TextView>(R.id.textCarac1)
        val viewTextCarac2 = popupView.findViewById<TextView>(R.id.textCarac2)
        val viewTextCarac3 = popupView.findViewById<TextView>(R.id.textCarac3)
        val viewTextCarac4 = popupView.findViewById<TextView>(R.id.textCarac4)

        //modification à effectuer si le restaurant est le dumbo
        if (idRestaurant == 1)
        {
            //modification avec les données stockés dans le fichier string.xml (remplaçant de la BDD)
            photoRestaurant.setBackgroundResource(R.mipmap.dumbo_screen)
            nomRestaurant.setText(R.string.dumbo_text)
            typeRestaurant.setText(R.string.dumbo_type_restaurant)
            adresseRestaurant.setText(R.string.dumbo_adresse_restaurant)
            viewTextCarac1.setText(R.string.dumbo_carac1)
            viewTextCarac2.setText(R.string.dumbo_carac2)
            viewTextCarac3.setText(R.string.dumbo_carac3)
            viewTextCarac4.setText(R.string.dumbo_carac4)
        }
        //modification à effectuer si le restaurant est le bouillon 47
        else if (idRestaurant == 2)
        {
            photoRestaurant.setBackgroundResource(R.mipmap.bouillon_screen)
            nomRestaurant.setText(R.string.bouillon_text)
            typeRestaurant.setText(R.string.bouillon_type_restaurant)
            adresseRestaurant.setText(R.string.bouillon_adresse_restaurant)
            viewTextCarac1.setText(R.string.bouillon_carac1)
            viewTextCarac2.setText(R.string.bouillon_carac2)
            viewTextCarac3.setText(R.string.bouillon_carac3)
            viewTextCarac4.setText(R.string.bouillon_carac4)
        }

        //initialisation de fenêtre pop-up à l'aide de la vue initié ci-dessus
        val popupWindow = PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        //initialisation de l'élévation du pop-up
        popupWindow.elevation = 40.0F

        //définition de l'animation d'entrée du pop-up
        val slideIn = Slide()
        slideIn.slideEdge = Gravity.TOP
        popupWindow.enterTransition = slideIn

        //définition de l'animation de sortie du pop-up
        val slideOut = Slide()
        slideOut.slideEdge = Gravity.TOP
        popupWindow.exitTransition = slideOut

        val quitButtonPopup = popupView.findViewById<ImageButton>(R.id.quitButton)
        quitButtonPopup.setOnClickListener{
            popupWindow.dismiss()
        }

        //définition de la position de création de la fenêtre pop-up
        TransitionManager.beginDelayedTransition(activity_main)
        popupWindow.showAtLocation(
            activity_main,
            Gravity.CENTER,
            0,
            0
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //appel de l'animation bounce appliqué aux boutons lors du lancement de l'app afin diriger l'attention de l'utilisateur vers les boutons
        dumboButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce))
        bouillonButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce))

        //mis en place d'écouteur réagissant au click sur les boutons
        dumboButton.setOnClickListener{
            drawPopup(1)
        }
        bouillonButton.setOnClickListener{
            drawPopup(2)
        }
    }
}