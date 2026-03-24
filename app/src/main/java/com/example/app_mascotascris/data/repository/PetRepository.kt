package com.example.app_mascotascris.data.repository

import com.example.app_mascotascris.data.local.dao.PetDao
import com.example.app_mascotascris.data.local.entities.PetEntity
import kotlinx.coroutines.flow.Flow

class PetRepository(private val petDao: PetDao) {
    val allPets: Flow<List<PetEntity>> = petDao.getAllPets()

    fun getPetsByType(type: String): Flow<List<PetEntity>> {
        return if (type == "Todos") {
            petDao.getAllPets()
        } else {
            petDao.getPetsByType(type)
        }
    }

    suspend fun insertPet(pet: PetEntity) {
        petDao.insertPet(pet)
    }

    suspend fun deletePet(pet: PetEntity) {
        petDao.deletePet(pet)
    }
}
