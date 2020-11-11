package fr.android.androidexercises

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        // Plant logger cf. Android Timber
        //Timber.plant(Timber.DebugTree())

        // TODO build Retrofit

        // TODO create a service

        // TODO listBooks()

        // TODO enqueue call and display book title

        // TODO log books

        // TODO display book as a list
    }

}
