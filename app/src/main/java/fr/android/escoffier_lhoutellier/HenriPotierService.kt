package fr.android.escoffier_lhoutellier

import retrofit2.http.GET

interface HenriPotierService {
    @GET("/books")
    suspend fun listBooks(): List<Book>
}
