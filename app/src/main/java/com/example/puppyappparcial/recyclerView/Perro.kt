package com.example.puppyappparcial.recyclerView

import android.os.Parcel
import android.os.Parcelable


class Perro(nombre: String?, edad: Int?, curso: String?, urlImage: String?): Parcelable {
    var nombre: String = ""

    var curso: String = ""

    var edad: Int = 0

    var urlImage: String = ""

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    class Constants {
        companion object {
            val cursoA = "A"
            val cursoB = "B"
            val cursoC = "C"
        }
    }

    init {
        this.nombre = nombre!!
        this.curso = curso!!
        this.edad = edad!!
        this.urlImage = urlImage!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(curso)
        parcel.writeInt(edad)
        parcel.writeString(urlImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Perro> {
        override fun createFromParcel(parcel: Parcel): Perro {
            return Perro(parcel)
        }

        override fun newArray(size: Int): Array<Perro?> {
            return arrayOfNulls(size)
        }
    }
}
