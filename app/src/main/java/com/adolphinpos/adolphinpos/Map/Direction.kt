package com.adolphinpos.adolphinpos.Map


import com.google.gson.annotations.SerializedName

data class Direction(
    @SerializedName("geocoded_waypoints")
    var geocodedWaypoints: List<GeocodedWaypoint?>?,
    @SerializedName("routes")
    var routes: List<Route?>?,
    @SerializedName("status")
    var status: String? // OK
) {
    data class GeocodedWaypoint(
        @SerializedName("geocoder_status")
        var geocoderStatus: String?, // OK
        @SerializedName("partial_match")
        var partialMatch: Boolean?, // true
        @SerializedName("place_id")
        var placeId: String?, // ChIJIy48sOehHBURNgql-2Uw-mo
        @SerializedName("types")
        var types: List<String?>?
    )

    data class Route(
        @SerializedName("bounds")
        var bounds: Bounds?,
        @SerializedName("copyrights")
        var copyrights: String?, // Map data ©2019 ORION-ME
        @SerializedName("legs")
        var legs: List<Leg?>?,
        @SerializedName("overview_polyline")
        var overviewPolyline: OverviewPolyline?,
        @SerializedName("summary")
        var summary: String?, // Salamah Al-Maayetah St.
        @SerializedName("warnings")
        var warnings: List<Any?>?,
        @SerializedName("waypoint_order")
        var waypointOrder: List<Any?>?
    ) {
        data class Bounds(
            @SerializedName("northeast")
            var northeast: Northeast?,
            @SerializedName("southwest")
            var southwest: Southwest?
        ) {
            data class Northeast(
                @SerializedName("lat")
                var lat: Double?, // 32.0013383
                @SerializedName("lng")
                var lng: Double? // 35.848691
            )

            data class Southwest(
                @SerializedName("lat")
                var lat: Double?, // 31.9879234
                @SerializedName("lng")
                var lng: Double? // 35.84224
            )
        }

        data class Leg(
            @SerializedName("distance")
            var distance: Distance?,
            @SerializedName("duration")
            var duration: Duration?,
            @SerializedName("end_address")
            var endAddress: String?, // Amro Ben Sinan, Amman, Jordan
            @SerializedName("end_location")
            var endLocation: EndLocation?,
            @SerializedName("start_address")
            var startAddress: String?, // Amir Ben Malek St., Amman, Jordan
            @SerializedName("start_location")
            var startLocation: StartLocation?,
            @SerializedName("steps")
            var steps: List<Step?>?,
            @SerializedName("traffic_speed_entry")
            var trafficSpeedEntry: List<Any?>?,
            @SerializedName("via_waypoint")
            var viaWaypoint: List<Any?>?
        ) {
            data class Distance(
                @SerializedName("text")
                var text: String?, // 2.9 km
                @SerializedName("value")
                var value: Int? // 2856
            )

            data class Duration(
                @SerializedName("text")
                var text: String?, // 7 mins
                @SerializedName("value")
                var value: Int? // 443
            )

            data class EndLocation(
                @SerializedName("lat")
                var lat: Double?, // 32.0011399
                @SerializedName("lng")
                var lng: Double? // 35.8475455
            )

            data class StartLocation(
                @SerializedName("lat")
                var lat: Double?, // 31.9885144
                @SerializedName("lng")
                var lng: Double? // 35.848691
            )

            data class Step(
                @SerializedName("distance")
                var distance: Distance?,
                @SerializedName("duration")
                var duration: Duration?,
                @SerializedName("end_location")
                var endLocation: EndLocation?,
                @SerializedName("html_instructions")
                var htmlInstructions: String?, // Continue onto <b>Amro Ben Sinan St.</b>
                @SerializedName("maneuver")
                var maneuver: String?, // turn-right
                @SerializedName("polyline")
                var polyline: Polyline?,
                @SerializedName("start_location")
                var startLocation: StartLocation?,
                @SerializedName("travel_mode")
                var travelMode: String? // DRIVING
            ) {
                data class Distance(
                    @SerializedName("text")
                    var text: String?, // 0.3 km
                    @SerializedName("value")
                    var value: Int? // 258
                )

                data class Duration(
                    @SerializedName("text")
                    var text: String?, // 1 min
                    @SerializedName("value")
                    var value: Int? // 35
                )

                data class EndLocation(
                    @SerializedName("lat")
                    var lat: Double?, // 32.0011399
                    @SerializedName("lng")
                    var lng: Double? // 35.8475455
                )

                data class Polyline(
                    @SerializedName("points")
                    var points: String? // wfibEa}wyE@yB@a@DsE?WFqB@i@
                )

                data class StartLocation(
                    @SerializedName("lat")
                    var lat: Double?, // 32.0012427
                    @SerializedName("lng")
                    var lng: Double? // 35.8448106
                )
            }
        }

        data class OverviewPolyline(
            @SerializedName("points")
            var points: String? // ewfbEiuxyEtAd@mB~HxAZl@R@LB@aA`E]`AUVa@ReAPeAH}@Hw@_@CCECGAU@GD[Fm@HeDe@iC]kACgADyANm@rFEv@SzCGp@IHKBG?oDgA}Ds@gDq@}Bc@?[NiESEU`HIpAIRQVa@^{@d@_AVmAFc@?oBQ_BWUKo@IAAACACEGGAMBC@e@KS?m@SsA[DuALaHHgKH{C
        )
    }
}