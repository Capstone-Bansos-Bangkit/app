package com.bangkit.genaidclean.navigation.utils

sealed class Screen (val route: String){

    // === login route === \\
    data object OnBoarding : Screen("onboarding")
    data object UserLogin : Screen("userLogin")
    data object AdminLogin : Screen("adminLogin")



    // === user route === \\
    data object UserHome : Screen("userHome")
    data object UserDetailBansos : Screen("userHome/{id}"){
        fun createRoute(id: Int) = "userHome/$id"
    }

    data object CekBansos : Screen("cekBansos")
    data object UserProfil : Screen("userProfil")

    data object UserPengajuan : Screen("userPengajuan")
    data object Question: Screen("userPengajuan/{id}"){
        fun createRoute(id: Int) = "userPengajuan/$id"
    }
    data object UpdateEmail: Screen("updateEmail")

    data object UpdatePhone: Screen("updatePhone")



    // === admin route === \\
    data object AdminDashboard: Screen("adminDashboard")

    data object AdminVerifikasi: Screen("adminVerifikasi")
    data object AdminDetailPengajuan: Screen("adminVerifikasi/{id}"){
        fun createRoute(id: Int) = "adminVerifikasi/$id"
    }

    data object AdminBansos : Screen("adminBansos")
    data object AdminDetailBansos: Screen("adminBansos/{id}"){
        fun createRoute(id: Int) = "adminBansos/$id"
    }

}