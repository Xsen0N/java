package builder;

import exception.RepositoryException;
import models.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import repository.dbconstants.UserTableConstants;
import util.HashPassword;


import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserBuilderTest {

    private static final String LOGIN = "admin";
    private static final byte [] PASSWORD = HashPassword.getHash("admin");

    private User EXPECTED_USER = null;

    @Before
    public void initExpectedUser(){
        EXPECTED_USER = new User(LOGIN, PASSWORD);
    }

    @Test
    public void shouldBuildAndReturnUserWithParameters() throws SQLException, RepositoryException {
        // Arrange
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getString(UserTableConstants.LOGIN.getFieldName())).thenReturn(LOGIN);
        when(resultSet.getBytes(UserTableConstants.PASSWORD.getFieldName())).thenReturn(PASSWORD);

        // Act
        UserBuilder userBuilder = new UserBuilder();
        User actualUser = userBuilder.build(resultSet);

        //Assert
        assertEquals(EXPECTED_USER, actualUser);
    }
}
