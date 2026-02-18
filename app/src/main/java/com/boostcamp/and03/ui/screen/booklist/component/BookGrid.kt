package com.boostcamp.and03.ui.screen.booklist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.boostcamp.and03.ui.screen.booklist.model.BookUiModel
import com.boostcamp.and03.ui.theme.And03Padding
import com.boostcamp.and03.ui.theme.And03Spacing
import com.boostcamp.and03.ui.theme.And03Theme
import kotlinx.collections.immutable.persistentListOf

private object BookGridValues {
    const val GRID_HORIZONTAL_COUNT = 3
}

@Composable
fun BookGrid(
    books: List<BookUiModel>,
    onBookClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(BookGridValues.GRID_HORIZONTAL_COUNT),
        verticalArrangement = Arrangement.spacedBy(And03Spacing.SPACE_L),
        horizontalArrangement = Arrangement.spacedBy(And03Spacing.SPACE_M),
        contentPadding = PaddingValues(bottom = And03Padding.PADDING_L)
    ) {
        items(books) { book ->
            BookItem(
                title = book.title,
                authors = book.authors,
                thumbnail = book.thumbnail,
                onClick = { onBookClick(book.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BookGridPreview() {
    val previewBooks = listOf(
        BookUiModel(
            id = "1",
            title = "Clean Code",
            authors = persistentListOf("Robert C. Martin"),
            publisher = "Prentice Hall",
            thumbnail = "https://books.google.com/books/content?id=hjEFCAAAQBAJ&printsec=frontcover&img=1&zoom=1",
            totalPage = 464,
            isbn = "9780132350884"
        ),
        BookUiModel(
            id = "2",
            title = "Effective Kotlin",
            authors = persistentListOf("Marcin Moskala"),
            publisher = "Leanpub",
            thumbnail = "https://books.google.com/books/content?id=K4s6EAAAQBAJ&printsec=frontcover&img=1&zoom=1",
            totalPage = 360,
            isbn = "9788395452833"
        ),
        BookUiModel(
            id = "3",
            title = "Kotlin in Action",
            authors = persistentListOf("Dmitry Jemerov", "Svetlana Isakova"),
            publisher = "Manning",
            thumbnail = "https://books.google.com/books/content?id=v1XnCwAAQBAJ&printsec=frontcover&img=1&zoom=1",
            totalPage = 360,
            isbn = "9781617293290"
        ),
        BookUiModel(
            id = "4",
            title = "Refactoring",
            authors = persistentListOf("Martin Fowler"),
            publisher = "Addison-Wesley",
            thumbnail = "https://books.google.com/books/content?id=1MsETFPD3I0C&printsec=frontcover&img=1&zoom=1",
            totalPage = 448,
            isbn = "9780201485677"
        ),
        BookUiModel(
            id = "5",
            title = "Domain-Driven Design",
            authors = persistentListOf("Eric Evans"),
            publisher = "Addison-Wesley",
            thumbnail = "https://books.google.com/books/content?id=7H4xDwAAQBAJ&printsec=frontcover&img=1&zoom=1",
            totalPage = 560,
            isbn = "9780321125217"
        ),
        BookUiModel(
            id = "6",
            title = "The Pragmatic Programmer",
            authors = persistentListOf("Andrew Hunt", "David Thomas"),
            publisher = "Addison-Wesley",
            thumbnail = "https://books.google.com/books/content?id=5wBQEp6ruIAC&printsec=frontcover&img=1&zoom=1",
            totalPage = 352,
            isbn = "9780201616224"
        )
    )

    And03Theme {
        BookGrid(
            books = previewBooks,
            onBookClick = {}
        )
    }
}
