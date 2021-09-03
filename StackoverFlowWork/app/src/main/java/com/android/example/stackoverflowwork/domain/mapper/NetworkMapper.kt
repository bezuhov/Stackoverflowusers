package com.android.example.stackoverflowwork.domain.mapper

import com.android.example.stackoverflowwork.base.extension.orFalse
import com.android.example.stackoverflowwork.base.extension.orZero
import com.android.example.stackoverflowwork.data.model.BadgeCounts
import com.android.example.stackoverflowwork.data.model.User
import com.android.example.stackoverflowwork.data.model.response.ListR
import com.android.example.stackoverflowwork.data.model.uimodel.UserDetailItem
import javax.inject.Inject

class NetworkMapper
@Inject
constructor(): Mapper<User, UserDetailItem> {
    override fun mapFromEntity(entity: User): UserDetailItem {
        return UserDetailItem(
        profile_image = entity.profile_image.orEmpty(),
        reputation = entity.reputation.orZero(),
        display_name = entity.display_name.orEmpty(),
        bronze = entity.badge_counts?.bronze.orZero(),
        gold= entity.badge_counts?.gold.orZero(),
        silver = entity.badge_counts?.silver.orZero(),
        location = entity.location.orEmpty(),
        creation_date = entity.creation_date.orZero(),
        accept_rate = entity.accept_rate.orZero(),
        account_id = entity.account_id.orZero(),
        is_employee = entity.is_employee.orFalse(),
        last_access_date = entity.last_access_date.orZero(),
        last_modified_date = entity.last_modified_date.orZero(),
        link = entity.link.orEmpty(),
        reputation_change_day = entity.reputation_change_day.orZero(),
        reputation_change_month = entity.reputation_change_month.orZero(),
        reputation_change_quarter = entity.reputation_change_quarter.orZero(),
        reputation_change_week = entity.reputation_change_week.orZero(),
        reputation_change_year = entity.reputation_change_year.orZero(),
        user_id = entity.user_id.orZero(),
        user_type = entity.user_type.orEmpty(),
        website_url = entity.website_url.orEmpty(),

        )
    }

    fun mapFromEntityList(entities: ListR): List<UserDetailItem>{
        return entities.items.map { mapFromEntity(it) }
    }

    override fun mapToEntity(domainModel: UserDetailItem): User {
        return User(
            profile_image = domainModel.profile_image.orEmpty(),
            reputation = domainModel.reputation.orZero(),
            display_name = domainModel.display_name.orEmpty(),
            badge_counts= BadgeCounts (domainModel.bronze,domainModel.gold,domainModel.silver),
            location = domainModel.location.orEmpty(),
            creation_date = domainModel.creation_date.orZero(),
            accept_rate = domainModel.accept_rate.orZero(),
            account_id = domainModel.account_id.orZero(),
            is_employee = domainModel.is_employee.orFalse(),
            last_access_date = domainModel.last_access_date.orZero(),
            last_modified_date = domainModel.last_modified_date.orZero(),
            link = domainModel.link.orEmpty(),
            reputation_change_day = domainModel.reputation_change_day.orZero(),
            reputation_change_month = domainModel.reputation_change_month.orZero(),
            reputation_change_quarter = domainModel.reputation_change_quarter.orZero(),
            reputation_change_week = domainModel.reputation_change_week.orZero(),
            reputation_change_year = domainModel.reputation_change_year.orZero(),
            user_id = domainModel.user_id.orZero(),
            user_type = domainModel.user_type.orEmpty(),
            website_url = domainModel.website_url.orEmpty()
        )
    }

}