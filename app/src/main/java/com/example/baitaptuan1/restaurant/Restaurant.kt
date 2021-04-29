package com.example.baitaptuan1.restaurant

class Restaurant(val id: Int, val name: String, val address: String, val picturePath: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Restaurant

        if (id != other.id) return false
        if (name != other.name) return false
        if (address != other.address) return false
        if (picturePath != other.picturePath) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + picturePath.hashCode()
        return result
    }
}