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
 * Part of the route for shared transport
 * @param provider
 * @param segmentName
 * @param vehicle
 * @param startStation
 * @param endStation
 * @param gearboxText
 * @param pricingText
 * @param rangeRemaining
 */
@Parcelize

data class RouteSegmentSharing(
    @Json(name = "provider")
    val provider: Provider,
    @Json(name = "segmentName")
    val segmentName: kotlin.String,
    @Json(name = "vehicle")
    val vehicle: SharedVehicle? = null,
    @Json(name = "startStation")
    val startStation: RouteSegmentSharingStation? = null,
    @Json(name = "endStation")
    val endStation: RouteSegmentSharingStation? = null,
    @Json(name = "gearboxText")
    val gearboxText: kotlin.String? = null,
    @Json(name = "pricingText")
    val pricingText: kotlin.String? = null,
    @Json(name = "rangeRemaining")
    val rangeRemaining: kotlin.String? = null
) : Parcelable
