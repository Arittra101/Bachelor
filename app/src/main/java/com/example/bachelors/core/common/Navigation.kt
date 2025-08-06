package com.example.bachelors.core.common

import androidx.navigation.NavController


fun NavController.navigateRoot(destination: Int){
    if(currentDestination?.id == destination) return
    popBackStack(destination,true)
    navigate(destination)
}