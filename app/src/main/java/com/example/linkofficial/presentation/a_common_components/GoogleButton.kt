import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.link2.R

@Composable
fun SocialButton(
    onClick: () -> Unit,
    text: String,
    colors: ButtonColors,
    modifier: Modifier,
    shape: Shape,
    elevation: ButtonElevation?,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    icon: Painter

) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = colors,
        shape = shape,
        elevation = elevation,
    ) {
        Image(painter = icon, contentDescription = text)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = fontSize,
            fontFamily = FontFamily.Default,
            fontWeight = fontWeight
        )
    }
}

@Preview
@Composable
fun GoogleButtonPreview() {
    SocialButton(
        onClick = {},
        text = "Sign in with Google",
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black,

            ),
        modifier = Modifier
            .padding(35.dp)
            .height(49.dp)
            .fillMaxWidth(),
        shape = ButtonDefaults.elevatedShape,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        icon = painterResource(id = R.drawable.google_icon)
    )
}

@Preview
@Composable
fun FacebookButtonPreview() {
    SocialButton(
        onClick = {},
        text = "Sign in with Google",
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black,

            ),
        modifier = Modifier
            .padding(35.dp)
            .height(49.dp)
            .fillMaxWidth(),
        shape = ButtonDefaults.elevatedShape,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        icon = painterResource(id = R.drawable.facebook_icon)
    )
}
