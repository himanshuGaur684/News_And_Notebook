package gaur.himanshu.august.androidtestingcodelabs.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import gaur.himanshu.august.androidtestingcodelabs.networks.NewsInterface
import gaur.himanshu.august.androidtestingcodelabs.repository.NoteRepository
import gaur.himanshu.august.androidtestingcodelabs.repository.RepositoryInterface
import gaur.himanshu.august.androidtestingcodelabs.room.NoteDao
import gaur.himanshu.august.androidtestingcodelabs.room.NoteDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object DIModules {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NoteDatabaseDependency

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NoteDaoDependency

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RetrofitInterfaceDependency


    @Singleton
    @NoteDatabaseDependency
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = NoteDatabase.get(context = context)

    @Singleton
    @Provides
    @NoteDaoDependency
    fun provideDao(noteDatabase: NoteDatabase) = noteDatabase.getNoteDao()


    @Singleton
    @Provides
    @RetrofitInterfaceDependency
    fun provideRetrofit(): NewsInterface {
        return Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NewsInterface::class.java)
    }

}

@InstallIn(ApplicationComponent::class)
@Module
object NoteRepoModule {
    @Singleton
    @Provides
    fun provideNoteRepository(
      @DIModules.NoteDatabaseDependency  dao: NoteDatabase,
      @DIModules.RetrofitInterfaceDependency  retrofitInterface: NewsInterface
    ): RepositoryInterface {
        return NoteRepository(dao.getNoteDao(), retrofitInterface)
    }

}