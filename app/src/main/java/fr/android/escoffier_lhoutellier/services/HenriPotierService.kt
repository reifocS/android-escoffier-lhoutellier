package fr.android.escoffier_lhoutellier.services

import fr.android.escoffier_lhoutellier.data.Book
import retrofit2.http.GET

interface HenriPotierService {
    @GET("/books")
    suspend fun listBooks(): List<Book>
}
