package com.example.quanlychitieucanhan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.quanlychitieucanhan.ui.navigation.AppNavigation
import com.example.quanlychitieucanhan.ui.theme.QuanLyChiTieuCaNhanTheme // Tên Theme tương ứng trong theme/Theme.kt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuanLyChiTieuCaNhanTheme {
                AppNavigation()
            }
        }
    }
}