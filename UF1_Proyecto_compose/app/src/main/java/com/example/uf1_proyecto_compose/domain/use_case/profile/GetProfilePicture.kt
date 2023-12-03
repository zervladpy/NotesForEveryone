package com.example.uf1_proyecto_compose.domain.use_case.profile

import android.net.Uri
import android.util.Log
import com.example.uf1_proyecto_compose.utils.Response
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetProfilePicture
@Inject constructor(
    private val firebaseStorage: FirebaseStorage
) {

    operator fun invoke(userUid: String): Flow<Response<Uri?>> = flow {
        try {
            emit(Response.Loading())

            val photo = firebaseStorage.reference.child("profile_pictures")
                .child(userUid).downloadUrl.await()

            emit(Response.Success(photo))
        } catch (e: Exception) {
            Log.d("error retriving photo", "")
            emit(Response.Error("null"))
        }
    }

}