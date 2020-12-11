package com.e.drc.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserModel : RealmObject() {

    var firstName: String? = null

    var email: String? = null

    var password: String? = null

}