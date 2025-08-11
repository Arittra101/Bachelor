import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bachelors.R
import com.example.bachelors.features.authentication.login.LogInEvent
import com.example.bachelors.features.authentication.login.LoginUiState
import com.example.bachelors.features.authentication.login.LoginViewModel
import com.example.bachelors.features.common.screen.BaseScreen
import com.example.bachelors.ui.MainActivity
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogInScreenRoute(viewModel: LoginViewModel = koinViewModel()) {

    val state by viewModel.uiState.collectAsState()
    val event = viewModel::handleEvent
    val context = LocalContext.current

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    if (state.isLoggedIn || (state.authorized == true)) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)

    } else {
        LoginScreen(context, viewModel, state, event)
    }
}

@Composable
fun LoginScreen(
    context: Context,
    viewModel: LoginViewModel,
    state: LoginUiState,
    onEvent: (event: LogInEvent) -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }


    BaseScreen(backgroundColor = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bachelor),
                contentDescription = "Login Screen",
                modifier = Modifier.size(120.dp),
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Sign In",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Enter your credentials to continue",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Email",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.username,
                onValueChange = { viewModel.onUsernameChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textStyle = MaterialTheme.typography.bodyLarge,
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text("Enter your email address")
                },
                leadingIcon = {
                    Image(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                },
                isError = state.emailError != null
            )
            if (state.emailError != null) {
                Text(
                    text = state.emailError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Password",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textStyle = MaterialTheme.typography.bodyLarge,
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text("Enter your password")
                },
                leadingIcon = {
                    Image(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                },
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility
                    else Icons.Default.VisibilityOff
                    Image(
                        imageVector = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { passwordVisible = !passwordVisible })
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                isError = state.passwordError != null
            )
            if (state.passwordError != null) {
                Text(
                    text = state.passwordError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { /*TODO*/ }, contentPadding = PaddingValues(0.dp)) {
                    Text(
                        text = "Forgot Password?",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp)
                    .padding(vertical = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp)
                    ),
                onClick = {
                    onEvent.invoke(LogInEvent.logIn)
                },
            ) {
                Text("Sign In", fontSize = 18.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                TextButton(onClick = { /*TODO*/ }, contentPadding = PaddingValues(4.dp)) {
                    Text(
                        text = "Sign Up",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }

}
