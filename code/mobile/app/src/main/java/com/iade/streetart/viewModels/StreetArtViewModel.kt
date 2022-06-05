package com.iade.streetart.viewModels

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.iade.streetart.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreetArtViewModel : ViewModel() {

  private var _streetArts by mutableStateOf<List<StreetArt>>(listOf())

  val streetArts: List<StreetArt>
    get() = _streetArts

  private val streetArtApi = RetrofitHelper.getInstance().create(StreetArtApi::class.java)

  suspend fun fetchStreetArts(): String? {
    val res = withContext(Dispatchers.Default) {
      val result = streetArtApi.getStreetArts()

      if (result.isSuccessful) {
        _streetArts = result.body()!!
      }

      result.message()
    }

    Log.i("streetArt", _streetArts.toString())
    return res
  }

  suspend fun fetchImages(id: Int): List<Image> {
    val res = withContext(Dispatchers.Default) {
      val result = streetArtApi.getStreetArtImages(id)

      if (result.isSuccessful) {
        return@withContext result.body()!!
      }

      return@withContext listOf<Image>()
    }

    Log.i("images", res.toString())
    return res
  }

  fun getSingleStreetArt(id: Int) =
    _streetArts.find { it.sta_id == id }
      ?: StreetArt(0, 0, "", 0,"", Coords(0.0, 0.0), "", "")
}