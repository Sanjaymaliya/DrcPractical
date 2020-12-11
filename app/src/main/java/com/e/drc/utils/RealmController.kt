package com.e.drc.utils

import com.e.drc.model.UserModel
import io.realm.Realm
import io.realm.RealmResults

class RealmController() {
    val realm: Realm = Realm.getDefaultInstance()
    fun queryUser(userId: String, password: String): RealmResults<UserModel> {
        return realm.where(UserModel::class.java)
            .equalTo("email", userId)
            .equalTo("password", password)
            .findAll()
    }
    companion object {

        var instance: RealmController? = null

        fun with(): RealmController {
            if (instance == null) {
                instance = RealmController()
            }
            return instance!!
        }

    }
}