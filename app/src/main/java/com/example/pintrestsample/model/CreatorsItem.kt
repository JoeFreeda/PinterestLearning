package com.example.pintrestsample.model

data class CreatorsItem(
    val alt_description: String?,
    val alternative_slugs: AlternativeSlugs?,
    val asset_type: String?,
    val blur_hash: String?,
    val breadcrumbs: List<Any?>?,
    val color: String?,
    val created_at: String?,
    val current_user_collections: List<Any?>?,
    val description: Any?,
    val downloads: Int?,
    val exif: Exif?,
    val height: Int?,
    val id: String?,
    val liked_by_user: Boolean?,
    val likes: Int?,
    val links: Links?,
    val location: Location?,
    val promoted_at: Any?,
    val slug: String?,
    val sponsorship: Any?,
    val topic_submissions: TopicSubmissions?,
    val updated_at: String?,
    val urls: Urls?,
    val user: User?,
    val views: Int?,
    val width: Int?
) {
    data class AlternativeSlugs(
        val de: String?,
        val en: String?,
        val es: String?,
        val fr: String?,
        val `it`: String?,
        val ja: String?,
        val ko: String?,
        val pt: String?
    )

    data class Exif(
        val aperture: Any?,
        val exposure_time: Any?,
        val focal_length: Any?,
        val iso: Any?,
        val make: String?,
        val model: String?,
        val name: String?
    )

    data class Links(
        val download: String?,
        val download_location: String?,
        val html: String?,
        val self: String?
    )

    data class Location(
        val city: String?,
        val country: String?,
        val name: String?,
        val position: Position?
    ) {
        data class Position(
            val latitude: Double?,
            val longitude: Double?
        )
    }

    data class TopicSubmissions(
        val film: Film?
    ) {
        data class Film(
            val approved_on: String?,
            val status: String?
        )
    }

    data class Urls(
        val full: String?,
        val raw: String?,
        val regular: String?,
        val small: String?,
        val small_s3: String?,
        val thumb: String?
    )

    data class User(
        val accepted_tos: Boolean?,
        val bio: String?,
        val first_name: String?,
        val for_hire: Boolean?,
        val id: String?,
        val instagram_username: String?,
        val last_name: String?,
        val links: Links?,
        val location: String?,
        val name: String?,
        val portfolio_url: Any?,
        val profile_image: ProfileImage?,
        val social: Social?,
        val total_collections: Int?,
        val total_illustrations: Int?,
        val total_likes: Int?,
        val total_photos: Int?,
        val total_promoted_illustrations: Int?,
        val total_promoted_photos: Int?,
        val twitter_username: Any?,
        val updated_at: String?,
        val username: String?
    ) {
        data class Links(
            val html: String?,
            val likes: String?,
            val photos: String?,
            val portfolio: String?,
            val self: String?
        )

        data class ProfileImage(
            val large: String?,
            val medium: String?,
            val small: String?
        )

        data class Social(
            val instagram_username: String?,
            val paypal_email: Any?,
            val portfolio_url: Any?,
            val twitter_username: Any?
        )
    }
}
