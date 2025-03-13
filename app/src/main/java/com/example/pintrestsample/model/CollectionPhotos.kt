package com.example.pintrestsample.model

data class CollectionPhotos(
    val cover_photo: CoverPhoto?,
    val description: String?,
    val featured: Boolean?,
    val id: String?,
    val last_collected_at: String?,
    val links: Links?,
    val preview_photos: List<PreviewPhoto?>?,
    val `private`: Boolean?,
    val published_at: String?,
    val share_key: String?,
    val tags: List<Tag?>?,
    val title: String?,
    val total_photos: Int?,
    val updated_at: String?,
    val user: User?
) {
    data class CoverPhoto(
        val alt_description: String?,
        val alternative_slugs: AlternativeSlugs?,
        val asset_type: String?,
        val blur_hash: String?,
        val breadcrumbs: List<Any?>?,
        val color: String?,
        val created_at: String?,
        val current_user_collections: List<Any?>?,
        val description: Any?,
        val height: Int?,
        val id: String?,
        val liked_by_user: Boolean?,
        val likes: Int?,
        val links: Links?,
        val promoted_at: Any?,
        val slug: String?,
        val sponsorship: Any?,
        val topic_submissions: TopicSubmissions?,
        val updated_at: String?,
        val urls: Urls?,
        val user: User?,
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

        data class Links(
            val download: String?,
            val download_location: String?,
            val html: String?,
            val self: String?
        )

        class TopicSubmissions

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
            val bio: Any?,
            val first_name: String?,
            val for_hire: Boolean?,
            val id: String?,
            val instagram_username: Any?,
            val last_name: String?,
            val links: Links?,
            val location: Any?,
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
                val followers: String?,
                val following: String?,
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
                val instagram_username: Any?,
                val paypal_email: Any?,
                val portfolio_url: Any?,
                val twitter_username: Any?
            )
        }
    }

    data class Links(
        val html: String?,
        val photos: String?,
        val related: String?,
        val self: String?
    )

    data class PreviewPhoto(
        val asset_type: String?,
        val blur_hash: String?,
        val created_at: String?,
        val id: String?,
        val slug: String?,
        val updated_at: String?,
        val urls: Urls?
    ) {
        data class Urls(
            val full: String?,
            val raw: String?,
            val regular: String?,
            val small: String?,
            val small_s3: String?,
            val thumb: String?
        )
    }

    data class Tag(
        val title: String?,
        val type: String?
    )

    data class User(
        val accepted_tos: Boolean?,
        val bio: Any?,
        val first_name: String?,
        val for_hire: Boolean?,
        val id: String?,
        val instagram_username: Any?,
        val last_name: String?,
        val links: Links?,
        val location: Any?,
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
            val followers: String?,
            val following: String?,
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
            val instagram_username: Any?,
            val paypal_email: Any?,
            val portfolio_url: Any?,
            val twitter_username: Any?
        )
    }
}