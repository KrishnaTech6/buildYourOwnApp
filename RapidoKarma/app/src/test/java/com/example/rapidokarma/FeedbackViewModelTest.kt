package com.example.rapidokarma

import com.example.rapidokarma.domain.usecase.InsertFeedbackUseCase
import com.example.rapidokarma.presentation.event.FeedbackEvent
import com.example.rapidokarma.presentation.viewmodel.FeedbackViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FeedbackViewModelTest {

    private lateinit var viewModel: FeedbackViewModel
    private lateinit var insertFeedbackUseCase: InsertFeedbackUseCase
    private val testDispatcher = StandardTestDispatcher(TestCoroutineScheduler())

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        insertFeedbackUseCase = mockk(relaxed = true)
        viewModel = FeedbackViewModel(insertFeedbackUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `submitFeedback updates state to success on successful insertion`() {
        runTest(testDispatcher) {
            // Arrange: Set initial state
            val initialEmoji = "🙂"
            val initialTag = "Polite"
            val initialComment = "Nice driver"
            viewModel.onEvent(FeedbackEvent.EmojiChanged(initialEmoji))
            viewModel.onEvent(FeedbackEvent.TagChanged(initialTag))
            viewModel.onEvent(FeedbackEvent.CommentChanged(initialComment))

            // Mock the use case to throw an exception
            // coEvery { insertFeedbackUseCase(any()) } throws RuntimeException("Database error")

            // Act: Submit feedback
            viewModel.onEvent(FeedbackEvent.SubmitFeedback)
            testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes

            // Assert: Check final state
            val state = viewModel.state.value
            assertFalse("Should not be submitting after completion", state.isSubmitting)
            assertTrue("Should be success after successful submission", state.isSuccess)
            assertEquals("Emoji should be cleared", "", state.emoji)
            assertEquals("Tag should be cleared", "", state.tag)
            assertEquals("Comment should be cleared", "", state.comment)
            assertNull("Error message should be null on success", state.errorMessage)
        }
    }

    @Test
    fun `submitFeedback updates state to error when insertion fails`() {
        runTest(testDispatcher) {
            // Arrange: Set initial state and mock use case failure
            val initialEmoji = "😞"
            val initialTag = "Rude"
            val initialComment = "Bad experience"
            val errorMessage = "Database error"
            // Mock the use case to throw an exception
            coEvery { insertFeedbackUseCase(any()) } throws RuntimeException(errorMessage)

            viewModel.onEvent(FeedbackEvent.EmojiChanged(initialEmoji))
            viewModel.onEvent(FeedbackEvent.TagChanged(initialTag))
            viewModel.onEvent(FeedbackEvent.CommentChanged(initialComment))

            // Act:  Submit feedback
            viewModel.onEvent(FeedbackEvent.SubmitFeedback)
            testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes

            // Assert: Check final state
            val state = viewModel.state.value
            assertFalse("Should not be submitting after error", state.isSubmitting)
            assertFalse("Should not be success on error", state.isSuccess)
            assertEquals("Emoji should remain unchanged on error", initialEmoji, state.emoji)
            assertEquals("Tag should remain unchanged on error", initialTag, state.tag)
            assertEquals("Comment should remain unchanged on error", initialComment, state.comment)
            assertEquals("Error message should be set on failure", errorMessage, state.errorMessage)
        }
    }
}