package rs.raf.jun.uros_nikolic_2619rn.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun.uros_nikolic_2619rn.create
import rs.raf.jun.uros_nikolic_2619rn.data.datasources.remote.PopularStocksService
import rs.raf.jun.uros_nikolic_2619rn.data.repositories.PopularStocksRepository
import rs.raf.jun.uros_nikolic_2619rn.data.repositories.PopularStocksRepositoryImpl
import rs.raf.jun.uros_nikolic_2619rn.presentation.viewmodels.PopularStocksViewModel

val psModule = module {

    viewModel { PopularStocksViewModel(popularStocksRepository = get()) }

    single<PopularStocksRepository> { PopularStocksRepositoryImpl(remoteDataSource = get()) }

    single<PopularStocksService> { create(retrofit = get()) }
}