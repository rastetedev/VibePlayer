package com.raulastete.vibeplayer.features.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raulastete.vibeplayer.R
import com.raulastete.vibeplayer.ui.components.MusicTrackItemUi
import com.raulastete.vibeplayer.ui.components.TrackItem
import com.raulastete.vibeplayer.ui.theme.ButtonHover
import com.raulastete.vibeplayer.ui.theme.ButtonPrimary
import com.raulastete.vibeplayer.ui.theme.Outline
import com.raulastete.vibeplayer.ui.theme.SurfaceBG
import com.raulastete.vibeplayer.ui.theme.TextDisabled
import com.raulastete.vibeplayer.ui.theme.TextSecondary
import com.raulastete.vibeplayer.ui.theme.bodyLargeMedium
import com.raulastete.vibeplayer.ui.theme.bodyLargeRegular
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    navigateToPlayer: (musicTrackId: String) -> Unit
) {

    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchResult by viewModel.searchResult.collectAsStateWithLifecycle()
    val isSearchProcessActive by viewModel.isSearchActive.collectAsStateWithLifecycle()
    val isLoadingResults by viewModel.isLoading.collectAsStateWithLifecycle()

    SearchScreenContent(
        searchQuery = searchQuery,
        isSearchActive = isSearchProcessActive,
        isLoading = isLoadingResults,
        onTextChanged = viewModel::onSearchQueryChange,
        onClearQuery = viewModel::clearQuery,
        musicTrackItemUis = searchResult,
        onClickTrackItem = { navigateToPlayer(it.id) },
        onCancelActionClick = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreenContent(
    searchQuery: String,
    isSearchActive: Boolean,
    isLoading: Boolean,
    musicTrackItemUis: List<MusicTrackItemUi>,
    onTextChanged: (String) -> Unit,
    onClickTrackItem: (MusicTrackItemUi) -> Unit,
    onClearQuery: () -> Unit,
    onCancelActionClick: () -> Unit
) {

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp, top = 10.dp, bottom = 10.dp)
            ) {
                SearchInput(
                    modifier = Modifier.weight(1f),
                    text = searchQuery,
                    onTextChanged = onTextChanged,
                    onClearQuery = onClearQuery
                )
                Spacer(Modifier.width(8.dp))
                CancelButton(onCancelActionClick = onCancelActionClick)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentPadding = PaddingValues(16.dp)
        ) {

            itemsIndexed(
                musicTrackItemUis,
                key = { _, trackItem -> trackItem.title }
            ) { index, trackItemUi ->
                TrackItem(
                    modifier = Modifier.padding(vertical = 12.dp),
                    musicTrackItemUi = trackItemUi,
                    onClick = { onClickTrackItem(trackItemUi) }
                )

                if (index != musicTrackItemUis.lastIndex) {
                    HorizontalDivider(thickness = 2.dp, color = Outline)
                }
            }

            if (musicTrackItemUis.isEmpty() && isSearchActive) {
                item("item_no_results") {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.no_results_search),
                        style = MaterialTheme.typography.bodyLargeRegular.copy(
                            color = TextSecondary
                        )
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = isLoading && isSearchActive,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(SurfaceBG),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun CancelButton(
    onCancelActionClick: () -> Unit
) {
    TextButton(onClick = onCancelActionClick) {
        Text(
            stringResource(R.string.cancel),
            style = MaterialTheme.typography.bodyLargeMedium.copy(color = ButtonPrimary)
        )
    }
}

@Composable
private fun SearchInput(
    text: String,
    onTextChanged: (String) -> Unit,
    onClearQuery: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
    }

    OutlinedTextField(
        modifier = modifier
            .focusRequester(focusRequester),
        value = text,
        onValueChange = onTextChanged,
        placeholder = {
            Text(
                stringResource(R.string.search_action),
                style = MaterialTheme.typography.bodyLargeRegular.copy(color = TextSecondary)
            )
        },
        leadingIcon = {
            Icon(
                ImageVector.vectorResource(R.drawable.search_icon),
                contentDescription = null
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable {
                        onClearQuery()
                    },
                    imageVector = Icons.Default.Clear,
                    contentDescription = null
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedLeadingIconColor = TextSecondary,
            unfocusedLeadingIconColor = TextSecondary,
            unfocusedTrailingIconColor = TextDisabled,
            focusedTrailingIconColor = TextDisabled,
            focusedContainerColor = ButtonHover,
            unfocusedContainerColor = ButtonHover,
            unfocusedBorderColor = Outline,
            focusedBorderColor = Outline,
            cursorColor = ButtonPrimary
        ),
        singleLine = true,
        shape = RoundedCornerShape(100)
    )
}