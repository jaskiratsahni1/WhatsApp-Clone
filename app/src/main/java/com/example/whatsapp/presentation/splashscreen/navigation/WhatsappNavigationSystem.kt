package com.example.whatsapp.presentation.splashscreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatsapp.presentation.splashscreen.SplashScreen
import com.example.whatsapp.presentation.splashscreen.callscreen.CallScreen
import com.example.whatsapp.presentation.splashscreen.community.CommunityScreen
import com.example.whatsapp.presentation.splashscreen.homescreen.HomeScreen
import com.example.whatsapp.presentation.splashscreen.profile.UserProfileScreen
import com.example.whatsapp.presentation.splashscreen.updatescreen.UpdateScreen
import com.example.whatsapp.presentation.splashscreen.userregistrationscreen.UserRegistrationScreen
import com.example.whatsapp.presentation.splashscreen.viewmodel.BaseViewModel
import com.example.whatsapp.presentation.splashscreen.welcomescreen.WelcomeScreen

@Composable
fun WhatsappNavigationSystem(modifier: Modifier = Modifier) {

val navController= rememberNavController()

    NavHost(startDestination = Routes.SplashScreen,navController=navController){


        composable <Routes.SplashScreen> {
            SplashScreen(navController)
        }

        composable <Routes.WelcomScreen>{
            WelcomeScreen(navController)
        }

        composable <Routes.RegistrationScreen>{
            UserRegistrationScreen(navController)
        }

        composable <Routes.HomeScreen>{
            val baseViewModel: BaseViewModel= hiltViewModel()
            HomeScreen(navController,baseViewModel)
        }

        composable <Routes.UpdateScreen>{
            UpdateScreen(navController)
        }

        composable <Routes.CommunityScreen>{
            CommunityScreen(navController)
        }

        composable <Routes.CallScreen>{
            CallScreen(navController)
        }

        composable <Routes.UserProfileScreen>{
            UserProfileScreen(navHostController = navController)
        }

    }
}