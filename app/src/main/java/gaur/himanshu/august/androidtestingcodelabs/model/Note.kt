package gaur.himanshu.august.androidtestingcodelabs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Note(
    val title: String,
    val disc: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int
) : Parcelable