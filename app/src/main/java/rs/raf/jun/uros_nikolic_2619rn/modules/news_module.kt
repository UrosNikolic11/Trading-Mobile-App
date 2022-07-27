package rs.raf.jun.uros_nikolic_2619rn

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun.uros_nikolic_2619rn.data.datasources.remote.NewsService
import rs.raf.jun.uros_nikolic_2619rn.data.repositories.NewsRepository
import rs.raf.jun.uros_nikolic_2619rn.data.repositories.NewsRepositoryImpl
import rs.raf.jun.uros_nikolic_2619rn.presentation.viewmodels.NewsViewModel


val newsModule = module {

    viewModel { NewsViewModel(newsRepository = get()) }

    single<NewsRepository> { NewsRepositoryImpl(remoteDataSource = get()) }

    single<NewsService> { create(retrofit = get()) }
}