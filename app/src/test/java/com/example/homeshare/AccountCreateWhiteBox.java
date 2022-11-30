package com.example.homeshare;

        import static org.junit.Assert.*;

        import android.content.Context;
        import android.widget.TextView;

        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        import static org.mockito.Mockito.*;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountCreateWhiteBox {

    private final String email = "";
    private static final String FAKE_STRING = "Error: Please fill out all fields!";

    @Mock
    Context mMockContext;


    @Test
    public void createAccountEmptyEmailAndPassword() {
        //check if error message says "Error: Please fill out all fields!"

        //TextView err = (TextView) findViewById(R.id.signUpErrMsg);
    }
}
