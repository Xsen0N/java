package builder;

import exception.RepositoryException;
import models.Person;
import org.junit.Before;
import org.junit.Test;
import repository.dbconstants.PersonTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonBuilderTest {

    private static final String NAME = "User";
    private static final String PHONE = "365482828344";
    private static final String EMAIL = "kfj@flkk.f";

    private Person EXPECTED_PERSON = null;

    @Before
    public void initExpectedPerson() {
        EXPECTED_PERSON = new Person(
                NAME,
                PHONE,
                EMAIL
        );

    }

    @Test
    public void shouldBuildAndReturnUserWithParameters() throws SQLException, RepositoryException {
        //Arrange
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getString(PersonTableConstants.NAME.getFieldName())).thenReturn(NAME);
        when(resultSet.getString(PersonTableConstants.PHONE.getFieldName())).thenReturn(PHONE);
        when(resultSet.getString(PersonTableConstants.EMAIL.getFieldName())).thenReturn(EMAIL);

        //Act
        PersonBuilder personBuilder = new PersonBuilder();
        Person actualPerson = personBuilder.build(resultSet);

        //Assert
        assertEquals(EXPECTED_PERSON, actualPerson);
    }
}