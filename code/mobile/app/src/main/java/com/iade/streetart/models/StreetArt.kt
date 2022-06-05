package com.iade.streetart.models

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

data class StreetArt (
  val sta_id: Int,
  val sta_usr_id: Int,
  val sta_artist: String,
  val sta_project: String,
  val sta_year: Int,
  val sta_photo_credits: String,
  val sta_address: String,
  val sta_coords: Coords,
  val sta_status: String,
//  val sta_published: Boolean,
//  val sta_active: Boolean,
  val img_url: String
)

data class StreetArtPost (
  val usr_id: Int,
  val artist: String,
  val project: String,
  val year: Int,
  val credits: String,
  val address: String,
  val coords: String,
  val status: String,
)

data class StreetArtResult (
  val sta_id: Int
)

data class Coords (val lat: Double, val lng: Double)

data class PredictResult (val author: String, val prediction: Double)

interface StreetArtApi {
  @GET("api/streetArts")
  suspend fun getStreetArts() : Response<List<StreetArt>>

  @POST("api/streetArts")
  suspend fun addStreetArt(@Body streetArt: StreetArtPost) : Response<StreetArtResult>

  @GET("api/streetArts/{id}/images")
  suspend fun getStreetArtImages(@Path("id") id: Int) : Response<List<Image>>

  @Multipart
  @POST("api/streetArts/predict")
  suspend fun predict(@Part body: MultipartBody.Part) : Response<List<PredictResult>>
}