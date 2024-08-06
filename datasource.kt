package com.example.dataSource

import com.example.woof.DogDataClass
import com.example.woofapp.R

class DataSource {
   fun listOfDogs():List<DogDataClass>{
    return listOf(
        DogDataClass(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
        DogDataClass(R.drawable.lola, R.string.dog_name_2, 16, R.string.dog_description_2),
        DogDataClass(R.drawable.frankie, R.string.dog_name_3, 2, R.string.dog_description_3),
        DogDataClass(R.drawable.nox, R.string.dog_name_4, 8, R.string.dog_description_4),
        DogDataClass(R.drawable.faye, R.string.dog_name_5, 8, R.string.dog_description_5),
        DogDataClass(R.drawable.bella, R.string.dog_name_6, 14, R.string.dog_description_6),
        DogDataClass(R.drawable.moana, R.string.dog_name_7, 2, R.string.dog_description_7),
        DogDataClass(R.drawable.tzeitel, R.string.dog_name_8, 7, R.string.dog_description_8),
        DogDataClass(R.drawable.leroy, R.string.dog_name_9, 4, R.string.dog_description_9)
    )
    }

}
