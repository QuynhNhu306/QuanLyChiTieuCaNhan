package com.example.quanlychitieucanhan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun login(email: String, pass: String, onNavigate: (Boolean) -> Unit) {
        if (email.isBlank() || pass.isBlank()) {
            _loginState.value = LoginState.Error("Vui lòng điền đầy đủ thông tin")
            return
        }

        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            // Giả lập độ trễ mạng/xử lý database (0.5 giây)
            kotlinx.coroutines.delay(500)

            _loginState.value = LoginState.Success

            // Logic tạm thời để test:
            // Nếu email chứa chữ "admin", chuyển sang trang Admin, ngược lại vào User.
            val isAdmin = email.contains("admin", ignoreCase = true)
            onNavigate(isAdmin)
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}