package com.example.quanlychitieucanhan.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieucanhan.R
import com.example.quanlychitieucanhan.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onNavigateToHome: (Boolean) -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    val primaryLightBlue = Color(0xFF4A90E2) // Màu xanh biển chủ đạo

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 1. Hình ảnh con heo tiết kiệm ở trên cùng
            Image(
                painter = painterResource(id = R.drawable.ic_piggy_bank),
                contentDescription = "Logo heo tiết kiệm",
                modifier = Modifier.size(110.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Tiêu đề "Chào mừng quay lại"
            Text(
                text = "Chào mừng quay lại",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 3. Trường Email
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Email", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Nhập email của bạn", color = Color.LightGray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = primaryLightBlue
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 4. Trường Mật khẩu
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Mật khẩu", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Nhập mật khẩu", color = Color.LightGray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = icon, contentDescription = "Ẩn/Hiện mật khẩu")
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = primaryLightBlue
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // 5. Ghi nhớ & Quên mật khẩu
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(checkedColor = primaryLightBlue)
                    )
                    Text(text = "Ghi nhớ", fontSize = 14.sp, color = Color.DarkGray)
                }

                Text(
                    text = "Quên mật khẩu?",
                    fontSize = 14.sp,
                    color = primaryLightBlue,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { onNavigateToForgotPassword() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 6. Nút Đăng nhập màu xanh biển nhạt
            Button(
                onClick = {
                    viewModel.login(email, password) { isAdmin ->
                        onNavigateToHome(isAdmin)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryLightBlue),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "Đăng nhập", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 7. Đăng ký tài khoản mới
            Text(
                text = "Đăng ký tài khoản mới",
                fontSize = 14.sp,
                color = primaryLightBlue,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { onNavigateToRegister() }
            )

            // Đã lược bỏ hoàn toàn phần "Hoặc đăng nhập với" và các nút mạng xã hội ở dưới.
        }
    }
}