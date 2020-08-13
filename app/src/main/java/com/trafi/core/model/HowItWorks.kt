/**
* Core Whitelabel API
* No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
*
* The version of the OpenAPI document: v1
*
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package com.trafi.core.model

import com.squareup.moshi.Json
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 * @param howToStartBookingWebUrl
 * @param howToEndBookingWebUrl
 * @param howToInstantStartBookingWebUrl
 */
@Parcelize

data class HowItWorks(
    @Json(name = "howToStartBookingWebUrl")
    val howToStartBookingWebUrl: kotlin.String,
    @Json(name = "howToEndBookingWebUrl")
    val howToEndBookingWebUrl: kotlin.String,
    @Json(name = "howToInstantStartBookingWebUrl")
    val howToInstantStartBookingWebUrl: kotlin.String? = null
) : Parcelable
