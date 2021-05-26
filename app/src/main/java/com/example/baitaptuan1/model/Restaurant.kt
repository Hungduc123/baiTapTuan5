package com.example.baitaptuan1.model

class Restaurant(val Id: Int, val Name: String, val Address: String, val PicturePath: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Restaurant

        if (Id != other.Id) return false
        if (Name != other.Name) return false
        if (Address != other.Address) return false
        if (PicturePath != other.PicturePath) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Id
        result = 31 * result + Name.hashCode()
        result = 31 * result + Address.hashCode()
        result = 31 * result + PicturePath.hashCode()
        return result
    }
}