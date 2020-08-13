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
 * @param id
 * @param price
 */
@Parcelize

data class Fare(
    @Json(name = "id")
    val id: kotlin.String,
    @Json(name = "price")
    val price: Money
) : Parcelable
