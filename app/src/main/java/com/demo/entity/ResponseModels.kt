package com.demo.entity
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


data class ResponseModels(
    @SerializedName("result") var result: List<Result>
)


open class Result : RealmObject() {
        @PrimaryKey
        @SerializedName("id")
        var id: Int? = 0
        @SerializedName("acount_No")
        var acountNo: String? = ""
        @SerializedName("account_name")
        var accountName: String? = ""
        @SerializedName("mobile")
        var mobile: String? = ""
        @SerializedName("address")
        var address: String? = ""
        @SerializedName("ac_type")
        var acType: String? = ""
}