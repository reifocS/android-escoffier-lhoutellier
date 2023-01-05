package fr.android.escoffier_lhoutellier.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.android.escoffier_lhoutellier.data.Book
import fr.android.escoffier_lhoutellier.data.Cart
import fr.android.escoffier_lhoutellier.services.HenriPotierService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class LibraryState(
    val books: List<Book> = emptyList(),
    var cart: Cart = Cart()
)

class BookViewModel : ViewModel() {
    fun loadBooks() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://henri-potier.techx.fr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: HenriPotierService = retrofit.create(HenriPotierService::class.java)

        viewModelScope.launch(context = Dispatchers.Main) {
            val books = withContext(Dispatchers.IO) {
                service.listBooks()
            }
            setBooks(books)
        }
    }

    val state = MutableLiveData<LibraryState>()

    fun setBooks(books: List<Book>) {
        val currentState = state.value ?: LibraryState(emptyList(), Cart())
        val newState = currentState.copy(books = books)
        state.postValue(newState)
    }

    fun setCart(newCart: Cart) {
        val currentState = state.value ?: LibraryState(emptyList(), Cart())
        val newState = currentState.copy(cart = newCart)
        state.postValue(newState)
    }

}

