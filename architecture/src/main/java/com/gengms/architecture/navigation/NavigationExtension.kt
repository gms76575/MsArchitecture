package com.gengms.architecture.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun Fragment.nav(): NavController = NavHostFragment.findNavController(this)