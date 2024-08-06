package com.example.woofapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dataSource.DataSource
import com.example.woof.DogDataClass
import com.example.woofapp.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AppLayout()
                }
            }
        }
    }
}
@Composable
fun AppLayout() {
        WoofList(dogs = DataSource().listOfDogs())
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopOfApp(){
    CenterAlignedTopAppBar(title ={
        Row {
            Image(modifier= Modifier
                .padding(dimensionResource(R.dimen.padding_small))
                .size(dimensionResource(R.dimen.image_size)),
                painter = painterResource(R.drawable.ic_woof_logo),
                contentDescription = "Deez Nuts",
                )
            Text(text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge)
        }
    }
    )
    }

@Composable
fun WoofList(dogs:List<DogDataClass>) {
    Scaffold(
        topBar = {
            TopOfApp()
        }) {
        LazyColumn(contentPadding = it) {
            items(dogs) { dogs ->
                DogItem(
                    dog = dogs,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )

            }
        }
    }
}
@Composable
fun DogItem(
    dog: DogDataClass,
    modifier:Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val colorOfCard by animateColorAsState(
            targetValue = if (expanded == true) {
                MaterialTheme.colorScheme.secondary
            }
            else {
                MaterialTheme.colorScheme.errorContainer
            }
        )//to change the color once it is expanded

    Card(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))) {
        Column(modifier=Modifier
            .animateContentSize(
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)//damping ratio is how much it bounces(pause)
        ).background(color= colorOfCard)){//To add animations
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {

                DogPic(dogIcon = dog.imageResourceId)
                DogInformation(dogName = dog.name, dogAge = dog.age)
                Spacer(modifier = Modifier.weight(1f))
                UpAndDownButton(
                    expand = expanded,
                    onClick = {
                        expanded = !expanded
                    })//means not expanded(true because expanded initial is false)

            }
            if (expanded == true) {
                DogHobby(
                    hobby = dog.hobbies,
                    modifier = Modifier.padding(
                        (dimensionResource(R.dimen.padding_medium))
                    )
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}
@Composable
fun DogHobby(
    @StringRes hobby:Int,
    modifier: Modifier
){
    Column {
        Text(text = stringResource(R.string.about),
            modifier=Modifier.padding(start=dimensionResource(R.dimen.padding_medium)))
        Text(text = stringResource(hobby),
            style=MaterialTheme.typography.labelSmall,
            modifier=Modifier.padding(start=dimensionResource(R.dimen.padding_medium)))
    }

}


@Composable
fun DogPic(
    @DrawableRes dogIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(dogIcon),
        contentDescription = null
    )
}
@Composable
fun DogInformation(
    @StringRes dogName: Int,
    dogAge: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
       Row(){
           Text(
               text = stringResource(dogName),
               modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
               style = MaterialTheme.typography.displayMedium)
           Image(painter = painterResource(R.drawable.angry),
               contentDescription ="angry emoji",
               modifier = Modifier
                   .padding(
                       top = dimensionResource(R.dimen.padding_small),
                       start = dimensionResource(R.dimen.padding_small)
                   )
                   .size(25.dp)
                   )

       }
        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
@Composable
fun UpAndDownButton(
    expand:Boolean,
    onClick:()->Unit
){
    IconButton(onClick = onClick) {
       Icon(imageVector =if(expand==true) {
           Icons.Filled.ExpandMore
       }else{
            Icons.Filled.ExpandLess
            },
           contentDescription = stringResource(R.string.expand_button_content_description),
           tint = MaterialTheme.colorScheme.secondary)
    }

}
@Preview(showBackground = true)
@Composable
fun AppPreview() {
       WoofTheme(darkTheme = false) {
           AppLayout()
       }

    }
