package com.example.ejemplologin.LoginScreen.Body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.core.util.PatternsCompat
import com.example.mvvm.LoginScreen.LoginViewModel
import com.example.mvvm.R


@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {
    //////////
    val email: String by loginViewModel.email.observeAsState("")

    val password: String by loginViewModel.password.observeAsState("")


    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(false)
    /////////

    /*  var password by rememberSaveable {
          mutableStateOf("")
      }
      var isLoginEnable by rememberSaveable {
          mutableStateOf(false)
      }*/
    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(email) {
            ///////////////
            loginViewModel.onLoginChanged(email = it, password = password)
            ///////////////////


            /// anterior a MVVM
            // email = it

        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            loginViewModel.onLoginChanged(email = email, password = it)

            //password = it

        }
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnable,loginViewModel/* retrofit add*/)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
        Spacer(modifier = Modifier.size(16.dp))
        ForgotPassword(Modifier.align(Alignment.CenterHorizontally))


    }

    // loginViewModel.onIsEnabledChanged(password.length > 0 && isValidEmail(email))


    //  isLoginEnable = password.length > 0 && isValidEmail(email)
}

@Composable
fun SocialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Social",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Log in with Facebook",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0XFF4EA8E9)
        )
    }
}

@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, /*Añadido retrofit*/ logiViewModel:LoginViewModel) {
    Button(
        onClick = { /*añadido retrofit*/ logiViewModel.onLoginSelected() }, enabled = loginEnable,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContentColor = Color.White,
            contentColor = Color.White,
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78F8F9)

        )
    ) {
        Text(text = "Log In")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot Password?",
        fontSize = 8.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFB5B5B5),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(
    password: String,
    onTextChanged: (String) -> Unit
) {
    var showPassword by rememberSaveable {
        mutableStateOf(false)
    }


    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        trailingIcon = {
            val imagen = if (showPassword) {
                com.google.android.material.R.drawable.design_ic_visibility_off
            } else {
                com.google.android.material.R.drawable.design_ic_visibility
            }


            Icon(
                painter = painterResource(id = imagen),
                contentDescription = "View",
                modifier = Modifier.clickable { showPassword = !showPassword }
            )
        },

        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions =
        KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,  // quita linea de abajo
            unfocusedIndicatorColor = Color.Transparent


        ),
        visualTransformation = if (showPassword) {
            VisualTransformation.None

        } else {
            PasswordVisualTransformation()
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,  // quita linea de abajo
            unfocusedIndicatorColor = Color.Transparent


        )
    )
}


@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "logo",
        modifier = modifier
    )
}

/*
fun isValidEmail(email: String): Boolean {
    return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
}
*/
