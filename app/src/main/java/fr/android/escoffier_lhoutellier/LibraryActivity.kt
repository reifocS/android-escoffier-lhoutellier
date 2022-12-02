package fr.android.escoffier_lhoutellier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean
)

class LibraryActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerFrameLayout, BookFragment())
            .commit()
    }
}

