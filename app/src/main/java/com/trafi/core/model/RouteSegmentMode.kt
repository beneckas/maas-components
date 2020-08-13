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

/**
*
* Values: UNKNOWN,TRANSIT,RIDE_HAILING,SHARING,WALKING
*/

enum class RouteSegmentMode(val value: kotlin.String) {

    @Json(name = "UNKNOWN")
    UNKNOWN("UNKNOWN"),

    @Json(name = "TRANSIT")
    TRANSIT("TRANSIT"),

    @Json(name = "RIDE_HAILING")
    RIDE_HAILING("RIDE_HAILING"),

    @Json(name = "SHARING")
    SHARING("SHARING"),

    @Json(name = "WALKING")
    WALKING("WALKING");
}
