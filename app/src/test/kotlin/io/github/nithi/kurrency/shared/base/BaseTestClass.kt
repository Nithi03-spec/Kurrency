/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
import androidx.annotation.CallSuper
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

/**
 * A base class for all test classes. If child class wants to override any of stated
 * methods they must call super first.
 */
open class BaseTestClass {

    /**
     * An open method that initializes mock-ups before starting a test.
     */
    @CallSuper
    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
    }

    /**
     * An open method that unmocks & clears all mock-ups in order to avoid any leak.
     */
    @CallSuper
    @After
    open fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}
