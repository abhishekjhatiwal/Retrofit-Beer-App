package com.example.pagginationapp.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.pagginationapp.data.remote.Beer
import androidx.compose.material3.Text

@Composable
fun BeerScreen(
    beers: LazyPagingItems<Beer>
) {
    val context = LocalContext.current

    // ✅ Show Toast for load errors only once per refresh
    LaunchedEffect(beers.loadState) {
        val errorState = beers.loadState.refresh as? LoadState.Error
        errorState?.let {
            Toast.makeText(context, "Error: ${it.error.message}", Toast.LENGTH_LONG).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            // ✅ Initial loading (first page)
            beers.loadState.refresh is LoadState.Loading -> {
                CircularProgressIndicator()
            }

            // ✅ No items found
            beers.itemCount == 0 && beers.loadState.refresh !is LoadState.Loading -> {
                Text(text = "No beers found.")
            }

            // ✅ Items available
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(beers.itemCount) { index ->
                        beers[index]?.let { beer ->
                            BeerItem(
                                beer = beer,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    // ✅ Show progress indicator for next page
                    item {
                        if (beers.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }

                    // ✅ Show error when appending fails
                    item {
                        val appendError = beers.loadState.append as? LoadState.Error
                        appendError?.let {
                            Text(text = "Error loading more: ${it.error.message}")
                        }
                    }
                }
            }
        }
    }
}



























/*
@Composable
fun BeerScreen(
    beers: LazyPagingItems<Beer>
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = beers.loadState) {
        if(beers.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (beers.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(beers.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(beers) { beer ->
                    if(beer != null) {
                        BeerItem(
                            beer = beer,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                item {
                    if(beers.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

 */