import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bachelors.authsdk.AuthSDK
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

    if (state.isLoggedIn || (state.authorized == true)) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)

    } else {
        LoginScreen(context, viewModel, state, event)
    }

    val authSdk = AuthSDK.getInstance()
//    sdk init
    authSdk.initSDK(context,null)
    viewModel.signIn(authSdk)




}

@Composable
fun LoginScreen(
    context: Context,
    viewModel: LoginViewModel,
    state: LoginUiState,
    onEvent: (event: LogInEvent) -> Unit
) {

    BaseScreen {
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
                text = "Sign in to your account",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Email Address",
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
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text("Enter your email address")
                }
            )
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
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text("Enter your password")
                },
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onClick = {
                    onEvent.invoke(LogInEvent.logIn)
//                    viewModel.login()
                },
            ) {
                Text("Login")
            }

            state.errorMessage?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it, color = Color.Red)
            }
        }
    }

}
