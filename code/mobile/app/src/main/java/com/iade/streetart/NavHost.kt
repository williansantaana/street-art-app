package com.iade.streetart

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iade.streetart.viewModels.StreetArtViewModel
import com.iade.streetart.viewModels.UserViewModel
import com.iade.streetart.views.*

@Composable
fun NavHost(
  navController: NavHostController,
  userViewModel: UserViewModel,
  streetArtViewModel: StreetArtViewModel,
) {

  NavHost(
    navController = navController,
    startDestination = NavRoutes.HomeView.route
  ) {
    composable(NavRoutes.HomeView.route) {
      HomeView(navController, userViewModel)
    }

    composable(NavRoutes.LoginView.route) {
      LoginViewState(navController, userViewModel)
    }

    composable(NavRoutes.SignInView.route) {
      SignUpViewState(navController, userViewModel)
    }

    composable(NavRoutes.MapView.route) {
      MapView(navController, userViewModel, streetArtViewModel)
    }

    composable(NavRoutes.CameraView.route) {
      CameraView()
    }

    composable(NavRoutes.SearchView.route) {
      SearchViewState(navController, streetArtViewModel)
    }

    composable(NavRoutes.SingleStreetArtView.route) { backStackEntry ->
      SingleStreetArtViewState(
        navController = navController,
        streetArtViewModel = streetArtViewModel,
        streetArtId = backStackEntry.arguments?.getString("streetArtId") ?: "0"
      )
    }
  }
}